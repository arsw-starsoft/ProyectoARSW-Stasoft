package edu.eci.arsw.synchdrive.cache.impl;

import edu.eci.arsw.synchdrive.cache.CustomerCache;
import edu.eci.arsw.synchdrive.model.Customer;


import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class CustomerCacheImpl implements CustomerCache {

    ConcurrentHashMap<String, Optional<Customer>> customers = new ConcurrentHashMap<>();

    ConcurrentHashMap<String, Long> tiempoCustomer = new ConcurrentHashMap<>();

    public CustomerCacheImpl(){
        customers = new ConcurrentHashMap<>();
        tiempoCustomer = new ConcurrentHashMap<>();
    }
    @Override
    public Optional<Customer> getCustomer(String email) {
        return Optional.empty();
    }

    @Override
    public void saveCustomer(Optional<Customer> customer) {

    }

    @Override
    public Long getTimeInCache(String email) {
        return null;
    }
}
