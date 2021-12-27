package com.itisravi.hym;

public class FoodEatenModel {
    int _id, mealId;
    String food;
    double kcals, protein, fats, carbs;

    public FoodEatenModel(int _id, int mealId, String food, double kcals, double protein, double fats, double carbs) {
        this._id = _id;
        this.mealId = mealId;
        this.food = food;
        this.kcals = kcals;
        this.protein = protein;
        this.fats = fats;
        this.carbs = carbs;
    }

    public FoodEatenModel(String food, double kcals) {
        this.food = food;
        this.kcals = kcals;
    }

    public FoodEatenModel(int _id, String food, double kcals) {
        this._id = _id;
        this.food = food;
        this.kcals = kcals;
    }

    public FoodEatenModel() {
    }

    public int get_id() {
        return _id;
    }

    public int getMealId() {
        return mealId;
    }

    public String getFood() {
        return food;
    }

    public double getKcals() {
        return kcals;
    }

    public double getProtein() {
        return protein;
    }

    public double getFats() {
        return fats;
    }

    public double getCarbs() {
        return carbs;
    }
}
