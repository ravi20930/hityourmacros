package com.itisravi.hym;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Profile extends Fragment {

    View view;
    Button btnUpdateWeight, btnWeightConfirm, btnSetGoalWeight, btnGoalWeightConfirm;
    int age;
    float weight, height;
    String gender;
    EditText editTextUpdateWeight, editTextGoalWeight;
    TextView txtViewEditProfile, txtViewGender, txtViewAge, txtViewWeight, txtViewHeight,
            txtViewActivityLevel, txtViewWeeklyGoal, txtViewWt, txtViewWf, txtViewGoal;
    int activityLevel, goal, weeklyGainChoice, weeklyLoseChoice;

    private void showToast(String msg) {
        Toast.makeText(getActivity().getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    private void initViews() {
        txtViewGender = view.findViewById(R.id.txtViewGender);
        txtViewAge = view.findViewById(R.id.txtViewAge);
        txtViewWeight = view.findViewById(R.id.txtViewWeight);
        txtViewHeight = view.findViewById(R.id.txtViewHeight);
        txtViewActivityLevel = view.findViewById(R.id.txtViewActivityLevel);
        txtViewWt = view.findViewById(R.id.txtViewWt);
        txtViewWf = view.findViewById(R.id.txtViewWf);
        txtViewEditProfile = view.findViewById(R.id.txtViewEditProfile);
        txtViewWeeklyGoal = view.findViewById(R.id.txtViewWeeklyGoal);
        txtViewGoal = view.findViewById(R.id.txtViewGoal);
        btnUpdateWeight = view.findViewById(R.id.btnUpdateWeight);
        btnWeightConfirm = view.findViewById(R.id.btnWeightConfirm);
        editTextUpdateWeight = view.findViewById(R.id.editTextUpdateWeight);
        btnSetGoalWeight = view.findViewById(R.id.btnSetGoalWeight);
        editTextGoalWeight = view.findViewById(R.id.editTextGoalWeight);
        btnGoalWeightConfirm = view.findViewById(R.id.btnGoalWeightConfirm);
    }

    private void setTexts() {
        SharedPreferences personData = getActivity().getSharedPreferences("personData", getActivity().MODE_PRIVATE);
        SharedPreferences personActivityAndGoal = getActivity().getSharedPreferences("personActivityAndGoal", getActivity().MODE_PRIVATE);
        txtViewGender.setText(personData.getString("gender", null));
        txtViewWeight.setText(String.valueOf(personData.getFloat("weight", 0)));
        txtViewHeight.setText(String.valueOf(personData.getFloat("height", 0)));
        txtViewAge.setText(String.valueOf(personData.getInt("age", 0)));
        txtViewWt.setText(String.valueOf(personData.getFloat("weight", 0)));
        txtViewWf.setText(""+personActivityAndGoal.getFloat("goalWeight", 0));
    }

    private void setTxtViewActivityLevel() {
        switch (activityLevel) {
            case 0:
                txtViewActivityLevel.setText("Sedentary");
                break;
            case 1:
                txtViewActivityLevel.setText("Light Ex.");
                break;
            case 2:
                txtViewActivityLevel.setText("Mod. Ex.");
                break;
            case 3:
                txtViewActivityLevel.setText("Very active");
                break;
            case 4:
                txtViewActivityLevel.setText("Extra active");
                break;
        }
    }

    private void setTxtViewGoal() {
        switch (goal) {
            case 0:
                txtViewGoal.setText("Maintenance");
                break;
            case 1:
                txtViewGoal.setText("Gain weight");
                break;
            case 2:
                txtViewGoal.setText("Lose weight");
                break;
        }
    }

    private void setTxtViewWeeklyGoal() {
        if (goal==1) {
            switch (weeklyGainChoice) {
                case 0:
                    txtViewWeeklyGoal.setText("Gain 0.5kg");
                    break;
                case 1:
                    txtViewWeeklyGoal.setText("Gain 0.25kg");
                    break;
            }
        }
        else if (goal==2) {
            switch (weeklyLoseChoice) {
                case 0:
                    txtViewWeeklyGoal.setText("Lose 1kg");
                    break;
                case 1:
                    txtViewWeeklyGoal.setText("Lose 0.5kg");
                    break;
                case 2:
                    txtViewWeeklyGoal.setText("Lose 0.25kg");
                    break;

            }
        }
        else txtViewWeeklyGoal.setText("Maintain");
    }

    private void btnUpdateWeight() {
        btnUpdateWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextUpdateWeight.setVisibility(View.VISIBLE);
                editTextUpdateWeight.requestFocus();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(editTextUpdateWeight, InputMethodManager.SHOW_IMPLICIT);
                editTextUpdateWeight.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        btnWeightConfirm.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
    }

    private void btnWeightConfirm() {
        btnWeightConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences personData = getActivity().getSharedPreferences("personData", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = personData.edit();
                editor.putFloat("weight", Float.parseFloat(editTextUpdateWeight.getText().toString()));
                editor.apply();
                editTextUpdateWeight.setVisibility(View.INVISIBLE);
                btnWeightConfirm.setVisibility(View.INVISIBLE);
                setTexts();
                showToast("Weight updated successfully");
            }
        });

    }

    private void btnSetGoalWeight() {
        btnSetGoalWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextGoalWeight.setVisibility(View.VISIBLE);
                editTextGoalWeight.requestFocus();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(editTextGoalWeight, InputMethodManager.SHOW_IMPLICIT);
                editTextGoalWeight.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        btnGoalWeightConfirm.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
        btnGoalWeightConfirm();
    }

    private void btnGoalWeightConfirm() {
        btnGoalWeightConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences personActivityAndGoal = getActivity().getSharedPreferences("personActivityAndGoal", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = personActivityAndGoal.edit();
                editor.putFloat("goalWeight", Float.parseFloat(editTextGoalWeight.getText().toString()));
                editor.apply();
                txtViewWf.setText(String.valueOf(personActivityAndGoal.getFloat("goalWeight", 0)));
                editTextGoalWeight.setVisibility(View.INVISIBLE);
                btnGoalWeightConfirm.setVisibility(View.INVISIBLE);
                showToast("Goal weight updated successfully");
            }
        });
    }

    public Profile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        SharedPreferences personData = this.getActivity().getSharedPreferences("personData", Context.MODE_PRIVATE);
        SharedPreferences personActivityAndGoal = this.getActivity().getSharedPreferences("personActivityAndGoal", Context.MODE_PRIVATE);

        age = personData.getInt("age", 0);
        weight = personData.getFloat("weight", 0);
        height = personData.getFloat("height", 0);
        gender = personData.getString("gender", null);

        activityLevel = personActivityAndGoal.getInt("activityLevel", 0);
        goal = personActivityAndGoal.getInt("goal", 0);
        weeklyGainChoice = personActivityAndGoal.getInt("weeklyGainChoice",0);
        weeklyLoseChoice = personActivityAndGoal.getInt("weeklyLoseChoice", 0);

        initViews();
        setTxtViewActivityLevel();
        setTxtViewGoal();
        setTxtViewWeeklyGoal();
        btnUpdateWeight();
        btnWeightConfirm();
        btnSetGoalWeight();

        setTexts();

        txtViewEditProfile.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ProfileEditor.class);
            startActivity(intent);
        });


        return view;
    }
}