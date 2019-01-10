package com.harrowedtale.culturedrestaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class MenuCategoriesActivity extends AppCompatActivity {


    Button Apps;
    Button Entrees;
    Button Drinks;
    Button KidsEntrees;
    Button Lactose;
    Button Desserts;
    Button Specials;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_categories);

        Specials = findViewById(R.id.bSpecials);
        Apps = findViewById(R.id.bAppetizers);
        Entrees = findViewById(R.id.bEntrees);
        Drinks = findViewById(R.id.bDrinks);
        KidsEntrees = findViewById(R.id.bKidsEntrees);
        Lactose = findViewById(R.id.bLactoseIntolerant);
        Desserts = findViewById(R.id.bDesserts);


        Apps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayItems("Appetizers");
            }
        });

        Entrees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayItems("Entrees");
            }
        });

        Drinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayItems("Drinks");
            }
        });

        KidsEntrees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayItems("KidsEntrees");
            }
        });

        Lactose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayItems("LactoseIntolerant");
            }
        });

        Desserts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayItems("Desserts");
            }
        });

        Specials.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v)  {
                DisplayItems("Specials");
            }
        });

    }

    public void DisplayItems(String category){
        Intent displayItemsIntent = new Intent(MenuCategoriesActivity.this, DisplayItemsActivity.class);
        displayItemsIntent.putExtra("Item Category", category);
        startActivity(displayItemsIntent);

    }
}
