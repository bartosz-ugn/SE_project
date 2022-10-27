package com.sandipbhattacharya.registerlogindemo;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Success2 extends AppCompatActivity {

    private Button offers_button;
    private Button findstores_button;
    private Button signout_button;
    private Button faq_button;
    private Button favorite_button;
    private Button english;

    private boolean icaUpdate = false;
    private boolean coopUpdate = false;
    private boolean willysUpdate = false;
    private boolean lidlUpdate = false;
    private boolean other = false;
    TextView user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success2);
        user=findViewById(R.id.showuserinfo);
        Bundle extras = getIntent().getExtras();
        if(extras!=null)
        {
            user.setText(Success.user_email);
        }

        offers_button = findViewById(R.id.offers_button);
        findstores_button = findViewById(R.id.findstores_button);
        signout_button = findViewById(R.id.signout_button);
        faq_button = findViewById(R.id.faq_button);
        favorite_button = findViewById(R.id.favoritestores_button);
        english=findViewById(R.id.language);

        if(icaUpdate || coopUpdate || willysUpdate || lidlUpdate) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel("my notification", "my notification", NotificationManager.IMPORTANCE_DEFAULT);
                NotificationManager manager = getSystemService(NotificationManager.class);
                manager.createNotificationChannel(channel);
            }

            NotificationCompat.Builder builder = new NotificationCompat.Builder(Success2.this, "my notification");
            builder.setContentTitle("Check out new offers!");
            String stores = "";
            if (icaUpdate) {
                stores = stores.concat("ICA ");
                other = true;
            }
            if (coopUpdate) {
                if(other)
                    stores = stores.concat(", COOP ");
                else {
                    stores = stores.concat("COOP ");
                    other = true;
                }
            }
            if (willysUpdate) {
                if(other)
                    stores = stores.concat(", WILLY'S ");
                else {
                    stores = stores.concat("WILLY'S ");
                    other = true;
                }
            }
            if (lidlUpdate) {
                if(other)
                    stores = stores.concat(", LIDL ");
                else
                    stores = stores.concat("LIDL ");

            }
            builder.setContentText("New offers available in: " + stores);
            builder.setSmallIcon(R.drawable.ic_launcher_background);
            builder.setAutoCancel(true);

            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(Success2.this);
            managerCompat.notify(1, builder.build());
        }

        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEnglish();
            }
        });

        offers_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSearchOffers();
            }
        });

        findstores_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFindStores();
            }
        });

        signout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                openSignOut();
            }
        });
        faq_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFAQ();
            }
        });
        favorite_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openFavoriteStores();
            }
        });
    }

    public void openEnglish(){
        Intent intent = new Intent(Success2.this, Success.class);
        startActivity(intent);
    }

    public void openSearchOffers(){
        Intent intent = new Intent(Success2.this, SearchOffers2.class);
        startActivity(intent);
    }

    public void openFindStores(){
        Intent intent = new Intent(Success2.this, FindStores2.class);
        startActivity(intent);
    }
    public void openSignOut(){
        Intent intent = new Intent(Success2.this, MainActivity.class);
        startActivity(intent);
    }
    public void openFAQ(){
        Intent intent = new Intent(Success2.this, FAQ2.class);
        startActivity(intent);
    }
    public void openFavoriteStores(){
        Intent intent = new Intent(Success2.this, FavoriteStores2.class);
        startActivity(intent);
    }
}
