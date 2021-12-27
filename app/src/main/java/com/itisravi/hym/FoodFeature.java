package com.itisravi.hym;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FoodFeature extends AppCompatActivity {

    private DBAdapter db = new DBAdapter(this);
    private String foodName;
    private Button btnAdd;
    private double protein, carbs, fats, kcals, valueRound, finalProtein, finalCarbs, finalFats, finalKcals;
    private TextView txtViewFoodName, txtViewProtein, txtViewCarbs, txtViewFats, txtViewKcals, txtViewSize, btnDelete;
    private EditText editTextFoodServing;
    private int mealId, category;
    private String servingFix;
    private double serving;
    private String table;

    private void usePojo(String foodName) {
        db.open();

        String fields[] = new String[]{
                "category",
                "foodName",
                "carbs",
                "fats",
                "kcals",
                "protein",
                "size"
        };

        Cursor foodFeatureCursor = db.select("foodDB", "foodName", foodName);

        txtViewFoodName.setText(foodName);

        txtViewCarbs.setText(""+foodFeatureCursor.getDouble(foodFeatureCursor.getColumnIndex("carbs")));
        txtViewProtein.setText(""+foodFeatureCursor.getDouble(foodFeatureCursor.getColumnIndex("protein")));
        txtViewFats.setText(""+foodFeatureCursor.getDouble(foodFeatureCursor.getColumnIndex("fats")));
        txtViewKcals.setText(""+foodFeatureCursor.getDouble(foodFeatureCursor.getColumnIndex("kcals")));
        txtViewSize.setText(""+foodFeatureCursor.getString(foodFeatureCursor.getColumnIndex("size")));

        protein = foodFeatureCursor.getDouble(foodFeatureCursor.getColumnIndex("protein"));
        fats = foodFeatureCursor.getDouble(foodFeatureCursor.getColumnIndex("fats"));
        carbs = foodFeatureCursor.getDouble(foodFeatureCursor.getColumnIndex("carbs"));
        kcals = foodFeatureCursor.getDouble(foodFeatureCursor.getColumnIndex("kcals"));

        category = foodFeatureCursor.getInt(foodFeatureCursor.getColumnIndex("category"));

        db.close();
    }

    private void initViews() {
        txtViewFoodName = findViewById(R.id.txtViewFoodName);
        txtViewProtein = findViewById(R.id.txtViewProtein);
        txtViewCarbs = findViewById(R.id.txtViewCarbs);
        txtViewFats = findViewById(R.id.txtViewFats);
        txtViewKcals = findViewById(R.id.txtViewKcals);
        txtViewSize = findViewById(R.id.txtViewSize);
        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);
        editTextFoodServing = findViewById(R.id.editTextFoodServing);
    }

    private void calculateMacros(TextView txtView, double initMacro) {

        if (editTextFoodServing.getText().toString().trim().length()>0) {


            if (editTextFoodServing.getText().toString().trim().charAt(0)=='.') {
                serving = Double.parseDouble(servingFix);
            }
            else {
                serving = Double.parseDouble(editTextFoodServing.getText().toString().trim());
            }

            double value = serving*initMacro;
            valueRound = Math.floor(value*10)/10;
            txtView.setText(String.valueOf(valueRound));

            if (initMacro==protein) {
                finalProtein=valueRound;
            }
            else if (initMacro==carbs) {
                finalCarbs=valueRound;
            }
            else if (initMacro==fats) {
                finalFats=valueRound;
            }
            else if (initMacro==kcals) {
                finalKcals=valueRound;
            }
        }
    }

    private void btnAddClick(String tableName) {

        if (editTextFoodServing.getText().toString().equals("1")) {
            db.open();
            db.insert(tableName,
                      "_id, mealId, food, kcals, protein, carbs, fats",
                      "NULL, "+mealId+", '"+foodName+"', "+kcals+", "+protein+","+carbs+", "+fats+"");
        }
        else {
            db.open();
            db.insert(tableName,
                      "_id, mealId, food, kcals, protein, carbs, fats",
                      "NULL, "+mealId+", '"+foodName+"', "+finalKcals+", "+finalProtein+", "+finalCarbs+", "+finalFats+"");
        }
        db.close();
        Toast.makeText(this, foodName+" added", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(FoodFeature.this, MainActivity.class);
        intent.putExtra("code", 2);
        intent.putExtra("tableName", tableName);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finishAffinity();
    }

    private void btnDeleteClick() {
        db.open();
        db.delete("foodDB", foodName);
        db.close();
        Toast.makeText(this, ""+foodName+" deleted from your food list", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(FoodFeature.this, MainActivity.class);
        intent.putExtra("code", 2);
        intent.putExtra("tableName", table);
        startActivity(intent);
        finishAffinity();
    }

    private TextWatcher servingWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (editTextFoodServing.getText().toString().isEmpty()) {
                txtViewKcals.setText("0");
                txtViewProtein.setText("0");
                txtViewFats.setText("0");
                txtViewCarbs.setText("0");
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

            if (editTextFoodServing.getText().toString().trim().length()>0) {
                if (editTextFoodServing.getText().toString().charAt(0)=='.') {
                    servingFix = "0"+editTextFoodServing.getText().toString();
                }
            }

            calculateMacros(txtViewCarbs, carbs);
            calculateMacros(txtViewFats, fats);
            calculateMacros(txtViewProtein, protein);
            calculateMacros(txtViewKcals, kcals);

            btnAdd.setEnabled(!editTextFoodServing.getText().toString().isEmpty()
                    && !editTextFoodServing.getText().toString().equals("0")
                    && !editTextFoodServing.getText().toString().equals("0.")
                    && !editTextFoodServing.getText().toString().equals("0.0")
                    && !editTextFoodServing.getText().toString().equals("00"));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_feature);

        Intent intent = getIntent();
        foodName = intent.getStringExtra("foodName");
        mealId = intent.getIntExtra("mealId", 555);
        table = intent.getStringExtra("tableName");
        initViews();

        usePojo(foodName);

        editTextFoodServing.addTextChangedListener(servingWatcher);

        btnAdd.setOnClickListener(v -> btnAddClick(table));

        if (category==1) {
            btnDelete.setVisibility(View.VISIBLE);
            btnDelete.setOnClickListener(v -> {
                AlertDialog.Builder alertDialogClear = new AlertDialog.Builder(FoodFeature.this);
                alertDialogClear.setTitle("Delete Food Item")
                        .setMessage(foodName+" will be deleted from your food database, are you sure?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                btnDeleteClick();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
                alertDialogClear.create();
            });
        } else {
            btnDelete.setVisibility(View.GONE);
        }

    }
}