package edu.eci.arsw.synchdrive.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
public class Driver  implements Serializable {

    @OneToMany(mappedBy = "driver")
    private List<Car> cars;

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


    

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
