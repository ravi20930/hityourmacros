package com.itisravi.hym;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class Track extends Fragment {

    View view;
    TextView txtViewFinalCal, meal2Data, meal3Data, meal4Data, meal1Data,
            meal1Cals, meal2Cals, meal3Cals, meal4Cals, txtViewCalsFood, txtViewLeftCal,
            btnAddMeal1, btnAddMeal2, btnAddMeal3, btnAddMeal4;
    SharedPreferences personMacros;
    int goalCals;
    String table = "foodEaten";

    RecyclerView recyclerViewMeal1, recyclerViewMeal2, recyclerViewMeal3, recyclerViewMeal4;
    MaterialToolbar trackToolbar;
    Button switchDay, clearDay;


    public void populateListRecyclerView(String tableName, int mealId) {
        DBAdapter db = new DBAdapter(getActivity());
        db.open();

        String fields[] = new String[]{
                "_id",
                "food",
                "kcals"
        };

        Cursor foodEatenCursor = db.select("" + tableName + "", fields, "mealId", mealId);

        ArrayList<FoodEatenModel> arrayList = new ArrayList<>();

        int categoriesCount = foodEatenCursor.getCount();
        for (int i = 0; i < categoriesCount; i++) {
            int _id = foodEatenCursor.getInt(foodEatenCursor.getColumnIndex("_id"));
            String food = foodEatenCursor.getString(foodEatenCursor.getColumnIndex("food"));
            double kcals = foodEatenCursor.getDouble(foodEatenCursor.getColumnIndex("kcals"));

            FoodEatenModel foodEatenModel = new FoodEatenModel(_id, food, kcals);
            arrayList.add(foodEatenModel);
            foodEatenCursor.moveToNext();
            db.close();
        }
        FoodAdapterRecycle foodAdapter = new FoodAdapterRecycle(getContext(), arrayList);

        switch (mealId) {
            case 1:
                recyclerViewMeal1.setAdapter(foodAdapter);
                swipeAction(recyclerViewMeal1, 1);
                break;
            case 2:
                recyclerViewMeal2.setAdapter(foodAdapter);
                swipeAction(recyclerViewMeal2, 2);
                break;
            case 3:
                recyclerViewMeal3.setAdapter(foodAdapter);
                swipeAction(recyclerViewMeal3, 3);
                break;
            case 4:
                recyclerViewMeal4.setAdapter(foodAdapter);
                swipeAction(recyclerViewMeal4, 4);
                break;
        }
    }

    private void swipeAction(RecyclerView recyclerViewId, int mealId) {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                removeFood(table, (int) viewHolder.itemView.getTag());
                populateListRecyclerView(table, mealId);
                switch (mealId) {
                    case 1:
                        mealDataSet(meal1Cals, meal1Data, 1);
                        break;
                    case 2:
                        mealDataSet(meal2Cals, meal2Data, 2);
                        break;
                    case 3:
                        mealDataSet(meal3Cals, meal3Data, 3);
                        break;
                    case 4:
                        mealDataSet(meal4Cals, meal4Data, 4);
                        break;
                }
                setGoal();
            }
        }).attachToRecyclerView(recyclerViewId);
    }

    private void removeFood(String tableName, int _id) {
        DBAdapter db = new DBAdapter(getContext());
        db.open();
        db.delete(tableName, "_id", _id);
        db.close();
        Toast.makeText(getContext(), "deleted!", Toast.LENGTH_SHORT).show();
    }

    private void initViews() {

        btnAddMeal1 = view.findViewById(R.id.btnAddMeal1);
        btnAddMeal2 = view.findViewById(R.id.btnAddMeal2);
        btnAddMeal3 = view.findViewById(R.id.btnAddMeal3);
        btnAddMeal4 = view.findViewById(R.id.btnAddMeal4);

        txtViewFinalCal = view.findViewById(R.id.txtViewFinalCal);
        txtViewCalsFood = view.findViewById(R.id.txtViewCalsFood);
        txtViewLeftCal = view.findViewById(R.id.txtViewLeftCal);

        recyclerViewMeal1 = view.findViewById(R.id.recyclerViewMeal1);
        recyclerViewMeal1.setLayoutManager(new LinearLayoutManager(getActivity()));


        recyclerViewMeal2 = view.findViewById(R.id.recyclerViewMeal2);
        recyclerViewMeal2.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerViewMeal3 = view.findViewById(R.id.recyclerViewMeal3);
        recyclerViewMeal3.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerViewMeal4 = view.findViewById(R.id.recyclerViewMeal4);
        recyclerViewMeal4.setLayoutManager(new LinearLayoutManager(getActivity()));


        meal1Cals = view.findViewById(R.id.meal1Cals);
        meal2Cals = view.findViewById(R.id.meal2Cals);
        meal3Cals = view.findViewById(R.id.meal3Cals);
        meal4Cals = view.findViewById(R.id.meal4Cals);

        meal1Data = view.findViewById(R.id.meal1Data);
        meal2Data = view.findViewById(R.id.meal2Data);
        meal3Data = view.findViewById(R.id.meal3Data);
        meal4Data = view.findViewById(R.id.meal4Data);

    }

    private void btnListeners() {
        btnAddMeal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), FoodList.class);
                intent.putExtra("mealId", 1);
                intent.putExtra("tableName", table);
                startActivity(intent);
            }
        });

        btnAddMeal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FoodList.class);
                intent.putExtra("mealId", 2);
                intent.putExtra("tableName", table);
                startActivity(intent);
            }
        });

        btnAddMeal3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FoodList.class);
                intent.putExtra("mealId", 3);
                intent.putExtra("tableName", table);
                startActivity(intent);
            }
        });

        btnAddMeal4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FoodList.class);
                intent.putExtra("mealId", 4);
                intent.putExtra("tableName", table);
                startActivity(intent);
            }
        });
    }

    private void mealDataSet(TextView cals, TextView macroData, int mealId) {

        int totalCalsEaten = getMealMacros(table, "kcals", mealId);

        int totalProteinEaten = getMealMacros(table, "protein", mealId);
        int totalCarbsEaten = getMealMacros(table, "carbs", mealId);
        int totalFatsEaten = getMealMacros(table, "fats", mealId);

        cals.setText("" + totalCalsEaten);
        macroData.setText("p: " + totalProteinEaten + "  c: " + totalCarbsEaten + "  f: " + totalFatsEaten);

        if (totalCalsEaten == 0) {
            cals.setText("--");
        }

        if (totalProteinEaten == 0) {
            macroData.setText("no food here!");
        }
    }

    private int getTotalMacros(String tableName, String column) {
        DBAdapter db = new DBAdapter(getContext());
        db.open();
        Cursor cursor = db.sum(tableName, column);
        int sum = cursor.getInt(cursor.getColumnIndex("Total"));
        db.close();
        return sum;
    }

    private int getMealMacros(String tableName, String column, int mealId) {
        DBAdapter db = new DBAdapter(getContext());
        db.open();
        Cursor cursor = db.sum(tableName, column, "mealId", mealId);
        int sum = cursor.getInt(cursor.getColumnIndex("Total"));
        db.close();
        return sum;
    }

    private void setGoal() {
        goalCals = personMacros.getInt("finalCals", 0);
        txtViewFinalCal.setText("" + goalCals);
        txtViewCalsFood.setText("" + getTotalMacros(table, "kcals"));
        txtViewLeftCal.setText("" + (goalCals - getTotalMacros(table, "kcals")));
    }

    public Track() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_track, container, false);
        switchDay = view.findViewById(R.id.switchDay);

        if (getArguments() != null) {
            table = getArguments().getString("tableName");
            if (table.equals("foodEatenYesterday")) {
                switchDay.setText("Today");
            }
        }

        initViews();
        trackToolbar = view.findViewById(R.id.trackToolbar);

        personMacros = getActivity().getSharedPreferences("personMacros", getActivity().MODE_PRIVATE);
        populate();
        toolbarActions();
        return view;
    }

    private void toolbarActions() {
        clearDay = view.findViewById(R.id.clearDay);


        switchDay.setOnClickListener(v -> switchDiary());
        clearDay.setOnClickListener(v -> {
            AlertDialog.Builder alertDialogClear = new AlertDialog.Builder(getContext());
            alertDialogClear.setTitle("Clear Entries")
                    .setMessage("All entries for this day will be cleared, are you sure?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            clearDay(table);
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            }).show();
            alertDialogClear.create();
        });
    }

    private void clearDay(String tableName) {
        DBAdapter db = new DBAdapter(getContext());
        db.open();
        db.emptyTable(tableName);
        db.close();
        Toast.makeText(getContext(), "diary cleared", Toast.LENGTH_SHORT).show();
        populate();
//        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new Track()).commit();
    }

    private void switchDiary() {

        if (switchDay.getText().toString().equals("Yesterday")) {
            switchDay.setText("Today");
            table = "foodEatenYesterday";
            populate();
        } else if (switchDay.getText().toString().equals("Today")) {
            switchDay.setText("Yesterday");
            table = "foodEaten";
            populate();
        }
    }

    private void populate() {

        populateListRecyclerView(table, 1);
        populateListRecyclerView(table, 2);
        populateListRecyclerView(table, 3);
        populateListRecyclerView(table, 4);

        mealDataSet(meal1Cals, meal1Data, 1);
        mealDataSet(meal2Cals, meal2Data, 2);
        mealDataSet(meal3Cals, meal3Data, 3);
        mealDataSet(meal4Cals, meal4Data, 4);

        setGoal();

        btnListeners();
    }
}
