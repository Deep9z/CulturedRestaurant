package com.harrowedtale.culturedrestaurant;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;

import java.text.NumberFormat;
import java.util.Locale;



public class DisplayItemsActivity extends AppCompatActivity {

    RecyclerView itemsList;

    private FirebaseFirestore db;
    private FirestoreRecyclerAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    String category;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_items);

        category = getIntent().getStringExtra("Item Category");

        itemsList = findViewById(R.id.rvListItems);

        linearLayoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);

        itemsList.setLayoutManager(linearLayoutManager);
        db = FirebaseFirestore.getInstance();

        listItems();
    }

    private void listItems(){

        //TODO get collection name from previous activity so this class is more dynamic
        Query query = db.collection(category).whereGreaterThan("Stock", 0);

        FirestoreRecyclerOptions<MenuItem> response = new FirestoreRecyclerOptions.Builder<MenuItem>()
                .setQuery(query,MenuItem.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<MenuItem, MenuItemHolder>(response) {
            @Override
            protected void onBindViewHolder(MenuItemHolder holder, int position, final MenuItem model) {


                holder.itemName.setText(model.getName());

                //U.S. currency format
                NumberFormat form = NumberFormat.getCurrencyInstance(Locale.US);
                holder.itemPrice.setText((form.format(model.getPrice())));

                holder.itemCalories.setText(String.format("%s Calories",
                        String.valueOf(model.getCalories())));

                holder.itemDescription.setText(model.getDescription());
                Glide.with(getApplicationContext())
                        .load(model.getPicture())
                        .apply(new RequestOptions().centerCrop())
                        .into(holder.itemPicture);



                //push to add to cart
                holder.fabAddToCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(getApplicationContext(), String.format("Added %s to Cart",
                                model.getName()), Toast.LENGTH_SHORT).show();


                        //Adding item name and price to cart ArrayList from the MakeCart class
                        MakeCart.addItem(model.getName(), model.getPrice());

                        //todo testing
                    }
                });
            }


            @NonNull
            @Override
            public MenuItemHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.menu_item, viewGroup, false);
                return new MenuItemHolder(view);
            }

            @Override
            public void onError(FirebaseFirestoreException e) {
                Log.e("error", e.getMessage());
            }
        };

        adapter.notifyDataSetChanged();
        itemsList.setAdapter(adapter);
    }

    public class MenuItemHolder extends RecyclerView.ViewHolder{
        TextView itemName;
        TextView itemPrice;
        TextView itemCalories;
        TextView itemDescription;
        ImageView itemPicture;
        FloatingActionButton fabAddToCart;


        public MenuItemHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.tvItemName);
            itemPrice = itemView.findViewById(R.id.tvItemPrice);
            itemCalories = itemView.findViewById(R.id.tvItemCalories);
            itemDescription = itemView.findViewById(R.id.tvItemDescription);
            itemPicture = itemView.findViewById(R.id.civItemImage);
            fabAddToCart = itemView.findViewById(R.id.fabAddToCart);
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}
