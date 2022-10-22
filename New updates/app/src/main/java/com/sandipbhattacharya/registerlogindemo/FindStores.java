package com.sandipbhattacharya.registerlogindemo;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient;
import com.google.maps.android.SphericalUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FindStores extends AppCompatActivity {

    private TextView AddressText;
    private Button LocationButton;
    private LocationRequest locationRequest;
    List<Store> stores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findstores);

        stores = new ArrayList<>();

        AddressText = findViewById(R.id.addressText);
        LocationButton = findViewById(R.id.locationButton);

       // locationRequest = LocationRequest.create();
      //  locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
       // locationRequest.setInterval(5000);
       // locationRequest.setFastestInterval(2000);

        LocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getStores();
              //  stores.add(new Store("dest", "distance"));
               // Toast.makeText(FindStores.this, stores.get(0).getDestination(), Toast.LENGTH_SHORT).show();
              //  TextView tv1 = (TextView)findViewById(R.id.addressText);
              // tv1.setText(stores.get(0).getDistance());
              //  Toast.makeText(FindStores.this, stores.get(0).getDistance(), Toast.LENGTH_SHORT).show();
              //  getCurrentLocation();

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                if (isGPSEnabled()) {
                    getCurrentLocation();
                }else{
                    turnOnGPS();
                }
            }
            if (grantResults[0] == PackageManager.PERMISSION_DENIED){
                Intent intent = new Intent(FindStores.this, Success.class);
                startActivity(intent);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                getCurrentLocation();
            }
        }
    }

    private void getCurrentLocation() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(FindStores.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                if (isGPSEnabled()) {
                    LocationServices.getFusedLocationProviderClient(FindStores.this)
                            .requestLocationUpdates(locationRequest, new LocationCallback() {
                                @Override
                                public void onLocationResult(@NonNull LocationResult locationResult) {
                                    super.onLocationResult(locationResult);
                                    LocationServices.getFusedLocationProviderClient(FindStores.this)
                                            .removeLocationUpdates(this);
                                    if (locationResult != null && locationResult.getLocations().size() >0){
                                        int index = locationResult.getLocations().size() - 1;
                                        double latitude = locationResult.getLocations().get(index).getLatitude();
                                        double longitude = locationResult.getLocations().get(index).getLongitude();

                                        //float distance[] = new float[1];
                                        //Location.distanceBetween(59.4063335, 13.578511, 59.4047052,13.5762343, distance);
                                        //https://maps.googleapis.com/maps/api/distancematrix/json?origins=&destinations=&key=AIzaSyBNvIUPvD106wLBO9sbA_-D4pdm1L7Ky4Q
                                        //https://maps.googleapis.com/maps/api/distancematrix/json?origins=59.4063335%2C13.578511&destinations=59.4047052%2C13.5762343&key=AIzaSyCL1ZwGhfwaU4RxoP1mw8fD5dpZMhBrOts

                                        AddressText.setText(String.format("Position:\n" + latitude + "\n" + longitude));
                                        //Location.distanceBetween(59.4063335, 13.578511, 59.4047052,13.5762343, distance);
                                    }
                                }
                            }, Looper.getMainLooper());
                }
                else {
                    turnOnGPS();
                }
            } else {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }

    private void turnOnGPS() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(getApplicationContext())
                .checkLocationSettings(builder.build());

        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {

                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                    Toast.makeText(FindStores.this, "GPS is already turned on", Toast.LENGTH_SHORT).show();

                } catch (ApiException e) {

                    switch (e.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                            try {
                                ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                                resolvableApiException.startResolutionForResult(FindStores.this, 2);
                            } catch (IntentSender.SendIntentException ex) {
                                ex.printStackTrace();
                            }
                            break;

                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            //Device does not have location
                            break;
                    }
                }
            }
        });

    }

    private boolean isGPSEnabled() {
        LocationManager locationManager = null;
        boolean isEnabled = false;

        if (locationManager == null) {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        }

        isEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isEnabled;

    }

    private void getStores(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://maps.googleapis.com/maps/api/distancematrix/json?origins=59.406399%2C13.582588&destinations=59.3771276%2C13.4218131%7C59.3916196%2C13.5147869%7C59.3777293%2C13.5145273%7C59.4027675%2C13.5695857%7C59.4039056%2C13.5465732%7C59.38025%2C13.4979895%7C59.3994657%2C13.4939257%7C59.3808438%2C13.4685619%7C59.3961768%2C13.5736277%7C59.3928402%2C13.5148258%7C59.3850276%2C13.4613525%7C59.3764635%2C13.4264735%7C59.381922%2C13.5111035%7C59.4348012%2C13.4380523%7C59.3769894%2C13.4886377%7C59.4777719%2C13.5835949%7C59.3833827%2C13.4838114%7C59.3966059%2C13.5783006%7C59.3774266%2C13.506715%7C59.3878508%2C13.4797013%7C59.3868358%2C13.473497%7C59.3980399%2C13.5328977%7C&key=AIzaSyCL1ZwGhfwaU4RxoP1mw8fD5dpZMhBrOts", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject responseJSON = new JSONObject(response);
                    //get distance
                    JSONObject objectDist = responseJSON.getJSONArray("rows")
                            .getJSONObject(0)
                            .getJSONArray("elements")
                            .getJSONObject(0)
                            .getJSONObject("distance");
                    String distance = objectDist.getString("text");
                    //get destination
                    String destination = responseJSON.get("destination_addresses").toString();
                    //function that removes [, ], "
                    destination = clearAddress(destination);
                    Toast.makeText(FindStores.this, destination, Toast.LENGTH_SHORT).show();
                    //add store to the list
                    Store store = new Store(destination, distance);
                    stores.add(store);
                }catch(Exception e){
                    Toast.makeText(FindStores.this, "ERROR!!!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(FindStores.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private String clearAddress(String destination_addr){
        StringBuilder stringBuilderDestinationAddr = new StringBuilder();

        for (int i = 0; i < destination_addr.length(); i++)
            if (destination_addr.charAt(i) != '[' && destination_addr.charAt(i) != ']' && destination_addr.charAt(i) != '"')
                stringBuilderDestinationAddr.append(destination_addr.charAt(i));

        String strCleanDestination = stringBuilderDestinationAddr.toString();
        return strCleanDestination;
    }
}
