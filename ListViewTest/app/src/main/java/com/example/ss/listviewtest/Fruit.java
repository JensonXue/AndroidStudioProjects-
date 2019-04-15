package com.example.ss.listviewtest;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class Fruit {
    private String name;
    private int imageid;

    public Fruit(String name,int imageId)
    {
        this.name = name;
        this.imageid = imageId;
    }

    public String getName(){
        return name;
    }

    public int getImageid(){
        return imageid;
    }
}