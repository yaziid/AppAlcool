package com.example.yazid.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.yazid.myapplication.MelService.ENDPOINT;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottom = (BottomNavigationView)findViewById(R.id.navigationView);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


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
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

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

        Call<EnsembleEtab> call = client.listRecords("base-sirene", "LILLE", "Débits de boissons");


        call.enqueue(new Callback<EnsembleEtab>() {
            @Override
            public void onResponse(Call<EnsembleEtab> call, Response<EnsembleEtab> response) {
                for(Etab etab: response.body().getRecords()){
                    if(etab.getGeometry() != null) {
                        LatLng loc = new LatLng(etab.getGeometry().getCoordinates().get(1), etab.getGeometry().getCoordinates().get(0));
                        googleMap.addMarker(new MarkerOptions().position(loc)
                                .title(etab.getFields().getL2_normalisee()));
                    }
                }

                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(50.6333, 3.0667E00), 10));

            }

            @Override
            public void onFailure(Call<EnsembleEtab> call, Throwable t) {

            }
        });
    }
}
