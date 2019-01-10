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

public class UpdateLactose extends AppCompatActivity {

    TextInputEditText etlactoseDoc;//edit text to get name of document
    TextInputEditText etlactoseDescription;//edit text for item description
    TextInputEditText etlactoseName;//edit text of item name
    TextInputEditText etlactosePrice;//text for price of item
    TextInputEditText etlactoseCalories;//calories
    TextInputEditText etlactosePicture;
    TextInputEditText etlactoseStock;
    Button updatelactoseConfirm;


    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_lactose);

        etlactosePrice = findViewById(R.id.etlactosePrice);
        etlactoseDescription = findViewById(R.id.etlactoseDescription);
        etlactoseName = findViewById(R.id.etlactoseName);
        etlactoseDoc = findViewById(R.id.etlactoseDoc);
        etlactoseCalories = findViewById(R.id.etlactoseCalories);
        etlactosePicture = findViewById(R.id.etlactosePicture);
        etlactoseStock = findViewById(R.id.etlactoseStock);
        updatelactoseConfirm = findViewById(R.id.updatelactoseConfirm);

        updatelactoseConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stock = etlactoseStock.getText().toString();
                String picture = etlactosePicture.getText().toString();
                String calories = etlactoseCalories.getText().toString();
                String price = etlactosePrice.getText().toString();
                String Name = etlactoseName.getText().toString();
                String Description = etlactoseDescription.getText().toString();
                String Doc = etlactoseDoc.getText().toString();

                //Checking if fields are empty
                if(TextUtils.isEmpty(stock)){
                    etlactoseStock.setError("Enter stock amount");
                    etlactoseStock.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(calories)){
                    etlactoseCalories.setError("Enter calories");
                    etlactoseCalories.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(picture)){
                    etlactosePicture.setError("Enter picture URL");
                    etlactosePicture.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(Doc)){
                    etlactoseDoc.setError("Enter Document name");
                    etlactoseDoc.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(price)){
                    etlactosePrice.setError("Enter item price");
                    etlactosePrice.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(Name)){
                    etlactoseName.setError("Enter Item Name");
                    etlactoseName.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(Description)){
                    etlactoseDescription.setError("Enter item description");
                    etlactoseDescription.requestFocus();
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
                db.collection("LactoseIntolerant").document(Doc)
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
