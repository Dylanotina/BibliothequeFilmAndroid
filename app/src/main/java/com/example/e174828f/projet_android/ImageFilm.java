package com.example.e174828f.projet_android;

class ImageFilm {


    private int id;
    private int height;
    private int width;
    private Poster[] posters;
    public ImageFilm(){}

    public ImageFilm( int height, int width) {

        this.height = height;
        this.width = width;
    }

    public int getId() {
        return id;
    }

    public Poster[] getPosters() {
        return posters;
    }


    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
