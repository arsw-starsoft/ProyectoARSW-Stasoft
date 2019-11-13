package edu.eci.arsw.synchdrive.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class App implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idApp;

    @Column
    private String name;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Driver driver;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    

}