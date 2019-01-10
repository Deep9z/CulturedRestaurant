package com.harrowedtale.culturedrestaurant;

import java.util.List;

public class Order {

    //private String order;
    private String TableNumber;
    private List<String> Items;
    private String Customizations;

    public Order() {
    }

    public Order(String TableNumber, List<String> Items) {
        this.TableNumber = TableNumber;
        this.Items = Items;
    }

    public List<String> getItems() {
        return Items;
    }

    public String getTableNumber() {
        return TableNumber;
    }

    public String getCustomizations() {
        return Customizations;
    }
}
