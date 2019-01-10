package com.harrowedtale.culturedrestaurant;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.Map;
public class UpdateAppetizers extends AppCompatActivity {

    TextInputEditText etDoc;//edit text to get name of document
    TextInputEditText etDescription;//edit text for item description
    TextInputEditText etItemName;//edit text of item name
    TextInputEditText etPrice;//text for price of item
    TextInputEditText etCalories;//calories
    TextInputEditText etPicture;
    TextInputEditText etStock;
    Button updateItemConfirm;


    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateappetizer);

        etPrice = findViewById(R.id.etPrice);
        etDescription = findViewById(R.id.etDescription);
        etItemName = findViewById(R.id.etitemName);
        etDoc = findViewById(R.id.etDoc);
        etCalories = findViewById(R.id.etCalories);
        etPicture = findViewById(R.id.etPicture);
        etStock = findViewById(R.id.etStock);
        updateItemConfirm = findViewById(R.id.updateItemConfirm);

        updateItemConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stock = etStock.getText().toString();
                String picture = etPicture.getText().toString();
                String calories = etCalories.getText().toString();
                String price = etPrice.getText().toString();
                String Name = etItemName.getText().toString();
                String Description = etDescription.getText().toString();
                String Doc = etDoc.getText().toString();

                //Checking if fields are empty
                if(TextUtils.isEmpty(stock)){
                    etStock.setError("Enter stock amount");
                    etStock.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(calories)){
                    etCalories.setError("Enter calories");
                    etCalories.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(picture)){
                    etPicture.setError("Enter picture URL");
                    etPicture.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(Doc)){
                    etDoc.setError("Enter Document name");
                    etDoc.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(price)){
                    etPrice.setError("Enter item price");
                    etPrice.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(Name)){
                    etItemName.setError("Enter Item Name");
                    etItemName.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(Description)){
                    etDescription.setError("Enter item description");
                    etDescription.requestFocus();
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
                db.collection("Appetizers").document(Doc)
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
