package edu.eci.arsw.synchdrive.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.synchdrive.model.App;
import edu.eci.arsw.synchdrive.model.Car;
import edu.eci.arsw.synchdrive.model.Driver;
import edu.eci.arsw.synchdrive.persistence.SynchdrivePersistenceException;
import edu.eci.arsw.synchdrive.services.DriverServices;

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
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @GetMapping(path = "/{driver}")
    public ResponseEntity<?> getDriverByEmail(@PathVariable("driver") String email){
        Driver driver = null;
        try{
            driver = driverServices.findDriverByEmail(email);
            return new ResponseEntity<>(driver,HttpStatus.ACCEPTED);
        }catch (SynchdrivePersistenceException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/{driver}/apps")
    public ResponseEntity<?> getAppsByEmail(@PathVariable("driver") String email){
        try{
            
            return new ResponseEntity<>(driverServices.findAppsByEmail(email),HttpStatus.ACCEPTED);
        }catch (SynchdrivePersistenceException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/{driver}/cars")
    public ResponseEntity<?> getCarsByEmail(@PathVariable("driver") String email){
        try{
            
            return new ResponseEntity<>(driverServices.findCarsByEmail(email),HttpStatus.ACCEPTED);
        }catch (SynchdrivePersistenceException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


    
    @PostMapping
    public ResponseEntity<?> addNewDriver(@RequestBody Driver driver){
        try{
            driverServices.saveDriver(driver);
            return new ResponseEntity<>(driver,HttpStatus.CREATED);
        }catch (SynchdrivePersistenceException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(path = "/{driver}/apps",method = RequestMethod.PUT)
    public ResponseEntity<?> updateApp(@PathVariable("driver") String driver,@RequestBody App app){
        try{
            driverServices.updateApps(driver,app);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (SynchdrivePersistenceException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }



    @RequestMapping(path = "/{driver}",method = RequestMethod.PUT)	
    public ResponseEntity<?> updateUser(@PathVariable("driver") String user,@Valid @RequestBody Driver driver){
        try {
            
            driverServices.updateDriver(user,driver);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.FORBIDDEN);            
        }        

    }

    @RequestMapping(path = "/{driver}/cars",method = RequestMethod.PUT)	
    public ResponseEntity<?> updateUser(@PathVariable("driver") String driver,@Valid @RequestBody Car car){
        try {
            
            driverServices.updateCar(driver,car);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.FORBIDDEN);            
        }        

    }

    

}
