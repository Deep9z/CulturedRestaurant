package com.harrowedtale.culturedrestaurant;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class customerActivity extends AppCompatActivity {

    public Button foodMenu;//Initializes the method for the login button, of class Button
    public Button merchandiseMenu;//Initializes the method for the login button, of class Button
    public Button games;//Initializes the method for the login button, of class Button
    public Button pay;//Initializes the method for the login button, of class Button
    public Button orderStatus;//Initializes the method for the login button, of class Button
    public Button requests;//Initializes the method for the login button, of class Button
    public Button couponGame;//Initializes the method for the login button, of class Button
    public Button viewCart;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        Intent intent = getIntent();
        final String IDString = intent.getStringExtra("TableNumber");
        final String ToGoString = intent.getStringExtra("ToGo");

        foodMenu = (Button) findViewById(R.id.bFoodMenu);//Assigns homeButton to the button to send the customer back tot he main menu
        merchandiseMenu = (Button) findViewById(R.id.bMerchandiseMenu);//Assigns homeButton to the button to send the customer back tot he main menu
        games = (Button) findViewById(R.id.bGames);//Assigns homeButton to the button to send the customer back tot he main menu
        pay = (Button) findViewById(R.id.bPay);//Assigns homeButton to the button to send the customer back tot he main menu
        orderStatus = (Button) findViewById(R.id.bOrderStatus);//Assigns homeButton to the button to send the customer back tot he main menu
        requests = (Button) findViewById(R.id.bRequests);//Assigns homeButton to the button to send the customer back tot he main menu
        couponGame = (Button) findViewById(R.id.bCouponGame);//Assigns homeButton to the button to send the customer back tot he main menu
        viewCart = (Button) findViewById(R.id.bViewCart);


        //TODO change the .class variables to the appropriate sections
        foodMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent foodMenuIntent = new Intent(customerActivity.this, MenuCategoriesActivity.class);
               startActivity(foodMenuIntent);
            }
        });

        //TODO change the .class variables to the appropriate sections
        merchandiseMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent merchandiseMenuIntent = new Intent(customerActivity.this, DisplayItemsActivity.class);
                merchandiseMenuIntent.putExtra("TableNumber", IDString);
                merchandiseMenuIntent.putExtra("ToGo", ToGoString);
                merchandiseMenuIntent.putExtra("Item Category", "MerchandiseMenu");
                startActivity(merchandiseMenuIntent);
            }
        });

        //TODO change the .class variables to the appropriate sections
        games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gamesIntent = new Intent(customerActivity.this, GameMenu.class);
                gamesIntent.putExtra("TableNumber", IDString);
                gamesIntent.putExtra("ToGo", ToGoString);
                startActivity(gamesIntent);
            }
        });

        //TODO change the .class variables to the appropriate sections
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent payIntent = new Intent(customerActivity.this, payActivity.class);
                payIntent.putExtra("TableNumber", IDString);
                payIntent.putExtra("ToGo", ToGoString);
                startActivity(payIntent);
            }
        });

        //TODO change the .class variables to the appropriate sections
        orderStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent orderStatusIntent = new Intent(customerActivity.this, orderStatusActivity.class);
                orderStatusIntent.putExtra("TableNumber", IDString);
                orderStatusIntent.putExtra("ToGo", ToGoString);
                startActivity(orderStatusIntent);
            }
        });

        //TODO change the .class variables to the appropriate sections
        requests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent requestsIntent = new Intent(customerActivity.this, NewRequestActivity.class);
                startActivity(requestsIntent);
            }
        });

        //TODO add in to only show the coupon game once there is and order status
        couponGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent couponGameIntent = new Intent(customerActivity.this, couponGame.class);
                couponGameIntent.putExtra("TableNumber", IDString);
                couponGameIntent.putExtra("ToGo", ToGoString);
                startActivity(couponGameIntent);
            }
        });

        viewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewCartIntent = new Intent(customerActivity.this, DisplayCartActivity.class);
                viewCartIntent.putExtra("TableNumber", IDString);
                viewCartIntent.putExtra("ToGo", ToGoString);
                startActivity(viewCartIntent);
            }
        });


    }
}
