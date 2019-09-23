package edu.eci.arsw.synchdrive.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Car implements Serializable {

    @Id
    private String plate;

    @Column
    private String model;

    @Column
    private Integer seats;

    @ManyToOne
    private Driver driver;

    @OneToOne(cascade = CascadeType.ALL)
    private Coordinate coordinate;

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
