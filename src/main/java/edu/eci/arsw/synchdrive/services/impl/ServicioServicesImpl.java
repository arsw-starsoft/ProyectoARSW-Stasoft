package edu.eci.arsw.synchdrive.services.impl;

import java.io.IOException;
import java.util.*;
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

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private UserServices userServices;

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
    public Map<String,List<Servicio>> generateServices(Servicio servicio) throws SynchdrivePersistenceException {
        Map<String, List<Servicio>> servicesMap = new HashMap<>();
        servicesMap = initMap(servicesMap);
        try {
            Customer customer = userServices.findUserByEmail(servicio.getCustomer().getEmail());
            for (App app : servicio.getCustomer().getApps()) {
                Servicio generatedService = null;
                switch (app.getName().toLowerCase()) {
                    case "didi":
                        generatedService = HttpConnectionService.getGenerateDidi(servicio);
                        App didiApp = new App();
                        didiApp.setName("Didi");
                        generatedService.setApp(didiApp);
                        break;
                    case "uber":
                        generatedService = HttpConnectionService.getGenerateUber(servicio);
                        App uberApp = new App();
                        uberApp.setName("Uber");
                        generatedService.setApp(uberApp);
                        break;
                    case "beat":
                        generatedService = HttpConnectionService.getGenerateBeat(servicio);
                        App beatApp = new App();
                        beatApp.setName("Beat");
                        generatedService.setApp(beatApp);
                        break;
                }
                app.setCustomer(customer);
                generatedService.setCustomer(customer);
                servicesMap.get(app.getName().toLowerCase()).add(generatedService);
                serviceRepository.save(generatedService);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return servicesMap;
    }

    private Map<String, List<Servicio>> initMap(Map<String, List<Servicio>> servicesMap) {
        servicesMap.put("uber", new ArrayList<>());
        servicesMap.put("didi", new ArrayList<>());
        servicesMap.put("beat", new ArrayList<>());
        return servicesMap;
    }

    @Override
    public Map<String, List<Servicio>> loadActiveServices(){
        List<Servicio> activeServices = serviceRepository.getAllByActiveIsTrue();
        Map<String,List<Servicio>> servicesMap = new HashMap<>();
        servicesMap = initMap(servicesMap);
        for (Servicio active: activeServices){
            servicesMap.get(active.getApp().getName().toLowerCase()).add(active);
        }
        return servicesMap;
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
                cancelOtherServices(serv.getCustomer(), serv);

            }else{
                throw new SynchdrivePersistenceException(SynchdrivePersistenceException.SERVICE_NOT_ACTIVE);
            }

        }else{
            throw new SynchdrivePersistenceException(SynchdrivePersistenceException.SERVICE_NOT_FOUND);
        }

    }

    @Override
    public List<Servicio> findByApp(App app) {
        
        return serviceRepository.findByAppAndActiveIsTrue(app);
    }

    @Override
    public List<Servicio> recordCustomer(Customer customer) {
        return serviceRepository.findByCustomerAndActiveIsFalse(customer);
    }

    @Override
    public List<Servicio> recordDriver(Driver driver) {
        return serviceRepository.findByDriverAndActiveIsFalse(driver);
    }

    @Override
    public void cancelService(Servicio servicio) throws SynchdrivePersistenceException {
        Optional<Servicio> optionalService = serviceRepository.findById(servicio.getIdService());

        if (optionalService.isPresent()){
            Servicio service = optionalService.get();
            serviceRepository.delete(service);
        }else{
            throw new SynchdrivePersistenceException(SynchdrivePersistenceException.SERVICE_NOT_FOUND);
        }

    }

    private void cancelOtherServices(Customer customer,Servicio servicioActual){

        List<Servicio> listCustomer = serviciosCustomer(customer);
        for (Servicio ser : listCustomer){
            if (servicioActual.getIdPeticion().equals(ser.getIdPeticion())){
                serviceRepository.delete(ser);
            }
        }
        
    }

    @Override
    public List<Servicio> serviciosCustomer(Customer customer) {
        return serviceRepository.findByCustomerAndActiveIsTrue(customer);
    }

}
