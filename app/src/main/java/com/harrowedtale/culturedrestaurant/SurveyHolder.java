package com.harrowedtale.culturedrestaurant;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;



public class SurveyHolder extends RecyclerView.ViewHolder {

    TextView rating;
    TextView feedback;

    public SurveyHolder(View itemView)
    {
        super(itemView);

        rating = itemView.findViewById(R.id.tv_rating);
        feedback = itemView.findViewById(R.id.tv_feedback);

    }

}
