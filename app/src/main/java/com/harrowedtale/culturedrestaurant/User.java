package com.harrowedtale.culturedrestaurant;

public class User {

    private int ID;
    private String Name;
    private String Classification;

    public User(){

    }

    public User(int ID, String Name, String Classification) {
        this.ID = ID;
        this.Name = Name;
        this.Classification = Classification;
    }


    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getClassification() {
        return Classification;
    }
}
