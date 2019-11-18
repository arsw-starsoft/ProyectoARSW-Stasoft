package edu.eci.arsw.synchdrive.persistence;

import java.util.List;
import java.util.Optional;

import edu.eci.arsw.synchdrive.model.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.eci.arsw.synchdrive.model.Servicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Integer> {

    List<Servicio> findAll();

    Optional<Servicio> findById(Integer id);

    Servicio save(Servicio service);

    List<Servicio> getAllByActiveIsTrue();

    List<Servicio> findByApp(App app);
}
