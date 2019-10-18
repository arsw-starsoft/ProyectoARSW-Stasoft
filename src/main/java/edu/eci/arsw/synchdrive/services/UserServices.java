package edu.eci.arsw.synchdrive.services;

import java.util.List;

import edu.eci.arsw.synchdrive.model.App;
import edu.eci.arsw.synchdrive.model.Customer;
import edu.eci.arsw.synchdrive.persistence.SynchdrivePersistenceException;

public interface UserServices {

    List<Customer> getAllUsers() throws SynchdrivePersistenceException;

    void saveUser(Customer customer) throws SynchdrivePersistenceException;

    Customer findUserByEmail(String email) throws SynchdrivePersistenceException;

    void updateApps(String customer,App app) throws SynchdrivePersistenceException;

    void updateUser(String user, Customer customer) throws SynchdrivePersistenceException;

    List<App> findAppsByEmail(String user) throws SynchdrivePersistenceException;
}
