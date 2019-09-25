package edu.eci.arsw.synchdrive.services.impl;

import edu.eci.arsw.synchdrive.model.User;
import edu.eci.arsw.synchdrive.persistence.SynchdrivePersistenceException;
import edu.eci.arsw.synchdrive.persistence.UserRepository;
import edu.eci.arsw.synchdrive.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() throws SynchdrivePersistenceException {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) throws SynchdrivePersistenceException {
        userRepository.save(user);
    }

    @Override
    public User findUserByName(String user) throws SynchdrivePersistenceException {
        Optional<User> optinalUser = userRepository.findByName(user);
        if (!optinalUser.isPresent())
            throw new SynchdrivePersistenceException(SynchdrivePersistenceException.CAR_NOT_FOUND);
        return optinalUser.get();
    }
}