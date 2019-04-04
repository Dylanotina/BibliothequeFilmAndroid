package com.example.e174828f.projet_android;

import com.google.gson.annotations.SerializedName;

import java.util.List;

class Films {

    @SerializedName("results")
    private List<Film> result;

    @Override
    public String toString() {
        return "Films{" +
                "result=" + result +
                '}';
    }

    public List<Film> getAllElement(){
        return result;
    }

    public int taille(){
        return result.size();
    }
}
