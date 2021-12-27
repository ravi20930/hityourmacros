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

public class WorkoutPlan extends AppCompatActivity {

    int planId;
    TextView planName, planDetails, planSubtitle;
    RecyclerView exerciseRecyclerView;
    ImageView planImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_plan);

        Intent intent = getIntent();
        planId = intent.getIntExtra("planId", 0);

        planName = findViewById(R.id.planName);
        planSubtitle = findViewById(R.id.planSubtitle);
        planDetails = findViewById(R.id.planDetails);
        planImage = findViewById(R.id.planImage);

        setFields();

    }

    private void setFields() {
        switch (planId) {
            case 1:
                Glide.with(this)
                        .load("https://firebasestorage.googleapis.com/v0/b/hityourmacros.appspot.com/o/workoutImages%2Fimage%20(24).jpg?alt=media&token=138b4293-1c53-4b1e-91bc-501198c51d93")
                        .into(planImage);
                planName.setText(" muscle & strength ");
                planSubtitle.setText("Training to increase strength & build muscles");
                planDetails.setText(R.string.plan1);
                populateExerciseList(1);
                break;
            case 2:
                Glide.with(this)
                        .load("https://firebasestorage.googleapis.com/v0/b/hityourmacros.appspot.com/o/workoutImages%2Fimage%20(25).jpg?alt=media&token=59e37145-3736-43a7-af20-d16f1836e447")
                        .into(planImage);
                planName.setText(" ultimate fat-loss ");
                planSubtitle.setText("Build strength & lose as much fat as possible");
                planDetails.setText(R.string.plan2);
                populateExerciseList(2);
                break;
            case 3:
                Glide.with(this)
                        .load("https://firebasestorage.googleapis.com/v0/b/hityourmacros.appspot.com/o/workoutImages%2Fimage%20(19).jpg?alt=media&token=3b9627d0-5973-4d61-b091-fb6dba9c5c64")
                        .into(planImage);
                planName.setText(" muscle hypertrophy ");
                planSubtitle.setText("Training to add as much muscle as possible");
                planDetails.setText(R.string.plan3);
                populateExerciseList(3);
                break;
            case 4:
                Glide.with(this)
                        .load("https://firebasestorage.googleapis.com/v0/b/hityourmacros.appspot.com/o/workoutImages%2Fimage%20(10).jpg?alt=media&token=c3225423-e73f-4167-a45b-68d3bde4774e")
                        .into(planImage);
                planName.setText(" beginners' 4 week ");
                planSubtitle.setText("To build strength and lose fat");
                planDetails.setText(R.string.plan4);
                populateExerciseList(4);
                break;
        }
    }

    private void populateExerciseList(int id) {
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
        exerciseRecyclerView = findViewById(R.id.exerciseRecyclerView);
        exerciseRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        exerciseRecyclerView.setAdapter(planExerciseListAdapter);
    }
}