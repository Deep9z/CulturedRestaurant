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

public class UpdateSpecials extends AppCompatActivity {

    TextInputEditText etspecialDescription;//edit text for item description
    TextInputEditText etspecialName;//edit text of item name
    TextInputEditText etspecialPrice;//text for price of item
    TextInputEditText etspecialCalories;//calories
    TextInputEditText etspecialPicture;
    TextInputEditText etspecialStock;
    Button updatespecialConfirm;


    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_specials);

        etspecialPrice = findViewById(R.id.etspecialPrice);
        etspecialDescription = findViewById(R.id.etspecialDescription);
        etspecialName = findViewById(R.id.etspecialName);
        etspecialCalories = findViewById(R.id.etspecialCalories);
        etspecialPicture = findViewById(R.id.etspecialPicture);
        etspecialStock = findViewById(R.id.etspecialStock);
        updatespecialConfirm = findViewById(R.id.updatespecialConfirm);

        updatespecialConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stock = etspecialStock.getText().toString();
                String picture = etspecialPicture.getText().toString();
                String calories = etspecialCalories.getText().toString();
                String price = etspecialPrice.getText().toString();
                String Name = etspecialName.getText().toString();
                String Description = etspecialDescription.getText().toString();

                //Checking if fields are empty
                if(TextUtils.isEmpty(stock)){
                    etspecialStock.setError("Enter stock amount");
                    etspecialStock.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(calories)){
                    etspecialCalories.setError("Enter calories");
                    etspecialCalories.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(picture)){
                    etspecialPicture.setError("Enter picture URL");
                    etspecialPicture.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(price)){
                    etspecialPrice.setError("Enter item price");
                    etspecialPrice.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(Name)){
                    etspecialName.setError("Enter Item Name");
                    etspecialName.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(Description)){
                    etspecialDescription.setError("Enter item description");
                    etspecialDescription.requestFocus();
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
                db.collection("Specials").document("Special of the Day")
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
