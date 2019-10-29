package edu.eci.arsw.synchdrive.services;

import java.util.List;

import edu.eci.arsw.synchdrive.model.Servicio;

public interface ServicioServices {

    List<Servicio> getAllServices();

    void saveService(Servicio service);

}
