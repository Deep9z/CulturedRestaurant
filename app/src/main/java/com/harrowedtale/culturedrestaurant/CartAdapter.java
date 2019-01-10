package com.harrowedtale.culturedrestaurant;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;



import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    ArrayList<String> itemName;
    ArrayList<Double> itemPrice;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public CartAdapter(ArrayList<String> itemName, ArrayList<Double> itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewtype) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_item, viewGroup, false);
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder( CartAdapter.ViewHolder viewHolder, int position) {

        viewHolder.tvItemName.setText(itemName.get(position));

        //U.S. currency format
        NumberFormat form = NumberFormat.getCurrencyInstance(Locale.US);
        viewHolder.tvItemPrice.setText(form.format(itemPrice.get(position)));
    }



    @Override
    public int getItemCount() {
        return itemName.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvItemName;
        public TextView tvItemPrice;
        public Button bRemoveCart;

        public ViewHolder(@NonNull final View itemView, final OnItemClickListener listener) {
            super(itemView);

            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvItemPrice = itemView.findViewById(R.id.tvItemPrice);
            bRemoveCart = itemView.findViewById(R.id.bRemoveCart);

            bRemoveCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }


    }


}
