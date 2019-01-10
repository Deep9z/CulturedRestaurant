package com.harrowedtale.culturedrestaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class waitstaffActivity extends AppCompatActivity {

    Button bViewRequest;
    Button bViewOrdersStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waitstaff);

        Intent intent = getIntent();
        final String IDString = intent.getStringExtra("TableNumber");
        final String ToGoString = intent.getStringExtra("ToGo");


        bViewRequest = findViewById(R.id.bViewRequests);
        bViewOrdersStatus = findViewById(R.id.bViewOrdersStatus);

        bViewRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewRequestsIntent = new Intent(waitstaffActivity.this, requestsActivity.class);
                startActivity(viewRequestsIntent);
            }
        });

        bViewOrdersStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO make so all waitstaff cant see all orders
               Intent viewOrdersIntent = new Intent(waitstaffActivity.this, orderstatus_waitstaffactivity.class);
               startActivity(viewOrdersIntent);
            }
        });
    }
}
