package com.harrowedtale.culturedrestaurant;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class DisplaySurveys extends AppCompatActivity {

    RecyclerView listSurveys;
    FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_surveys);

        listSurveys = findViewById(R.id.list_surveys);
        queryList();
    }

    private void queryList() {
        Query surveyQuery = FirebaseFirestore.getInstance()
                .collection("Surveys")
                .orderBy("Rating");

        FirestoreRecyclerOptions<Survey> options = new FirestoreRecyclerOptions.Builder<Survey>()
                .setQuery(surveyQuery, Survey.class)
                .build();

        // Create the RecyclerViewAdapter
        adapter = new FirestoreRecyclerAdapter<Survey, SurveyHolder>(options) {
            @NonNull
            @Override
            public SurveyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.survey_list, parent, false);

                return new SurveyHolder(view);
            }

            //set text values to recycler view
            @Override
            protected void onBindViewHolder(@NonNull SurveyHolder holder, int position, @NonNull Survey model) {
                holder.rating.setText(String.valueOf(model.getRating()));
                holder.feedback.setText(model.getFeedback());


                
            }
        };

        listSurveys.setAdapter(adapter);
        listSurveys.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }

        @Override
        protected void onStart() {
            super.onStart();
            adapter.startListening();
    }

        @Override
        protected void onStop() {
            super.onStop();
            adapter.stopListening();
    }
}
