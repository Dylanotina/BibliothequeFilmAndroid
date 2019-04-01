package com.example.e174828f.projet_android;

class Company {

    private int id;
    private String logo;
    private String name;

    public Company(){}

    public Company(int id,String logo,String name){
        this.id = id;
        this.logo = logo;
        this.name = name;
    }

    public String toString(){
        return "Company [ " +
                "id = " + id +'\n' +
                "logo = " + logo + '\n' +
                "name = " + name + "\n]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getLogo() { return logo;}

    public void setLogo(String logo) {this.logo = logo;}

    public void setName(String name) {
        this.name = name;
    }
}
