package edu.eci.arsw.synchdrive.cache;

import edu.eci.arsw.synchdrive.model.Customer;

import java.util.Optional;

public interface CustomerCache {

    Optional<Customer> getCustomer(String email);

    void saveCustomer(Optional<Customer> customer);

    Long getTimeInCache(String email);

}
