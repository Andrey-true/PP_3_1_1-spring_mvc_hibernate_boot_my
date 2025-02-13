package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {

    List<User> getListUserFilled();

    void addUser(User user);

    void updateUser(Long id, User user);

    void removeUser(Long id);

    User getUserById(Long id);

    List<User> getUsers(int count);

}
