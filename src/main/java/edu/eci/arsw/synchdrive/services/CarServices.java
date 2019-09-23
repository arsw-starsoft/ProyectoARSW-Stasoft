package edu.eci.arsw.synchdrive.services;

import edu.eci.arsw.synchdrive.model.Car;
import edu.eci.arsw.synchdrive.persistence.SynchdrivePersistenceException;

import java.util.List;

public interface CarServices {

    List<Car> getAllCars() throws SynchdrivePersistenceException;

    void saveCar(Car car) throws SynchdrivePersistenceException;

    Car findCarByPlate(String plate) throws SynchdrivePersistenceException;
}
