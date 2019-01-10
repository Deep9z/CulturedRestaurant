package com.harrowedtale.culturedrestaurant;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;



import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SurveyActivity extends AppCompatActivity {

    RatingBar mRatingBar;
    TextView mRatingScale;
    EditText mFeedback;
    Button mSendFeedback;
    Button mSkipSurvey;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        Intent intent = getIntent();
        final String IDString = intent.getStringExtra("TableNumber");
        final String ToGoString = intent.getStringExtra("ToGo");


        mRatingBar =  findViewById(R.id.ratingBar);
        mRatingScale = findViewById(R.id.tvRatingScale);
        mFeedback =  findViewById(R.id.etFeedback);
        mSendFeedback = findViewById(R.id.bSendSurvey);
        mSkipSurvey = findViewById(R.id.bSkipSurvey);

        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                mRatingScale.setText(String.valueOf(v));
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        mRatingScale.setText("Bad");
                        break;
                    case 2:
                        mRatingScale.setText("Okay");
                        break;
                    case 3:
                        mRatingScale.setText("Good");
                        break;
                    case 4:
                        mRatingScale.setText("Great");
                        break;
                    case 5:
                        mRatingScale.setText("Awesome");
                        break;
                    default:
                        mRatingScale.setText("");
                }
            }
        });

        mSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFeedback.getText().toString().isEmpty()) {
                    Toast.makeText(SurveyActivity.this, "Please fill in feedback text box", Toast.LENGTH_LONG).show();
                } else {
                    Map<String,Object> survey = new HashMap<>();

                    int rating =  Math.round(mRatingBar.getRating());
                    String feedback = mFeedback.getText().toString();

                    survey.put("Rating",rating);
                    survey.put("Feedback", feedback);

                    db.collection("Surveys").add(survey);


                    mFeedback.setText("");
                    mRatingBar.setRating(0);
                    Toast.makeText(SurveyActivity.this, "Thank you for sharing your feedback", Toast.LENGTH_LONG).show();

                    Intent couponIntent = new Intent(SurveyActivity.this, couponGame.class);
                    couponIntent.putExtra("TableNumber", IDString);
                    couponIntent.putExtra("ToGo", ToGoString);
                    startActivity(couponIntent);
                    finish();
                }
            }
        });

        mSkipSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent couponIntent = new Intent(SurveyActivity.this, couponGame.class);
                couponIntent.putExtra("TableNumber", IDString);
                couponIntent.putExtra("ToGo", ToGoString);
                startActivity(couponIntent);
                finish();

            }
        });
    }

}
