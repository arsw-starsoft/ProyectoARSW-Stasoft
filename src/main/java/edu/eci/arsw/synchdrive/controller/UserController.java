package edu.eci.arsw.synchdrive.controller;

import edu.eci.arsw.synchdrive.model.User;
import edu.eci.arsw.synchdrive.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//Rest controller de prueba

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/prueba")
    public void prueba(){
        User us = new User();
        us.setName("test");
        userRepository.save(us);
    }
}
