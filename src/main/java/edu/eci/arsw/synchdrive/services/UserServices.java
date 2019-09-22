package edu.eci.arsw.synchdrive.services;

import edu.eci.arsw.synchdrive.model.User;

import java.util.List;

public interface UserServices {

    List<User> getAllUsers();

    void saveUser(User user);
}
