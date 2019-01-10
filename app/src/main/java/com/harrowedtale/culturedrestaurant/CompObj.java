package com.harrowedtale.culturedrestaurant;



public class CompObj{

    //private String order;
    private int TableNumber;
    private String Total;

    public CompObj() {
    }

    public CompObj(int TableNumber, String Total) {
        this.TableNumber = TableNumber;
        this.Total = Total;
    }

    public int getTableNumber() {
        return TableNumber;
    }

    public String getTotal() {
        return Total;
    }
}
