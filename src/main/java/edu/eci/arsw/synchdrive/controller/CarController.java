package edu.eci.arsw.synchdrive.controller;

import edu.eci.arsw.synchdrive.model.Car;
import edu.eci.arsw.synchdrive.persistence.SynchdrivePersistenceException;
import edu.eci.arsw.synchdrive.services.CarServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping(value = "/cars")
public class CarController {

    @Autowired
    private CarServices carServices;

    //Método de prueba para verificar autowired y repo
    @GetMapping(value = "/cartest")
    public ResponseEntity<?> testMethod() throws SynchdrivePersistenceException {
        Car car = new Car();
        car.setPlate("123ABC");
        car.setModel("Z");
        car.setSeats(5);
        carServices.saveCar(car);
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    /**
     * Gets the cars
     * @return All cars
     */
    @GetMapping
    public ResponseEntity<?> getAllCars(){
        try{
            List<Car> cars = carServices.getAllCars();
            return new ResponseEntity<>(cars,HttpStatus.ACCEPTED);
        }catch (SynchdrivePersistenceException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Finds a car given a plate
     * @param plate A car´s plate
     * @return OK, if the car is found else NOT FOUND
     */
    @GetMapping(path = "/{plate}")
    public ResponseEntity<?> getCarByPlate(@PathVariable("plate") String plate){
        Car car = null;
        try{
            car = carServices.findCarByPlate(plate);
            return new ResponseEntity<>(car,HttpStatus.ACCEPTED);
        }catch (SynchdrivePersistenceException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Creates a new car
     * @param car
     * @return The newly created car
     */
    @PostMapping
    public ResponseEntity<?> addNewCar(@RequestBody Car car){
        try{
            carServices.saveCar(car);
            return new ResponseEntity<>(car,HttpStatus.CREATED);
        }catch (SynchdrivePersistenceException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
