package Repository.Interfaces;

import Domain.AgeCategory;
import Domain.Participant;
import Domain.Trial;
import Repository.Exceptions.RepositoryException;

import java.util.List;

/**
 * Created by andrei on 2017-05-07.
 */
public interface ITrialRepository extends ICrudRepository<String, Trial>{
    List<Participant> getParticipantsForTrial(String trialName, AgeCategory ageCategory) throws RepositoryException;
}
