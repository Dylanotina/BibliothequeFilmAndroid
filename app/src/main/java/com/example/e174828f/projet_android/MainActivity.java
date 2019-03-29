package com.example.e174828f.projet_android;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.LoginFilter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String API_KEY = "6f24e995a9146dc661b833c2a79481b5";

    private Button rechercher;
    private EditText nomEnt;
    private EditText dateFilm;
    private SeekBar nbNombre;
    private TextView nb;
    private Spinner genre;
    private Call<Genre> call;
    private List<Genre> movieResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rechercher = findViewById(R.id.rechercher);
        nomEnt = findViewById(R.id.nomEnt);
        dateFilm = findViewById(R.id.dateFilm);
        nbNombre = findViewById(R.id.nbsearch);
        nb = findViewById(R.id.nbfilm);

        final List<String> spinnerArray =  new ArrayList<String>();




        /*Retrofit retro = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/genre/movie/list?api_key=6f24e995a9146dc661b833c2a79481b5")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        */

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GetMovieDataService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        GetMovieDataService getMovieDataService = retrofit.create(GetMovieDataService.class);


        Call<ListGenre> call = getMovieDataService.getGenre();
        spinnerArray.add("Pas de Selection");

        call.enqueue(new Callback<ListGenre>() {
            @Override
            public void onResponse(Call<ListGenre>call, Response<ListGenre> response) {
                ListGenre listGenre = response.body();

                List<Genre> genre = listGenre.getAllElement();
                Log.d("tag", "onResponse: " + genre.toString());

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
        final Spinner sItems = (Spinner) findViewById(R.id.spinner);
        sItems.setAdapter(adapter);


        nbNombre.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Toast.makeText(MainActivity.this,nbNombre.getProgress(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, sItems.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
