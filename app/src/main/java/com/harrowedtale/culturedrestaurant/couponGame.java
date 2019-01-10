package com.harrowedtale.culturedrestaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class couponGame extends AppCompatActivity {

    public Button couponButton1;//Initializes the method for the first button, of class Button
    public Button couponButton2;//Initializes the method for the second button, of class Button
    public Button couponButton3;//Initializes the method for the third button, of class Button
    public Button couponButton4;//Initializes the method for the fourth button, of class Button
    public Button couponButton5;//Initializes the method for the fifth button, of class Button
    public Button homeButton;//Initializes the method for the fifth button, of class Button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_game);

        Intent intent = getIntent();
        final String IDString = intent.getStringExtra("TableNumber");
        final String ToGoString = intent.getStringExtra("ToGo");

        couponButton1 = (Button) findViewById(R.id.bCoupon1);//Assigns the method button to the button created in the .xml file. It is type casted to type Button.
        couponButton2 = (Button) findViewById(R.id.bCoupon2);//Assigns the method button to the button created in the .xml file. It is type casted to type Button.
        couponButton3 = (Button) findViewById(R.id.bCoupon3);//Assigns the method button to the button created in the .xml file. It is type casted to type Button.
        couponButton4 = (Button) findViewById(R.id.bCoupon4);//Assigns the method button to the button created in the .xml file. It is type casted to type Button.
        couponButton5 = (Button) findViewById(R.id.bCoupon5);//Assigns the method button to the button created in the .xml file. It is type casted to type Button.
        homeButton    = (Button) findViewById(R.id.bCouponHome);//Assigns homeButton to the button to send the customer back tot he main menu


        //Detects which button the customer selects, giving a 1/5 chance to get the coupon

        couponButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setVisibility(View.GONE);//Set the button to go invisible

                //Disables clicking any of the other buttons after one is selected
                couponButton2.setEnabled(false);
                couponButton3.setEnabled(false);
                couponButton4.setEnabled(false);
                couponButton5.setEnabled(false);
            }
        });

        couponButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setVisibility(View.GONE);//Set the button to go invisible

                //Disables clicking any of the other buttons after one is selected
                couponButton1.setEnabled(false);
                couponButton3.setEnabled(false);
                couponButton4.setEnabled(false);
                couponButton5.setEnabled(false);
            }
        });

        couponButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setVisibility(View.GONE);//Set the button to go invisible

                //Disables clicking any of the other buttons after one is selected
                couponButton2.setEnabled(false);
                couponButton1.setEnabled(false);
                couponButton4.setEnabled(false);
                couponButton5.setEnabled(false);
            }
        });

        couponButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setVisibility(View.GONE);//Set the button to go invisible

                //Disables clicking any of the other buttons after one is selected
                couponButton2.setEnabled(false);
                couponButton3.setEnabled(false);
                couponButton1.setEnabled(false);
                couponButton5.setEnabled(false);
            }
        });

        couponButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setVisibility(View.GONE);//Set the button to go invisible

                //Disables clicking any of the other buttons after one is selected
                couponButton2.setEnabled(false);
                couponButton3.setEnabled(false);
                couponButton4.setEnabled(false);
                couponButton1.setEnabled(false);
            }
        });

        //Detects if the user would like to go back to the customer home page
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent customerIntent = new Intent(couponGame.this, customerActivity.class);
                customerIntent.putExtra("TableNumber", IDString);
                customerIntent.putExtra("ToGo", ToGoString);
                startActivity(customerIntent);
            }
        });


    }
}
