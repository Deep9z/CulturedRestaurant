package com.harrowedtale.culturedrestaurant;

public class Request {

    private String type;
    private String item;
    private int tableNumber;

    //Empty constructor needed for Firebase
    public Request() {

    }

    public Request(String type, String item, int tableNumber){
        this.type = type;
        this.item = item;
        this.tableNumber = tableNumber;
    }

    public String getType() {
        return type;
    }

    public String getItem() {
        return item;
    }

    public int getTableNumber() {
        return tableNumber;
    }
}