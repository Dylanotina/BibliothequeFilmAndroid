package com.example.e174828f.projet_android;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;



import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Activite_Liste extends AppCompatActivity {
private ListView mListView;

private List<Film> listFilm;
private Films listFilms;
private TextView mTextView;
private String[] films;
private Button bouton;
private int nbResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activite__liste);

        bouton = findViewById(R.id.button);
        mListView = findViewById(R.id.list_films);
        mTextView = findViewById(R.id.textView);

        Intent recherche = getIntent();
        int val = recherche.getIntExtra("recherche",0);
        switch (val){
            case 1:
                int entreprise =recherche.getIntExtra("idEnt",0);
                int genre =recherche.getIntExtra("idGenre",0);
                 nbResult=recherche.getIntExtra("nbResult",0);
                Log.d("TAG ERREUR","val :" +nbResult);
                 CharSequence date= recherche.getCharSequenceExtra("date");
                int dateF =Integer.parseInt(date.toString());

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(GetMovieDataService.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                GetMovieDataService api =retrofit.create(GetMovieDataService.class);
                Call<Films> call = api.getFilms(entreprise,genre,dateF);



                call.enqueue(new Callback<Films>() {
                    @Override
                    public void onResponse(Call<Films> call, Response<Films> response) {
                        if (response.isSuccessful()) {
                            listFilms = response.body();
                            listFilm = listFilms.getAllElement();
                            films = new String[nbResult];
                            Log.d("TAG ERREUR","val :" +listFilm.size());
                            for (int i=0; i<=listFilm.size()-1;i++) {
                                films[i] = listFilm.get(i).getTitle();
                                if(films[i]==null){
                                    break;
                                }
                            }

                        }
                        ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,films);
                        mListView.setAdapter(mArrayAdapter);
                    }

                    @Override
                    public void onFailure(Call<Films> call, Throwable t) {
                        Log.d("TAG", "onFailure: " + t.getMessage());
                    }


                });

                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(Activite_Liste.this,Visuel_Film_Activite.class);
                        intent.putExtra("id",listFilm.get(position).getId());
                        startActivity(intent);
                    }
                });
                bouton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                break;



            case 2:
                Retrofit retrofit2 = new Retrofit.Builder()
                        .baseUrl(GetMovieDataService.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                GetMovieDataService api2 =retrofit2.create(GetMovieDataService.class);
                Call<Films> call2 = api2.getPopularFilms();



                call2.enqueue(new Callback<Films>() {
                    @Override
                    public void onResponse(Call<Films> call, Response<Films> response) {
                        if (response.isSuccessful()) {
                            listFilms = response.body();
                            listFilm = listFilms.getAllElement();
                            films = new String[listFilm.size()];
                            for (int i = 0; i < listFilm.size(); i++) {
                                films[i] = listFilm.get(i).getTitle();
                            }
                        }
                        ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,films);
                        mListView.setAdapter(mArrayAdapter);
                    }

                    @Override
                    public void onFailure(Call<Films> call, Throwable t) {
                        Log.d("TAG", "onFailure: " + t.getMessage());
                    }


                });

                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(Activite_Liste.this,Visuel_Film_Activite.class);
                        intent.putExtra("id",listFilm.get(position).getId());
                        startActivity(intent);
                    }
                });
                bouton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

                break;
            case 3:
                Retrofit retrofit3 = new Retrofit.Builder()
                        .baseUrl(GetMovieDataService.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                GetMovieDataService api3 =retrofit3.create(GetMovieDataService.class);
                Call<Films> call3 = api3.getTopRatedFilms();



                call3.enqueue(new Callback<Films>() {
                    @Override
                    public void onResponse(Call<Films> call, Response<Films> response) {
                        if (response.isSuccessful()) {
                            listFilms = response.body();
                            listFilm = listFilms.getAllElement();
                            films = new String[listFilm.size()];
                            for (int i = 0; i < listFilm.size(); i++) {
                                films[i] = listFilm.get(i).getTitle();
                            }
                        }
                        ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,films);
                        mListView.setAdapter(mArrayAdapter);
                    }

                    @Override
                    public void onFailure(Call<Films> call, Throwable t) {
                        Log.d("TAG", "onFailure: " + t.getMessage());
                    }


                });

                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(Activite_Liste.this,Visuel_Film_Activite.class);
                        intent.putExtra("id",listFilm.get(position).getId());
                        startActivity(intent);
                    }
                });
                bouton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

                break;
            case 4:
                Retrofit retrofit4 = new Retrofit.Builder()
                        .baseUrl(GetMovieDataService.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                GetMovieDataService api4 =retrofit4.create(GetMovieDataService.class);
                Call<Films> call4 = api4.getUpcommingFilms();



                call4.enqueue(new Callback<Films>() {
                    @Override
                    public void onResponse(Call<Films> call, Response<Films> response) {
                        if (response.isSuccessful()) {
                            listFilms = response.body();
                            listFilm = listFilms.getAllElement();
                            films = new String[listFilm.size()];
                            for (int i = 0; i < listFilm.size(); i++) {
                                films[i] = listFilm.get(i).getTitle();
                            }
                        }
                        ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,films);
                        mListView.setAdapter(mArrayAdapter);
                    }

                    @Override
                    public void onFailure(Call<Films> call, Throwable t) {
                        Log.d("TAG", "onFailure: " + t.getMessage());
                    }


                });

                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(Activite_Liste.this,Visuel_Film_Activite.class);
                        intent.putExtra("id",listFilm.get(position).getId());
                        startActivity(intent);
                    }
                });
                bouton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                break;

            case 5:
                Retrofit retrofit5 = new Retrofit.Builder()
                        .baseUrl(GetMovieDataService.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                GetMovieDataService api5 =retrofit5.create(GetMovieDataService.class);
                Call<Films> call5 = api5.getDiscoverMovie();



                call5.enqueue(new Callback<Films>() {
                    @Override
                    public void onResponse(Call<Films> call, Response<Films> response) {
                        if (response.isSuccessful()) {
                            listFilms = response.body();
                            listFilm = listFilms.getAllElement();
                            films = new String[listFilm.size()];
                            for (int i = 0; i < listFilm.size(); i++) {
                                films[i] = listFilm.get(i).getTitle();
                            }
                        }
                        ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,films);
                        mListView.setAdapter(mArrayAdapter);
                    }

                    @Override
                    public void onFailure(Call<Films> call, Throwable t) {
                        Log.d("TAG", "onFailure: " + t.getMessage());
                    }


                });

                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(Activite_Liste.this,Visuel_Film_Activite.class);
                        intent.putExtra("id",listFilm.get(position).getId());
                        startActivity(intent);
                    }
                });
                bouton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

                break;
            case 6:
                String filmTitre = recherche.getStringExtra("titre");
                Log.d("TAG ERREUR","val :" +filmTitre);
                Retrofit retrofit6 = new Retrofit.Builder()
                        .baseUrl(GetMovieDataService.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                GetMovieDataService api6 =retrofit6.create(GetMovieDataService.class);
                Call<Films> call6 = api6.getMovieByName(filmTitre);



                call6.enqueue(new Callback<Films>() {
                    @Override
                    public void onResponse(Call<Films> call, Response<Films> response) {
                        if (response.isSuccessful()) {
                            listFilms = response.body();
                            listFilm = listFilms.getAllElement();
                            films = new String[listFilm.size()];
                            for (int i = 0; i < listFilm.size(); i++) {
                                films[i] = listFilm.get(i).getTitle();
                            }
                        }

                        ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,films);
                        mListView.setAdapter(mArrayAdapter);
                    }

                    @Override
                    public void onFailure(Call<Films> call, Throwable t) {
                        Log.d("TAG", "onFailure: " + t.getMessage());
                    }


                });

                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(Activite_Liste.this,Visuel_Film_Activite.class);
                        intent.putExtra("id",listFilm.get(position).getId());
                        startActivity(intent);
                    }
                });
                bouton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                break;

        }


    }


}
