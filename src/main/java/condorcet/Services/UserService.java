package condorcet.Services;

import condorcet.DataAccessObjects.UserDAO;
import condorcet.Interfaces.Service;
import condorcet.Models.Entities.User;
import condorcet.Utility.HibernateSessionFactory;
import org.hibernate.Session;

import java.util.List;

public class UserService implements Service<User> {
    private static UserDAO userDao;

    public UserService() {
        userDao = new UserDAO();
    }

    @Override
    public User findEntity(int id) {
        User user = userDao.findById(id);
        return user;
    }

    @Override
    public void saveEntity(User entity) {
        userDao.saveEntity(entity);

    }

    @Override
    public List<User> findAllEntities() {
        List<User> users = userDao.findAll();
        return users;
    }

    @Override
    public void deleteEntity(User entity) {
        userDao.deleteEntity(entity);
    }

    @Override
    public void updateEntity(User entity) {
        userDao.updateEntity(entity);
    }
}
