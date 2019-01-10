package com.harrowedtale.culturedrestaurant;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class RequestAdapter extends FirestoreRecyclerAdapter<Request, RequestAdapter.RequestHolder> {



    /**
     *
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public RequestAdapter(@NonNull FirestoreRecyclerOptions<Request> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull RequestHolder holder, int position, @NonNull Request model) {

        holder.tvRequestType.setText(model.getType());
        holder.tvRequestItem.setText(model.getItem());
        holder.tvTableNumber.setText(String.valueOf(model.getTableNumber()));
    }

    @NonNull
    @Override
    public RequestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_item, parent, false);
        return new RequestHolder(v);
    }

    //deletes request(request filled)
    public void deleteRequest(int position) {
        getSnapshots().getSnapshot(position).getReference().delete();

    }
    public class RequestHolder extends RecyclerView.ViewHolder {


        TextView tvRequestType;
        TextView tvRequestItem;
        TextView tvTableNumber;



        public RequestHolder(View itemView) {
            super(itemView);
            tvRequestType = itemView.findViewById(R.id.tv_request_type);
            tvRequestItem = itemView.findViewById(R.id.tv_request_item);
            tvTableNumber = itemView.findViewById(R.id.tv_table_number);
        }
    }

}