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

public class Success extends AppCompatActivity {

    private Button offers_button;
    private Button findstores_button;
    private Button signout_button;
    private Button faq_button;
    private Button favorite_button;
    private Button english;

    TextView user;
    public static String user_email;
    int flag = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success);

        user = findViewById(R.id.showuserinfo);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (flag == -1) {
                user_email = extras.getString("email");
                flag = 1;
            }
            if (flag == 1) {
                user.setText(Success.user_email);
            }
        }

        offers_button = findViewById(R.id.offers_button);
        findstores_button = findViewById(R.id.findstores_button);
        signout_button = findViewById(R.id.signout_button);
        faq_button = findViewById(R.id.faq_button);
        favorite_button = findViewById(R.id.favoritestores_button);
        english = findViewById(R.id.language);

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
    public void openEnglish () {
        Intent intent = new Intent(Success.this, Success2.class);
        startActivity(intent);
    }

    public void openSearchOffers(){
        Intent intent = new Intent(Success.this, SearchOffers.class);
        startActivity(intent);
    }

    public void openFindStores(){
        Intent intent = new Intent(Success.this, FindStores.class);
        startActivity(intent);
    }
    public void openSignOut(){
        Intent intent = new Intent(Success.this, MainActivity.class);
        startActivity(intent);
    }
    public void openFAQ(){
        Intent intent = new Intent(Success.this, FAQ.class);
        startActivity(intent);
    }
    public void openFavoriteStores(){
        Intent intent = new Intent(Success.this, FavoriteStores.class);
        startActivity(intent);
    }
}
