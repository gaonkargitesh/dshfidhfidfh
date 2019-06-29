package com.example.dshfidhfidfh;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    ArrayList<Users> userlist;
    Context context;

    public UserAdapter(ArrayList<Users> userlist, Context context) {
        this.userlist = userlist;
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.useritems,parent,false);
        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserViewHolder userViewHolder, int i) {

        Users users=userlist.get(i);
        userViewHolder.nameitem.setText(userlist.get(userViewHolder.getAdapterPosition()).getName());
        userViewHolder.hobbyitem.setText(userlist.get(userViewHolder.getAdapterPosition()).getHobby());
        userViewHolder.genderitem.setText(userlist.get(userViewHolder.getAdapterPosition()).getGender());

    }
    public void setCreateComplaints(ArrayList<Users> userlist) {
        this.userlist = userlist;
        notifyDataSetChanged();
    }

    public void clearCollection() {
        if (userlist != null) {
            userlist.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        TextView nameitem,genderitem,hobbyitem;


        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            nameitem=itemView.findViewById(R.id.nameitem);
            genderitem=itemView.findViewById(R.id.genderitem);
            hobbyitem=itemView.findViewById(R.id.hobbyitem);
        }
    }

}
