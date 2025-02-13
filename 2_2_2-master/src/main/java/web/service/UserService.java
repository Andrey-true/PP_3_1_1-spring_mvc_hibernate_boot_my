package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    List<User> getListUserFilled();


    void addUser(User user);


   User getUserById(Long id);


    void updateUser(Long id, User user);


    void removeUser(Long id);

    List<User> getUsers(int parseInt);
}
