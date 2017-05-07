package Repository.DBRepository;

import Domain.AgeCategory;
import Domain.Participant;
import Domain.Trial;
import Repository.Exceptions.RepositoryException;
import Repository.Interfaces.ITrialRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by andrei on 2017-05-07.
 */
public class TrialDBRepository implements ITrialRepository {
    private DAO dao;

    public TrialDBRepository() {
        this.dao = new DAO();
    }

    @Override
    public void addItem(Trial item) throws RepositoryException {
        dao.add(Trial.class, item);
    }

    @Override
    public List<Participant> getParticipantsForTrial(String trialName, AgeCategory ageCategory) throws RepositoryException {
        Set<Participant> allParticipants = dao.get(Trial.class, trialName).getParticipants();
        List<Participant> selectedParticipants = new ArrayList<>();
        allParticipants.forEach(o->{
            if(o.getAgeCategory().getName().equals(ageCategory.getName()))
            {
                selectedParticipants.add(o);
            }
        });
        return selectedParticipants;
    }

    @Override
    public void deleteItem(String s) throws RepositoryException {
        dao.delete(Trial.class, s);
    }

    @Override
    public Boolean existsItem(String s) throws RepositoryException {
        return dao.exists(Trial.class, s);
    }

    @Override
    public Trial getItem(String s) throws RepositoryException {
        return dao.get(Trial.class, s);
    }

    @Override
    public void updateItem(Trial updatedItem) throws RepositoryException {
        dao.update(Trial.class, updatedItem);
    }

    @Override
    public List<Trial> getAll() throws RepositoryException {
        return dao.getAll(Trial.class);
    }
}
