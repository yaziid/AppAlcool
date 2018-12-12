package com.example.yazid.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.yazid.myapplication.MelService.ENDPOINT;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottom = (BottomNavigationView)findViewById(R.id.navigationView);

        bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                switch (item.getItemId()){
                    case R.id.navigation_songs:
                        Intent intent = new Intent(MainActivity.this, Calcul.class);
                        startActivity(intent);
                        break;
                    case R.id.navigation_albums: break;
                    case R.id.navigation_artists: break;
                    default: break;
                }

                return false;
            }
        });



        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(ENDPOINT)
                        .addConverterFactory(
                                GsonConverterFactory.create()
                        );

        Retrofit retrofit =
                builder
                        .client(
                                httpClient.build()
                        )
                        .build();

        MelService client =  retrofit.create(MelService.class);

        Call<Nhits> call = client.listRecords("base-sirene", "LILLE", "Débits de boissons");


        call.enqueue(new Callback<Nhits>() {
            @Override
            public void onResponse(Call<Nhits> call, Response<Nhits> response) {
                TextView text = (TextView)findViewById(R.id.hello);
                //call.execute().body().getNhits()

                text.setText((CharSequence) response.body().getNhits());

            }

            @Override
            public void onFailure(Call<Nhits> call, Throwable t) {

            }
        });
    }
}
