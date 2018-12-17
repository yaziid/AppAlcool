package com.example.yazid.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.common.util.Strings;

import java.util.ArrayList;

public class Calcul extends AppCompatActivity {
    private ArrayList<String> nom_bieres = new ArrayList<>();
    private ArrayList<String> prix_bieres = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul);

        BottomNavigationView bottom = (BottomNavigationView)findViewById(R.id.navigationView);

        bottom.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener(){
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

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final RecyclerViewAdapter adapter = new RecyclerViewAdapter(nom_bieres, prix_bieres, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button bouton = (Button)findViewById(R.id.button);
        final EditText nom_biere = (EditText)findViewById(R.id.nom_biere);
        final EditText prix_biere = (EditText)findViewById(R.id.prix_biere);
        final EditText alcool_biere = (EditText)findViewById(R.id.alcool_biere);
        final EditText quantite_biere = (EditText)findViewById(R.id.quantite_biere);

        bouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Strings.isEmptyOrWhitespace(nom_biere.getText().toString()) &&
                        !Strings.isEmptyOrWhitespace(prix_biere.getText().toString()) &&
                        !Strings.isEmptyOrWhitespace(alcool_biere.getText().toString()) &&
                        !Strings.isEmptyOrWhitespace(quantite_biere.getText().toString())){
                    nom_bieres.add(nom_biere.getText().toString());

                    float rapport = CalculerRapportEuroCentilitre(Float.parseFloat(prix_biere.
                                    getText().toString()),
                            Float.parseFloat(alcool_biere.getText().toString()),
                            Float.parseFloat(quantite_biere.getText().toString()));

                    prix_bieres.add(Float.toString(rapport) + " euros/cl");

                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private float CalculerRapportEuroCentilitre(float prix_biere, float alcool_biere,
                                                float quantite_biere){
        float quantite_ethanol = (alcool_biere * quantite_biere) / 100.0f;
        float rapport = prix_biere / quantite_ethanol;

        return rapport;

    }
}
