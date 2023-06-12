package model;

import java.util.Date;

public class User {

    public User(String lastname, String firstname, String birthdate, String sexe){
        this.lastname = lastname;
        this.firstname = firstname;
        this.birthdate = birthdate;
        this.sexe = sexe;
    }

    private String lastname;
    private String firstname;
    private String birthdate;
    private String sexe;

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getSexe() {
        return sexe;
    }

    public void setLoad(String sexe) {
        this.sexe = sexe;
    }
}
