package com.harrowedtale.culturedrestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class UpdateMenu extends AppCompatActivity {

    public Button editAppetizers, editEntrees, editKidsentrees, editDesserts, editDrinks, editLactose_intolerant, editSpecials;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatemenu);

        editAppetizers = findViewById(R.id.editAppetizers);
        editEntrees = findViewById(R.id.editEntrees);
        editKidsentrees = findViewById(R.id.editKidsentrees);
        editDesserts = findViewById(R.id.editDesserts);
        editDrinks = findViewById(R.id.editDrinks);
        editLactose_intolerant = findViewById(R.id.editLactose_intolerant);
        editSpecials = findViewById(R.id.editSpecials);

        editAppetizers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent appetizerIntent = new Intent(UpdateMenu.this, UpdateAppetizers.class);
                startActivity(appetizerIntent);
            }
        });

        editEntrees.setOnClickListener(new View.OnClickListener()   {
            @Override
            public void onClick(View v) {
                Intent entreeIntent = new Intent(UpdateMenu.this, UpdateEntrees.class);
                startActivity(entreeIntent);
            }
        });

        editKidsentrees.setOnClickListener(new View.OnClickListener()   {
            @Override
            public void onClick(View v) {
                Intent kidsentrees = new Intent(UpdateMenu.this, UpdateKidsEntrees.class);
                startActivity(kidsentrees);
            }
        });

        editDesserts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dessertsintent = new Intent(UpdateMenu.this, UpdateDesserts.class);
                startActivity(dessertsintent);
            }
        });

        editDrinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent drinksintent = new Intent(UpdateMenu.this, UpdateDrinks.class);
                startActivity(drinksintent);
            }
        });

        editLactose_intolerant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lactoseintent = new Intent(UpdateMenu.this, UpdateLactose.class);
                startActivity(lactoseintent);
            }
        });

        editSpecials.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                Intent specialsintent = new Intent(UpdateMenu.this, UpdateSpecials.class);
                startActivity(specialsintent);
            }
        });
    }
}
