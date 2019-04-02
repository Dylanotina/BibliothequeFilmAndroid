package com.example.e174828f.projet_android;

public class Poster {

    private  int id;
    private String file_path;




    public Poster(int id, String path_file) {
        this.id = id;
        this.file_path = path_file;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath_file() {
        return file_path;
    }

    public void setPath_file(String path_file) {
        this.file_path = path_file;
    }
}
