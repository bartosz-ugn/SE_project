package com.sandipbhattacharya.registerlogindemo;

import android.os.Bundle;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Success extends AppCompatActivity {

    private Button offers_button;
    private Button findstores_button;
    private Button signout_button;
    private Button faq_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success);

        offers_button = findViewById(R.id.offers_button);
        findstores_button = findViewById(R.id.findstores_button);
        signout_button = findViewById(R.id.signout_button);
        faq_button = findViewById(R.id.faq_button);

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
}
