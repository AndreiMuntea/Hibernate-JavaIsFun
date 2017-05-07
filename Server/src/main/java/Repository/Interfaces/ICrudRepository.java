package Repository.Interfaces;

import Domain.HasId;
import Repository.Exceptions.RepositoryException;

import java.io.Serializable;
import java.util.List;

/**
 * Created by andrei on 2017-05-07.
 */
public interface ICrudRepository<TId extends Serializable, T extends HasId<TId>> {
    void addItem(T item) throws RepositoryException;

    void deleteItem(TId id) throws RepositoryException;

    Boolean existsItem(TId id) throws RepositoryException;

    T getItem(TId id) throws RepositoryException;

    void updateItem(T updatedItem)throws RepositoryException;

    List<T> getAll() throws RepositoryException;
}
