package com.example.myapplication;

public class Report_Database {
    String huurder;
    String items;
    String ophaal;
    String terugbreng;

    public Report_Database(String huurder, String items, String ophaal, String terugbreng) {
        this.huurder = huurder;
        this.items = items;
        this.ophaal = ophaal;
        this.terugbreng = terugbreng;
    }

    public void setHuurder(String huurder) {
        this.huurder = huurder;
    }
    public String getHuurder() {
        return this.huurder;
    }
    public void setItems(String items) {
        this.items = items;
    }
    public String getItems() {
        return this.items;
    }
    public void setOphaal(String ophaal) {
        this.ophaal = ophaal;
    }
    public String getOphaal() {
        return this.ophaal;
    }
    public void setTerugbreng(String terugbreng) {
        this.terugbreng = terugbreng;
    }
    public String getTerugbreng() {
        return this.terugbreng;
    }
}