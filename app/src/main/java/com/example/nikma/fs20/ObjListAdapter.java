package com.example.nikma.fs20;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ObjListAdapter extends RecyclerView.Adapter<ObjListAdapter.ViewHolder>{
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view   = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_view_layout,parent,false);
        return new ViewHolder(view);
    }

    public List<Object> objList;

    public ObjListAdapter(List<Object> objList){
        this.objList = objList;

    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.nameText.setText(objList.get(position).getObjName());
        holder.dtsText.setText(objList.get(position).getObjDTS());
        holder.weightText.setText(objList.get(position).getObjWeight());
    }

    @Override
    public int getItemCount() {
        return objList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View mView;

        public TextView nameText;
        public TextView dtsText;
        public TextView weightText;
        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            nameText = (TextView) mView.findViewById(R.id.textView1);
            dtsText = (TextView) mView.findViewById(R.id.textView2);
            weightText = (TextView) mView.findViewById(R.id.textView3);
        }
    }
}
