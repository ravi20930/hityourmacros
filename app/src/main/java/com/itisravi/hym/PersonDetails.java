package com.itisravi.hym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.textfield.TextInputLayout;

public class PersonDetails extends AppCompatActivity {

    private EditText editTextAge, editTextWeight, editTextHeight;
    private MaterialButton btnNext;
    private MaterialButtonToggleGroup maleFemaleToggle;

    private String gender = "gender";

    public void initViews() {
        editTextAge = findViewById(R.id.editTextAge);
        editTextWeight = findViewById(R.id.editTextWeight);
        editTextHeight = findViewById(R.id.editTextHeight);
        btnNext = findViewById(R.id.btnNext);
        maleFemaleToggle = findViewById(R.id.maleFemaleToggle);
    }

    public void maleFemaleToggle() {
        maleFemaleToggle.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                if (isChecked) {
                    switch (checkedId) {
                        case R.id.toggleMale:
                            gender = "Male";
                            break;
                        case R.id.toggleFemale:
                            gender = "Female";
                            break;
                    }
                }
            }
        });
    }

    public void onBtnProceedClick() {

        Boolean notOkay = editTextAge.getText().toString().trim().equals("")
                || editTextHeight.getText().toString().trim().equals("")
                || editTextWeight.getText().toString().trim().equals("")
                || gender.equals("gender");

            if (notOkay) {
                Toast.makeText(PersonDetails.this, "Error! field empty", Toast.LENGTH_SHORT).show();

            } else {
                int age = Integer.parseInt(editTextAge.getText().toString());
                float weight = Float.parseFloat(editTextWeight.getText().toString());
                float height = Float.parseFloat(editTextHeight.getText().toString());

                SharedPreferences personData = getSharedPreferences("personData", MODE_PRIVATE);
                SharedPreferences.Editor editor = personData.edit();


                editor.putInt("age", age);
                editor.putFloat("weight", weight);
                editor.putFloat("height", height);
                editor.putString("gender", gender);
                editor.apply();

                Intent intent = new Intent(PersonDetails.this, PersonGoal.class);
                startActivity(intent);
            }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_details);

        initViews();
        maleFemaleToggle();

        btnNext.setOnClickListener(v -> {
            onBtnProceedClick();
        });
    }
}