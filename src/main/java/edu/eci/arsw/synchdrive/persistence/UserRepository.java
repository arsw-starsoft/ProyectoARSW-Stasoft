package edu.eci.arsw.synchdrive.persistence;

import edu.eci.arsw.synchdrive.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//Repositorio de prueba

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findAll();

    void saveNewUser(User user);

}
