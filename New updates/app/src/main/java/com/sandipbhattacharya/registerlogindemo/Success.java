package com.sandipbhattacharya.registerlogindemo;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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


public class Success extends AppCompatActivity {
    //Connection to database
    private static final String BASE_URL = "http://169.254.65.225/login/getProducts.php";
    private List<Product> products;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    private RecyclerView.Adapter mAdapter;

    List<Product> items;
    ListView lvProduct;
   // String[] products = {"Product1", "Product2"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.success);
        //getting the recyclerview from xml
        recyclerView = findViewById(R.id.recyclerview);
        // recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        items = new ArrayList<Product>();
      //  items.add(new Product("https://se.cat-ret.assets.lidl/catalog5media/se/article/82031/xs/82031.jpg", "Isbergsallad", "xd", 666));

        getProducts2();
        //recyclerView.setAdapter(new MyAdapter(getApplicationContext(),items));


    }
    private void getProducts2(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(Success.this, response, Toast.LENGTH_SHORT).show();
                try{
                    JSONArray array = new JSONArray(response);
                    for(int i = 0; i < array.length(); i++){
                        JSONObject object = array.getJSONObject(i);
                        String image = object.getString("image");
                        String title = object.getString("title");
                        String campaign = object.getString("campaign");
                        Integer price = object.getInt("price");
                        if(!image.contains("https")){
                           image = ("https:").concat(image);
                        }
                        Product product = new Product(image, title, campaign, price);
                        items.add(product);
                    }
                }catch(Exception e){

                }


                recyclerView.setAdapter(new MyAdapter(getApplicationContext(),items));


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Success.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }


    private void getProducts(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(Success.this, response, Toast.LENGTH_SHORT).show();
                try{
                    JSONArray array = new JSONArray(response);
                    for(int i = 0; i < array.length(); i++){
                        JSONObject object = array.getJSONObject(i);
                        String image = object.getString("image");
                        String title = object.getString("title");
                        String campaign = object.getString("campaign");
                        Integer price = object.getInt("price");

                        Product product = new Product(image, title, campaign, price);
                        products.add(product);
                    }
                }catch(Exception e){

                }





            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Success.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
