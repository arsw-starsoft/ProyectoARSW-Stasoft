package edu.eci.arsw.synchdrive.controller;

import edu.eci.arsw.synchdrive.model.User;
import edu.eci.arsw.synchdrive.persistence.SynchdrivePersistenceException;
import edu.eci.arsw.synchdrive.services.UserServices;

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
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserServices userServices;

    //Método de prueba para verificar autowired y repo
    @GetMapping(value = "/usertest")
    public ResponseEntity<?> testMethod() throws SynchdrivePersistenceException {
        User user = new User();
        user.setName("test name");
        user.setEmail("test1@mail.com");
        user.setPassword("123");
        userServices.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    /**
     * Gets the User
     * @return All User
     */
    @GetMapping
    public ResponseEntity<?> getAllUser(){
        try{
            List<User> users = userServices.getAllUsers();
            return new ResponseEntity<>(users,HttpStatus.ACCEPTED);
        }catch (SynchdrivePersistenceException ex){
            return new ResponseEntity<>("Error 500",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @GetMapping(path = "/{user}")
    public ResponseEntity<?> getUserByName(@PathVariable("user") String name){
        User user = null;
        try{
            user = userServices.findUserByName(name);
            return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
        }catch (SynchdrivePersistenceException ex){
            return new ResponseEntity<>("Error 404",HttpStatus.NOT_FOUND);
        }
    }

    
    @PostMapping
    public ResponseEntity<?> addNewUser(@RequestBody User user){
        try{
            userServices.saveUser(user);
            return new ResponseEntity<>(user,HttpStatus.CREATED);
        }catch (SynchdrivePersistenceException ex){
            return new ResponseEntity<>("ERROR 400",HttpStatus.BAD_REQUEST);
        }
    }

}
