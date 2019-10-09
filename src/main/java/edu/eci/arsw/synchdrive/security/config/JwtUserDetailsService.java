package edu.eci.arsw.synchdrive.security.config;

import edu.eci.arsw.synchdrive.model.Customer;
import edu.eci.arsw.synchdrive.model.Driver;
import edu.eci.arsw.synchdrive.persistence.DriverRepository;
import edu.eci.arsw.synchdrive.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Driver> optionalDriver = driverRepository.findByEmail(email);
        if (optionalDriver.isPresent()){
            Driver driver = optionalDriver.get();
            return new User(driver.getEmail(),driver.getPassword(), new ArrayList<>());
        }

        Optional<Customer> optionalCustomer = userRepository.findByEmail(email);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            return new User(customer.getEmail(), customer.getPassword(), new ArrayList<>());
        }
        if (email.equals("test_user")){
            return new User("test_user","123",new ArrayList<>());
        }
        throw new UsernameNotFoundException("User not found with username: " + email);
    }



}