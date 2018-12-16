package com.example.yazid.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private ArrayList<String> nomBieres = new ArrayList<>();
    private ArrayList<String> prixBieres = new ArrayList<>();
    private Context context;

    public RecyclerViewAdapter(ArrayList<String> nomBieres, ArrayList<String> prixBieres, Context context) {
        this.nomBieres = nomBieres;
        this.prixBieres = prixBieres;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vue = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);

        return new ViewHolder(vue);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.biere.setText(nomBieres.get(position));
        holder.prix.setText(prixBieres.get(position));
    }

    @Override
    public int getItemCount() {
        return nomBieres.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView biere;
        TextView prix;
        RelativeLayout layout;

        public ViewHolder(View itemView) {
            super(itemView);
            biere = itemView.findViewById(R.id.Biere_view);
            prix = itemView.findViewById(R.id.prix_view);
            layout = itemView.findViewById(R.id.relative);
        }
    }
}
