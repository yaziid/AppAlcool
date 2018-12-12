package com.example.yazid.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class Calcul extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul);

        BottomNavigationView bottom = (BottomNavigationView)findViewById(R.id.navigationView);

        bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                switch (item.getItemId()){
                    case R.id.navigation_songs:
                        break;
                    case R.id.navigation_albums:
                        Intent intent2 = new Intent(Calcul.this, MainActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.navigation_artists: break;
                    default: break;
                }

                return false;
            }
        });
    }
}
