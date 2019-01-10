package com.harrowedtale.culturedrestaurant;



import java.util.ArrayList;

public class MakeCart {

    static ArrayList<String> ItemNames = new ArrayList<>();
    static ArrayList<Double> ItemPrices = new ArrayList<>();

    public static void addItem(String Item,Double Price)
    {
        ItemNames.add(Item);
        ItemPrices.add(Price);
    }

    public static ArrayList<String> getAllItems()
    {
        return (ItemNames);
    }

    public static  ArrayList<Double> getAllPrices()
    {
        return (ItemPrices);
    }



}
