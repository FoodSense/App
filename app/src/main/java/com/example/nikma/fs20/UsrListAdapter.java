package com.example.nikma.fs20;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class UsrListAdapter extends RecyclerView.Adapter<UsrListAdapter.ViewHolder>{
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view   = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_view_layout,parent,false);
        return new ViewHolder(view);
    }

    public List<User> usrList;

    public UsrListAdapter(List<User> usrList){
        this.usrList = usrList;

    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.usrName.setText(usrList.get(position).getusrName());
       // holder.usrAcc.setChecked(usrList.get(position).getusrAcc());

    }

    @Override
    public int getItemCount() {
        return usrList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View mView;

        public TextView usrName;
        public Switch usrAcc;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            usrName = (TextView) mView.findViewById(R.id.usrNameView);
            usrAcc = (Switch) mView.findViewById(R.id.register_user);

        }
    }
}

