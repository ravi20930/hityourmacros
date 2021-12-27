package com.itisravi.hym;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AbsWorkoutLayout extends AppCompatActivity {

    RecyclerView abCircuit1List, abCircuit2List, abCircuit3List;
    TextView absPlanDetails;
    ImageView absPlanImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abs_workout_layout);

        abCircuit1List = findViewById(R.id.abCircuit1List);
        abCircuit2List = findViewById(R.id.abCircuit2List);
        abCircuit3List = findViewById(R.id.abCircuit3List);
        absPlanDetails = findViewById(R.id.absPlanDetails);
        absPlanImage = findViewById(R.id.absPlanImage);

        absPlanDetails.setText(R.string.absPlan);

        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/hityourmacros.appspot.com/o/workoutImages%2Fimage%20(26).jpg?alt=media&token=57d68240-baa0-43d9-809f-2de8381db528")
                .into(absPlanImage);

        populateExerciseList(abCircuit1List, 5);
        populateExerciseList(abCircuit2List, 6);
        populateExerciseList(abCircuit3List, 7);

    }
    private void populateExerciseList(RecyclerView recyclerView, int id) {

        DBAdapter db = new DBAdapter(this);
        db.open();

        String fields[] = new String[]{
                "typeId",
                "name",
                "sets",
                "reps",
                "rest",
                "load"
        };

        Cursor exerciseListCursor = db.select("planExerciseList", fields, "typeId", id);

        ArrayList<ExerciseModel> arrayList = new ArrayList<>();

        int categoriesCount = exerciseListCursor.getCount();
        for (int i = 0; i < categoriesCount; i++) {
            int typeId = exerciseListCursor.getInt(exerciseListCursor.getColumnIndex("typeId"));
            String name = exerciseListCursor.getString(exerciseListCursor.getColumnIndex("name"));
            int sets = exerciseListCursor.getInt(exerciseListCursor.getColumnIndex("sets"));
            int reps = exerciseListCursor.getInt(exerciseListCursor.getColumnIndex("reps"));
            int rest = exerciseListCursor.getInt(exerciseListCursor.getColumnIndex("rest"));
            String load = exerciseListCursor.getString(exerciseListCursor.getColumnIndex("load"));

            ExerciseModel exerciseModel = new ExerciseModel(typeId, name, sets, reps, rest, load);
            arrayList.add(exerciseModel);

            exerciseListCursor.moveToNext();
            db.close();
        }
        PlanExerciseListAdapter planExerciseListAdapter = new PlanExerciseListAdapter(this, arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(planExerciseListAdapter);
    }
}