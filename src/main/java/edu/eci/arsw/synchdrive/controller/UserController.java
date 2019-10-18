package edu.eci.arsw.synchdrive.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.eci.arsw.synchdrive.model.App;
import edu.eci.arsw.synchdrive.model.Customer;
import edu.eci.arsw.synchdrive.persistence.SynchdrivePersistenceException;
import edu.eci.arsw.synchdrive.services.UserServices;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserServices userServices;

    //Método de prueba para verificar autowired y repo
    @GetMapping(value = "/usertest")
    public ResponseEntity<?> testMethod() throws SynchdrivePersistenceException {
        Customer customer = new Customer();
        customer.setName("test name");
        customer.setEmail("test1@mail.com");
        customer.setPassword("123");
        userServices.saveUser(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    /**
     * Gets the User
     * @return All User
     */
    @GetMapping
    public ResponseEntity<?> getAllUser(){
        try{
            List<Customer> customers = userServices.getAllUsers();
            return new ResponseEntity<>(customers,HttpStatus.ACCEPTED);
        }catch (SynchdrivePersistenceException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @GetMapping(path = "/{user}")
    public ResponseEntity<?> getUserByEmail(@PathVariable("user") String email){
        Customer customer = null;
        try{
            customer = userServices.findUserByEmail(email);
            return new ResponseEntity<>(customer,HttpStatus.ACCEPTED);
        }catch (SynchdrivePersistenceException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/{user}/apps")
    public ResponseEntity<?> getAppsByEmail(@PathVariable("user") String email){
        try{
            
            return new ResponseEntity<>(userServices.findAppsByEmail(email),HttpStatus.ACCEPTED);
        }catch (SynchdrivePersistenceException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    
    @PostMapping
    public ResponseEntity<?> addNewUser(@RequestBody Customer customer){
        try{
            userServices.saveUser(customer);
            return new ResponseEntity<>(customer,HttpStatus.CREATED);
        }catch (SynchdrivePersistenceException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

/*
    @PutMapping(path = "/{user}/apps")
    public ResponseEntity<?> addNewApp(@PathVariable("user") String user,@RequestBody App app){
        try{
            userServices.updateApps(user,app);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (SynchdrivePersistenceException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }*/


    
    @PutMapping(path = "/{user}")
    public ResponseEntity<?> addApp(@PathVariable("user") String user,@Valid @RequestBody Customer customer){
        try {
            //System.out.println(customer.getEmail());
            userServices.updateUser(user,customer);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.FORBIDDEN);            
        }        

    }

}
