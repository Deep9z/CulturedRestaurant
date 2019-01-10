package com.harrowedtale.culturedrestaurant;

public class Survey {

    private int Rating;
    private String Feedback;

    public Survey(){
    }

    public Survey(int rating, String feedback){
        this.Rating = rating;
        this.Feedback = feedback;
    }

    public int getRating() {
        return Rating;
    }


    public String getFeedback() {
        return Feedback;
    }

}
