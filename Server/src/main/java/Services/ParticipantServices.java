package Services;

import Domain.AgeCategory;
import Domain.Participant;
import Domain.Trial;
import Repository.Exceptions.RepositoryException;
import Repository.Interfaces.IAgeCategoryRepository;
import Repository.Interfaces.IParticipantRepository;
import Repository.Interfaces.ITrialRepository;

import java.util.List;

/**
 * Created by andrei on 2017-04-13.
 */
public class ParticipantServices {
    IParticipantRepository participantRepository;
    IAgeCategoryRepository ageCategoryRepository;
    ITrialRepository trialRepository;

    public ParticipantServices(IParticipantRepository participantRepository, IAgeCategoryRepository ageCategoryRepository, ITrialRepository trialRepository) {
        this.ageCategoryRepository = ageCategoryRepository;
        this.participantRepository = participantRepository;
        this.trialRepository = trialRepository;
    }

    public void registerParticipant(String participantName, Integer age, List<String> trials) throws ServiceException {
        try {
            AgeCategory ageCategory = ageCategoryRepository.findSuitableAgeCategory(age);
            Participant p = null;
            if (!participantRepository.existsItem(participantName))
                participantRepository.addItem(new Participant(participantName, age, ageCategory));
            p = participantRepository.getItem(participantName);

            if (!p.getAge().equals(age)) {
                throw new ServiceException("Participant already exists in database with a different age!\n");
            }
            List<String> registeredTrials = participantRepository.getTrialsForParticipant(participantName);

            if (registeredTrials.size() + trials.size() > 2) {
                throw new ServiceException("A participant can be registered only at 2 trials!\n");
            }

            for (String t : trials) {
                Trial trial = trialRepository.getItem(t);
                participantRepository.registerParticipant(p, trial);
            }
        }catch (RepositoryException e)
        {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<String> getTrialsForParticipant(String participantName)throws ServiceException
    {
        try {
            return participantRepository.getTrialsForParticipant(participantName);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
