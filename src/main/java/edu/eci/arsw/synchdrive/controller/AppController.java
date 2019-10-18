package edu.eci.arsw.synchdrive.controller;

import edu.eci.arsw.synchdrive.model.App;
import edu.eci.arsw.synchdrive.persistence.SynchdrivePersistenceException;
import edu.eci.arsw.synchdrive.services.AppServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/apps")
public class AppController {

    @Autowired
    private AppServices appServices;

    //Método de prueba para verificar autowired y repo
    @GetMapping(value = "/apptest")
    public ResponseEntity<?> testMethod() throws SynchdrivePersistenceException {
        App app = new App();
        app.setName("testApp");
        appServices.saveApp(app);
        return new ResponseEntity<>(app, HttpStatus.CREATED);
    }

 
    @GetMapping
    public ResponseEntity<?> getAllApps(){
        try{
            List<App> apps = appServices.getAllApps();
            return new ResponseEntity<>(apps,HttpStatus.ACCEPTED);
        }catch (SynchdrivePersistenceException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @GetMapping(path = "/{name}")
    public ResponseEntity<?> getAppByName(@PathVariable("name") String name){
        App app = null;
        try{
            app = appServices.findAppByName(name);
            return new ResponseEntity<>(app,HttpStatus.ACCEPTED);
        }catch (SynchdrivePersistenceException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    
    @PostMapping
    public ResponseEntity<?> addNewApp(@RequestBody App app){
        try{
            appServices.saveApp(app);
            return new ResponseEntity<>(app,HttpStatus.CREATED);
        }catch (SynchdrivePersistenceException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
