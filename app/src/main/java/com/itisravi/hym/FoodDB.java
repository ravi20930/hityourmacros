package com.itisravi.hym;

public class FoodDB {

    private double protein, carbs, fats, kcals;
    private String foodName, size;


    public FoodDB() {
    }

    public FoodDB(double protein, double carbs, double fats, double kcals, String foodName, String size) {
        this.protein = protein;
        this.carbs = carbs;
        this.fats = fats;
        this.kcals = kcals;
        this.foodName = foodName;
        this.size = size;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getKcals() {
        return kcals;
    }

    public void setKcals(double kcals) {
        this.kcals = kcals;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
