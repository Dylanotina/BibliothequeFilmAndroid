package com.example.e174828f.projet_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Activite_Liste extends AppCompatActivity {
private ListView mListView;
private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activite__liste);

        mListView = findViewById(R.id.list_films);
        mTextView = findViewById(R.id.textView);



        //ArrayAdapter mArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,films);
        //mListView.setAdapter(mArrayAdapter);

    }


}
