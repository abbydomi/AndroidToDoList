package com.dunny.shoppingcart;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ListActivity {
    List<Activity> list;

    public ListActivity(){
        this.list = new ArrayList<>();
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public ListActivity fromJson(String json){
        Gson gson = new Gson();
        return gson.fromJson(json,ListActivity.class);
    }
}
