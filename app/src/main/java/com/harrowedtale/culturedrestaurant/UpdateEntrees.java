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

public class UpdateEntrees extends AppCompatActivity {

    TextInputEditText etentreeDoc;//edit text to get name of document
    TextInputEditText etentreeDescription;//edit text for item description
    TextInputEditText etentreeName;//edit text of item name
    TextInputEditText etentreePrice;//text for price of item
    TextInputEditText etentreeCalories;//calories
    TextInputEditText etentreePicture;
    TextInputEditText etentreeStock;
    Button updateEntreeConfirm;


    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateentree);

        etentreePrice = findViewById(R.id.etentreePrice);
        etentreeDescription = findViewById(R.id.etentreeDescription);
        etentreeName = findViewById(R.id.etentreeName);
        etentreeDoc = findViewById(R.id.etentreeDoc);
        etentreeCalories = findViewById(R.id.etentreeCalories);
        etentreePicture = findViewById(R.id.etentreePicture);
        etentreeStock = findViewById(R.id.etentreeStock);
        updateEntreeConfirm = findViewById(R.id.updateEntreeConfirm);

        updateEntreeConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stock = etentreeStock.getText().toString();
                String picture = etentreePicture.getText().toString();
                String calories = etentreeCalories.getText().toString();
                String price = etentreePrice.getText().toString();
                String Name = etentreeName.getText().toString();
                String Description = etentreeDescription.getText().toString();
                String Doc = etentreeDoc.getText().toString();

                //Checking if fields are empty
                if(TextUtils.isEmpty(stock)){
                    etentreeStock.setError("Enter stock amount");
                    etentreeStock.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(calories)){
                    etentreeCalories.setError("Enter calories");
                    etentreeCalories.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(picture)){
                    etentreePicture.setError("Enter picture URL");
                    etentreePicture.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(Doc)){
                    etentreeDoc.setError("Enter Document name");
                    etentreeDoc.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(price)){
                    etentreePrice.setError("Enter item price");
                    etentreePrice.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(Name)){
                    etentreeName.setError("Enter Item Name");
                    etentreeName.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(Description)){
                    etentreeDescription.setError("Enter item description");
                    etentreeDescription.requestFocus();
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
                db.collection("Entrees").document(Doc)
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
