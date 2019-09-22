package edu.eci.arsw.synchdrive.services.impl;

import edu.eci.arsw.synchdrive.model.Car;
import edu.eci.arsw.synchdrive.persistence.CarRepository;
import edu.eci.arsw.synchdrive.services.CarServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServicesImpl implements CarServices {

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public void saveCar(Car car) {
        carRepository.save(car);
    }
}
