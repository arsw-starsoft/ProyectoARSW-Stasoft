package edu.eci.arsw.synchdrive.services.impl;

import edu.eci.arsw.synchdrive.model.App;
import edu.eci.arsw.synchdrive.persistence.AppRepository;
import edu.eci.arsw.synchdrive.persistence.SynchdrivePersistenceException;
import edu.eci.arsw.synchdrive.services.AppServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppServicesImpl implements AppServices {

    @Autowired
    private AppRepository appRepository;

    @Override
    public List<App> getAllApps() throws SynchdrivePersistenceException {
        return appRepository.findAll();
    }

    @Override
    public void saveApp(App app) throws SynchdrivePersistenceException{
        Optional<App> optinalApp = appRepository.findByName(app.getName());
        if (optinalApp.isPresent()){
            throw new SynchdrivePersistenceException(SynchdrivePersistenceException.APP_ALREDY_EXISTS);
        }
        else{
            appRepository.save(app);
        }
        
    }


    /***
     * Finds a car from a given plate
     * @param plate The car's plate
     * @return The car with the given plate
     * @throws SynchdrivePersistenceException CAR_NOT_FOUND exception if no car matches the given plate
     */
    @Override
    public App findAppByName(String name) throws SynchdrivePersistenceException {
        Optional<App> optionalApp = appRepository.findByName(name);
        if (!optionalApp.isPresent())
            throw new SynchdrivePersistenceException(SynchdrivePersistenceException.APP_NOT_FOUND);
        return optionalApp.get();
    }
}
