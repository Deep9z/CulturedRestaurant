package com.harrowedtale.culturedrestaurant;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends FirestoreRecyclerAdapter<Order, OrderAdapter.OrderHolder> {

    /**
     *
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public OrderAdapter(@NonNull FirestoreRecyclerOptions<Order> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull OrderHolder  holder, int position, @NonNull Order model) {
        //holder.tvOrder.setText(model.getOrder());

        List<String> items = new ArrayList<>(model.getItems());

        String order = "";

        for (String item : items){
            order += " \n" + item;
        }

        holder.tvOrder.setText(order);

        holder.tvTableNumber.setText(model.getTableNumber());

        holder.tvCustomizations.setText(model.getCustomizations());


    }


    @NonNull
    @Override
    public OrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        return new OrderHolder(v);
    }



    public void UpdateStatus(int position) {
        getSnapshots().getSnapshot(position).getReference().update("OrderStatus", "Completed");

    }

    public class OrderHolder extends RecyclerView.ViewHolder {

        TextView tvOrder;
        TextView tvTableNumber;
        TextView tvCustomizations;


        public OrderHolder(View itemView) {
            super(itemView);
            tvOrder = itemView.findViewById(R.id.tv_order);
            tvTableNumber = itemView.findViewById(R.id.tv_order_table_number);
            tvCustomizations = itemView.findViewById(R.id.tv_customizations);
        }
    }

}
