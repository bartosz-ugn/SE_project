package com.sandipbhattacharya.registerlogindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import java.util.List;
import java.util.Map;

public class FavoriteStores2 extends AppCompatActivity {
    private CheckBox lidl1, lidl2,
            willys1,willys2,
            coop1,coop2,coop3,coop4,coop5,coop6,coop7,coop8,coop9,
            ica1,ica2,ica3,ica4,ica5,ica6,ica7,ica8,ica9;

    private List<CheckBox> checkBoxList;
    private int chid = 1000;
    static boolean PHPresponse = false;

    public boolean[] qwe;
    public ArrayList<Integer> checkboxIDs;
    public int boxIndex = 0;
    public ArrayList<Integer> availableStores;

    private Button result;
    private TextView showfav;
    private ArrayList<String> stores;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_stores2);

        qwe = new boolean[22];
        checkBoxList = new ArrayList<>();
        checkboxIDs = new ArrayList<>();
        availableStores = new ArrayList<>();

        coop1 = (CheckBox) findViewById(R.id.coopbergvik);  coop1.setId(++chid); checkBoxList.add(coop1);checkboxIDs.add(R.id.coopbergvik);
        coop2 = findViewById(R.id.coopnorrstrand);  coop2.setId(++chid); checkBoxList.add(coop2);checkboxIDs.add(R.id.coopnorrstrand);
        coop3 = findViewById(R.id.coopherrhagen); coop3.setId(++chid);  checkBoxList.add(coop3);checkboxIDs.add(R.id.coopherrhagen);
        coop4 = findViewById(R.id.coopkronoparken);  coop4.setId(++chid); checkBoxList.add(coop4);checkboxIDs.add(R.id.coopkronoparken);
        coop5 = findViewById(R.id.coopkroppkarr); checkBoxList.add(coop5); coop5.setId(++chid); checkboxIDs.add(R.id.coopkroppkarr);
        coop6 = findViewById(R.id.coopcity);  coop6.setId(++chid); checkBoxList.add(coop6);checkboxIDs.add(R.id.coopcity);
        coop7 = findViewById(R.id.coopratorp);  coop7.setId(++chid);checkBoxList.add(coop7); checkboxIDs.add(R.id.coopratorp);
        coop8 = findViewById(R.id.coopstrand); coop8.setId(++chid);  checkBoxList.add(coop8);checkboxIDs.add(R.id.coopstrand);
        coop9 = findViewById(R.id.coopvalsviken);  coop9.setId(++chid); checkBoxList.add(coop9);checkboxIDs.add(R.id.coopvalsviken);

        ica1 = findViewById(R.id.icawallinders); ica1.setId(++chid);checkBoxList.add(ica1);  checkboxIDs.add(R.id.icawallinders);
        ica2 = findViewById(R.id.icanarakarnan);  ica2.setId(++chid); checkBoxList.add(ica2);checkboxIDs.add(R.id.icanarakarnan);
        ica3 = findViewById(R.id.icamaxibergvik);  ica3.setId(++chid); checkBoxList.add(ica3);checkboxIDs.add(R.id.icamaxibergvik);
        ica4 = findViewById(R.id.icahagahallen); ica4.setId(++chid); checkBoxList.add(ica4); checkboxIDs.add(R.id.icahagahallen);
        ica5 = findViewById(R.id.icaskare);  ica5.setId(++chid);checkBoxList.add(ica5); checkboxIDs.add(R.id.icaskare);
        ica6 = findViewById(R.id.icanaraviken);  ica6.setId(++chid); checkBoxList.add(ica6);checkboxIDs.add(R.id.icanaraviken);
        ica7 = findViewById(R.id.icanaramossbergs);  ica7.setId(++chid);checkBoxList.add(ica7); checkboxIDs.add(R.id.icanaramossbergs);
        ica8 = findViewById(R.id.icafanfaren);  ica8.setId(++chid);checkBoxList.add(ica8); checkboxIDs.add(R.id.icafanfaren);
        ica9 = findViewById(R.id.icamaxivalsviken);  ica9.setId(++chid);checkBoxList.add(ica9); checkboxIDs.add(R.id.icamaxivalsviken);

        willys1 = findViewById(R.id.willysbryggudden);  willys1.setId(++chid);checkBoxList.add(willys1); checkboxIDs.add(R.id.willysbryggudden);
        willys2 = findViewById(R.id.willysvaxnas);  willys2.setId(++chid);checkBoxList.add(willys2); checkboxIDs.add(R.id.willysvaxnas);

        lidl1 = findViewById(R.id.lidlrattgatan);  lidl1.setId(++chid); checkBoxList.add(lidl1);checkboxIDs.add(R.id.lidlrattgatan);
        lidl2 = findViewById(R.id.lidlostrainfarten);  lidl2.setId(++chid);checkBoxList.add(lidl2); checkboxIDs.add(R.id.lidlostrainfarten);

        // Log.i("zzz", "coop1 id = " + coop1.getId());
        //  Log.i("zzz", "lidl2 id = " + lidl2.getId());
        result = findViewById(R.id.favsave);
        showfav = findViewById(R.id.showfav);
        stores = new ArrayList<>();
        showfav.setEnabled(false);

      /* for(int i = 0; i < checkboxIDs.size(); i++){
            // isInDB(checkBoxList.get(i).getId());
            isInDB((checkboxIDs.get(i)), checkBoxList.get(i).getId());
        }*/

     /*   for(int i = 0; i < 22; i++){
            if(isInDB(checkBoxList.get(i).getId())){
                Log.i("buttons", "id: " + checkBoxList.get(i).getId());
                checkBoxList.get(i).setChecked(true);
            }else{
                checkBoxList.get(i).setChecked(false);
            }
        }*/

     /*   if(isInDB(coop1.getId())){
            coop1.setChecked(true);
        }else{
            coop1.setChecked(false);
        }*/


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
                    stores.remove("Coop Bergvik");
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
                clearUserInfo();
                addToDB();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.setLength(0);
                for (String s : stores)
                    stringBuilder.append(s).append(", ").append("\n");

                showfav.setText(stringBuilder.toString());
                showfav.setEnabled(false);

            }
        });
        //   Log.i("buttons", "kurwa chuj" + isInDB(coop1.getId()));
        // setBoxes();
    }

   /*    private void setBoxes(){
            isInDB(coop1.getId());
            if(FavoriteStores.PHPresponse){
               coop1.setChecked(true);
               coop1.invalidate();
            }else{
               coop1.setChecked(false);
            }
           isInDB(coop2.getId());
           if(FavoriteStores.PHPresponse){
               coop2.setChecked(true);
               coop2.invalidate();
           }else{
               coop2.setChecked(false);
           }
           isInDB(coop3.getId());
           if(FavoriteStores.PHPresponse){
               coop3.setChecked(true);
               coop3.invalidate();
           }else{
               coop3.setChecked(false);
           }
           isInDB(coop4.getId());
           if(FavoriteStores.PHPresponse){
               coop4.setChecked(true);
               coop4.invalidate();
           }else{
               coop4.setChecked(false);
           }
           isInDB(coop5.getId());
           if(FavoriteStores.PHPresponse){
               coop5.setChecked(true);
               coop5.invalidate();
           }else{
               coop5.setChecked(false);
           }
           isInDB(coop6.getId());
           if(FavoriteStores.PHPresponse){
               coop6.setChecked(true);
               coop6.invalidate();
           }else{
               coop6.setChecked(false);
           }
           isInDB(coop7.getId());
           if(FavoriteStores.PHPresponse){
               coop7.setChecked(true);
               coop7.invalidate();
           }else{
               coop7.setChecked(false);
           }
           isInDB(coop8.getId());
           if(FavoriteStores.PHPresponse){
               coop8.setChecked(true);
               coop8.invalidate();
           }else{
               coop8.setChecked(false);
           }
           isInDB(coop9.getId());
           if(FavoriteStores.PHPresponse){
               coop9.setChecked(true);
               coop9.invalidate();
           }else{
               coop9.setChecked(false);
           }

       }*/


    private void isInDB(Integer id, Integer myID) {
        Log.i("buttons", "findbyviewid sent = " + id);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://169.254.65.225/login/isInDB.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("success")) {
                    Log.i("buttons", "success11");
                    //    FavoriteStores.PHPresponse = true;
                    //   CheckBox simpleCheckBox = (CheckBox) findViewById(id);
                    //    simpleCheckBox.setChecked(true);
                    //  simpleCheckBox.invalidate();
                } else{
                    Log.i("buttons", "failure11");
                    //   CheckBox simpleCheckBox = (CheckBox) findViewById(id);
                    Log.i("buttons", "failure22");
                    // simpleCheckBox.setChecked(false);
                    // simpleCheckBox.invalidate();
                    Log.i("buttons", "failure33");
                    //  FavoriteStores.PHPresponse = false;
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(FavoriteStores2.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("storetag",String.valueOf(myID));
                Log.i("buttons", "String.valueOf(id) " + String.valueOf(myID));
                data.put("username",MainActivity.usern);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

        Log.i("buttons", "before return");

        // return FavoriteStores.PHPresponse;
    }

    public void addToDB(){

        for(int i = 0; i < stores.size(); i++){
            addFavorite(stores.get(i));
        }
    }

    public void clearUserInfo(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://169.254.65.225/login/clearUserInfo.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("success")) {
                    Toast.makeText(FavoriteStores2.this, "Updating...", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(FavoriteStores2.this, response, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(FavoriteStores2.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(FavoriteStores2.this, "Favorite stores updated!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(FavoriteStores2.this, response, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(FavoriteStores2.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
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
