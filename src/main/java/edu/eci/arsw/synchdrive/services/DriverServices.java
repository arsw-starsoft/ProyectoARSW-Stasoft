package edu.eci.arsw.synchdrive.services;

import edu.eci.arsw.synchdrive.model.Driver;

import java.util.List;

public interface DriverServices {

    List<Driver> getAllDrivers();

    void saveDriver(Driver driver);
}
