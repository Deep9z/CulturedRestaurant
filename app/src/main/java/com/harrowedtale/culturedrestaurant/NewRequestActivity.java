package com.harrowedtale.culturedrestaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class NewRequestActivity extends AppCompatActivity {

    Spinner sDrinkSelection;

    //TODO add more drinks
    String[] drinks = new String[] {"Water","Whole Milk","Unsweet tea","Café au lait", "Coca-Cola"};


    private String type;
    private String item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_request);

        sDrinkSelection = findViewById(R.id.sDrinkSelection);


        //for spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this,android.R.layout.simple_spinner_item,drinks);
        sDrinkSelection.setAdapter(adapter);

        final Button bDrinkRefill = findViewById(R.id.bDrinkRefill);

        bDrinkRefill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "Drink";

                //get value from spinner
                item = sDrinkSelection.getSelectedItem().toString();



                CollectionReference requestsRef = FirebaseFirestore.getInstance()
                        .collection("Requests");
                requestsRef.add(new Request(type,item,MainActivity.TABLE_NUM));

                Toast.makeText(getApplicationContext(),"Drink Refill Submitted",Toast.LENGTH_SHORT).show();

                //return to parent activity
                finish();

            }
        });

        final Button bCrackerRefill = findViewById(R.id.bCrackerRefill);
        bCrackerRefill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "Cracker";
                item = "Cracker";



                CollectionReference requestsRef = FirebaseFirestore.getInstance()
                        .collection("Requests");
                requestsRef.add(new Request(type,item,MainActivity.TABLE_NUM));

                Toast.makeText(getApplicationContext(),"Cracker Refill Submitted", Toast.LENGTH_SHORT).show();

                //return to parent activity
                finish();
            }
        });

        final Button bHelp = findViewById(R.id.bHelp);
        bHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "Help";
                item = "Help";


                CollectionReference requestsRef = FirebaseFirestore.getInstance()
                        .collection("Requests");
                requestsRef.add(new Request(type,item,MainActivity.TABLE_NUM));

                Toast.makeText(getApplicationContext(),"Help is on the way", Toast.LENGTH_SHORT).show();

                //return to parent activity
                finish();
            }
        });
    }
}