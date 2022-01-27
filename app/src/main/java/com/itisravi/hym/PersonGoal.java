package com.itisravi.hym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class PersonGoal extends AppCompatActivity {

    private Button btnProceed;
    private int activityChoice = 5, goalChoice = 5;
    private int weeklyGainChoice = 5;
    private int weeklyLoseChoice = 5;
    private AutoCompleteTextView autoCompleteTextViewActivity, autoCompleteTextViewGoal, autoCompleteTextViewWeeklyGoal;

    private void dropDownActivity() {
        String[] lifestyle = getResources().getStringArray(R.array.activityLevels);
        ArrayAdapter arrayAdapterActivity = new ArrayAdapter(this, R.layout.dropdown, lifestyle);

        autoCompleteTextViewActivity.setAdapter(arrayAdapterActivity);

        autoCompleteTextViewActivity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        activityChoice=0;
                        break;
                    case 2:
                        activityChoice = 2;
                        autoCompleteTextViewWeeklyGoal.setText("Maintenance");
                        break;
                    case 3:
                        activityChoice = 3;
                        autoCompleteTextViewWeeklyGoal.setText("Maintenance");
                        break;
                    case 4:
                        activityChoice = 4;
                        autoCompleteTextViewWeeklyGoal.setText("Maintenance");
                        break;
                    default:
                        activityChoice = 1;
                        autoCompleteTextViewWeeklyGoal.setText("Maintenance");
                        break;
                }
                dropDownGoal();
            }
        });
    }

    private void dropDownGoal() {
        String[] goal = getResources().getStringArray(R.array.goal);
        ArrayAdapter arrayAdapterGoal = new ArrayAdapter(this, R.layout.dropdown, goal);

        autoCompleteTextViewGoal.setAdapter(arrayAdapterGoal);
        autoCompleteTextViewGoal.setText(arrayAdapterGoal.getItem(0).toString(), false);

        autoCompleteTextViewGoal.setOnItemClickListener((parent, view, position, id) -> {
            switch (position) {
                case 1:
                    goalChoice=1;
                    break;
                case 2:
                    goalChoice=2;
                    break;
                default:
                    goalChoice=0;
            }
            weeklyGoal();
        });
    }

    private void weeklyGoal() {

        switch (goalChoice) {
            case 1:
                String[] goalGain = getResources().getStringArray(R.array.weeklyGainGoal);
                ArrayAdapter arrayAdapterGoalGain = new ArrayAdapter(this, R.layout.dropdown, goalGain);

                autoCompleteTextViewWeeklyGoal.setAdapter(arrayAdapterGoalGain);
                autoCompleteTextViewWeeklyGoal.setText(arrayAdapterGoalGain.getItem(0).toString(), false);

                autoCompleteTextViewWeeklyGoal.setOnItemClickListener((parent, view, position, id) -> {
                    switch (position) {
                        case 1:
                            weeklyGainChoice=1;
                            break;
                        default:
                            weeklyGainChoice=0;
                            break;
                    }
                });
                break;
            case 2:
                String[] goalLose = getResources().getStringArray(R.array.weeklyLoseGoal);
                ArrayAdapter arrayAdapterGoalLose = new ArrayAdapter(this, R.layout.dropdown, goalLose);

                autoCompleteTextViewWeeklyGoal.setAdapter(arrayAdapterGoalLose);
                autoCompleteTextViewWeeklyGoal.setText(arrayAdapterGoalLose.getItem(0).toString(), false);

                autoCompleteTextViewWeeklyGoal.setOnItemClickListener((parent, view, position, id) -> {
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
                });
                break;
            default:
                autoCompleteTextViewWeeklyGoal.setText("Maintenance");
                break;

        }
        btnProceed.setEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_goal);


        autoCompleteTextViewActivity = findViewById(R.id.autoCompleteTextViewActivity);
        autoCompleteTextViewGoal = findViewById(R.id.autoCompleteTextViewGoal);
        autoCompleteTextViewWeeklyGoal = findViewById(R.id.autoCompleteTextViewWeeklyGoal);

        dropDownActivity();

        btnProceed = findViewById(R.id.btnProceed);
        btnProceed.setOnClickListener(v -> {

            SharedPreferences personActivityAndGoal = getSharedPreferences("personActivityAndGoal", MODE_PRIVATE);
            SharedPreferences.Editor editor = personActivityAndGoal.edit();

            Toast.makeText(PersonGoal.this, "Preferences recorded", Toast.LENGTH_SHORT).show();

            editor.putInt("activityLevel", activityChoice);
            editor.putInt("goal", goalChoice);

            if (weeklyGainChoice != 5) {
                editor.putInt("weeklyGainChoice", weeklyGainChoice);
            }
            if (weeklyLoseChoice != 5) {
                editor.putInt("weeklyLoseChoice", weeklyLoseChoice);
            }

            editor.apply();

            Intent intent = new Intent(PersonGoal.this, MainActivity.class);
            startActivity(intent);
            finishAffinity();
        });

    }
}