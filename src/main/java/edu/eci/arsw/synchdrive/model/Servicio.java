package edu.eci.arsw.synchdrive.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;

@Entity
public class Servicio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private Integer idService;

    @Column
    @Expose
    private Double price;

    @Column
    @Expose
    private Double duration;

    @Column
    @Expose
    private Double distance;

    @Column
    @Expose
    private Boolean active;

    @Column
    @Expose
    private App app;

    @ManyToOne
    @Expose
    private Driver driver;
    
    @ManyToOne
    @Expose
    private Customer customer;


    public Integer getIdService() {
        return idService;
    }

    public void setIdService(Integer idService) {
        if (idService != null){
            this.idService = idService;
        }
        
    }
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }
  
    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
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
    
    @Override
    public String toString(){
        return "Service {Time: " + duration + ", Price: " + price + ", Distance: " + distance+"}";
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }
}