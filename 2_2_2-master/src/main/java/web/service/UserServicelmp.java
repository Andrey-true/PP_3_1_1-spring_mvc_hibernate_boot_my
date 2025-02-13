package web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service
public class UserServicelmp implements UserService {

    private final UserDao userDao;

    public UserServicelmp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override

    @Transactional(readOnly = true)
    public List<User> getListUserFilled() {
        return userDao.getListUserFilled();
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public void updateUser(Long id, User user) {
        userDao.updateUser(id, user);
    }

    @Override
    @Transactional
    public void removeUser(Long id) {
        userDao.removeUser(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers(int parseInt) {
        return userDao.getUsers(parseInt);
    }


}

