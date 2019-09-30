package edu.eci.arsw.synchdrive.persistence;

import edu.eci.arsw.synchdrive.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Integer> {

    List<Driver> findAll();

    Driver save(Driver driver);

    Optional<Driver> findByEmail(String email);
}
