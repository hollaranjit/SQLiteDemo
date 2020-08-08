package com.example.fightclubapp.model;

public class MartialArt {

    private int martialID;
    private String martialArtName;
    private double martialArtPrice;
    private String martialArtColor;




    public MartialArt(int id, String name, Double price, String color) {

        setMartialID(id);
        setMartialArtName(name);
        setMartialArtPrice(price);
        setMartialArtColor(color);
    }

    public int getMartialID() {
        return martialID;
    }

    public String getMartialArtName() {
        return martialArtName;
    }

    public double getMartialArtPrice() {
        return martialArtPrice;
    }

    public String getMartialArtColor() {
        return martialArtColor;
    }

    //Setters--------------------------------------------------------------------------

    public void setMartialID(int martialID) {
        this.martialID = martialID;
    }

    public void setMartialArtName(String martialArtName) {
        this.martialArtName = martialArtName;
    }

    public void setMartialArtPrice(double martialArtPrice) {
        this.martialArtPrice = martialArtPrice;
    }

    public void setMartialArtColor(String martialArtColor) {
        this.martialArtColor = martialArtColor;
    }


    @Override
    public String toString() {

        return getMartialID() + " " + getMartialArtName() + " " +
                getMartialArtPrice() + " " + getMartialArtColor();

    }
}
