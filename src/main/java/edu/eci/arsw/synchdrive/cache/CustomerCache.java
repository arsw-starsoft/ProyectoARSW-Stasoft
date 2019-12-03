package edu.eci.arsw.synchdrive.cache;

import edu.eci.arsw.synchdrive.model.App;
import edu.eci.arsw.synchdrive.model.Customer;

import java.util.Optional;

public interface CustomerCache {

    Optional<Customer> findByEmail(String email);

    void save(Customer customer);

    void save(App customerApp);

    void delete(App customerApp);

}
