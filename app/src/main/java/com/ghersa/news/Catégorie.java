package com.ghersa.news;

import java.util.Vector;

public class Catégorie {

    Vector<String> MyCatégorie = new Vector<>();

    public Catégorie(){
        MyCatégorie.add("business");
        MyCatégorie.add("entertainment");
        MyCatégorie.add("general");
        MyCatégorie.add("health");
        MyCatégorie.add("science");
        MyCatégorie.add("sports");
        MyCatégorie.add("technology");
    }

    public Vector<String> getMyCatégorie(){
        return this.MyCatégorie;
    }


}
