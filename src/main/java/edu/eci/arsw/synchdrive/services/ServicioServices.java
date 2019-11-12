package edu.eci.arsw.synchdrive.services;

import java.util.List;

import edu.eci.arsw.synchdrive.model.Servicio;
import java.util.Queue;


public interface ServicioServices {

    List<Servicio> getAllServices();

    void saveService(Servicio service);

    Queue<Servicio> generateServices(Servicio servicio);

    boolean isStarted();

    void setStarted(boolean started);

    void cleanServices();

}
