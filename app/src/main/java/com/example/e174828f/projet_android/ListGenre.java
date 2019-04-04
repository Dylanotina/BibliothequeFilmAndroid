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

    public Genre getElement(String s){


        for (int i = 0; i<genres.size(); i++) {
            if (s.equalsIgnoreCase(genres.get(i).getName())) {
                return genres.get(i);
            }
        }
        return null;
    }
}
