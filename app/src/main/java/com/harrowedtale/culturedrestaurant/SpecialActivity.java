package com.harrowedtale.culturedrestaurant;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class SpecialActivity extends AppCompatActivity {

    TextView Special;//textview to display special of the day
    ImageView Special_picture;
    public FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specials);
        db = FirebaseFirestore.getInstance();
        Special_picture = findViewById(R.id.special_picture);
        Special = findViewById(R.id.special_container);

        DocumentReference special = db.collection("Specials").document("Special of the Day");

        special.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot doc = task.getResult();
                StringBuilder fields = new StringBuilder();
                fields.append(doc.get("Name"));
                fields.append("\n").append(doc.get("Description"));
                fields.append("\nCalories: ").append(doc.get("Calories"));
                fields.append("\n$").append(doc.get("Price"));
                Special.setText(fields.toString());

                Glide.with(getApplicationContext())
                        .load(doc.get("Picture"))
                        .apply(new RequestOptions().centerCrop())
                        .into(Special_picture);
            }

        });


    }
}
