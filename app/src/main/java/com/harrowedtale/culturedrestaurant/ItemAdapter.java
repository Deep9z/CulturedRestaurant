package com.harrowedtale.culturedrestaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemAdapter extends FirestoreRecyclerAdapter<storeItem, ItemAdapter.ItemViewHolder> {


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     *
     */
    public Context context;
    private ArrayList<String> imageRec = new ArrayList<>();

    public FirebaseFirestore db = FirebaseFirestore.getInstance();//Point db to the root directory of the database
    CollectionReference orRef = db.collection("Orders");//Creates a document reference to the "Orders" collection
    DocumentReference test = db.collection("Orders").document("1");//WILL CHANGE "CART"

    public ItemAdapter(@NonNull FirestoreRecyclerOptions<storeItem> options, Context parent) {
        super(options);
        context = parent;
    }



    @Override
    protected void onBindViewHolder(@NonNull final ItemViewHolder holder, int position, @NonNull final storeItem model)
    {
        //get data from store item class
        final storeItem mod = model;
        holder.itemName.setText(mod.getName());
        holder.itemPrice.setText("$"+String.valueOf(mod.getPrice()));
        holder.itemDes.setText(mod.getDescription());
        //holder.image.setImageResource(mod.getPicture());
        Picasso.with(context).load(mod.getImage()).into(holder.image); //load string URL image into holder image view

        //when view is clicked on make toast with the item that was added to cart
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "Added to Cart", Toast.LENGTH_LONG).show();//."+mod.getName()+" "+mod.getPrice()+" "+mod.getDescription(), Toast.LENGTH_LONG).show();

                //Update fields in doc 1 orders collections
                test.update("Name", mod.getName());
                test.update("Price", mod.getPrice());
            }
        });

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v =LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_store_item, parent, false);
        return new ItemViewHolder(v);
    }
    //when it is touched method

    //

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        //public View view;
        TextView itemName;
        TextView itemPrice;
        TextView itemDes;
        LinearLayout linearLayout;
        ImageView image;


        public ItemViewHolder(View itemView) {
            //add to views
            super(itemView);
            itemName = itemView.findViewById(R.id.nameView);
            itemPrice = itemView.findViewById(R.id.priceView);
            itemDes = itemView.findViewById(R.id.desView);
            image = itemView.findViewById(R.id.image);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);



        }
    }

}