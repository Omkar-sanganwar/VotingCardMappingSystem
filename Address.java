package com.nov21;

import javax.persistence.*;
import java.util.List;

@Entity
public class Address {
    @Id
    private int pincode;
    private String cityName;

    @ManyToMany(mappedBy = "addresses")
    private List<Person> persons;

    public Address() {}

    public Address(int pincode, String cityName) {
        this.pincode = pincode;
        this.cityName = cityName;
    }

    // Getters and Setters
    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
