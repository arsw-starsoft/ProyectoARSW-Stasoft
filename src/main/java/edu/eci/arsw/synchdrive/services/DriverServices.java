package edu.eci.arsw.synchdrive.services;

import java.util.List;

import edu.eci.arsw.synchdrive.model.App;
import edu.eci.arsw.synchdrive.model.Car;
import edu.eci.arsw.synchdrive.model.Driver;
import edu.eci.arsw.synchdrive.persistence.SynchdrivePersistenceException;

public interface DriverServices {

    List<Driver> getAllDrivers() throws SynchdrivePersistenceException;

    void saveDriver(Driver driver) throws SynchdrivePersistenceException;

    Driver findDriverByEmail(String email) throws SynchdrivePersistenceException;

    void updateApps(String dirver,App app) throws SynchdrivePersistenceException;

    List<App> findAppsByEmail(String user) throws SynchdrivePersistenceException;

    List<Car> findCarsByEmail(String user) throws SynchdrivePersistenceException;

    void updateDriver(String user, Driver driver) throws SynchdrivePersistenceException;

    void updateCar(String user, Car car) throws SynchdrivePersistenceException;

}
