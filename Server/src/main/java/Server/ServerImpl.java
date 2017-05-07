package Server;

import Domain.AgeCategory;
import Domain.Participant;
import Domain.Trial;
import Domain.User;
import Generated.Protobuf.*;
import Services.*;
import io.grpc.stub.StreamObserver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by andrei on 2017-05-07.
 */
public class ServerImpl extends AppServiceGrpc.AppServiceImplBase {
    private Map<String, StreamObserver<Response>> loggedClients;
    private AgeCategoryServices ageCategoryServices;
    private ParticipantServices participantServices;
    private TrialServices trialServices;
    private UserServices userServices;


    public ServerImpl(AgeCategoryServices ageCategoryServices, ParticipantServices participantServices, TrialServices trialServices, UserServices userServices) {
        this.ageCategoryServices = ageCategoryServices;
        this.participantServices = participantServices;
        this.trialServices = trialServices;
        this.userServices = userServices;

        this.loggedClients = new ConcurrentHashMap<>();
    }

    @Override
    public StreamObserver<Request> sendRequest(StreamObserver<Response> responseObserver) {
        return new StreamObserver<Request>() {
            @Override
            public void onNext(Request value) {
                HandleRequest(value, responseObserver);
            }

            @Override
            public void onError(Throwable t) {
                loggedClients.remove(responseObserver);
            }

            @Override
            public void onCompleted() {
                loggedClients.remove(responseObserver);
            }
        };
    }

    public void HandleRequest(Request request, StreamObserver<Response> responseStream) {
        if (request.getRequestType().equals(RequestType.LoginRequest)) {
            login(ProtoUtils.userFromDto(request.getUser()), responseStream);
        } else if (request.getRequestType().equals(RequestType.GetTrialsRequest)) {
            getTrials(responseStream);
        } else if (request.getRequestType().equals(RequestType.LogoutRequest)){
            logout(request.getUsername(), responseStream);
        } else if(request.getRequestType().equals(RequestType.GetAgeCategoriesRequest)){
            getAgeCategories(responseStream);
        } else if (request.getRequestType().equals(RequestType.GetParticipantsForTrialRequest)){
            getParticipantsForTrial(request.getTrialName(), request.getAgeCategoryName(), responseStream);
        } else if (request.getRequestType().equals(RequestType.RegisterParticipantRequest)){
            registerParticipant(request.getUsername(), request.getParticipantName(), request.getParticipantAge(), request.getTrialsList(), responseStream);
        }
    }

    public void login(User user, StreamObserver<Response> responseStream) {

        if (loggedClients.containsKey(user.getUserName())) {
            responseStream.onNext(
                    Response
                            .newBuilder()
                            .setResponseType(ResponseType.FailureResponse)
                            .setErrorMessage("User already logged in!\n")
                            .build()
            );
            return;
        }

        try {
            userServices.LogIn(user);
            responseStream.onNext(
                    Response
                            .newBuilder()
                            .setResponseType(ResponseType.OkResponse)
                            .build()
            );
            loggedClients.put(user.getUserName(), responseStream);
        } catch (ServiceException e) {
            responseStream.onNext(
                    Response
                            .newBuilder()
                            .setResponseType(ResponseType.FailureResponse)
                            .setErrorMessage(e.getMessage())
                            .build()
            );
        }
    }

    public void getTrials(StreamObserver<Response> responseStream) {
        List<Trial> allTrials;
        try {
            allTrials = trialServices.getAll();
        } catch (ServiceException e) {
            e.printStackTrace();
            return;
        }
        responseStream.onNext(
                Response
                        .newBuilder()
                        .setResponseType(ResponseType.OkResponse)
                        .addAllTrials(ProtoUtils.trialsListToDto(allTrials))
                        .build()
        );
    }

    public void logout(String username, StreamObserver<Response> responseStream){
        if (!loggedClients.containsKey(username)) {
            responseStream.onNext(
                    Response
                            .newBuilder()
                            .setResponseType(ResponseType.FailureResponse)
                            .setErrorMessage("User isn't logged in!\n")
                            .build()
            );
            return;
        }
        loggedClients.remove(username);
        responseStream.onNext(
                Response
                        .newBuilder()
                        .setResponseType(ResponseType.OkResponse)
                        .build()
        );
    }

    public void getAgeCategories(StreamObserver<Response> responseStream) {
        List<AgeCategory> allAges;
        try{
            allAges = ageCategoryServices.getAgeCategories();
        } catch (ServiceException e) {
            e.printStackTrace();
            return;
        }
        responseStream.onNext(
                Response
                        .newBuilder()
                        .setResponseType(ResponseType.OkResponse)
                        .addAllAgeCategories(ProtoUtils.ageCategoryListToDto(allAges))
                        .build()
        );
    }

    public void getParticipantsForTrial(String trialName, String ageCategoryName, StreamObserver<Response> responseStream)   {
        List<Participant> allParticipants;

        try {
            allParticipants = trialServices.getParticipantsForTrial(trialName, ageCategoryName);
            responseStream.onNext(
                    Response
                            .newBuilder()
                            .setResponseType(ResponseType.OkResponse)
                            .addAllParticipants(ProtoUtils.participantsListToDto(allParticipants))
                            .build());
        } catch (ServiceException e) {
            responseStream.onNext(
                    Response
                            .newBuilder()
                            .setResponseType(ResponseType.FailureResponse)
                            .setErrorMessage(e.getMessage())
                            .build()
            );
        }


    }

    public void registerParticipant(String userName, String participantName, Integer participantAge, List<String> trials, StreamObserver<Response> responseStream) {
        try {
            participantServices.registerParticipant(participantName, participantAge, trials);
            AgeCategory a = ageCategoryServices.FindSuitableAgeCategory(participantAge.toString());
            responseStream.onNext(
                    Response
                            .newBuilder()
                            .setResponseType(ResponseType.OkResponse)
                            .setAgeCategoryName(a.getName())
                            .addAllRegisteredTrials(trials)
                            .build());
            notify(userName, participantAge, trials);

        } catch (ServiceException e) {
            responseStream.onNext(
                    Response
                            .newBuilder()
                            .setResponseType(ResponseType.FailureResponse)
                            .setErrorMessage(e.getMessage())
                            .build()
            );
        }
    }

    public void notify(String userName, Integer ageCategory, List<String> trials) {
        AgeCategory a;
        try{
            a = ageCategoryServices.FindSuitableAgeCategory(ageCategory.toString());
        } catch (ServiceException e) {
            e.printStackTrace();
            return;
        }
        for(String user : loggedClients.keySet())
        {
            if(!user.equals(userName))
            {
                loggedClients.get(user).onNext(
                        Response
                        .newBuilder()
                        .setResponseType(ResponseType.UpdateResponse)
                        .setAgeCategoryName(a.getName())
                        .addAllRegisteredTrials(trials)
                        .build()
                );
            }
        }
    }
}
