package com.sandipbhattacharya.registerlogindemo;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<Product> items;

    public MyAdapter(Context context, List<Product> items){
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.titleView.setText(items.get(position).getTitle());
        holder.campaignView.setText(items.get(position).getCampaign());
        holder.priceView.setText(String.valueOf(items.get(position).getPrice()) + " SEK");
        Picasso.get().load(items.get(position).getImage()).into(holder.imageView);
      //  Picasso.get().load(items.get(position).getImage()).into(holder.imageView);
        // Glide.with(mCtx).load(product.getImage()).into(mImageView);
       // holder.imageView.setImageResource(items.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
