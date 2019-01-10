package com.harrowedtale.culturedrestaurant;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

public class SplitTipsActivity extends AppCompatActivity {

    public FirebaseFirestore db = FirebaseFirestore.getInstance();//Point db to the root directory of the database
    CollectionReference mUserReference = db.collection("Users");//Creates a document reference to the "Users" collection

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_tips);

        final TextView waitStaff1;
        final TextView waitStaff2;
        final TextView waitStaff3;

        waitStaff1 = findViewById(R.id.etWaitStaff1);
        waitStaff2 = findViewById(R.id.etWaitStaff2);
        waitStaff3 = findViewById(R.id.etWaitStaff3);

        Button apply = findViewById(R.id.bSetPercent);

        //Creates variable that will create a reference to the specified username, which is used to login (For example, <username: Customer06>, which is from the database
        DocumentReference waitstaff1Doc = db.collection("Users").document("waitStaff01");
        DocumentReference waitstaff2Doc = db.collection("Users").document("waitStaff02");
        DocumentReference waitstaff3Doc = db.collection("Users").document("waitStaff03");

        waitstaff1Doc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String waitStaff1Name = documentSnapshot.getString("Name");
                waitStaff1.setText(waitStaff1Name);
            }
        });

        waitstaff2Doc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String waitStaff2Name = documentSnapshot.getString("Name");
                waitStaff2.setText(waitStaff2Name);
            }
        });

        waitstaff3Doc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String waitStaff3Name = documentSnapshot.getString("Name");
                waitStaff3.setText(waitStaff3Name);
            }
        });

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText percent1 = findViewById(R.id.etPercent1);
                EditText percent2 = findViewById(R.id.etPercent2);
                EditText percent3 = findViewById(R.id.etPercent3);

                //Create variables to store the user-inputted text from the username and password fields
                final String percent1Hint = percent1.getText().toString();
                final String percent2Hint = percent2.getText().toString();
                final String percent3Hint = percent3.getText().toString();

                percent1.setHint(percent1Hint + "%");
                percent2.setHint(percent2Hint + "%");
                percent3.setHint(percent3Hint + "%");

                Toast.makeText(getApplicationContext(), "New tip percentage applied", Toast.LENGTH_SHORT).show();

            }
        });




    }
}
