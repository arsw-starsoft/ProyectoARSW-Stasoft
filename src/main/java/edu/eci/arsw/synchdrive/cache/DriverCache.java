package edu.eci.arsw.synchdrive.cache;


import edu.eci.arsw.synchdrive.model.Driver;

import java.util.Optional;

public interface DriverCache {

    Optional<Driver> getDriver(String email);

    void saveDriver(Optional<Driver> driver);

    Long getTimeInCache(String email);
}
