package com.example.e174828f.projet_android;

public class Genre {
    private int id;
    private String name;

    public Genre(){}

    public Genre(int id,String name){
        this.id = id;
        this.name = name;
    }

    public String toString(){
        return "Genre [ " +
                "id = " + id +'\n' +
                "name = " + name + '\n';
    }
}
