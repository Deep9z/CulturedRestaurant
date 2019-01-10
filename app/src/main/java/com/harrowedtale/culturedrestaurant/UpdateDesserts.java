package com.harrowedtale.culturedrestaurant;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UpdateDesserts extends AppCompatActivity {

    TextInputEditText etdessertDoc;//edit text to get name of document
    TextInputEditText etdessertDescription;//edit text for item description
    TextInputEditText etdessertName;//edit text of item name
    TextInputEditText etdessertPrice;//text for price of item
    TextInputEditText etdessertCalories;//calories
    TextInputEditText etdessertPicture;
    TextInputEditText etdessertStock;
    Button updatedessertConfirm;


    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedesserts);

        etdessertPrice = findViewById(R.id.etdessertPrice);
        etdessertDescription = findViewById(R.id.etdessertDescription);
        etdessertName = findViewById(R.id.etdessertName);
        etdessertDoc = findViewById(R.id.etdessertDoc);
        etdessertCalories = findViewById(R.id.etdessertCalories);
        etdessertPicture = findViewById(R.id.etdessertPicture);
        etdessertStock = findViewById(R.id.etdessertStock);
        updatedessertConfirm = findViewById(R.id.updatedessertConfirm);

        updatedessertConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stock = etdessertStock.getText().toString();
                String picture = etdessertPicture.getText().toString();
                String calories = etdessertCalories.getText().toString();
                String price = etdessertPrice.getText().toString();
                String Name = etdessertName.getText().toString();
                String Description = etdessertDescription.getText().toString();
                String Doc = etdessertDoc.getText().toString();

                //Checking if fields are empty
                if(TextUtils.isEmpty(stock)){
                    etdessertStock.setError("Enter stock amount");
                    etdessertStock.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(calories)){
                    etdessertCalories.setError("Enter calories");
                    etdessertCalories.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(picture)){
                    etdessertPicture.setError("Enter picture URL");
                    etdessertPicture.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(Doc)){
                    etdessertDoc.setError("Enter Document name");
                    etdessertDoc.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(price)){
                    etdessertPrice.setError("Enter item price");
                    etdessertPrice.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(Name)){
                    etdessertName.setError("Enter Item Name");
                    etdessertName.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(Description)){
                    etdessertDescription.setError("Enter item description");
                    etdessertDescription.requestFocus();
                    return;
                }

                Map<String,Object> user = new HashMap<>();

                //string id to int ID for firebase
                int ID = Integer.parseInt(price), itemcalories = Integer.parseInt(calories);
                int itemstock = Integer.parseInt(stock);
                user.put("Price",ID);
                user.put("Description",Description);
                user.put("Name",Name);
                user.put("Stock", itemstock);
                user.put("Calories", itemcalories);
                user.put("Picture", picture);
                //writes info to firestore, updates users fields if document name exists otherwise creates a new user
                db.collection("Desserts").document(Doc)
                        .set(user)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(),"Item Updated",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),"Error updating item",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });

                finish();

            }
        });
    }
}
