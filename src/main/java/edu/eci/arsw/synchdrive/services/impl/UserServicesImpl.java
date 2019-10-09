package edu.eci.arsw.synchdrive.services.impl;

import edu.eci.arsw.synchdrive.model.App;
import edu.eci.arsw.synchdrive.model.Customer;
import edu.eci.arsw.synchdrive.persistence.SynchdrivePersistenceException;
import edu.eci.arsw.synchdrive.persistence.UserRepository;
import edu.eci.arsw.synchdrive.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptPasswordEncoder;

    @Override
    public List<Customer> getAllUsers() throws SynchdrivePersistenceException {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(Customer customer) throws SynchdrivePersistenceException {
        Optional<Customer> optionalCustomer = userRepository.findByEmail(customer.getEmail());
        if (optionalCustomer.isPresent()) {
            throw new SynchdrivePersistenceException(SynchdrivePersistenceException.CUSTOMER_ALREDY_EXISTS);
        } else {
            String rawPassword = customer.getPassword();
            String encodedPassword = bcryptPasswordEncoder.encode(rawPassword);
            customer.setPassword(encodedPassword);
            userRepository.save(customer);
        }

    }

    @Override
    public Customer findUserByEmail(String user) throws SynchdrivePersistenceException {
        Optional<Customer> optinalUser = userRepository.findByEmail(user);
        boolean present = optinalUser.isPresent();
        System.out.println(present);
        if (!present)
            throw new SynchdrivePersistenceException(SynchdrivePersistenceException.CUSTOMER_NOT_FOUND);
        return optinalUser.get();
    }

    @Override
    public void saveApp(String customer, App app) throws SynchdrivePersistenceException {
        Optional<Customer> optinalUser = userRepository.findByEmail(customer);
        boolean present = optinalUser.isPresent();
        System.out.println(present);
        if (!present){
            throw new SynchdrivePersistenceException(SynchdrivePersistenceException.CUSTOMER_NOT_FOUND);
        }else{

        }
        

    }

    @Override
    public void updateApps(String customer, App app) throws SynchdrivePersistenceException {
        Optional<Customer> optinalUser = userRepository.findByEmail(customer);
        boolean present = optinalUser.isPresent();
        System.out.println(present);
        if (!present){
            throw new SynchdrivePersistenceException(SynchdrivePersistenceException.CUSTOMER_NOT_FOUND);
        }else{
            
        }
        
    }
}
