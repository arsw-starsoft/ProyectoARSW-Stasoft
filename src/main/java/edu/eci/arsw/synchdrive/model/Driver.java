package edu.eci.arsw.synchdrive.model;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Driver implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String email;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String userName;

    @Column
    private String cellPhone;

    @Column
    private String password;

    @OneToMany(mappedBy = "driver")
    private List<Car> cars;

    @OneToMany(mappedBy = "driver")
    private List<App> apps;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null){
            this.email = email;
        }
        
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName != null){
            this.firstName = firstName;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName != null){
            this.lastName = lastName;
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setName(String userName) {
        if (userName != null){
            this.userName = userName;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password != null){
            this.password = password;
        }
        
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        if (cellPhone != null){
            this.cellPhone = cellPhone;
        }
    }
    

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        if (cars != null){
            this.cars = cars;
        }
    }

    public List<App> getApps() {
        return apps;
    }

    public void setApps(List<App> apps) {
        if (apps != null){
            this.apps = apps;
        }
    }
}
