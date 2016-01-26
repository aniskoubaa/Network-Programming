package org.psu.acmchapter.networking.structures;

import java.io.Serializable;

public class Student implements Serializable{
    private int id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    

    public Student(int id, String firstName, String lastName, Date birthDate) {
        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    @Override
    public String toString() {
        return String.format("[id] %s %s (%s)",id, this.firstName, this.lastName, this.birthDate);
    }
    
    
    
}
