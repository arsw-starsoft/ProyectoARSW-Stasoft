package edu.eci.arsw.synchdrive.persistence;

import edu.eci.arsw.synchdrive.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    List<User> findAll();

    User save(User user);

    Optional<User> findByName(String user);

}
