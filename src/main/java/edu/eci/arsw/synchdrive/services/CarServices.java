package edu.eci.arsw.synchdrive.services;

import edu.eci.arsw.synchdrive.model.Car;

import java.util.List;

public interface CarServices {

    List<Car> getAllCars();

    void saveCar(Car car);
}
