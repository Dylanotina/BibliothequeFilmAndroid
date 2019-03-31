package com.example.e174828f.projet_android;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
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
public  static final String api_key="34d79476b86de9146c6f439a4b34c68d";
private TextView mTextView;
private String[] films;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activite__liste);

        mListView = findViewById(R.id.list_films);
        mTextView = findViewById(R.id.textView);


        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(ListFilms.endpoint)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

            ListFilms api =retrofit.create(ListFilms.class);
        Call<Films> call = api.getFilms();



        call.enqueue(new Callback<Films>() {
            @Override
            public void onResponse(Call<Films> call, Response<Films> response) {
                if (response.isSuccessful()) {
                    Films listFilms = response.body();
                    List<Film> listFilm = listFilms.getAllElement();
                     films = new String[listFilm.size()];
                    for (int i = 0; i < listFilm.size(); i++) {
                        films[i] = listFilm.get(i).getOriginal_title();
                    }
                }
                ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,films) {

                };
                mListView.setAdapter(mArrayAdapter);
            }

            @Override
            public void onFailure(Call<Films> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getMessage());
            }


        });






    }


}
