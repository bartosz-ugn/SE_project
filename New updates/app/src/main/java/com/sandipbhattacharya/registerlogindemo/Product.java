package com.sandipbhattacharya.registerlogindemo;

public class Product {
    private String title, image, campaign, store;
    private int price;


    public Product(String image, String title, String campaign, Integer price, String store){
        this.image = image;
        this.title = title;
        this.price = price;
        this.campaign = campaign;
        this.store = store;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {  return image;}
    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public String getCampaign() {
        return campaign;
    }
    public void setCampaign(String campaign) {
        this.campaign = campaign;
    }

    public String getStore() {return store;}
    public void setStore(String store) {
        this.store = store;
    }
}