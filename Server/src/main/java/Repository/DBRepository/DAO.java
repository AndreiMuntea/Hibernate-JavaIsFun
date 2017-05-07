package Repository.DBRepository;

import Domain.HasId;
import Repository.Exceptions.RepositoryException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

/**
 * Created by andrei on 2017-05-07.
 */
public class DAO {

    public <Id extends Serializable, T> Boolean exists(final Class<T> type, final Id id) throws RepositoryException {
        Transaction tx = null;
        Session session = DatabaseConnection.newSession();

        tx = session.beginTransaction();
        T item = session.get(type, id);
        tx.commit();

        session.close();

        return (item != null);
    }

    public <Id extends Serializable, T extends HasId<Id>> void add(final Class<T> type, final T item) throws RepositoryException {
        if (exists(type, item.getId())) {
            throw new RepositoryException("Item already exists!\n");
        }

        Transaction tx = null;
        Session session = DatabaseConnection.newSession();

        tx = session.beginTransaction();
        session.save(item);
        tx.commit();

        session.close();
    }

    public <Id extends Serializable, T extends HasId<Id>> void delete(final Class<T> type, final Id id) throws RepositoryException {
        T item = get(type,id);

        Transaction tx = null;
        Session session = DatabaseConnection.newSession();

        tx = session.beginTransaction();
        session.delete(item);
        tx.commit();

        session.close();
    }


    public <Id extends Serializable, T extends HasId<Id>> T get(final Class<T> type, final Id id) throws RepositoryException {
        if (!exists(type, id)) {
            throw new RepositoryException("Item doesn't exists!\n");
        }
        Transaction tx = null;
        Session session = DatabaseConnection.newSession();

        tx = session.beginTransaction();
        T item = session.get(type, id);
        tx.commit();

        session.close();
        return item;
    }

    public <Id extends Serializable, T extends HasId<Id>> void update(final Class<T> type, final T updatedItem) throws RepositoryException {
        if (!exists(type, updatedItem.getId())) {
            throw new RepositoryException("Item doesn't exists!\n");
        }

        Transaction tx = null;
        Session session = DatabaseConnection.newSession();
        tx = session.beginTransaction();
        session.update(updatedItem);
        tx.commit();
        session.close();
    }

    public <T> List<T> getAll(final Class<T> type) throws RepositoryException
    {
        Transaction tx = null;
        Session session = DatabaseConnection.newSession();
        tx = session.beginTransaction();
        List<T> all = session.createCriteria(type).list();
        tx.commit();
        session.close();
        return all;
    }
}
