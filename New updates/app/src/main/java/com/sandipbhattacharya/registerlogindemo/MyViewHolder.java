package com.sandipbhattacharya.registerlogindemo;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView titleView, campaignView, priceView, storeView;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
        titleView = itemView.findViewById(R.id.titleView);
        campaignView = itemView.findViewById(R.id.campaignView);
        priceView = itemView.findViewById(R.id.priceView);
        storeView = itemView.findViewById(R.id.storeView);
    }

}