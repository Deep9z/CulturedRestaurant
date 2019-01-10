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

public class UpdateDrinks extends AppCompatActivity {

    TextInputEditText etDrinksDoc;//edit text to get name of document
    TextInputEditText etDrinksDescription;//edit text for item description
    TextInputEditText etDrinksName;//edit text of item name
    TextInputEditText etDrinksPrice;//text for price of item
    TextInputEditText etDrinksCalories;//calories
    TextInputEditText etDrinksPicture;
    TextInputEditText etDrinksStock;
    Button updateDrinksConfirm;


    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedrinks);

        etDrinksPrice = findViewById(R.id.etDrinksPrice);
        etDrinksDescription = findViewById(R.id.etDrinksDescription);
        etDrinksName = findViewById(R.id.etDrinksName);
        etDrinksDoc = findViewById(R.id.etDrinksDoc);
        etDrinksCalories = findViewById(R.id.etDrinksCalories);
        etDrinksPicture = findViewById(R.id.etDrinksPicture);
        etDrinksStock = findViewById(R.id.etDrinksStock);
        updateDrinksConfirm = findViewById(R.id.updateDrinksConfirm);

        updateDrinksConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stock = etDrinksStock.getText().toString();
                String picture = etDrinksPicture.getText().toString();
                String calories = etDrinksCalories.getText().toString();
                String price = etDrinksPrice.getText().toString();
                String Name = etDrinksName.getText().toString();
                String Description = etDrinksDescription.getText().toString();
                String Doc = etDrinksDoc.getText().toString();

                //Checking if fields are empty
                if(TextUtils.isEmpty(stock)){
                    etDrinksStock.setError("Enter stock amount");
                    etDrinksStock.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(calories)){
                    etDrinksCalories.setError("Enter calories");
                    etDrinksCalories.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(picture)){
                    etDrinksPicture.setError("Enter picture URL");
                    etDrinksPicture.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(Doc)){
                    etDrinksDoc.setError("Enter Document name");
                    etDrinksDoc.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(price)){
                    etDrinksPrice.setError("Enter item price");
                    etDrinksPrice.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(Name)){
                    etDrinksName.setError("Enter Item Name");
                    etDrinksName.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(Description)){
                    etDrinksDescription.setError("Enter item description");
                    etDrinksDescription.requestFocus();
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
                db.collection("Drinks").document(Doc)
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
