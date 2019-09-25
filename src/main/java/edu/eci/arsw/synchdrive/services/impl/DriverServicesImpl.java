package edu.eci.arsw.synchdrive.services.impl;

import edu.eci.arsw.synchdrive.model.Driver;
import edu.eci.arsw.synchdrive.persistence.DriverRepository;
import edu.eci.arsw.synchdrive.persistence.SynchdrivePersistenceException;
import edu.eci.arsw.synchdrive.services.DriverServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServicesImpl implements DriverServices {

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public List<Driver> getAllDrivers() throws SynchdrivePersistenceException{
       
         return driverRepository.findAll();
        
        
    }

    @Override
    public void saveDriver(Driver driver) throws SynchdrivePersistenceException {
        driverRepository.save(driver);
    }

    @Override
    public Driver findDriverByName(String user) throws SynchdrivePersistenceException {
        Optional<Driver> optinalDriver = driverRepository.findByName(user);
        if (!optinalDriver.isPresent())
            throw new SynchdrivePersistenceException(SynchdrivePersistenceException.CAR_NOT_FOUND);
        return optinalDriver.get();
    }
}
