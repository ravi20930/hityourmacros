package com.itisravi.hym;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.Calendar;

public class Goal extends Fragment {

    View view;
    float weight, height, bmrGeneral;
    int age, activityLevel, goal, intKcal, kcalGain, kcalLose, gramsP, gramsC, gramsF;
    String gender;
    TextView finalCals, txtViewTotalProtein, txtViewTotalFats, txtViewTotalCarbs,
            txtViewTotalCals, txtViewLeftCals, txtViewLeftP, txtViewLeftC, txtViewLeftF,
            txtViewMeal4Cals, txtViewMeal3Cals, txtViewMeal2Cals, txtViewMeal1Cals, txtViewCalPercentage;
    TextView mp1, mp2, mp3, mp4, mc1, mc2, mc3, mc4, mf1, mf2, mf3, mf4;
    ProgressBar pbProtein, pbCarbs, pbFats, pbMeal1, pbMeal2, pbMeal3, pbMeal4, pbTotal;
    SharedPreferences personMacros;
    SharedPreferences.Editor macrosEditor;
    private boolean checkCustomMacros;


    public Goal() {
    }

    private void setProgressBar() {
        pbProtein = view.findViewById(R.id.pbProtein);
        pbCarbs = view.findViewById(R.id.pbCarbs);
        pbFats = view.findViewById(R.id.pbFats);


        pbMeal1 = view.findViewById(R.id.pbMeal1);
        pbMeal2 = view.findViewById(R.id.pbMeal2);
        pbMeal3 = view.findViewById(R.id.pbMeal3);
        pbMeal4 = view.findViewById(R.id.pbMeal4);
        pbTotal = view.findViewById(R.id.pbTotal);
        txtViewCalPercentage = view.findViewById(R.id.txtViewCalPercentage);

        if (getTotalMacros("kcals") == 0) {
            pbProtein.setProgress(0);
            pbCarbs.setProgress(0);
            pbFats.setProgress(0);
        } else {
            pbProtein.setProgress(getTotalMacros("protein") * 100 / gramsP);
            pbCarbs.setProgress(getTotalMacros("carbs") * 100 / gramsC);
            pbFats.setProgress(getTotalMacros("fats") * 100 / gramsF);
        }

        if (getTotalMacros("kcals") == 0) {
            pbMeal1.setProgress(0);
            pbMeal2.setProgress(0);
            pbMeal3.setProgress(0);
            pbMeal4.setProgress(0);
        } else {
            pbMeal1.setProgress(getMealMacros("kcals", 1) * 100 / getTotalMacros("kcals"));
            pbMeal2.setProgress(getMealMacros("kcals", 2) * 100 / getTotalMacros("kcals"));
            pbMeal3.setProgress(getMealMacros("kcals", 3) * 100 / getTotalMacros("kcals"));
            pbMeal4.setProgress(getMealMacros("kcals", 4) * 100 / getTotalMacros("kcals"));
        }

        pbTotal.setProgress(getTotalMacros("kcals") * 100 / personMacros.getInt("finalCals", 0));
        txtViewCalPercentage.setText(getTotalMacros("kcals") * 100 / personMacros.getInt("finalCals", 1) + "%");
    }

    private float bmrFormula(double m1, double m2, double m3, double m4) {
        float bmrValue = (float) (m1 + (m2 * weight) + (m3 * height) - (m4 * age));
        return bmrValue;
    }

    private float bmrMale() {
        float value = bmrFormula(66.4, 13.75, 5.003, 6.755);
        float valueRounded = Math.round(value);
        bmrGeneral = valueRounded;
        return bmrGeneral;
    }

    private float bmrFemale() {
        float value = bmrFormula(655.1, 9.536, 1.85, 4.676);
        float valueRounded = Math.round(value);
        bmrGeneral = valueRounded;
        return bmrGeneral;
    }

    private void maleOrFemale() {
        if (gender.equals("Male")) {
            bmrMale();
        } else {
            bmrFemale();
        }
    }

