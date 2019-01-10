package com.harrowedtale.culturedrestaurant;


public class MenuItem {

    private String Name;
    private double Price;
    private int Calories;
    private String Picture;
    private String Description;

    public MenuItem() {
    }

    public MenuItem(String name, double price, int calories, String picture, String description) {
        this.Name = name;
        this.Price = price;
        this.Calories = calories;
        this.Picture = picture;
        this.Description = description;
    }

    public String getName() {
        return Name;
    }

    /*public void setName(String Name) {
        this.Name = Name;
    }

*/

    public double getPrice() {
        return Price;
    }

    /*
    public void setPrice(double price) {
        this.price = price;
    }

    */

    public int getCalories() {
        return Calories;
    }

    /*
    public void setCalories(int calories) {
        this.calories = calories;
    }

    */

    public String getPicture() {
        return Picture;
    }

    /*
    public void setImage(String image) {
        this.image = image;
    }
    */

    public String getDescription() {
        return Description;
    }

    /*
    public void setDescription(String description) {
        this.description = description;
    }
    */
}