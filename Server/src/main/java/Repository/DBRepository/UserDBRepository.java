package Repository.DBRepository;

import Domain.User;
import Repository.Exceptions.RepositoryException;
import Repository.Interfaces.IUserRepository;

import java.util.List;

/**
 * Created by andrei on 2017-05-07.
 */
public class UserDBRepository implements IUserRepository {

    private DAO dao;

    public UserDBRepository() {
        dao = new DAO();
    }

    @Override
    public Boolean logIn(User user) throws RepositoryException {
        User checkUser = getItem(user.getId());
        if (!checkUser.getPassword().equals(user.getPassword())) {
            throw new RepositoryException("Invalid password!\n");
        }
        return true;
    }

    @Override
    public void addItem(User item) throws RepositoryException {
        dao.add(User.class, item);
    }

    @Override
    public void deleteItem(String s) throws RepositoryException {
        dao.delete(User.class, s);
    }

    @Override
    public Boolean existsItem(String s) throws RepositoryException {
        return dao.exists(User.class, s);
    }

    @Override
    public User getItem(String s) throws RepositoryException {
        return dao.get(User.class, s);
    }

    @Override
    public void updateItem(User updatedItem) throws RepositoryException {
        dao.update(User.class, updatedItem);
    }

    @Override
    public List<User> getAll() throws RepositoryException {
        return dao.getAll(User.class);
    }
}
