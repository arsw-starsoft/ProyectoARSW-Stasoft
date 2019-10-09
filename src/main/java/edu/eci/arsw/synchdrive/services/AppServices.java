package edu.eci.arsw.synchdrive.services;

import edu.eci.arsw.synchdrive.model.App;
import edu.eci.arsw.synchdrive.persistence.SynchdrivePersistenceException;

import java.util.List;

public interface AppServices {

    List<App> getAllApps() throws SynchdrivePersistenceException;

    void saveApp(App app) throws SynchdrivePersistenceException;

    App findAppByName(String name) throws SynchdrivePersistenceException;
}
