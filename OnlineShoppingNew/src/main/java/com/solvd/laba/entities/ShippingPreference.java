package com.solvd.laba.entities;
public enum ShippingPreference {
    Domestic("Domestic",25), International("International",50), Pickup("Pickup", 0);
    private final String name;
    private final double cost;
    ShippingPreference(String name, double cost){
        this.name = name;
        this.cost = cost;
    }

    public String getName(){
        return this.name;
    }

    public double getCost(){
        return cost;
    }
}
