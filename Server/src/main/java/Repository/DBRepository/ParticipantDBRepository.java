package Repository.DBRepository;

import Domain.Participant;
import Domain.Trial;
import Repository.Exceptions.RepositoryException;
import Repository.Interfaces.IParticipantRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by andrei on 2017-05-07.
 */
public class ParticipantDBRepository implements IParticipantRepository {

    private DAO dao;

    public ParticipantDBRepository() {
        dao = new DAO();
    }

    @Override
    public void addItem(Participant item) throws RepositoryException {
        dao.add(Participant.class, item);
    }


    @Override
    public void registerParticipant(Participant participant, Trial trial) throws RepositoryException {
        Transaction tx = null;
        Session session = DatabaseConnection.newSession();
        tx = session.beginTransaction();
        participant.addTrial(trial);
        session.update(participant);
        tx.commit();
        session.close();
    }

    @Override
    public void deleteItem(String s) throws RepositoryException {
        dao.delete(Participant.class, s);
    }

    @Override
    public Boolean existsItem(String s) throws RepositoryException {
        return dao.exists(Participant.class, s);
    }

    @Override
    public List<String> getTrialsForParticipant(String participantName) throws RepositoryException {
        Set<Trial> trialList = getItem(participantName).getTrials();
        List<String> trialsNames = new ArrayList<>();
        trialList.forEach(o->trialsNames.add(o.getName()));
        return trialsNames;
    }

    @Override
    public Participant getItem(String s) throws RepositoryException {
        return dao.get(Participant.class, s);
    }

    @Override
    public void updateItem(Participant updatedItem) throws RepositoryException {
        dao.update(Participant.class, updatedItem);
    }

    @Override
    public List<Participant> getAll() throws RepositoryException {
        return dao.getAll(Participant.class);
    }
}
