package edu.eci.arsw.synchdrive.services;

import edu.eci.arsw.synchdrive.model.Driver;
import edu.eci.arsw.synchdrive.persistence.SynchdrivePersistenceException;

import java.util.List;

public interface DriverServices {

    List<Driver> getAllDrivers() throws SynchdrivePersistenceException;

    void saveDriver(Driver driver) throws SynchdrivePersistenceException;

    Driver findDriverByName(String user) throws SynchdrivePersistenceException;
}
