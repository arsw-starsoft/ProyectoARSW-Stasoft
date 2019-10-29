package edu.eci.arsw.synchdrive.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.synchdrive.model.Servicio;
import edu.eci.arsw.synchdrive.persistence.SynchdrivePersistenceException;
import edu.eci.arsw.synchdrive.services.ServicioServices;

@RestController
@RequestMapping(value = "/servicios")
public class ServicioController {

    @Autowired
    private ServicioServices servicioServices;

    //Método de prueba para verificar autowired y repo
    @GetMapping(value = "/serviciotest")
    public ResponseEntity<?> testMethod() throws SynchdrivePersistenceException {
        Servicio servicio = new Servicio();
        servicio.setPrecio("123456");
        servicio.setDistancia("12");
        servicio.setDuracion("15");
        servicioServices.saveService(servicio);
        return new ResponseEntity<>(servicio, HttpStatus.CREATED);
    }

 
    @GetMapping
    public ResponseEntity<?> getAllServicios(){
        
        List<Servicio> servicios = servicioServices.getAllServices();
        return new ResponseEntity<>(servicios,HttpStatus.ACCEPTED);
    }
    

    
    @PostMapping
    public ResponseEntity<?> addNewApp(@RequestBody Servicio servicio){
        
        servicioServices.saveService(servicio);
        return new ResponseEntity<>(servicio,HttpStatus.CREATED);
        
    }
}
