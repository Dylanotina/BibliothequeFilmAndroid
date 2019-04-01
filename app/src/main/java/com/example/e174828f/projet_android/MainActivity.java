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
import java.util.Iterator;
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
    private Spinner genre;
    private List<Company> c1;
    private Button autreRecherche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rechercher = findViewById(R.id.rechercher);
        nomEnt = findViewById(R.id.nomEnt);
        dateFilm = findViewById(R.id.dateFilm);
        nbNombre = findViewById(R.id.nbsearch);
        nb = findViewById(R.id.nbfilm);

        nb.setText(valueOf(nbNombre.getProgress()));


        final List<String> spinnerArray =  new ArrayList<String>();
        final List<String> spinnerCompany = new ArrayList<String>();




        /*Retrofit retro = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/genre/movie/list?api_key=6f24e995a9146dc661b833c2a79481b5")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        */

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GetMovieDataService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        final GetMovieDataService getMovieDataService = retrofit.create(GetMovieDataService.class);



        Call<ListGenre> call = getMovieDataService.getGenre();
        spinnerArray.add("Pas de Selection");

        call.enqueue(new Callback<ListGenre>() {
            @Override
            public void onResponse(Call<ListGenre>call, Response<ListGenre> response) {
                ListGenre listGenre = response.body();

                List<Genre> genre = listGenre.getAllElement();
//                Log.d("tag", "onResponse: " + genre.toString());
                for (int i = 0; i<genre.size(); i++) {
                    spinnerArray.add(genre.get(i).getName());
                }
            }

            @Override
            public void onFailure(Call<ListGenre> call, Throwable t) {
//                Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
//
                Log.d("TAG", "onFailure: " + t.getMessage());
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genre = (Spinner) findViewById(R.id.spinner);
        genre.setAdapter(adapter);


        nomEnt.setThreshold(1);


        /**TODO
         * AUTOCOMPLETION si possible
         */

        spinnerCompany.add("Pas de Selection");
        nomEnt.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {
           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {


           }

           @Override
           public void afterTextChanged(Editable s) {
               if(!s.toString().isEmpty()) {
                   Call<ListCompany> callCompany = getMovieDataService.getCompany(ImmutableMap.of("api_key", "34d79476b86de9146c6f439a4b34c68d", "query", s.toString()));
                   Log.d("callCompany", callCompany.request().toString());
                   callCompany.enqueue(new Callback<ListCompany>() {
                       @Override
                       public void onResponse(Call<ListCompany> call, Response<ListCompany> response) {
                           spinnerCompany.clear();
                           ListCompany listCompany = response.body();
                           Log.d("tag", "onResponse: " + listCompany.toString());
                           c1 = listCompany.getAllElement();
                           for (int i = 0; i<c1.size(); i++) {
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
//                Toast.makeText(MainActivity.this,progress, Toast.LENGTH_SHORT).show();
                nb.setText(valueOf(progress));
//                Log.d("onProgressChanged", "onProgressChanged: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
//                Toast.makeText(MainActivity.this, "LOL", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




        autreRecherche = findViewById(R.id.Autrerecherche);


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

                if( (genre.getSelectedItem().toString().isEmpty() || genre.getSelectedItem().toString().equals("Pas de Selection"))
                && (dateFilm.getText().toString().isEmpty())) {

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
                    Toast.makeText(MainActivity.this, genre.getSelectedItem().toString() + " " + nb.getText() + " " + nomEnt.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
