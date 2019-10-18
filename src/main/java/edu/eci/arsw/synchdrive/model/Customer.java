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
public class Customer implements Serializable {


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
    
    
    @OneToMany(mappedBy = "customer")
    private List<App> apps;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!email.isEmpty()){
            this.email = email;
        }
        
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (!firstName.isEmpty()){
            this.firstName = firstName;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (!lastName.isEmpty()){
            this.lastName = lastName;
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setName(String userName) {
        if (!userName.isEmpty()){
            this.userName = userName;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (!password.isEmpty()){
            this.password = password;
        }
        
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        if (!cellPhone.isEmpty()){
            this.cellPhone = cellPhone;
        }
    }

    public List<App> getApps() {
        return apps;
    }

    public void setApps(List<App> apps) {
        if (!apps.isEmpty()){
            this.apps = apps;
        }
    }
}

