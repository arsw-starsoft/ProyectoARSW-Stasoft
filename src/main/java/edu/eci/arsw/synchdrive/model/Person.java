package edu.eci.arsw.synchdrive.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Inheritance(
        strategy = InheritanceType.JOINED
)
public class Person implements Serializable {

    @Id
    private String email;

    @Column
    private String name;

    @Column
    private String password;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}