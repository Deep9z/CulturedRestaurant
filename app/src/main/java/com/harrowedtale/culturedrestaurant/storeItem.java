package com.harrowedtale.culturedrestaurant;

import android.media.Image;

public class storeItem {

    private String Name;
    private String Description;
    private int Price;
    private  String Image;

    public storeItem() {
        //empty class constructor
    }

    public storeItem(String name, String description, int price, String image) {
        Name=name;
        Description=description;
        Price=price;
        Image=image;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public int getPrice() {
        return Price;
    }

    public String getImage() {
        return Image;
    }

}