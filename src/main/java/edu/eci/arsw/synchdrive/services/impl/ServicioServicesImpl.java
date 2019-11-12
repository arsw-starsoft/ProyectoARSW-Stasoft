package edu.eci.arsw.synchdrive.services.impl;

import java.io.IOException;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import edu.eci.arsw.synchdrive.connection.HttpConnectionService;
import edu.eci.arsw.synchdrive.model.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.arsw.synchdrive.model.Servicio;
import edu.eci.arsw.synchdrive.persistence.ServicioRepository;
import edu.eci.arsw.synchdrive.services.ServicioServices;

@Service
public class ServicioServicesImpl implements ServicioServices {

    private Queue<Servicio> servicesQueue;

    private AtomicBoolean started;

    public ServicioServicesImpl(){
        servicesQueue = new ConcurrentLinkedQueue<>();
        started = new AtomicBoolean(false);
    }

    @Autowired
    private ServicioRepository serviceRepository;

    @Override
    public List<Servicio> getAllServices() {
        return serviceRepository.findAll();
    }

    @Override
    public void saveService(Servicio servicio) {
        serviceRepository.save(servicio);
    }

    @Override
    public Queue<Servicio> generateServices(Servicio servicio) {
        try {
            Servicio generatedService = null;
            for (App app : servicio.getCustomer().getApps()) {
                switch (app.getName().toLowerCase()) {
                    case "uber":
                        generatedService = HttpConnectionService.getGenerateUber(servicio);
                        System.out.println(generatedService);
                        break;
                    case "didi":
                        //TODO
                        break;
                    case "beat":
                        //TODO
                        break;
                }
                servicesQueue.add(generatedService);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return servicesQueue;
    }

    @Override
    public boolean isStarted() {
        return started.get();
    }

    @Override
    public void setStarted(boolean started) {
        this.started.set(started);
    }

    @Override
    public void cleanServices() {
        servicesQueue.clear();
    }

}
