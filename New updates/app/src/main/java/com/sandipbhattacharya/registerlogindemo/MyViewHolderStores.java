package com.sandipbhattacharya.registerlogindemo;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolderStores extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView titleView, addressView, distanceView;

    public MyViewHolderStores(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
        titleView = itemView.findViewById(R.id.titleView);
        addressView = itemView.findViewById(R.id.addressView);
        distanceView = itemView.findViewById(R.id.distanceView);
    }

}