package com.example.e174828f.projet_android;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.List;

class ListGenre {
    @SerializedName("genres")
    private List<Genre> genres;


    @Override
    public String toString() {
        return "ListGenre{" +
                "genres=" + genres +
                '}';
    }

    public List<Genre> getAllElement(){
        return genres;
    }
}
