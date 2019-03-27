package com.example.e174828f.projet_android;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;



public class Activite_Liste extends AppCompatActivity {
private ListView mListView;
public  static final String api_key="34d79476b86de9146c6f439a4b34c68d";
private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activite__liste);

        mListView = findViewById(R.id.list_films);
        mTextView = findViewById(R.id.textView);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient client =new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(ListFilms.endpoint)
                            .client(client)
                            .build();
            ListFilms api =retrofit.create(ListFilms.class);
        Call<List<Film>> call = api.getFilmsResults(550,api_key);
        call.enqueue(new Callback<List<Film>>() {
            @Override
            public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                if(response.isSuccessful()){
                    List<Film> moviesList = response.body();
                    String[] films = new String[moviesList.size()];
                    for (int i =0; i<moviesList.size();i++){
                        films[i] = moviesList.get(i).getOriginal_title();
                    }
                    ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,android.R.id.text1,films){
                    public View getView(int position, View convertView, ViewGroup parent){
                        View view =super.getView(position,convertView,parent);
                        TextView tv =(TextView) findViewById(android.R.id.text1);
                        tv.setTextColor(Color.BLACK);
                        return view;
                        }
                    };
                    mListView.setAdapter(mArrayAdapter);
                }else{
                    Log.e("ERR","Erreur");
                }
            }

            @Override
            public void onFailure(Call<List<Film>> call, Throwable t) {

            }
        });




    }


}
