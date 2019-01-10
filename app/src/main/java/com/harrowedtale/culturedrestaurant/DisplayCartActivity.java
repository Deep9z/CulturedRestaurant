package com.harrowedtale.culturedrestaurant;



import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DisplayCartActivity extends AppCompatActivity  {

    public RecyclerView rvCart;
    public RecyclerView.LayoutManager rvLayoutManager;
    private CartAdapter rvAdapter;

    TextView tvTotal;
    Button bPlaceOrder;
    Button bCustomize;
    Button bConfirmCustom;
    EditText etCustomizations;

    String customizations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_cart);

        Intent intent = getIntent();
        final String IDString = intent.getStringExtra("TableNumber");
        final String ToGoString = intent.getStringExtra("ToGo");


        //calculating total cost
        double total = 0;
        ArrayList<Double> tempHolder = MakeCart.getAllPrices();
        for (int i = 0; i < tempHolder.size(); i++){
            total += tempHolder.get(i);
        }

        tvTotal = findViewById(R.id.tvTotal);
        bPlaceOrder = findViewById(R.id.bPlaceOrder);
        bCustomize = findViewById(R.id.bCustomize);
        etCustomizations = findViewById(R.id.etCustomize);
        bConfirmCustom = findViewById(R.id.bConfirmCustom);



        if(MakeCart.getAllItems().size() == 0)
        {
            Toast.makeText(getApplicationContext(), "Nothing in Cart", Toast.LENGTH_SHORT).show();
            bPlaceOrder.setVisibility(View.INVISIBLE);
            bCustomize.setVisibility(View.INVISIBLE);
        }

        //RecylerView Stuff
        rvCart = findViewById(R.id.rvCart);
        rvLayoutManager = new LinearLayoutManager(this);
        rvCart.setHasFixedSize(true);
        rvAdapter = new CartAdapter(MakeCart.getAllItems(), MakeCart.getAllPrices());
        rvCart.setLayoutManager(rvLayoutManager);
        rvCart.setAdapter(rvAdapter);


        //for removing items from cart
        rvAdapter.setOnItemClickListener(new CartAdapter.OnItemClickListener() {

            @Override
            public void onDeleteClick(int position) {
                MakeCart.ItemNames.remove(position);
                MakeCart.ItemPrices.remove(position);
                rvAdapter.notifyItemChanged(position);
                double total = 0;
                ArrayList<Double> tempHolder = MakeCart.getAllPrices();
                for (int i = 0; i < tempHolder.size(); i++){
                    total += tempHolder.get(i);
                }

                if(MakeCart.getAllItems().size() == 0){
                    bPlaceOrder.setVisibility(View.INVISIBLE);
                    bCustomize.setVisibility(View.INVISIBLE);
                }

                //formatting total cost
                NumberFormat nf_us = NumberFormat.getCurrencyInstance(Locale.US);
                tvTotal.setText(String.format("Total %s", nf_us.format(total)));

            }
        });

        //formatting total cost
        NumberFormat nf_us = NumberFormat.getCurrencyInstance(Locale.US);
        tvTotal.setText(String.format("Total %s", nf_us.format(total)));

        bCustomize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                etCustomizations.setVisibility(View.VISIBLE);
                bConfirmCustom.setVisibility(View.VISIBLE);
                bPlaceOrder.setVisibility(View.INVISIBLE);
                bCustomize.setVisibility(View.INVISIBLE);


                bConfirmCustom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        etCustomizations.setVisibility(View.INVISIBLE);
                        bConfirmCustom.setVisibility(View.INVISIBLE);
                        bPlaceOrder.setVisibility(View.VISIBLE);
                        bCustomize.setVisibility(View.VISIBLE);
                    }
                });
            }
        });

        bPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customizations =  etCustomizations.getText().toString();

                FirebaseFirestore db = FirebaseFirestore.getInstance();

                //Putting the document here allows for a premade document name, rather than a randomly generated one
                DocumentReference test = db.collection("Orders").document(IDString);

                Map<String,Object> cartItem = new HashMap<>();

                cartItem.put("Items",MakeCart.getAllItems());
                cartItem.put("Prices", MakeCart.getAllPrices());
                cartItem.put("Customizations", customizations);
                cartItem.put("TableNumber", IDString);
                cartItem.put("ToGo", ToGoString);
                cartItem.put("OrderStatus", "Not Completed");

                test.set(cartItem).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"Your Order Has Been Placed",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(DisplayCartActivity.this, "Error Placing Order", Toast.LENGTH_SHORT).show();
                    }
                });

                //clearing cart items
                MakeCart.getAllItems().clear();

                finish();

            }
        });
    }
}
