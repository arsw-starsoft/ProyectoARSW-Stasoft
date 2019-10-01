package edu.eci.arsw.synchdrive.controller;

import edu.eci.arsw.synchdrive.model.Driver;
import edu.eci.arsw.synchdrive.persistence.SynchdrivePersistenceException;
import edu.eci.arsw.synchdrive.services.DriverServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/drivers")
public class DriverController {

    @Autowired
    private DriverServices driverServices;

    //Método de prueba para verificar autowired y repo
    @GetMapping(value = "/drivertest")
    public ResponseEntity<?> testMethod() throws SynchdrivePersistenceException {
        Driver driver = new Driver();
        driver.setName("test dname");
        driver.setEmail("test2@mail.com");
        driver.setPassword("123");
        driverServices.saveDriver(driver);
        return new ResponseEntity<>(driver, HttpStatus.CREATED);
    }

     /**
     * Gets the Drivers
     * @return All Drivers
     */
    @GetMapping
    public ResponseEntity<?> getAllDriver(){
        try{
            List<Driver> driver = driverServices.getAllDrivers();
            return new ResponseEntity<>(driver,HttpStatus.ACCEPTED);
        }catch (SynchdrivePersistenceException ex){
            return new ResponseEntity<>("Error 500",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @GetMapping(path = "/{driver}")
    public ResponseEntity<?> getDriverByEmail(@PathVariable("driver") String email){
        Driver driver = null;
        try{
            driver = driverServices.findDriverByEmail(email);
            return new ResponseEntity<>(driver,HttpStatus.ACCEPTED);
        }catch (SynchdrivePersistenceException ex){
            return new ResponseEntity<>("Error 404",HttpStatus.NOT_FOUND);
        }
    }

    
    @PostMapping
    public ResponseEntity<?> addNewDriver(@RequestBody Driver driver){
        try{
            driverServices.saveDriver(driver);
            return new ResponseEntity<>(driver,HttpStatus.CREATED);
        }catch (SynchdrivePersistenceException ex){
            return new ResponseEntity<>("ERROR 400",HttpStatus.BAD_REQUEST);
        }
    }

}
