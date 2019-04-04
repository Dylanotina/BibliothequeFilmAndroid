package com.example.e174828f.projet_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Activite_AutreRecherche extends AppCompatActivity {

    private Button pop;
    private Button vote;
    private Button comming;
    private Button discover;
    private Button annuler;
    private Button partitre;
    private EditText titre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autre_recherche);

        pop = findViewById(R.id.pop);
        vote = findViewById(R.id.top_rated);
        comming = findViewById(R.id.A_venir);
        discover = findViewById(R.id.decouvrir);
        annuler = findViewById(R.id.retour);
        partitre = findViewById(R.id.partitre);
        titre = findViewById(R.id.Titre);

        pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Activite_Liste.class);
                i.putExtra("recherche", 2);
                startActivity(i);
            }
        });

        vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Activite_Liste.class);
                i.putExtra("recherche", 3);
                startActivity(i);
            }
        });

        comming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Activite_Liste.class);
                i.putExtra("recherche", 4);
                startActivity(i);
            }
        });

        discover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Activite_Liste.class);
                i.putExtra("recherche", 5);
                startActivity(i);
            }
        });

        partitre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Activite_Liste.class);
                i.putExtra("recherche", 6);
                if(!titre.getText().toString().isEmpty()){
                    i.putExtra("titre", titre.getText().toString());
                }
                startActivity(i);
            }
        });
        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
