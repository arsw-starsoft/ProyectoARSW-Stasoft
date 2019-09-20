package edu.eci.arsw.synchdrive.model;

import javax.persistence.*;
import java.io.Serializable;


//Usuario de prueba

@Entity
@Table(name="suser")
public class User implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @Column(name="sname")
    private String name;

    public User() {

    }

    public long getId(){
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
