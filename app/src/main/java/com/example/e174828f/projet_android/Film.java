package com.example.e174828f.projet_android;


import java.io.Serializable;
import java.util.ArrayList;

public class Film implements Serializable {
    private int id;
    private String original_title;
    private String overview;
    private String release_date;
    private Genre genre;
    private ArrayList<Film> filmsResults;

    public Film (){}



    public Film(int id, String original_title, String overview, String release_date, Genre genre,ArrayList<Film> filmsResults) {
        this.id = id;
        this.original_title = original_title;
        this.overview = overview;
        this.release_date = release_date;
        this.genre = genre;
        this.filmsResults = filmsResults;
    }

    public ArrayList<Film> getFilmsResults() {
        return filmsResults;
    }

    public void setFilmsResults(ArrayList<Film> filmsResults) {
        this.filmsResults = filmsResults;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
