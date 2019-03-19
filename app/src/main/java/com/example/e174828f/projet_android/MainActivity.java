package com.example.e174828f.projet_android;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button rechercher;
    private EditText nomEnt;
    private EditText dateFilm;
    private SeekBar nbNombre;
    private TextView nb;
    private Spinner genre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HashMap<String, Object> map = new HashMap<>();
        map.put("id", null);

        String result = gson.toJson(map);
        System.out.println(result);  // {}


        rechercher = findViewById(R.id.rechercher);
        nomEnt = findViewById(R.id.nomEnt);
        dateFilm = findViewById(R.id.dateFilm);
        nbNombre = findViewById(R.id.nbsearch);
        nb = findViewById(R.id.nbfilm);


        Ion.with(this)
                .load("https://api.themoviedb.org/3/genre/movie/list?api_key=6f24e995a9146dc661b833c2a79481b5")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        Log.d("data", "onCompleted: " + result.toString());
                    }
                });


        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("item1");
        spinnerArray.add("item2");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.spinner);
        sItems.setAdapter(adapter);


        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, nbNombre.getProgress(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
