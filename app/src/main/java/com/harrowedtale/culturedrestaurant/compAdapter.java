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

public class compAdapter extends FirestoreRecyclerAdapter<CompObj, compAdapter.CompHolder> {

    /**
     *
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public compAdapter(@NonNull FirestoreRecyclerOptions<CompObj> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull CompHolder holder, int position, @NonNull CompObj model) {


        holder.tvTableNumber.setText(Integer.toString(model.getTableNumber()));
        holder.tvTotal.setText(String.valueOf("Total = $" + model.getTotal()));

    }


    @NonNull
    @Override
    public CompHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comp_item, parent, false);
        return new CompHolder(v);
    }


    public class CompHolder extends RecyclerView.ViewHolder {

        TextView tvTotal;
        TextView tvTableNumber;



        public CompHolder(View itemView) {
            super(itemView);
            tvTotal = itemView.findViewById(R.id.tv_orderTotal);
            tvTableNumber = itemView.findViewById(R.id.tv_order_table_num);
        }
    }

}
