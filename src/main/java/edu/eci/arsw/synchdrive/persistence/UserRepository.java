package edu.eci.arsw.synchdrive.persistence;

import edu.eci.arsw.synchdrive.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Customer,Integer> {

    List<Customer> findAll();

    Customer save(Customer customer);

    Optional<Customer> findByEmail(String email);

    

}
