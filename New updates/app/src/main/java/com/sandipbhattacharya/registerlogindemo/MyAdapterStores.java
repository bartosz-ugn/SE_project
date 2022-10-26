package com.sandipbhattacharya.registerlogindemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterStores extends RecyclerView.Adapter<MyViewHolderStores> {

    public Context context;
    public List<Store> items = new ArrayList<>();

    public MyAdapterStores(Context context, List<Store> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolderStores onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolderStores(LayoutInflater.from(context).inflate(R.layout.store_view, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolderStores holder, int position) {
        holder.titleView.setText(items.get(position).getName());
        holder.addressView.setText(items.get(position).getAddress());
        holder.imageView.setImageResource(items.get(position).getImage());
        holder.distanceView.setText(items.get(position).getDistance());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}


