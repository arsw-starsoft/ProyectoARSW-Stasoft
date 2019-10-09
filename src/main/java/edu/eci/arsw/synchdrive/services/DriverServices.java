package edu.eci.arsw.synchdrive.services;

import edu.eci.arsw.synchdrive.model.App;
import edu.eci.arsw.synchdrive.model.Driver;
import edu.eci.arsw.synchdrive.persistence.SynchdrivePersistenceException;

import java.util.List;

public interface DriverServices {

    List<Driver> getAllDrivers() throws SynchdrivePersistenceException;

    void saveDriver(Driver driver) throws SynchdrivePersistenceException;

    Driver findDriverByEmail(String email) throws SynchdrivePersistenceException;

    void saveApp(String dirver,App app) throws SynchdrivePersistenceException;

    void updateApps(String dirver,App app) throws SynchdrivePersistenceException;

}
