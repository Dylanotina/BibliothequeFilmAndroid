package com.example.e174828f.projet_android;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.LoginFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.CompletionInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.common.collect.*;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity {

    public static final String API_KEY = "6f24e995a9146dc661b833c2a79481b5";

    private Button rechercher;
    private AutoCompleteTextView nomEnt;
    private EditText dateFilm;
    private SeekBar nbNombre;
    private TextView nb;
    private List<Genre> genre;
    private List<Company> c1;
    private ListGenre listGenre;
    private ListCompany listCompany;
    private Button autreRecherche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialisation des Items
        rechercher = findViewById(R.id.rechercher);
        nomEnt = findViewById(R.id.nomEnt);
        dateFilm = findViewById(R.id.dateFilm);
        nbNombre = findViewById(R.id.nbsearch);
        nb = findViewById(R.id.nbfilm);
        nb.setText(valueOf(nbNombre.getProgress()));

        //Initialisation des ArrayList
        final List<String> spinnerArray =  new ArrayList<String>();
        final List<String> spinnerCompany = new ArrayList<String>();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GetMovieDataService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        final GetMovieDataService getMovieDataService = retrofit.create(GetMovieDataService.class);


        //On appelle une méthode de l'interface getMovieDataService
        Call<ListGenre> call = getMovieDataService.getGenre();
        //On rajoute dans le spinner une Premier Item Pas de Selection
        spinnerArray.add("Pas de Selection");


        call.enqueue(new Callback<ListGenre>() {
            @Override
            //A la reponse
            public void onResponse(Call<ListGenre>call, Response<ListGenre> response) {
                //On récupère le JSON dans le body
                listGenre = response.body();

                //On déplace la réponse dans une List de genre
                genre = listGenre.getAllElement();
                //On parcours la Liste
                for (int i = 0; i<genre.size(); i++) {
                    //On rajoute dans la liste les genres
                    spinnerArray.add(genre.get(i).getName());
                }
            }

            @Override
            public void onFailure(Call<ListGenre> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getMessage());
            }
        });

        //Adapter de la liste de genre
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner sItem = findViewById(R.id.spinner);
        sItem.setAdapter(adapter);

        //On indique que l'on veut que la liste se "montre" a partir d'un seul caractère
        nomEnt.setThreshold(1);
        //On rajoute une valeur de base
        spinnerCompany.add("Pas de Selection");

        //AutoComplétion du champs text
        nomEnt.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {
           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {


           }

           @Override
           //après chaque completion,
           public void afterTextChanged(Editable s) {

               //Si le text n'est pas vide
               if(!s.toString().isEmpty()) {
                    //On récupère une url et une méthode depuis une interface
                    Call<ListCompany> callCompany = getMovieDataService.getCompany(ImmutableMap.of("api_key", "34d79476b86de9146c6f439a4b34c68d", "query", s.toString()));


                    callCompany.enqueue(new Callback<ListCompany>() {
                        @Override
                        //A la réponse
                        public void onResponse(Call<ListCompany> call, Response<ListCompany> response) {
                            //On clear le spinner de base
                            spinnerCompany.clear();
                            //On récupère le body de la réponse (JSON)
                            listCompany = response.body();
                            //On met met le Json dans une liste de company
                            c1 = listCompany.getAllElement();
                            //On parcours la liste
                            for (int i = 0; i<c1.size(); i++) {
                                //On rajoute au spinner l'item
                                spinnerCompany.add(c1.get(i).getName());
                            }
                            ArrayAdapter nomCompany = new ArrayAdapter(getBaseContext(), R.layout.my_spinner, spinnerCompany);
                            nomEnt.setAdapter(nomCompany);
                        }
                        @Override
                        public void onFailure(Call<ListCompany> call, Throwable t) {
                            Log.d("TAG", "onFailure: " + t.getMessage());
                        }
                    });
               }
           }
        });




        nbNombre.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress == 0){
                    progress = 1;
                }

                //Recupération du nombre de film entre 1 et 20
                nb.setText(valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        // Initialisation du bouton Autre Recherche
        autreRecherche = findViewById(R.id.Autrerecherche);

        //Autre recherche
        autreRecherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Activite_AutreRecherche.class);
                startActivity(intent);
            }
        });



        rechercher.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //S'il manque une donnée (Entreprise, Genre, Date)
                if( (listGenre.getElement(sItem.getSelectedItem().toString()) == null)
                        || (dateFilm.getText().toString().isEmpty())
                        || listCompany.getElement(nomEnt.getText().toString()) == null) {

                    //Creation de la pop up
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setCancelable(true);
                    builder.setTitle("Champs Manquant");
                    builder.setMessage("Veuillez bien vérifier tous les champs saisis");
                    builder.setPositiveButton("ok",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }

                else {

                    //Récupération de l'id du genre entré
                    int idgenre =  listGenre.getElement(sItem.getSelectedItem().toString()).getId();
//                  Log.d("ea", "onClick: " + listCompany.toString());

                    //Récupération de l'id de la compagnie de prod. entrée
                    int idcompany = listCompany.getElement(nomEnt.getText().toString()).getId();

                    //Création de la nouvelle vue
                    Intent i = new Intent(view.getContext(), Activite_Liste.class);

                    //rajout des données
                    i.putExtra("recherche", 1);
                    i.putExtra("idEnt", idcompany);
                    i.putExtra("idGenre", idgenre);
                    i.putExtra("nbResult", nbNombre.getProgress());
                    i.putExtra("date", dateFilm.getText());
                    startActivity(i);
                }
            }
        });
    }
}
