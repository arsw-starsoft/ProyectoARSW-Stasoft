package edu.eci.arsw.synchdrive.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.eci.arsw.synchdrive.model.User;
import edu.eci.arsw.synchdrive.persistence.UserPersistenceException;
import edu.eci.arsw.synchdrive.persistence.UserRepository;

@Service("userServices")
public class UserServices{
    @Autowired
    @Qualifier("UserPersistence")
    UserRepository userRepo = null;

    public void addNewUser(User user) throws UserPersistenceException{
        userRepo.saveNewUser(user);
        
    }


}