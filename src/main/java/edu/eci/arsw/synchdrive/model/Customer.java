package edu.eci.arsw.synchdrive.model;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class Customer implements Serializable {


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private Integer idCustomer;

    @Column
    @Expose
    private String email;

    @Column
    @Expose
    private String firstName;

    @Column
    @Expose
    private String lastName;

    @Column
    @Expose
    private String userName;

    @Column
    @Expose
    private String cellPhone;

    @Column
    @Expose
    private String password;

    @OneToMany(mappedBy = "customer",fetch = FetchType.EAGER)
    @Expose
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

    public List<App> getApps() {
        return apps;
    }

    public void setApps(List<App> apps) {
        if (apps != null){
            this.apps = apps;
        }
    }
}

