package com.harrowedtale.culturedrestaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class orderStatusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        Intent intent = getIntent();
        final String IDString = intent.getStringExtra("TableNumber");
        final String ToGoString = intent.getStringExtra("ToGo");

        final TextView orderStatusText = (TextView) findViewById(R.id.etOrderStatus);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        //Putting the document here allows for a premade document name, rather than a randomly generated one
        DocumentReference test = db.collection("Orders").document(IDString);
        test.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    String orderStatus = "Order Status: ";
                    orderStatus = orderStatus + documentSnapshot.getString("OrderStatus");

                    orderStatusText.setText(orderStatus);
                }
                else{
                    Toast.makeText(getApplicationContext(), "No current order.", Toast.LENGTH_SHORT).show();
                }
            }
            });


    }
}
