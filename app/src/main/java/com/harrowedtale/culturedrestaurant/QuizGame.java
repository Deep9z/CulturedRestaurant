package com.harrowedtale.culturedrestaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;


public class QuizGame extends AppCompatActivity {


   // private Spinner spinnerCategory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quiz_game5);

        Intent intent = getIntent();
        final String IDString = intent.getStringExtra("TableNumber");
        final String ToGoString = intent.getStringExtra("ToGo");

        Spinner spinnerCategory =(Spinner) findViewById(R.id.Spinner);

        Button home  =  findViewById(R.id.quizhomeButton2);//Assigns homeButton to the button to send the customer back tot he main menu
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent HomeIntent = new Intent(QuizGame.this, GameMenu.class);
                startActivity(HomeIntent);
           }
        });

        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this, R.array.Category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);

        spinnerCategory.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(
                    android.widget.AdapterView<?> arg0,
                    View arg1, int pos, long arg3) {

                ((Spinner) findViewById(R.id.Spinner)).setSelection(0);

                String text;


                switch (pos) {
                    case 1:
                        text = "Programming";
                        Intent GameIntent = new Intent(QuizGame.this, QuizGameProgramming.class);
                        GameIntent.putExtra("TableNumber", IDString);
                        GameIntent.putExtra("ToGo", ToGoString);
                        startActivity(GameIntent);
                        break;
                    case 2:
                        text = "Math";
                        Intent GameIntent2 = new Intent(QuizGame.this, QuizGameMath.class);
                        GameIntent2.putExtra("TableNumber", IDString);
                        GameIntent2.putExtra("ToGo", ToGoString);
                        startActivity(GameIntent2);
                        break;
                    case 3:
                        text = "Geography";
                        Intent GameIntent3 = new Intent(QuizGame.this, QuizGameGeography.class);
                        GameIntent3.putExtra("TableNumber", IDString);
                        GameIntent3.putExtra("ToGo", ToGoString);
                        startActivity(GameIntent3);
                        break;
                    default:
                        return;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

    }

}
