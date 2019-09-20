package edu.eci.arsw.synchdrive.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//Usuario de prueba

@Entity
@Table(name = "suser")
public class User implements Serializable {

    private List<Application> apps;

    private String correo;

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "sname")
    private String name;

    public User() {
        apps = new ArrayList<>();
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<Application> getApps() {
        return apps;
    }

    public void addApp(Application app) {
        this.apps.add(app);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

}
