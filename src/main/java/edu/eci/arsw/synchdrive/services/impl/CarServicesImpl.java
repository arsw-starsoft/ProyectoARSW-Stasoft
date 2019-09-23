package edu.eci.arsw.synchdrive.services.impl;

import edu.eci.arsw.synchdrive.model.Car;
import edu.eci.arsw.synchdrive.persistence.CarRepository;
import edu.eci.arsw.synchdrive.persistence.SynchdrivePersistenceException;
import edu.eci.arsw.synchdrive.services.CarServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServicesImpl implements CarServices {

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<Car> getAllCars() throws SynchdrivePersistenceException {
        return carRepository.findAll();
    }

    @Override
    public void saveCar(Car car) throws SynchdrivePersistenceException{
        carRepository.save(car);
    }


    /***
     * Finds a car from a given plate
     * @param plate The car's plate
     * @return The car with the given plate
     * @throws SynchdrivePersistenceException CAR_NOT_FOUND exception if no car matches the given plate
     */
    @Override
    public Car findCarByPlate(String plate) throws SynchdrivePersistenceException {
        Optional<Car> optionalCar = carRepository.findByPlate(plate);
        if (!optionalCar.isPresent())
            throw new SynchdrivePersistenceException(SynchdrivePersistenceException.CAR_NOT_FOUND);
        return optionalCar.get();
    }
}
