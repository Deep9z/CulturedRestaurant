package com.harrowedtale.culturedrestaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class goActivity extends AppCompatActivity {

    public Button goButton;//Initializes the method for the To Go button, of class Button
    public Button inButton;//Initializes the method for the Dine In button, of class Button



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go);

        Intent intent = getIntent();
        final String IDString = intent.getStringExtra("TableNumber");


        goButton = (Button) findViewById(R.id.go);//Assigns the method button to the button created in the .xml file. It is type casted to type Button.

        //Creates an function (onClickListener) that will listen to when the storeButton is clicked, and execute the code below.
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                    MAKE SURE DATABASE KNOWS IT IS A TO GO ORDER CODE HERE
                 */
                //start customerActivity through a new intent CHANGE TO THE TO GO KIOSK
                Intent goActivityIntent = new Intent(goActivity.this, customerActivity.class);
                goActivityIntent.putExtra("TableNumber", IDString);
                goActivityIntent.putExtra("ToGo", "Yes");
                startActivity(goActivityIntent);
            }
        });

        inButton = (Button) findViewById(R.id.in);//Assigns the method button to the button created in the .xml file. It is type casted to type Button.

        //Creates an function (onClickListener) that will listen to when the storeButton is clicked, and execute the code below.
        inButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                    MAKE SURE DATABASE KNOWS IT IS A DINE IN ORDER CODE HERE
                 */
                //start customerActivity through a new intent
                Intent goActivityIntent = new Intent(goActivity.this, customerActivity.class);
                goActivityIntent.putExtra("TableNumber", IDString);
                goActivityIntent.putExtra("ToGo", "No");
                startActivity(goActivityIntent);             }
        });

    }
}
