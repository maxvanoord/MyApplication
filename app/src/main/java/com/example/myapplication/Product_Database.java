package com.example.myapplication;

public class Product_Database {
    String name;
    int stock;
    String categorie;

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setStock(int stock)
    {
        this.stock = stock;
    }
    public int getStock()
    {
        return this.stock;
    }
    public void setCategorie(String categorie)
    {
        this.categorie = categorie;
    }
    public String getCategorie(){
        return this.categorie;
    }
}