package com.sandipbhattacharya.registerlogindemo;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WithoutGPS extends AppCompatActivity {
    private RecyclerView recyclerView;
    public MyAdapterStores itemsAdapter;
    List<Store> stores;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.withoutgps);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        stores = new ArrayList<Store>();
        getStores();
        preAdapter();
    }

    public void preAdapter(){
        itemsAdapter = new MyAdapterStores(getApplicationContext(),stores);
        recyclerView.setAdapter(itemsAdapter);
    }

  private void getStores(){
      stores.add(new Store("Coop Bergvik", "Hallviksvägen 7", R.drawable.coop));
      stores.add(new Store("Coop Norrstrand", "Rudsvägen 13B", R.drawable.coop));
      stores.add(new Store("Coop Herrhagen", "Stagnellsgatan 12A", R.drawable.coop));
      stores.add(new Store("Coop Kronoparken", "Karlstad Kronoparkens C", R.drawable.coop));
      stores.add(new Store("Coop Kroppkärr", "Karlstad, Kroppkärrsskolan", R.drawable.coop));
      stores.add(new Store("Coop Karlstad", "Älvgatan 3", R.drawable.coop));
      stores.add(new Store("Coop Råtorp", "Råtorps allé 4", R.drawable.coop));
      stores.add(new Store("Coop Strand", "Montörsgatan 6", R.drawable.coop));
      stores.add(new Store("Coop Välsviken", "Rävbergsvägen 13", R.drawable.coop));
      stores.add(new Store("Ica Wallinders", "Drottning Kristinas väg 12A", R.drawable.ica));
      stores.add(new Store("Ica Nära Kärnan", "Karmgatan 117A", R.drawable.ica));
      stores.add(new Store("Ica Stormarknad Karlstad", "Frykmans väg 1", R.drawable.ica));
      stores.add(new Store("Ica Hagahallen", "Trekantsgatan 4", R.drawable.ica));
      stores.add(new Store("Ica Skåre", "Centrumvägen 5", R.drawable.ica));
      stores.add(new Store("Ica Nära Viken", "Muraregatan 12", R.drawable.ica));
      stores.add(new Store("Ica Nära Mossbergs", "Diamantvägen 12", R.drawable.ica));
      stores.add(new Store("Ica Fanfaren", "Gustaf Anders gata 13", R.drawable.ica));
      stores.add(new Store("Ica Välsviken", "Rävbergsvägen 6", R.drawable.ica));
      stores.add(new Store("Willy:s Bryggudden", "Tolagsgatan 4A", R.drawable.willys));
      stores.add(new Store("Willy:s Växnås", "Spärrgatan 4a", R.drawable.willys));
      stores.add(new Store("Lidl Rattgatan", "Blockgatan 17", R.drawable.lidl));
      stores.add(new Store("Lidl Östra Infarten", "Östra Infarten 19", R.drawable.lidl));
  }

}
