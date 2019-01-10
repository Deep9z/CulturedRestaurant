package com.harrowedtale.culturedrestaurant;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;


public class UserAdapter extends FirestoreRecyclerAdapter<User, UserAdapter.UserHolder> {

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public UserAdapter(@NonNull FirestoreRecyclerOptions<User> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull UserAdapter.UserHolder holder, int position, @NonNull User model) {

        holder.tvClassification.setText(model.getClassification());
        holder.tvID.setText(String.valueOf("ID " + model.getID()));
        holder.tvName.setText(model.getName());
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
            .inflate(R.layout.user_item, viewGroup, false);
        return new UserHolder(v);
    }

    public void deleteUser(int position) {

        //TODO testing
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    public class UserHolder extends RecyclerView.ViewHolder {

        TextView tvClassification;
        TextView tvID;
        TextView tvName;

        public UserHolder(@NonNull View itemView) {
            super(itemView);

            tvClassification = itemView.findViewById(R.id.tvClassification);
            tvID = itemView.findViewById(R.id.tvID);
            tvName  = itemView.findViewById(R.id.tvName);



        }
    }

}
