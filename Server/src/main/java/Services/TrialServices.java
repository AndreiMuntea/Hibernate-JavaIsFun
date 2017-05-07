package Services;

import Domain.AgeCategory;
import Domain.Participant;
import Domain.Trial;
import Repository.Exceptions.RepositoryException;
import Repository.Interfaces.IAgeCategoryRepository;
import Repository.Interfaces.ITrialRepository;

import java.util.List;

/**
 * Created by andrei on 2017-04-13.
 */
public class TrialServices {
    private ITrialRepository trialRepository;
    private IAgeCategoryRepository ageCategoryRepository;

    public TrialServices(ITrialRepository trialRepository, IAgeCategoryRepository ageCategoryRepository) {
        this.trialRepository = trialRepository;
        this.ageCategoryRepository = ageCategoryRepository;
    }

    public List<Trial> getAll() throws ServiceException {
        try {
            return trialRepository.getAll();
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Participant> getParticipantsForTrial(String trialName, String ageCategoryName) throws ServiceException {
        try {
            AgeCategory ageCategory = ageCategoryRepository.getItem(ageCategoryName);
            return trialRepository.getParticipantsForTrial(trialName, ageCategory);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
