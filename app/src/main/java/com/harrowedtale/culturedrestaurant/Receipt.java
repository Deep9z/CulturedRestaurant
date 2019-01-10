package com.harrowedtale.culturedrestaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class Receipt extends AppCompatActivity {

    TextView tvCost;
    TextView tvNumOfPeople;
    TextView tvPricePerPerson;

    Button bPrintReceipt;
    Button bEmailReceipt;


    //TODO temp
    TextView temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        tvCost = findViewById(R.id.tvCost);
        tvNumOfPeople = findViewById(R.id.tvNumOfPeople);
        tvPricePerPerson = findViewById(R.id.tvPricePerPerson);


        bPrintReceipt = findViewById(R.id.bPrintReceipt);
        bEmailReceipt = findViewById(R.id.bEmailReceipt);


        //TODO temp text reminding me to make receipt nicer looking
        temp = findViewById(R.id.TEMP);
        temp.setText("Thank you for visiting, and remember to stay Cultured.");


        //TODO make receipt nicer looking and get more values from previous activity
        //get values from previous activity
        Intent intent = getIntent();
        String numOfPeople = intent.getStringExtra("numOfPeople");
        final String cost = intent.getStringExtra("cost");
        String pricePerPerson = intent.getStringExtra("pricePerPerson");
        final String IDString = intent.getStringExtra("TableNumber");
        final String ToGoString = intent.getStringExtra("ToGo");


        tvCost.setText(String.valueOf("Total $" + cost));
        tvNumOfPeople.setText(String.valueOf("Number of Payers " + numOfPeople));
        tvPricePerPerson.setText(String.valueOf("Price per person: $" + pricePerPerson));

        bPrintReceipt.setVisibility(View.VISIBLE);
        bEmailReceipt.setVisibility(View.VISIBLE);

        bPrintReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CollectionReference payments = FirebaseFirestore.getInstance()
                        .collection("Payments");

                Map<String,Object> pay = new HashMap<>();

                pay.put("Total", cost);
                pay.put("TableNumber", MainActivity.TABLE_NUM);
                pay.put("Comped", payActivity.MAN_COMPED);


                //writing to Payments collection
                payments.add(pay);

                //clearing all prices
                MakeCart.getAllPrices().clear();

                Toast.makeText(getApplicationContext(), "Printing Receipt", Toast.LENGTH_SHORT).show();

                Intent surveyIntent = new Intent(Receipt.this, SurveyActivity.class);
                surveyIntent.putExtra("TableNumber", IDString);
                surveyIntent.putExtra("ToGo", ToGoString);
                startActivity(surveyIntent);
                finish();
            }
        });

        bEmailReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CollectionReference payments = FirebaseFirestore.getInstance()
                        .collection("Payments");

                Map<String,Object> pay = new HashMap<>();

                pay.put("Total", cost);
                pay.put("TableNumber", MainActivity.TABLE_NUM);
                pay.put("Comped", payActivity.MAN_COMPED);


                //writing to Payments collection merge so data isn't overwritten for new orders
                payments.add(pay);

                //clearing all prices
                MakeCart.getAllPrices().clear();


                Toast.makeText(getApplicationContext(), "Emailing Receipt", Toast.LENGTH_SHORT).show();

                Intent surveyIntent = new Intent(Receipt.this, SurveyActivity.class);
                surveyIntent.putExtra("TableNumber", IDString);
                surveyIntent.putExtra("ToGo", ToGoString);
                startActivity(surveyIntent);
                finish();

            }
        });
    }
}
