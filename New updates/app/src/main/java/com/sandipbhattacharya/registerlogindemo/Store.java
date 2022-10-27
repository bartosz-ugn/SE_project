package com.sandipbhattacharya.registerlogindemo;

public class Store {
    public String name;
    public String destination;
    public String distance;
    public String address;
    public Integer image;

    public Store(String destination, String distance) {
        this.destination = destination;
        this.distance = distance;
    }
    public Store(String name, String address, int image) {
        this.name = name;
        this.address = address;
        this.image = image;
        this.distance = " ";
        this.destination = " ";
    }
    public Store(String name, String address, String distance, int image) {
        this.name = name;
        this.address = address;
        this.distance = distance;
        this.image = image;
        this.destination = " ";
    }

    public String getDestination(){ return this.destination;}
    public String getDistance(){ return this.distance;}
    public String getName(){ return this.name;}
    public String getAddress(){ return this.address;}
    public Integer getImage(){ return this.image;}
}
