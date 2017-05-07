package Proxy;

import Domain.AgeCategory;
import Domain.Participant;
import Domain.Trial;
import Domain.User;
import java.util.List;

/**
 * Created by andrei on 2017-05-07.
 */
public interface IAppServices {
    void login(User user, IObserverService userObserver) throws ServiceException;
    void logout(User user) throws ServiceException;
    List<Trial> getTrials() throws ServiceException;
    List<AgeCategory> getAgeCategories() throws ServiceException;
    List<Participant> getParticipantsForTrial(String trialName, String ageCategoryName) throws ServiceException;
    void registerParticipant(String participantName, Integer participantAge, List<String> trials) throws ServiceException;
}
