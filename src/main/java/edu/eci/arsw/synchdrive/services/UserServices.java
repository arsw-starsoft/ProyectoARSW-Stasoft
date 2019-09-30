package edu.eci.arsw.synchdrive.services;

import edu.eci.arsw.synchdrive.model.User;
import edu.eci.arsw.synchdrive.persistence.SynchdrivePersistenceException;

import java.util.List;

public interface UserServices {

    List<User> getAllUsers() throws SynchdrivePersistenceException;

    void saveUser(User user) throws SynchdrivePersistenceException;

    User findUserByEmail(String email) throws SynchdrivePersistenceException;
}
