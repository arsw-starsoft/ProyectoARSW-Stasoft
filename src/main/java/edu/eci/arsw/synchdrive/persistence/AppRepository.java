package edu.eci.arsw.synchdrive.persistence;

import edu.eci.arsw.synchdrive.model.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppRepository extends JpaRepository<App,Integer> {

    List<App> findAll();

    App save(App car);

    Optional<App> findByName(String name);
}
