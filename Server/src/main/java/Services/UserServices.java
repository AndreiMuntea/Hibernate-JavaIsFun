package Services;

import Domain.User;
import Repository.Exceptions.RepositoryException;
import Repository.Interfaces.IUserRepository;

/**
 * Created by andrei on 2017-04-13.
 */
public class UserServices {
    IUserRepository userRepository;

    public UserServices(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Boolean LogIn(User user) throws ServiceException
    {
        try {
            return userRepository.logIn(user);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
