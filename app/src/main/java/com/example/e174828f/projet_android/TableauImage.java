package com.example.e174828f.projet_android;

public class TableauImage {
    private ImageFilm[] images;

    public TableauImage(){
    }

    public TableauImage(ImageFilm[] images) {
        this.images = images;
    }

    public ImageFilm[] getImages() {
        return images;
    }

    public void setImages(ImageFilm[] images) {
        this.images = images;
    }
}
