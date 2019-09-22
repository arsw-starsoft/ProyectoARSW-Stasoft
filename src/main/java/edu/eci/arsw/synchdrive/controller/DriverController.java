package edu.eci.arsw.synchdrive.controller;

import edu.eci.arsw.synchdrive.model.Driver;
import edu.eci.arsw.synchdrive.model.User;
import edu.eci.arsw.synchdrive.services.DriverServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverController {

    @Autowired
    private DriverServices driverServices;

    //Método de prueba para verificar autowired y repo
    @GetMapping(value = "/drivertest")
    public ResponseEntity<?> testMethod(){
        Driver driver = new Driver();
        driver.setName("test dname");
        driver.setEmail("test2@mail.com");
        driver.setPassword("123");
        driverServices.saveDriver(driver);
        return new ResponseEntity<>(driver, HttpStatus.CREATED);
    }

}
