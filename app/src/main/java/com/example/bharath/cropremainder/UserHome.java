package com.example.bharath.cropremainder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class UserHome extends AppCompatActivity {

    Button search, logout, crop_search;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), UserHome.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        search = (Button) findViewById(R.id.search);
        logout = (Button) findViewById(R.id.logout);
        crop_search = (Button) findViewById(R.id.crop_search);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        crop_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchCrop.class);
                startActivity(intent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserSearch.class);
                startActivity(intent);
            }
        });

    }
}
