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

public class EditUser extends AppCompatActivity {


    TextInputEditText etDocumentName;
    TextInputEditText etClassification;
    TextInputEditText etID;
    TextInputEditText etName;
    TextInputEditText etPassword;
    Button bConfirm;


    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        etDocumentName = findViewById(R.id.etDocumentName);
        etClassification = findViewById(R.id.etClassification);
        etID = findViewById(R.id.etID);
        etName = findViewById(R.id.etName);
        etPassword = findViewById(R.id.etPassword);

        bConfirm = findViewById(R.id.bConfirm);

        bConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String documentName = etDocumentName.getText().toString();
                String Classification = etClassification.getText().toString();
                String id = etID.getText().toString();
                String Name = etName.getText().toString();
                String Password = etPassword.getText().toString();

                //Checking if fields are empty
                if(TextUtils.isEmpty(documentName)){
                    etDocumentName.setError("Enter Document Name");
                    etDocumentName.requestFocus();
                    return;
                }

                if(TextUtils.isEmpty(Classification)) {
                    etClassification.setError("Enter Classification");
                    etClassification.requestFocus();
                    return;
                }


                if (TextUtils.isEmpty(id)){
                    etID.setError("Enter User ID");
                    etID.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(Name)){
                    etName.setError("Enter User Name");
                    etName.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(Password)){
                    etPassword.setError("Enter User Password");
                    etPassword.requestFocus();
                    return;
                }

                Map<String,Object> user = new HashMap<>();

                //string id to int ID for firebase
                int ID = Integer.parseInt(id);

                user.put("ID",ID);
                user.put("Classification",Classification);
                user.put("Name",Name);
                user.put("Password",Password);

                //writes info to firestore, updates users fields if document name exists otherwise creates a new user
                db.collection("Users").document(documentName)
                        .set(user)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(),"User Collection Updated",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),"Error updating User",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });

                finish();

            }
        });
    }
}
