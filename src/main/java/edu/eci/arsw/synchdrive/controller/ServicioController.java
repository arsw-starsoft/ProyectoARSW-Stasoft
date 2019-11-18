package edu.eci.arsw.synchdrive.controller;

import java.util.List;

import edu.eci.arsw.synchdrive.model.App;
import edu.eci.arsw.synchdrive.persistence.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


import edu.eci.arsw.synchdrive.model.Coordinate;
import edu.eci.arsw.synchdrive.model.Servicio;
import edu.eci.arsw.synchdrive.persistence.SynchdrivePersistenceException;
import edu.eci.arsw.synchdrive.services.ServicioServices;

@RestController
@RequestMapping(value = "/servicios")
public class ServicioController {

    @Autowired
    private ServicioServices servicioServices;

    @Autowired
    ServicioRepository servicioRepository;

    //Método de prueba para verificar autowired y repo
    @GetMapping(value = "/serviciotest")
    public ResponseEntity<?> testMethod() throws SynchdrivePersistenceException {
        Servicio servicio = new Servicio();
        servicio.setPrice(123456.0);
        servicio.setDistance(12.0);
        servicio.setDuration(15.0);
        servicioServices.saveService(servicio);
        return new ResponseEntity<>(servicio, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<?> getAllServicios() {

        List<Servicio> servicios = servicioServices.getAllServices();
        return new ResponseEntity<>(servicios, HttpStatus.ACCEPTED);
    }


    @PostMapping
    public ResponseEntity<?> addNewApp(@RequestBody Servicio servicio) {

        servicioServices.saveService(servicio);
        return new ResponseEntity<>(servicio, HttpStatus.CREATED);

    }

    @PutMapping(path = "/{driver}/{app}")
    public ResponseEntity<?> acceptService(@PathVariable("driver") String driver, @PathVariable("app") String app, @Valid @RequestBody Servicio servicio) {
        try {

            servicioServices.acceptService(driver, app, servicio);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (SynchdrivePersistenceException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }

    }

    @GetMapping(path = "/{app}")
    public ResponseEntity<?> getServicesOfApp(@PathVariable("app") String app) {
        App app2 = new App();
        app = StringUtils.capitalize(app);
        app2.setName(app);
        return new ResponseEntity<>(servicioRepository.findByApp(app2), HttpStatus.ACCEPTED);
    }


}
