package com.example.e174828f.projet_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Visuel_Film_Activite extends AppCompatActivity {


    private ImageView imageView;
    private TextView text1;
    private TextView titre;
    private Film result;
    private String image;
    private Button retour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visuel__film__activite);

    imageView =findViewById(R.id.image_film);
    titre =findViewById(R.id.titre);
    text1 =findViewById(R.id.text);
    retour =findViewById(R.id.buttonRetour);

        Intent reception = getIntent();
     int movieid = reception.getIntExtra("id",0);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GetMovieDataService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetMovieDataService api =retrofit.create(GetMovieDataService.class);
         Call<Film> filmResult=api.getFilmsResults(movieid);

         filmResult.enqueue(new Callback<Film>() {
             @Override
             public void onResponse(Call<Film> call, Response<Film> response) {
                result =response.body();
                titre.setText(result.getTitle());
                text1.setText(result.getOverview());
                image = result.getPoster_path();
                 if(image!=null) {
                     Picasso.get().load("https://image.tmdb.org/t/p/original" + image)
                             .resize(600, 800)
                             .centerCrop()
                             .into(imageView);

                 }
             }

             @Override
             public void onFailure(Call<Film> call, Throwable t) {
                 Log.d("TAG", "onFailure: " + t.getMessage());
             }
         });
    retour.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    });










    }
}
