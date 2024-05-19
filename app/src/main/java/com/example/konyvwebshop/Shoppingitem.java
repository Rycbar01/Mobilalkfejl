package com.example.konyvwebshop;

public class Shoppingitem {
    private String ID;
    private String name;
    private String info;
    private String price;
    private float ratedinfo;
    private int imageResource;
    private int count;

    public Shoppingitem(){

    }

    public Shoppingitem(String name, String info, String price, float ratedinfo, int imageResource, int count){
        this.name = name;
        this.info = info;
        this.price = price;
        this.ratedinfo = ratedinfo;
        this.imageResource = imageResource;
        this.count = count;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    public String getName() {return name;}
    public String getInfo() {return info;}
    public String getPrice() {return price;}
    public float getRatedinfo() {return ratedinfo;}
    public int getImageresource() {return imageResource;}
    public int getCount() {
        return count;
    }
    public String _getID() {
        return ID;
    }


}
