package com.ghersa.news;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Country {

    Map<String,String> country = new HashMap();
    Vector<String> count = new Vector<>();

    public Country(){
        country.put("Israël","ae");
        count.add("Israël");
        country.put("Îles Marshall","ar");
        count.add("Îles Marshall");
        country.put("Kiribati","at");
        count.add("Kiribati");
        country.put("Autriche","au");
        count.add("Autriche");
        country.put("Belgique","be");
        count.add("Belgique");
        country.put("Brésil","br");
        count.add("Brésil");
        country.put("Cap Vert","ca");
        count.add("Cap Vert");
        country.put("Liechtenstein","ch");
        count.add("Liechtenstein");
        country.put("Cuba","cu");
        count.add("Cuba");
        country.put("France","fr");
        count.add("France");
        country.put("Islande","de");
        count.add("Islande");
        country.put("Luxembourg","hu");
        count.add("Luxembourg");
        country.put("Italie","it");
        count.add("Italie");
        country.put("Finlande","nl");
        count.add("Finlande");
        country.put("États-Unis","us");
        count.add("États-Unis");
    }

    public String getCode(String count){
       return this.country.get(count);
    }

    public Vector<String> getCountry(){
        return this.count;
    }

}
