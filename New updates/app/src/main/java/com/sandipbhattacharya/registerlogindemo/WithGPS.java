package com.sandipbhattacharya.registerlogindemo;

import android.os.Bundle;
import android.util.Log;
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

public class WithGPS extends AppCompatActivity {
    String apiURL = "https://maps.googleapis.com/maps/api/distancematrix/json?origins="+FindStores.pos[0]+"%2C"+FindStores.pos[1]+"&destinations=59.3771276%2C13.4218131%7C59.3916196%2C13.5147869%7C59.3777293%2C13.5145273%7C59.4027675%2C13.5695857%7C59.4039056%2C13.5465732%7C59.38025%2C13.4979895%7C59.3994657%2C13.4939257%7C59.3808438%2C13.4685619%7C59.3961768%2C13.5736277%7C59.3928402%2C13.5148258%7C59.3850276%2C13.4613525%7C59.3764635%2C13.4264735%7C59.381922%2C13.5111035%7C59.4348012%2C13.4380523%7C59.3769894%2C13.4886377%7C59.4777719%2C13.5835949%7C59.3833827%2C13.4838114%7C59.3966059%2C13.5783006%7C59.3774266%2C13.506715%7C59.3878508%2C13.4797013%7C59.3868358%2C13.473497%7C59.3980399%2C13.5328977%7C&key=AIzaSyCL1ZwGhfwaU4RxoP1mw8fD5dpZMhBrOts";
    private RecyclerView recyclerView;
   // String apiURL = "https://shorturl.at/opFP3";
    public MyAdapterStores itemsAdapter;
    List<Store> stores;
    List<Store> distDestStores;
    String[] distances;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.withgps);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        stores = new ArrayList<Store>();
        distDestStores = new ArrayList<Store>();
        distances = new String[22];
        getDestAndDist();
      //  preAdapter();
    }

    public void preAdapter(){
        itemsAdapter = new MyAdapterStores(getApplicationContext(),stores);
        recyclerView.setAdapter(itemsAdapter);
    }

  private void getStores(String[] distances){
      stores.add(new Store("Coop Bergvik", "Hallviksvägen 7",distances[0], R.drawable.coop));
      stores.add(new Store("Coop Norrstrand", "Rudsvägen 13B",distances[1], R.drawable.coop));
      stores.add(new Store("Coop Herrhagen", "Stagnellsgatan 12A",distances[2], R.drawable.coop));
      stores.add(new Store("Coop Kronoparken", "Karlstad Kronoparkens C",distances[3], R.drawable.coop));
      stores.add(new Store("Coop Kroppkärr", "Karlstad, Kroppkärrsskolan",distances[4], R.drawable.coop));
      stores.add(new Store("Coop Karlstad", "Älvgatan 3",distances[5], R.drawable.coop));
      stores.add(new Store("Coop Råtorp", "Råtorps allé 4",distances[6], R.drawable.coop));
      stores.add(new Store("Coop Strand", "Montörsgatan 6",distances[7], R.drawable.coop));
      stores.add(new Store("Coop Välsviken", "Rävbergsvägen 13",distances[8], R.drawable.coop));
      stores.add(new Store("Ica Wallinders", "Drottning Kristinas väg 12A",distances[8], R.drawable.ica));
      stores.add(new Store("Ica Nära Kärnan", "Karmgatan 117A",distances[10], R.drawable.ica));
      stores.add(new Store("Ica Stormarknad Karlstad", "Frykmans väg 1",distances[11], R.drawable.ica));
      stores.add(new Store("Ica Hagahallen", "Trekantsgatan 4",distances[12], R.drawable.ica));
      stores.add(new Store("Ica Skåre", "Centrumvägen 5",distances[13], R.drawable.ica));
      stores.add(new Store("Ica Nära Viken", "Muraregatan 12",distances[14], R.drawable.ica));
      stores.add(new Store("Ica Nära Mossbergs", "Diamantvägen 12",distances[15], R.drawable.ica));
      stores.add(new Store("Ica Fanfaren", "Gustaf Anders gata 13",distances[16], R.drawable.ica));
      stores.add(new Store("Ica Välsviken", "Rävbergsvägen 6",distances[17], R.drawable.ica));
      stores.add(new Store("Willy:s Bryggudden", "Tolagsgatan 4A",distances[18], R.drawable.willys));
      stores.add(new Store("Willy:s Växnås", "Spärrgatan 4a",distances[19], R.drawable.willys));
      stores.add(new Store("Lidl Rattgatan", "Blockgatan 17",distances[20], R.drawable.lidl));
      stores.add(new Store("Lidl Östra Infarten", "Östra Infarten 19",distances[21], R.drawable.lidl));
  }
    private void getDestAndDist(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, apiURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject responseJSON = new JSONObject(response);
                  //  String destination = responseJSON.get("destination_addresses").toString();
                    JSONObject objectDist = responseJSON.getJSONArray("rows")
                            .getJSONObject(0);
                    JSONArray jsonarr = objectDist.getJSONArray("elements");

                    for(int i = 0; i < jsonarr.length(); i++){
                        JSONObject jsonobj= jsonarr.getJSONObject(i);
                        JSONObject jsondist = jsonobj.getJSONObject("distance");
                        String distance = jsondist.getString("text");
                        distances[i] = distance;
                    }
                    getStores(distances);
                    preAdapter();
                   // parseAddresses(destination, distances);

                }catch(Exception e){
                    Toast.makeText(WithGPS.this, e.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(WithGPS.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        });
        Toast.makeText(WithGPS.this, "Calculating distances...", Toast.LENGTH_LONG).show();
        Toast.makeText(WithGPS.this, "Calculating distances...", Toast.LENGTH_LONG).show();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

   /* private String clearAddress(String destination_addr){
        StringBuilder stringBuilderDestinationAddr = new StringBuilder();

        for (int i = 0; i < destination_addr.length(); i++)
            if (destination_addr.charAt(i) != '[' && destination_addr.charAt(i) != ']' && destination_addr.charAt(i) != '"')
                stringBuilderDestinationAddr.append(destination_addr.charAt(i));

        String strCleanDestination = stringBuilderDestinationAddr.toString();
        return strCleanDestination;
    }

    private void parseAddresses(String addr, String[] dist){
        StringBuilder strBldr = new StringBuilder();
        int z = 0;
        for (int i = 0; i < addr.length(); i++){
            if(addr.charAt(i) == ',' || addr.charAt(i) == ']'){
                if(addr.charAt(i-1) == '"') {
                    String destination = strBldr.toString();
                    destination = clearAddress(destination);
                    Store store = new Store(destination, dist[z]);
                    distDestStores.add(store);
                    z++;
                    strBldr.setLength(0);
                }
            }else{
                strBldr.append(addr.charAt(i));
            }
        }
    }   */
}
