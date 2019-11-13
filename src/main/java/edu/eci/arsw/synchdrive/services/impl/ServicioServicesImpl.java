package edu.eci.arsw.synchdrive.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import edu.eci.arsw.synchdrive.connection.HttpConnectionService;
import edu.eci.arsw.synchdrive.model.App;
import edu.eci.arsw.synchdrive.model.Customer;
import edu.eci.arsw.synchdrive.model.Driver;

import edu.eci.arsw.synchdrive.persistence.UserRepository;
import edu.eci.arsw.synchdrive.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.arsw.synchdrive.model.Servicio;
import edu.eci.arsw.synchdrive.persistence.DriverRepository;
import edu.eci.arsw.synchdrive.persistence.ServicioRepository;
import edu.eci.arsw.synchdrive.persistence.SynchdrivePersistenceException;
import edu.eci.arsw.synchdrive.services.ServicioServices;

@Service
public class ServicioServicesImpl implements ServicioServices {

    private Map<String,Queue<Servicio>> servicesMap;

    private AtomicBoolean started;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private UserServices userServices;

    public ServicioServicesImpl(){
        servicesMap = new ConcurrentHashMap();
        servicesMap.put("uber",new ConcurrentLinkedQueue<>());
        servicesMap.put("didi",new ConcurrentLinkedQueue<>());
        servicesMap.put("beat",new ConcurrentLinkedQueue<>());
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
    public Map<String,Queue<Servicio>> generateServices(Servicio servicio) throws SynchdrivePersistenceException {
        try {
            Customer customer= userServices.findUserByEmail(servicio.getCustomer().getEmail());
            for (App app : servicio.getCustomer().getApps()) {
                Servicio generatedService = new Servicio(); //Temporal

                switch (app.getName().toLowerCase()) {
                    case "didi":
                        Servicio temp = HttpConnectionService.getGenerateDidi(servicio);
                        generatedService.setDuration(temp.getDuration());
                        generatedService.setPrice(temp.getPrice());
                        generatedService.setDistance(temp.getDistance());
                        generatedService.setActive(temp.getActive());
                        break;
                    case "uber":
                        Servicio temp2 = HttpConnectionService.getGenerateUber(servicio);
                        generatedService.setDuration(temp2.getDuration());
                        generatedService.setPrice(temp2.getPrice());
                        generatedService.setDistance(temp2.getDistance());
                        generatedService.setActive(temp2.getActive());
                        break;
                    case "beat":
                        Servicio temp3 = HttpConnectionService.getGenerateBeat(servicio);
                        generatedService.setDuration(temp3.getDuration());
                        generatedService.setPrice(temp3.getPrice());
                        generatedService.setDistance(temp3.getDistance());
                        generatedService.setActive(temp3.getActive());
                        break;
                }
                generatedService.setCustomer(customer);
                App apptemp = new App();
                apptemp.setCustomer(customer);
                apptemp.setName(app.getName());
                servicesMap.get(apptemp.getName().toLowerCase()).add(generatedService);
                serviceRepository.save(generatedService);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return servicesMap;
    }

    @Override
    public Map<String, Queue<Servicio>> loadActiveServices(){
        List<Servicio> activeServices = serviceRepository.getAllByActiveIsTrue();
        for (Servicio active: activeServices){
            for (App app : active.getCustomer().getApps()) {
                switch (app.getName().toLowerCase()) {
                    case "uber":
                        servicesMap.get("uber").add(active);
                        break;
                    case "didi":
                        servicesMap.get("didi").add(active);
                        break;
                    case "beat":
                        servicesMap.get("beat").add(active);
                        break;
                }
            }
        }
        return servicesMap;
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
        servicesMap.clear();
        servicesMap.put("uber",new ConcurrentLinkedQueue<>());
        servicesMap.put("didi",new ConcurrentLinkedQueue<>());
        servicesMap.put("beat",new ConcurrentLinkedQueue<>());
    }

    @Override
    public void acceptService(String driver,String app,Servicio servicio) throws SynchdrivePersistenceException{

        Optional<Servicio> optionalService = serviceRepository.findById(servicio.getIdService());

        if (optionalService.isPresent()){
            Servicio serv = optionalService.get();
            if (serv.getActive()){
                serv.setActive(false);
                Optional<Driver> optionalDriver = driverRepository.findByEmail(driver);
                if (!optionalDriver.isPresent()){
                    throw new SynchdrivePersistenceException(SynchdrivePersistenceException.DRIVER_NOT_FOUND);
                }
                serv.setDriver(optionalDriver.get());
                serviceRepository.save(serv);
            }else{
                throw new SynchdrivePersistenceException(SynchdrivePersistenceException.SERVICE_NOT_ACTIVE);
            }

        }else{
            throw new SynchdrivePersistenceException(SynchdrivePersistenceException.SERVICE_NOT_FOUND);
        }

    }

}
