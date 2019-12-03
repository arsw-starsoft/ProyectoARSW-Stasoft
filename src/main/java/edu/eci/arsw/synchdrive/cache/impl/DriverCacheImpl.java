package edu.eci.arsw.synchdrive.cache.impl;

import edu.eci.arsw.synchdrive.cache.DriverCache;
import edu.eci.arsw.synchdrive.model.Driver;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class DriverCacheImpl implements DriverCache {

    ConcurrentHashMap<String, Optional<Driver>> drivers = new ConcurrentHashMap<>();

    ConcurrentHashMap<String, Long> tiempoDrivers = new ConcurrentHashMap<>();

    public DriverCacheImpl(){
        drivers = new ConcurrentHashMap<>();
        tiempoDrivers = new ConcurrentHashMap<>();
    }

    @Override
    public Optional<Driver> getDriver(String email) {
        return drivers.get(email);

    }

    @Override
    public void saveDriver(Optional<Driver> driver) {

    }



    @Override
    public Long getTimeInCache(String name) {
        return tiempoDrivers.get(name);
    }

}
