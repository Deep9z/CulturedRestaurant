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

public class UpdateKidsEntrees extends AppCompatActivity {

    TextInputEditText etkidsentreesDoc;//edit text to get name of document
    TextInputEditText etkidsentreesDescription;//edit text for item description
    TextInputEditText etkidsentreesName;//edit text of item name
    TextInputEditText etkidsentreesPrice;//text for price of item
    TextInputEditText etkidsentreesCalories;//calories
    TextInputEditText etkidsentreesPicture;
    TextInputEditText etkidsentreesStock;
    Button updatekidsentreesConfirm;


    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_kidsentrees);

        etkidsentreesPrice = findViewById(R.id.etkidsentreesPrice);
        etkidsentreesDescription = findViewById(R.id.etkidsentreesDescription);
        etkidsentreesName = findViewById(R.id.etkidsentreesName);
        etkidsentreesDoc = findViewById(R.id.etkidsentreesDoc);
        etkidsentreesCalories = findViewById(R.id.etkidsentreesCalories);
        etkidsentreesPicture = findViewById(R.id.etkidsentreesPicture);
        etkidsentreesStock = findViewById(R.id.etkidsentreesStock);
        updatekidsentreesConfirm = findViewById(R.id.updatekidsentreesConfirm);

        updatekidsentreesConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stock = etkidsentreesStock.getText().toString();
                String picture = etkidsentreesPicture.getText().toString();
                String calories = etkidsentreesCalories.getText().toString();
                String price = etkidsentreesPrice.getText().toString();
                String Name = etkidsentreesName.getText().toString();
                String Description = etkidsentreesDescription.getText().toString();
                String Doc = etkidsentreesDoc.getText().toString();

                //Checking if fields are empty
                if(TextUtils.isEmpty(stock)){
                    etkidsentreesStock.setError("Enter stock amount");
                    etkidsentreesStock.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(calories)){
                    etkidsentreesCalories.setError("Enter calories");
                    etkidsentreesCalories.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(picture)){
                    etkidsentreesPicture.setError("Enter picture URL");
                    etkidsentreesPicture.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(Doc)){
                    etkidsentreesDoc.setError("Enter Document name");
                    etkidsentreesDoc.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(price)){
                    etkidsentreesPrice.setError("Enter item price");
                    etkidsentreesPrice.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(Name)){
                    etkidsentreesName.setError("Enter Item Name");
                    etkidsentreesName.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(Description)){
                    etkidsentreesDescription.setError("Enter item description");
                    etkidsentreesDescription.requestFocus();
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
                db.collection("KidsEntrees").document(Doc)
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
