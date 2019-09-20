package edu.eci.arsw.synchdrive.model;

import java.util.ArrayList;
import java.util.List;

public class Driver {

    private Integer id;
    private String name;
    private List<Car> cars;
    private List<Application> apps;

    public Driver() {
        apps = new ArrayList<>();
        cars = new ArrayList<>();
    }

    public List<Application> getApps() {
        return apps;
    }

    public void addApps(Application app) {
        this.apps.add(app);
    }

    public List<Car> getCarros() {
        return cars;
    }

    public void addCars(Car car) {
        this.cars.add(car);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}