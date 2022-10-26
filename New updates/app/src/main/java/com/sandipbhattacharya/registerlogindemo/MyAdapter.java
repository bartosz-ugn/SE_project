package com.sandipbhattacharya.registerlogindemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    public Context context;
    public List<Product> items = new ArrayList<>();

    public void setFilteredList(List<Product> filteredList) {
        this.items = filteredList;
        notifyDataSetChanged();
    }

    public MyAdapter(Context context, List<Product> items) {
        this.context = context;
        this.items = items;
        //  this.getProductListFilter = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.titleView.setText(items.get(position).getTitle());
        holder.campaignView.setText(items.get(position).getCampaign());
        holder.priceView.setText(String.valueOf(items.get(position).getPrice()) + " SEK");
        holder.storeView.setText(items.get(position).getStore());
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
   /* @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                if(charSequence == null || charSequence.length() == 0){
                    filterResults.values = getProductListFilter;
                    filterResults.count = getProductListFilter.size();
                }else{
                    String searchStr = charSequence.toString().toLowerCase();
                    List<Product> items = new ArrayList<>();
                    for(Product product: getProductListFilter){
                        if(product.getTitle().toLowerCase().contains(searchStr)){
                            items.add(product);
                        }

                    }

                    filterResults.values = items;
                    filterResults.count = items.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                items = (List<Product>) filterResults.values;
                notifyDataSetChanged();
            }
        };
        return null;
    }*/

