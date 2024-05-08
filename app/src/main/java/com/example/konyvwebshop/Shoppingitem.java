package com.example.konyvwebshop;

public class Shoppingitem {
    private String name;
    private String info;
    private String price;
    private float ratedinfo;
    private final int imageresource;


    public Shoppingitem(String name, String info, String price, float ratedinfo, int imageresource){
        this.name = name;
        this.info = info;
        this.price = price;
        this.ratedinfo = ratedinfo;
        this.imageresource = imageresource;
    }

    String getName() {return name;}
    String getInfo() {return info;}
    String getPrice() {return price;}
    float getRatedinfo() {return ratedinfo;}
    public int getimageresource() {return getimageresource();}




}
