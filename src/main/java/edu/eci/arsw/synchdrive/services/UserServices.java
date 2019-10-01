package edu.eci.arsw.synchdrive.services;

import edu.eci.arsw.synchdrive.model.Customer;
import edu.eci.arsw.synchdrive.persistence.SynchdrivePersistenceException;

import java.util.List;

public interface UserServices {

    List<Customer> getAllUsers() throws SynchdrivePersistenceException;

    void saveUser(Customer customer) throws SynchdrivePersistenceException;

    Customer findUserByEmail(String email) throws SynchdrivePersistenceException;
}
