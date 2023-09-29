package com.solvd.laba.entities;

public class Address {
    private int id = 0;
    private String street;
    private String city;
    private String state;
    private int zip;

    public Address(String street, String city, String state, int zip) {
        this.setId(this.getId() + 1);
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    @Override
    public String toString() {
        return this.street + ", " + this.city + ", " + this.state + ", " + zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        Address address = (Address) obj;
        return (address.street.equals(this.street) && address.city.equals(this.city)
                && address.state.equals(this.state) && address.zip == this.zip);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + this.getId();
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
