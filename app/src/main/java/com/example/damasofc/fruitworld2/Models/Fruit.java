package com.example.damasofc.fruitworld2.Models;

public class Fruit {
    private String name;
    private String details;
    private int imgBackground;
    private int cantidad;

    public Fruit(String name, String details, int imgBackground) {
        this.name = name;
        this.details = details;
        this.imgBackground = imgBackground;
        this.cantidad = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getImgBackground() {
        return imgBackground;
    }

    public void setImgBackground(int imgBackground) {
        this.imgBackground = imgBackground;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public void addCant(){
        if(this.cantidad == 10){

        }else {
            this.cantidad++;
        }
    }
    public void resetCant(){
        this.cantidad = 0;
    }
}
