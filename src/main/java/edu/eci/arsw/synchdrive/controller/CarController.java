package edu.eci.arsw.synchdrive.controller;

import edu.eci.arsw.synchdrive.model.Car;
import edu.eci.arsw.synchdrive.services.CarServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {

    @Autowired
    private CarServices carServices;

    //Método de prueba para verificar autowired y repo
    @GetMapping(value = "/cartest")
    public ResponseEntity<?> testMethod(){
        Car car = new Car();
        car.setPlate("123SRE");
        car.setModel("Z");
        car.setSeats(5);
        carServices.saveCar(car);
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }
}
