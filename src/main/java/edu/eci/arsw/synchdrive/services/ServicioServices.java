package edu.eci.arsw.synchdrive.services;

import java.util.List;

import edu.eci.arsw.synchdrive.model.App;
import edu.eci.arsw.synchdrive.model.Customer;
import edu.eci.arsw.synchdrive.model.Driver;
import edu.eci.arsw.synchdrive.model.Servicio;
import edu.eci.arsw.synchdrive.persistence.SynchdrivePersistenceException;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


public interface ServicioServices {

    List<Servicio> getAllServices();

    void saveService(Servicio service);

    Map<String, Queue<Servicio>> generateServices(Servicio servicio) throws SynchdrivePersistenceException;

    Map<String, Queue<Servicio>> loadActiveServices();

    boolean isStarted();

    void setStarted(boolean started);

    void cleanServices();

    void acceptService(String driver,String app,Servicio servicio) throws SynchdrivePersistenceException;

    List<Servicio> findByApp (App app);

    List<Servicio> recordCustomer (Customer customer);

    List<Servicio> recordDriver(Driver driver);

    void cancelService(Servicio servicio) throws SynchdrivePersistenceException;


}
