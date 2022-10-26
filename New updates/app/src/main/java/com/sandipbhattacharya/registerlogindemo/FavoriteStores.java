package com.sandipbhattacharya.registerlogindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FavoriteStores extends AppCompatActivity {
    private CheckBox lidl1, lidl2,
            willys1,willys2,
            coop1,coop2,coop3,coop4,coop5,coop6,coop7,coop8,coop9,
            ica1,ica2,ica3,ica4,ica5,ica6,ica7,ica8,ica9;

    private Button result;
    private TextView showfav;
    private ArrayList<String> stores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_stores);

        lidl1 = findViewById(R.id.lidlrattgatan);
        lidl2 = findViewById(R.id.lidlostrainfarten);

        willys1 = findViewById(R.id.willysbryggudden);
        willys2 = findViewById(R.id.willysvaxnas);

        coop1 = findViewById(R.id.coopbergvik);
        coop2 = findViewById(R.id.coopnorrstrand);
        coop3 = findViewById(R.id.coopherrhagen);
        coop4 = findViewById(R.id.coopkronoparken);
        coop5 = findViewById(R.id.coopkroppkarr);
        coop6 = findViewById(R.id.coopcity);
        coop7 = findViewById(R.id.coopratorp);
        coop8 = findViewById(R.id.coopstrand);
        coop9 = findViewById(R.id.coopvalsviken);

        ica1 = findViewById(R.id.icawallinders);
        ica2 = findViewById(R.id.icanarakarnan);
        ica3 = findViewById(R.id.icamaxibergvik);
        ica4 = findViewById(R.id.icahagahallen);
        ica5 = findViewById(R.id.icaskare);
        ica6 = findViewById(R.id.icanaraviken);
        ica7 = findViewById(R.id.icanaramossbergs);
        ica8 = findViewById(R.id.icafanfaren);
        ica9 = findViewById(R.id.icamaxivalsviken);


        result = findViewById(R.id.favsave);
        showfav = findViewById(R.id.showfav);
        stores = new ArrayList<>();
        showfav.setEnabled(false);


        lidl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lidl1.isChecked())
                    stores.add("Lidl Rattgatan");
                else
                    stores.remove("Lidl Rattgatan");
            }
        });

        lidl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lidl2.isChecked())
                    stores.add("Lidl Östrainfarten");
                else
                    stores.remove("Lidl Östrainfarten");
            }
        });


        willys1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (willys1.isChecked())
                    stores.add("Willys Bryggudden");
                else
                    stores.remove("Willys Bryggudden");
            }
        });

        willys2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (willys2.isChecked())
                    stores.add("Willys Våxnäs");
                else
                    stores.remove("Willys Våxnäs");
            }
        });


        coop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (coop1.isChecked())
                    stores.add("Coop Bergvik");
                else
                    stores.remove("Stora Coop Bergvik");
            }
        });


        coop2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (coop2.isChecked())
                    stores.add("Coop Norrstrand");
                else
                    stores.remove("Coop Norrstrand");
            }
        });

        coop3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (coop3.isChecked())
                    stores.add("Coop Herrhagen");
                else
                    stores.remove("Coop Herrhagen");
            }
        });

        coop4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (coop4.isChecked())
                    stores.add("Coop Kronoparken");
                else
                    stores.remove("Coop Kronoparken");
            }
        });

        coop5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (coop5.isChecked())
                    stores.add("Coop Kroppkärr");
                else
                    stores.remove("Coop Kroppkärr");
            }
        });

        coop6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (coop6.isChecked())
                    stores.add("Coop City");
                else
                    stores.remove("Coop City");
            }
        });

        coop7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (coop7.isChecked())
                    stores.add("Coop Råtorp");
                else
                    stores.remove("Coop Råtorp");
            }
        });

        coop8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (coop8.isChecked())
                    stores.add("Coop Strand");
                else
                    stores.remove("Coop Strand");
            }
        });

        coop9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (coop9.isChecked())
                    stores.add("Stora Coop Välsviken");
                else
                    stores.remove("Stora Coop Välsviken");
            }
        });


        ica1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ica1.isChecked())
                    stores.add("Ica Wallinders");
                else
                    stores.remove("Ica Wallinders");
            }
        });


        ica2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ica2.isChecked())
                    stores.add("Ica Nära Kärnan");
                else
                    stores.remove("Ica Nära Kärnan");
            }
        });

        ica3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ica3.isChecked())
                    stores.add("Ica Maxi Bergvik");
                else
                    stores.remove("Ica Maxi Bergvik");
            }
        });

        ica4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ica4.isChecked())
                    stores.add("Ica Hagahallen");
                else
                    stores.remove("Ica Hagahallen");
            }
        });

        ica5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ica5.isChecked())
                    stores.add("Ica Skåre");
                else
                    stores.remove("Ica Skåre");
            }
        });

        ica6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ica6.isChecked())
                    stores.add("Ica Nära Viken");
                else
                    stores.remove("Ica Nära Viken");
            }
        });

        ica7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ica7.isChecked())
                    stores.add("Ica Nära Mossbergs");
                else
                    stores.remove("Ica Nära Mossbergs");
            }
        });

        ica8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ica8.isChecked())
                    stores.add("Ica Fanfaren");
                else
                    stores.remove("Ica Maxi Fanfaren");
            }
        });


        ica9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ica9.isChecked())
                    stores.add("Ica Maxi Välsviken");
                else
                    stores.remove("Ica Maxi Välsviken");
            }
        });


        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToDB();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.setLength(0);
                for (String s : stores)
                    stringBuilder.append(s).append(", ").append("\t");

                showfav.setText(stringBuilder.toString());
                showfav.setEnabled(false);

            }
        });
       }

    public void addToDB(){
        clearUserInfo();
        for(int i = 0; i < stores.size(); i++){
            addFavorite(stores.get(i));
        }
    }

    public void clearUserInfo(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://169.254.65.225/login/clearUserInfo.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("success")) {
                    Toast.makeText(FavoriteStores.this, "Updating...", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(FavoriteStores.this, response, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(FavoriteStores.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("username", MainActivity.usern);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    public void addFavorite(String storename) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://169.254.65.225/login/setFavorite.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("res", response);
                    if (response.equals("success")) {
                        Toast.makeText(FavoriteStores.this, "Favorite stores updated!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(FavoriteStores.this, response, Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(FavoriteStores.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("storename", storename);
                    data.put("username", MainActivity.usern);
                    return data;
                }
            };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

}
