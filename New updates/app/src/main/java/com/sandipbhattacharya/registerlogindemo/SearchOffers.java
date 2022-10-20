package com.sandipbhattacharya.registerlogindemo;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.toolbox.StringRequest;

import java.text.DateFormatSymbols;
import java.util.zip.DataFormatException;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class SearchOffers extends AppCompatActivity {
    //Connection to database
    private static final String BASE_URL = "http://169.254.199.252/login/getProducts.php";
   // private List<Product> products;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    private RecyclerView.Adapter mAdapter;
    public MyAdapter itemsAdapter;

    ArrayAdapter arrayAdapter;

    ListView searchListView;
    List<Product> items;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchoffers);
        //getting the recyclerview from xml
        recyclerView = findViewById(R.id.recyclerview);
        // recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        items = new ArrayList<Product>();

        getProducts();



    }

    public void preAdapter(){
        itemsAdapter = new MyAdapter(getApplicationContext(),items);
        recyclerView.setAdapter(itemsAdapter);
    }


      @Override
  public boolean onCreateOptionsMenu(Menu menu) {

      getMenuInflater().inflate(R.menu.menu, menu);
      MenuItem menuItem = menu.findItem(R.id.searchView);
      SearchView searchView = (SearchView) menuItem.getActionView();
      searchView.setQueryHint("Type here to search");

      searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

          @Override
          public boolean onQueryTextSubmit(String s) {
              return false;
          }

          @Override
          public boolean onQueryTextChange(String s) {
              filterList(s);
              //String searchStr = s;
            //  itemsAdapter.getFilter().filter(s);
              return false;
          }
      });

      return super.onCreateOptionsMenu(menu);
  }

    private void filterList(String text) {
        List<Product> filteredList = new ArrayList<>();
        for(Product product : items){
            if(product.getTitle().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(product);
            }
        }
        if(filteredList.isEmpty()){
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }else{
            itemsAdapter.setFilteredList(filteredList);
        }
     }

    private void getProducts(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try{
                    JSONArray array = new JSONArray(response);
                    for(int i = 0; i < array.length(); i++){
                        JSONObject object = array.getJSONObject(i);
                        String image = object.getString("image");
                        String title = object.getString("title");
                        String campaign = object.getString("campaign");
                        String store = object.getString("store");
                        Integer price = object.getInt("price");
                        if(!image.contains("https")){
                            image = ("https:").concat(image);
                        }
                        Product product = new Product(image, title, campaign, price, store);
                        items.add(product);
                    }
                }catch(Exception e){

                }

               //  arrayAdapter = new ArrayAdapter<Product>(getApplicationContext(), R.layout.item_view);
                //  searchListView.setAdapter(arrayAdapter);
                preAdapter();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SearchOffers.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

}
