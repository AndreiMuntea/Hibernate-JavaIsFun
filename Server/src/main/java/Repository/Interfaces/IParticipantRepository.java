package Repository.Interfaces;

import Domain.Participant;
import Domain.Trial;
import Repository.Exceptions.RepositoryException;

import java.util.List;

/**
 * Created by andrei on 2017-05-07.
 */
public interface IParticipantRepository extends ICrudRepository<String, Participant>{
    void registerParticipant(Participant participant, Trial trial) throws RepositoryException;

    List<String> getTrialsForParticipant(String participantName) throws RepositoryException;
}
