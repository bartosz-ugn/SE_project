package com.sandipbhattacharya.registerlogindemo;

public class Store {
    public String name;
    public String destination;
    public String distance;

    public Store(String destination, String distance) {
        this.destination = destination;
        this.distance = distance;
    }
    public String getDestination(){ return this.destination;}
}
