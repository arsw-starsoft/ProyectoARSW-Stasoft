package edu.eci.arsw.synchdrive.persistence;

import edu.eci.arsw.synchdrive.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,String> {

    List<Car> findAll();

    Car save(Car car);
}