    private void callGoal(int goal) {
        if (checkCustomMacros) {
            gramsP = personMacros.getInt("customP", 0);
            gramsC = personMacros.getInt("customC", 0);
            gramsF = personMacros.getInt("customF", 0);
            finalCals.setText("" + personMacros.getInt("finalCals", 0));
        } else {
            switch (goal) {
                case 1:
                    kcalGain = intKcal + 300;
                    gramsP = kcalGain * 25 / 400;
                    gramsC = kcalGain * 45 / 400;
                    gramsF = kcalGain * 30 / 900;
                    macrosEditor.putInt("finalCals", kcalGain);
                    macrosEditor.putInt("gramsP", gramsP);
                    macrosEditor.putInt("gramsC", gramsC);
                    macrosEditor.putInt("gramsF", gramsF);
                    finalCals.setText("" + kcalGain);
                    break;
                case 2:
                    kcalLose = intKcal - 300;
                    gramsP = kcalLose * 35 / 400;
                    gramsC = kcalLose * 25 / 400;
                    gramsF = kcalLose * 40 / 900;
                    macrosEditor.putInt("finalCals", kcalLose);
                    macrosEditor.putInt("gramsP", gramsP);
                    macrosEditor.putInt("gramsC", gramsC);
                    macrosEditor.putInt("gramsF", gramsF);
                    finalCals.setText("" + kcalLose);
                    break;
                default:
                    gramsP = intKcal * 20 / 400;
                    gramsC = intKcal * 50 / 400;
                    gramsF = intKcal * 30 / 900;
                    macrosEditor.putInt("finalCals", intKcal);
                    macrosEditor.putInt("gramsP", gramsP);
                    macrosEditor.putInt("gramsC", gramsC);
                    macrosEditor.putInt("gramsF", gramsF);
                    finalCals.setText("" + intKcal);
                    break;
            }
            macrosEditor.apply();
        }
    }


    private void setTextInGrams() {

        txtViewTotalCals.setText(getTotalMacros("kcals") + "");

        txtViewTotalProtein.setText(getTotalMacros("protein") + " / " + gramsP);
        txtViewTotalCarbs.setText(getTotalMacros("carbs") + " / " + gramsC);
        txtViewTotalFats.setText(getTotalMacros("fats") + " / " + gramsF);

        txtViewLeftCals.setText("" + (personMacros.getInt("finalCals", 0) - getTotalMacros("kcals")));

        txtViewLeftC.setText("" + (gramsC - getTotalMacros("carbs")));
        txtViewLeftP.setText("" + (gramsP - getTotalMacros("protein")));
        txtViewLeftF.setText("" + (gramsF - getTotalMacros("fats")));
    }

    private void setMealData(int mealId) {
        mp1 = view.findViewById(R.id.mp1);
        mp2 = view.findViewById(R.id.mp2);
        mp3 = view.findViewById(R.id.mp3);
        mp4 = view.findViewById(R.id.mp4);
        mc1 = view.findViewById(R.id.mc1);
        mc2 = view.findViewById(R.id.mc2);
        mc3 = view.findViewById(R.id.mc3);
        mc4 = view.findViewById(R.id.mc4);
        mf1 = view.findViewById(R.id.mf1);
        mf2 = view.findViewById(R.id.mf2);
        mf3 = view.findViewById(R.id.mf3);
        mf4 = view.findViewById(R.id.mf4);

        switch (mealId) {
            case 1:
                mp1.setText("" + getMealMacros("protein", 1));
                mc1.setText("" + getMealMacros("carbs", 1));
                mf1.setText("" + getMealMacros("fats", 1));
                break;
            case 2:
                mp2.setText("" + getMealMacros("protein", 2));
                mc2.setText("" + getMealMacros("carbs", 2));
                mf2.setText("" + getMealMacros("fats", 2));
                break;
            case 3:
                mp3.setText("" + getMealMacros("protein", 3));
                mc3.setText("" + getMealMacros("carbs", 3));
                mf3.setText("" + getMealMacros("fats", 3));
                break;
            case 4:
                mp4.setText("" + getMealMacros("protein", 4));
                mc4.setText("" + getMealMacros("carbs", 4));
                mf4.setText("" + getMealMacros("fats", 4));
                break;
        }
    }

    private void setMealMacros() {


        txtViewMeal1Cals = view.findViewById(R.id.txtViewMeal1Cals);
        txtViewMeal2Cals = view.findViewById(R.id.txtViewMeal2Cals);
        txtViewMeal3Cals = view.findViewById(R.id.txtViewMeal3Cals);
        txtViewMeal4Cals = view.findViewById(R.id.txtViewMeal4Cals);

        txtViewMeal1Cals.setText("" + getMealMacros("kcals", 1));
        txtViewMeal2Cals.setText("" + getMealMacros("kcals", 2));
        txtViewMeal3Cals.setText("" + getMealMacros("kcals", 3));
        txtViewMeal4Cals.setText("" + getMealMacros("kcals", 4));

        setMealData(1);
        setMealData(2);
        setMealData(3);
        setMealData(4);
    }

