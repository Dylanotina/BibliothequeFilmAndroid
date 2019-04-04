package com.example.e174828f.projet_android;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListCompany {
    @SerializedName("results")
    private List<Company> company;


    @Override
    public String toString() {
        return "compagny {" +
                "comapny = " + company +
                '}';
    }

    public List<Company> getAllElement(){
        return company;
    }


    public Company getElement(String s){

        for (int i = 0; i<company.size(); i++) {
            if (s.equals(company.get(i).getName())) {
                return company.get(i);
            }
        }
        return null;
    }
}

