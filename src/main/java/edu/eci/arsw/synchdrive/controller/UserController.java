package edu.eci.arsw.synchdrive.controller;

import edu.eci.arsw.synchdrive.model.Customer;
import edu.eci.arsw.synchdrive.persistence.SynchdrivePersistenceException;
import edu.eci.arsw.synchdrive.services.UserServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin
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
            return new ResponseEntity<>("Error 500",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @GetMapping(path = "/{user}")
    public ResponseEntity<?> getUserByEmail(@PathVariable("user") String email){
        Customer customer = null;
        try{
            customer = userServices.findUserByEmail(email);
            return new ResponseEntity<>(customer,HttpStatus.ACCEPTED);
        }catch (SynchdrivePersistenceException ex){
            return new ResponseEntity<>("Error 404",HttpStatus.NOT_FOUND);
        }
    }

    
    @PostMapping
    public ResponseEntity<?> addNewUser(@RequestBody Customer customer){
        try{
            userServices.saveUser(customer);
            return new ResponseEntity<>(customer,HttpStatus.CREATED);
        }catch (SynchdrivePersistenceException ex){
            return new ResponseEntity<>("ERROR 400",HttpStatus.BAD_REQUEST);
        }
    }

}
