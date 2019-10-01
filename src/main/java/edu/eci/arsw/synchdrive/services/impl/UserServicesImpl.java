package edu.eci.arsw.synchdrive.services.impl;

import edu.eci.arsw.synchdrive.model.Customer;
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
    public List<Customer> getAllUsers() throws SynchdrivePersistenceException {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(Customer customer) throws SynchdrivePersistenceException {
        userRepository.save(customer);
    }

    @Override
    public Customer findUserByEmail(String user) throws SynchdrivePersistenceException {
        Optional<Customer> optinalUser = userRepository.findByEmail(user);
        if (!optinalUser.isPresent())
            throw new SynchdrivePersistenceException(SynchdrivePersistenceException.CAR_NOT_FOUND);
        return optinalUser.get();
    }
}
