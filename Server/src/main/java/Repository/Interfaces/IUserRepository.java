package Repository.Interfaces;

import Domain.User;
import Repository.Exceptions.RepositoryException;

/**
 * Created by andrei on 2017-05-07.
 */
public interface IUserRepository extends ICrudRepository<String, User>{
    Boolean logIn(User user) throws RepositoryException;
}
