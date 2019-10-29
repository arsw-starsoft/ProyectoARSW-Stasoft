package edu.eci.arsw.synchdrive.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.arsw.synchdrive.model.Servicio;
import edu.eci.arsw.synchdrive.persistence.ServicioRepository;
import edu.eci.arsw.synchdrive.services.ServicioServices;

@Service
public class ServicioServicesImpl implements ServicioServices {

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

}
