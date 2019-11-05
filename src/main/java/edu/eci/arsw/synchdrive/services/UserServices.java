package edu.eci.arsw.synchdrive.services;

import java.io.IOException;
import java.util.List;

import edu.eci.arsw.synchdrive.model.App;
import edu.eci.arsw.synchdrive.model.Coordinate;
import edu.eci.arsw.synchdrive.model.Customer;
import edu.eci.arsw.synchdrive.model.Servicio;
import edu.eci.arsw.synchdrive.persistence.SynchdrivePersistenceException;

public interface UserServices {

    List<Customer> getAllUsers() throws SynchdrivePersistenceException;

    void saveUser(Customer customer) throws SynchdrivePersistenceException;

    Customer findUserByEmail(String email) throws SynchdrivePersistenceException;

    void updateApps(String customer,App app) throws SynchdrivePersistenceException;

    void updateUser(String user, Customer customer) throws SynchdrivePersistenceException  ,IOException ;

    List<App> findAppsByEmail(String user) throws SynchdrivePersistenceException;

    List<Servicio> getCloseServices(String user,Coordinate coordinate)throws  SynchdrivePersistenceException , IOException ;
}
