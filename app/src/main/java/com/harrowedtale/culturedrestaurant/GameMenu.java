package com.harrowedtale.culturedrestaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class GameMenu extends AppCompatActivity {

    public Button GameButton1;//Initializes the method for the first button, of class Button
    public Button GameButton2;//Initializes the method for the second button, of class Button
    public Button GameButton3;//Initializes the method for the third button, of class Button
    public Button GameButton4;//Initializes the method for the fourth button, of class Button
    public Button GameButton5;//Initializes the method for the fifth button, of class Button
    public Button homeButton;//Initializes the method for the fifth button, of class Button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);

        Intent intent = getIntent();
        final String IDString = intent.getStringExtra("TableNumber");
        final String ToGoString = intent.getStringExtra("ToGo");


        //TODO: Get background from Anderson and add it into the xml
        GameButton1 = (Button) findViewById(R.id.bGame1);//Assigns the method button to the button created in the .xml file. It is type casted to type Button.
        GameButton2 = (Button) findViewById(R.id.bGame2);//Assigns the method button to the button created in the .xml file. It is type casted to type Button.
        GameButton3 = (Button) findViewById(R.id.bGame3);//Assigns the method button to the button created in the .xml file. It is type casted to type Button.
        GameButton4 = (Button) findViewById(R.id.bGame4);//Assigns the method button to the button created in the .xml file. It is type casted to type Button.
        GameButton5 = (Button) findViewById(R.id.bGame5);//Assigns the method button to the button created in the .xml file. It is type casted to type Button.
        homeButton  = (Button) findViewById(R.id.bCouponHome);//Assigns homeButton to the button to send the customer back tot he main menu


        //Detects which button the customer selects, starting that game

        GameButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent GameIntent = new Intent(GameMenu.this, tictacGame.class);
                startActivity(GameIntent);

            }
        });

        GameButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent GameIntent = new Intent(GameMenu.this, MemoryGame.class);
                startActivity(GameIntent);

            }
        });

        GameButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent GameIntent = new Intent(GameMenu.this, ClickerGameActivity.class);
                startActivity(GameIntent);

            }
        });

        GameButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent GameIntent = new Intent(GameMenu.this, QuizGame.class);
                GameIntent.putExtra("TableNumber", IDString);
                GameIntent.putExtra("ToGo", ToGoString);
                startActivity(GameIntent);

            }
        });

        GameButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent GameIntent = new Intent(GameMenu.this, SpinBottle.class);
                startActivity(GameIntent);

            }
        });
    }
}
