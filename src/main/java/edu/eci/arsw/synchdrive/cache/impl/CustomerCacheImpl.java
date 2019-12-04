package edu.eci.arsw.synchdrive.cache.impl;

import edu.eci.arsw.synchdrive.cache.CustomerCache;
import edu.eci.arsw.synchdrive.model.App;
import edu.eci.arsw.synchdrive.model.Customer;
import edu.eci.arsw.synchdrive.persistence.AppRepository;
import edu.eci.arsw.synchdrive.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CustomerCacheImpl implements CustomerCache {

    ConcurrentHashMap<String, Optional<Customer>> customers;
    ConcurrentHashMap<String, List<App>> customerApps;
    ConcurrentHashMap<String, List<App>> toDeleteApps;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppRepository appRepository;

    public CustomerCacheImpl(){
        customers = new ConcurrentHashMap<>();
        customerApps = new ConcurrentHashMap<>();
        toDeleteApps = new ConcurrentHashMap<>();
        Timer saveToDatabaseTimer = new Timer();
        saveToDatabaseTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("SAVING USERS!...");
                for (Map.Entry<String,Optional<Customer>> entry: customers.entrySet()){
                    userRepository.save(entry.getValue().get());
                }

                System.out.println("DELETING PREVIOUS USER'S APP!...");
                for (Map.Entry<String,List<App>> entry: toDeleteApps.entrySet()){
                    for (App app : entry.getValue()){
                        appRepository.delete(app);
                    }
                }

                System.out.println("SAVING USER'S APP!...");
                for (Map.Entry<String,List<App>> entry: customerApps.entrySet()){
                    for (App app : entry.getValue()) {
                        appRepository.save(app);
                    }
                }

            }
        }, 900000, 900000); //15 mins
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        Optional<Customer> customerOptional = customers.get(email);
        //No esta en cache, intente en base de datos
        if (customerOptional == null){
            customerOptional = userRepository.findByEmail(email);
        }
        customerOptional.ifPresent(customer -> {
            if (customer.getApps() == null){
                customer.setApps(new ArrayList<>());
            }
        });
        return customerOptional;
    }

    @Override
    public void save(Customer customer) {
        Optional<Customer> customerOptional = Optional.ofNullable(customer);
        customers.put(customer.getEmail(),customerOptional);
    }

    @Override
    public void save(App customerApp) {
        String email = customerApp.getCustomer().getEmail();
        if (!customerApps.containsKey(email)){
            customerApps.put(email,new ArrayList<>());
        }
        customerApps.get(email).add(customerApp);
    }

    @Override
    public void delete(App customerApp) {
        String email = customerApp.getCustomer().getEmail();
        customerApps.remove(email);
        if (!toDeleteApps.containsKey(email)) {
            toDeleteApps.put(email,new ArrayList<>());
        }
        toDeleteApps.get(email).add(customerApp);
    }

}
