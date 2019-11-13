package edu.eci.arsw.synchdrive.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Servicio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idService;

    @Column
    private Double price;

    @Column
    private Double duration;

    @Column
    private Double distance;

    @Column
    private Boolean active;

    @ManyToOne(cascade = CascadeType.ALL)
    private Driver driver;
    
    
    @ManyToOne(cascade = CascadeType.ALL)
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
}