package com.example.test;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.Database.Entities.Profile;

import java.util.List;


public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.ViewHolder> {


    private List<Profile> listOfProfiles;
    // This class hold the element inside the Layout, of every item created in this recycler view
    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView Surname_;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Surname_ = itemView.findViewById(R.id.ProfileAttribute);

        }

        public TextView getTextViewSurname(){return Surname_;}


    }
    public CustomRecyclerViewAdapter(List<Profile> listOfProfiles) {
        this.listOfProfiles = listOfProfiles;
    }



    @NonNull
    @Override
    public CustomRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_viewer,parent , false);

        return new ViewHolder(view);
    }

    // Binding the item to the holder,
    @Override
    public void onBindViewHolder(@NonNull CustomRecyclerViewAdapter.ViewHolder holder, int position) {

        // Setting the text on text viewer
        holder.getTextViewSurname().setText((position + ". " + listOfProfiles.get(position).ProfileSurname)+", "+listOfProfiles.get(position).ProfileName);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position_of_holder = holder.getLayoutPosition();

                Intent intent = new Intent(view.getContext(), ProfileInformationActivity.class);
                intent.putExtra("profile_id", listOfProfiles.get(position_of_holder).ProfileId);
                view.getContext().startActivity(intent);
            }
        });
    }


    // Length of list
    @Override
    public int getItemCount() {
        return listOfProfiles.size();
    }
}



