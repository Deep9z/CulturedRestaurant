package com.harrowedtale.culturedrestaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class kitchenstaffActivity extends AppCompatActivity {

    Button bViewOrder;
    Button bViewMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchenstaff);

        bViewOrder = findViewById(R.id.kitchen_orders_button);
        bViewMenu = findViewById(R.id.kitchen_menu_button);

        ///orders activity is recycle view made that just lists the orders like requests and deletes them when swiped.
        ///TODO must send flag to waitstaff on swipe function in order activity to display a request that the order is ready
        bViewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewRequestsIntent = new Intent(kitchenstaffActivity.this, orderActivity.class);
                startActivity(viewRequestsIntent);
            }
        });

        ///right now should just send to menu activity that the customer sees
        bViewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewRequestsIntent = new Intent(kitchenstaffActivity.this, MenuCategoriesActivity.class);
                startActivity(viewRequestsIntent);
            }
        });
    }
}
