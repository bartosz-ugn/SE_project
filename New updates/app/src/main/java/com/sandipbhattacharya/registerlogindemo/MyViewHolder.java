package com.sandipbhattacharya.registerlogindemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView titleView, campaignView, priceView;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
        titleView = itemView.findViewById(R.id.titleView);
        campaignView = itemView.findViewById(R.id.campaignView);
        priceView = itemView.findViewById(R.id.priceView);
    }

}