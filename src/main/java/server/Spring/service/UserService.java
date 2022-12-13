package server.Spring.service;

import server.Spring.model.User;

import java.util.Collection;

public interface UserService {

    User createUser (User user);
    Collection<User> listAllUsers (int limit);
    User getUser(Long id);
    User updateUser(User user);
    Boolean deleteUser(Long id);
}