    private void callCalorie(int activityLevel) {
        switch (activityLevel) {
            case 1:
                calorie(1.375f);
                break;
            case 2:
                calorie(1.55f);
                break;
            case 3:
                calorie(1.725f);
                break;
            case 4:
                calorie(1.9f);
                break;
            default:
                calorie(1.20f);
                break;
        }
        callGoal(goal);
    }

    private void calorie(float f) {
        float kcal = bmrGeneral * f;
        intKcal = Math.round(kcal);
    }


    private void initTexts() {
        finalCals = view.findViewById(R.id.txtViewFinalCals);
        txtViewTotalProtein = view.findViewById(R.id.txtViewTotalProtein);
        txtViewTotalCarbs = view.findViewById(R.id.txtViewTotalCarbs);
        txtViewTotalFats = view.findViewById(R.id.txtViewTotalFats);
        txtViewTotalCals = view.findViewById(R.id.txtViewTotalCals);
        txtViewLeftF = view.findViewById(R.id.txtViewLeftF);
        txtViewLeftC = view.findViewById(R.id.txtViewLeftC);
        txtViewLeftP = view.findViewById(R.id.txtViewLeftP);
        txtViewLeftCals = view.findViewById(R.id.txtViewLeftCals);
    }

    private int getTotalMacros(String column) {
        DBAdapter db = new DBAdapter(getContext());
        db.open();
        Cursor cursor = db.sum("foodEaten", column);
        int sum = cursor.getInt(cursor.getColumnIndex("Total"));
        db.close();
        return sum;
    }

    private int getMealMacros(String column, int mealId) {
        DBAdapter db = new DBAdapter(getContext());
        db.open();
        Cursor cursor = db.sum("foodEaten", column, "mealId", mealId);
        int sum = cursor.getInt(cursor.getColumnIndex("Total"));
        db.close();
        return sum;
    }

    private void getDataFromSharedPreference() {
        SharedPreferences personData = getActivity().getSharedPreferences("personData", getActivity().MODE_PRIVATE);
        SharedPreferences personActivityAndGoal = getActivity().getSharedPreferences("personActivityAndGoal", getActivity().MODE_PRIVATE);

        personMacros = getActivity().getSharedPreferences("personMacros", getActivity().MODE_PRIVATE);
        checkCustomMacros = personMacros.getBoolean("customMacros", false);
        macrosEditor = personMacros.edit();

        age = personData.getInt("age", 0);
        weight = personData.getFloat("weight", 0);
        height = personData.getFloat("height", 0);
        gender = personData.getString("gender", "null");
        activityLevel = personActivityAndGoal.getInt("activityLevel", 0);
        goal = personActivityAndGoal.getInt("goal", 0);
    }

    private void dateChecker() {
        Calendar calendar = Calendar.getInstance();
        String date = String.valueOf(calendar.get(Calendar.DATE));
        SharedPreferences lastLogin = getActivity().getSharedPreferences("lastLogin", getActivity().MODE_PRIVATE);

        if (lastLogin.contains("date")) {
            if (!lastLogin.getString("date", null).equals(date)) {
                DBAdapter db = new DBAdapter(getContext());
                db.open();
                db.emptyTable("foodEatenYesterday");

                db.copyTable("foodEatenYesterday", "foodEaten");

                db.emptyTable("foodEaten");
                db.close();

                Toast.makeText(getContext(), "A fresh day", Toast.LENGTH_SHORT).show();
            }
        }

        calendar = Calendar.getInstance();
        date = String.valueOf(calendar.get(Calendar.DATE));
        SharedPreferences.Editor datePutter = lastLogin.edit();
        datePutter.putString("date", date);
        datePutter.apply();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_goal, container, false);

        dateChecker();
        getDataFromSharedPreference();

        initTexts();
        maleOrFemale();
        callCalorie(activityLevel);
        setTextInGrams();
        setMealMacros();
        setProgressBar();

        return view;
    }
}