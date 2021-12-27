package com.itisravi.hym;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ProfileEditor extends AppCompatActivity {

    SharedPreferences personData, personActivityAndGoal;
    String gender;
    int age, activityChoice, goalChoice, weeklyGainChoice, weeklyLoseChoice;
    float weight, height;
    EditText editTextAge, editTextWeight, editTextHeight;
    Button btnDone;
    Spinner spinGender, spinAct, spinGoal, spinWeeklyGoal;
    ArrayAdapter adapter;

    private void setEditTexts() {
        editTextAge.setText(String.valueOf(age));
        editTextWeight.setText(String.valueOf(weight));
        editTextHeight.setText(String.valueOf(height));
    }

    private void spinnerAdapter(Spinner spinner, int id) {
        adapter = ArrayAdapter.createFromResource(this, id, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void setSpinGender() {
        spinnerAdapter(spinGender, R.array.gender);
        if (gender.equals("Male")) {
            spinGender.setSelection(0);
        }
        else {
            spinGender.setSelection(1);
        }
    }

    private void setSpinAct() {
        spinnerAdapter(spinAct, R.array.activityLevels);
        spinAct.setSelection(activityChoice);
    }

    private void setSpinGoal() {
        spinnerAdapter(spinGoal, R.array.goal);
        spinGoal.setSelection(goalChoice);
    }

    private void setSpinWeeklyGoal() {
        switch (goalChoice) {
            case 1:
                spinnerAdapter(spinWeeklyGoal, R.array.weeklyGainGoal);
                spinWeeklyGoal.setSelection(weeklyGainChoice);
                break;
            case 2:
                spinnerAdapter(spinWeeklyGoal, R.array.weeklyLoseGoal);
                spinWeeklyGoal.setSelection(weeklyLoseChoice);
                break;
            default:
                spinnerAdapter(spinWeeklyGoal, R.array.maintenance);
                spinWeeklyGoal.setSelection(0);
                break;
        }
    }

    private void setSpinners() {
        setSpinGender();
        setSpinAct();
        setSpinGoal();
        setSpinWeeklyGoal();
    }


    private void spinGenderOnSelect() {
        spinGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        gender="Male";
                        break;
                    case 1:
                        gender="Female";
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void spinActOnSelect() {
        spinAct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        activityChoice=0;
                        break;
                    case 2:
                        activityChoice = 2;
                        break;
                    case 3:
                        activityChoice = 3;
                        break;
                    case 4:
                        activityChoice = 4;
                        break;
                    default:
                        activityChoice = 1;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void spinGoalOnSelect() {
        spinGoal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        goalChoice=1;
                        spinnerAdapter(spinWeeklyGoal, R.array.weeklyGainGoal);
                        break;
                    case 2:
                        goalChoice=2;
                        spinnerAdapter(spinWeeklyGoal, R.array.weeklyLoseGoal);
                        break;
                    default:
                        goalChoice=0;
                        spinnerAdapter(spinWeeklyGoal, R.array.maintenance);
                        spinWeeklyGoal.setSelection(0);
                        weeklyLoseChoice=5;
                        weeklyGainChoice=5;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void spinWeeklyGoalOnSelect() {
        spinWeeklyGoal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (goalChoice==1) {
                    switch (position) {
                        case 1:
                            weeklyGainChoice=1;
                            break;
                        default:
                            weeklyGainChoice=0;
                            break;
                    }
                } else if (goalChoice==2) {
                    switch (position) {
                        case 1:
                            weeklyLoseChoice=1;
                            break;
                        case 2:
                            weeklyLoseChoice=2;
                            break;
                        default:
                            weeklyLoseChoice=0;
                            break;
                    }
                } else {
                    spinnerAdapter(spinWeeklyGoal, R.array.maintenance);
                    spinWeeklyGoal.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void spinnersOnSelect() {
        spinGenderOnSelect();
        spinActOnSelect();
        spinGoalOnSelect();
        spinWeeklyGoalOnSelect();
    }


    private void initTexts() {
        editTextAge = findViewById(R.id.editTextAge);
        editTextWeight = findViewById(R.id.editTextWeight);
        editTextHeight = findViewById(R.id.editTextHeight);
        btnDone = findViewById(R.id.btnDone);
        spinGender = findViewById(R.id.spinGender);
        spinAct = findViewById(R.id.spinAct);
        spinGoal = findViewById(R.id.spinGoal);
        spinWeeklyGoal = findViewById(R.id.spinWeeklyGoal);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_editor);

        initTexts();

        personData = getSharedPreferences("personData", MODE_PRIVATE);
        personActivityAndGoal = getSharedPreferences("personActivityAndGoal", MODE_PRIVATE);

        age = personData.getInt("age", 0);
        weight = personData.getFloat("weight", 0);
        height = personData.getFloat("height", 0);
        gender = personData.getString("gender", null);

        activityChoice = personActivityAndGoal.getInt("activityLevel", 0);
        goalChoice = personActivityAndGoal.getInt("goal", 0);
        weeklyGainChoice = personActivityAndGoal.getInt("weeklyGainChoice", 0);
        weeklyLoseChoice = personActivityAndGoal.getInt("weeklyLoseChoice", 0);


        setSpinners();
        setEditTexts();
        spinnersOnSelect();


        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editPersonData = personData.edit();
                SharedPreferences.Editor editActivityAndGoal = personActivityAndGoal.edit();

                editPersonData.putInt("age", Integer.parseInt(editTextAge.getText().toString()));
                editPersonData.putFloat("weight", Float.parseFloat(editTextWeight.getText().toString()));
                editPersonData.putFloat("height", Float.parseFloat(editTextHeight.getText().toString()));
                editPersonData.putString("gender", gender);
                editPersonData.apply();

                editActivityAndGoal.putInt("activityLevel", activityChoice);
                editActivityAndGoal.putInt("goal", goalChoice);
                if (weeklyGainChoice != 5) {
                    editActivityAndGoal.putInt("weeklyGainChoice", weeklyGainChoice);
                }
                if (weeklyLoseChoice != 5) {
                    editActivityAndGoal.putInt("weeklyLoseChoice", weeklyLoseChoice);
                }
                editActivityAndGoal.apply();

                Intent intent = new Intent(ProfileEditor.this, MainActivity.class);
                intent.putExtra("code", 4);
                Toast.makeText(ProfileEditor.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finishAffinity();
            }
        });

    }
}