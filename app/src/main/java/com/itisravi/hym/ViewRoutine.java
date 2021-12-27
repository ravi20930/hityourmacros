package com.itisravi.hym;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class ViewRoutine extends AppCompatActivity {

    TextView routineName, routineDetails, titleDay5, titleDay4, titleDay3, titleDay2, titleDay1, notes;
    int id;
    MaterialCardView cardDay3, cardDay4, cardDay5;
    RecyclerView day1List, day2List, day3List, day4List, day5List;

    private void initViews() {
        cardDay3 = findViewById(R.id.cardDay3);
        cardDay4 = findViewById(R.id.cardDay4);
        cardDay5 = findViewById(R.id.cardDay5);

        day1List = findViewById(R.id.day1List);
        day2List = findViewById(R.id.day2List);
        day3List = findViewById(R.id.day3List);
        day4List = findViewById(R.id.day4List);
        day5List = findViewById(R.id.day5List);

        titleDay5 = findViewById(R.id.titleDay5);
        titleDay4 = findViewById(R.id.titleDay4);
        titleDay3 = findViewById(R.id.titleDay3);
        titleDay2 = findViewById(R.id.titleDay2);
        titleDay1 = findViewById(R.id.titleDay1);

        notes = findViewById(R.id.notes);
    }

    private void setTitles() {
        routineName = findViewById(R.id.routineName);
        routineDetails = findViewById(R.id.routineDetails);

        switch (id) {
            case 0:
                routineName.setText("Classic Push Pull Leg");
                routineDetails.setText("Build insane mass with this PPL routine");

                titleDay1.setText("Push Day");
                populateExerciseList(day1List, "ppl", 1);

                titleDay2.setText("Pull Day");
                populateExerciseList(day2List, "ppl", 2);

                titleDay3.setText("Leg Day");
                populateExerciseList(day3List, "ppl", 3);

                cardDay4.setVisibility(View.GONE);
                cardDay5.setVisibility(View.GONE);
                notes.setText("Week Pattern: P P L P P L R (R is Rest day)");
                break;
            case 1:
                routineName.setText("Bro Split");
                routineDetails.setText("World's most popular split routine");

                titleDay1.setText("Chest Day");
                populateExerciseList(day1List, "bs", 1);

                titleDay2.setText("Back Day");
                populateExerciseList(day2List, "bs", 2);

                titleDay3.setText("Delts and Traps Day");
                populateExerciseList(day3List, "bs", 3);

                titleDay4.setText("Leg Day");
                populateExerciseList(day4List, "bs", 4);

                titleDay5.setText("Arms Day");
                populateExerciseList(day5List, "bs", 5);

                notes.setText("6th day will be Cardio/Running followed by a Rest day");
                break;
            case 2:
                routineName.setText("Full Body Workout");
                routineDetails.setText("Maintain size and build muscles slowly");

                titleDay1.setText("Workout A");
                populateExerciseList(day1List, "fb", 1);

                titleDay2.setText("Workout B");
                populateExerciseList(day2List, "fb", 2);

                cardDay3.setVisibility(View.GONE);
                cardDay4.setVisibility(View.GONE);
                cardDay5.setVisibility(View.GONE);

                notes.setText("Week Pattern: A R B R A R B (R is Rest day)");
                break;
            case 3:
                routineName.setText("Hybrid Split");
                routineDetails.setText("This routine is best when you are Bulking up");

                titleDay1.setText("Upper Body");
                populateExerciseList(day1List, "hs", 1);

                titleDay2.setText("Lower Body");
                populateExerciseList(day2List, "hs", 2);

                titleDay3.setText("Pull");
                populateExerciseList(day3List, "hs", 3);

                titleDay4.setText("Legs");
                populateExerciseList(day4List, "hs", 4);

                titleDay5.setText("Push");
                populateExerciseList(day5List, "hs", 5);

                notes.setText("6th day will be Cardio/Running followed by a Rest day");
                break;
        }
    }

    private void setupInsertToExerciseList(String values) {
        DBAdapter db = new DBAdapter(this);
        db.open();
        db.insert("routineExerciseList",
                  "_id, routineName, day, exName, setsR, repsR",
                values);
        db.close();
    }

    private void insertAllExercise() {

        DBAdapter db = new DBAdapter(this);
        db.open();

        if (db.count("routineExerciseList") < 1) {
//        ppl day 1
            setupInsertToExerciseList("NULL, 'ppl', 1, 'Incline DB Press', 4, '8-12'");
            setupInsertToExerciseList("NULL, 'ppl', 1, 'Flat DB press', 4, '8-12'");
            setupInsertToExerciseList("NULL, 'ppl', 1, 'Pec-Dec Flies', 3, '8-12'");
            setupInsertToExerciseList("NULL, 'ppl', 1, 'Cable Press', 3, '8-12'");
            setupInsertToExerciseList("NULL, 'ppl', 1, 'Arnold Press', 4, '8-12'");
            setupInsertToExerciseList("NULL, 'ppl', 1, 'Lateral Raise', 4, '12-15'");
            setupInsertToExerciseList("NULL, 'ppl', 1, 'Skull Crushers', 4, '8-12'");
            setupInsertToExerciseList("NULL, 'ppl', 1, 'Triceps Pushdown', 4, '10-12'");
            setupInsertToExerciseList("NULL, 'ppl', 1, 'Dips', 3, '6-10'");
//        ppl day 2
            setupInsertToExerciseList("NULL, 'ppl', 2, 'Pull-Ups', 3, '5-12'");
            setupInsertToExerciseList("NULL, 'ppl', 2, 'Lat Pulldown', 4, '8-12'");
            setupInsertToExerciseList("NULL, 'ppl', 2, 'BB Rows', 3, '6-12'");
            setupInsertToExerciseList("NULL, 'ppl', 2, 'Seated Rows', 4, '12-15'");
            setupInsertToExerciseList("NULL, 'ppl', 2, 'T-Bar', 3, '12-15'");
            setupInsertToExerciseList("NULL, 'ppl', 2, 'Alt. DB Curls', 3, '10-12'");
            setupInsertToExerciseList("NULL, 'ppl', 2, 'Cable Biceps Curl', 3, '10-12'");
            setupInsertToExerciseList("NULL, 'ppl', 2, 'Incline DB Curls', 3, '10-12'");
            setupInsertToExerciseList("NULL, 'ppl', 2, 'Alt. Close/Wide BB Curls', 5, '8-10'");
//        ppl day 3
            setupInsertToExerciseList("NULL, 'ppl', 3, 'BB Squats', 4, '6-10'");
            setupInsertToExerciseList("NULL, 'ppl', 3, 'Leg Press', 4, '8-12'");
            setupInsertToExerciseList("NULL, 'ppl', 3, 'Leg Extensions', 4, '15-18'");
            setupInsertToExerciseList("NULL, 'ppl', 3, 'Leg Curls', 4, '8-12'");
            setupInsertToExerciseList("NULL, 'ppl', 3, 'Calf Raises', 4, '12-15'");
//        bs day 1
            setupInsertToExerciseList("NULL, 'bs', 1, 'Flat DB Press', 4, '8-12'");
            setupInsertToExerciseList("NULL, 'bs', 1, 'Incline DB Press', 4, '8-12'");
            setupInsertToExerciseList("NULL, 'bs', 1, 'Pec-Dec Flies', 3, '8-12'");
            setupInsertToExerciseList("NULL, 'bs', 1, 'Dips', 3, '6-10'");
            setupInsertToExerciseList("NULL, 'bs', 1, 'High Cable Press', 4, '8-12'");
//            bs day 2
            setupInsertToExerciseList("NULL, 'bs', 2, 'Pull-Ups', 3, '6-10'");
            setupInsertToExerciseList("NULL, 'bs', 2, 'BB Rows', 4, '8-12'");
            setupInsertToExerciseList("NULL, 'bs', 2, 'Lat Pulldown', 4, '8-12'");
            setupInsertToExerciseList("NULL, 'bs', 2, 'T-Bar', 4, '12-15'");
            setupInsertToExerciseList("NULL, 'bs', 2, 'Seated Cable Rows', 4, '12-15'");
//            bs day 3
            setupInsertToExerciseList("NULL, 'bs', 3, 'Arnold Press', 4, '8-12'");
            setupInsertToExerciseList("NULL, 'bs', 3, 'Front Raise', 4, '10-15'");
            setupInsertToExerciseList("NULL, 'bs', 3, 'Lateral Raise', 4, '10-15'");
            setupInsertToExerciseList("NULL, 'bs', 3, 'Face-Pulls', 4, '8-12'");
            setupInsertToExerciseList("NULL, 'bs', 3, 'BB/DB Shrugs', 4, '12-15'");
//            bs day 4
            setupInsertToExerciseList("NULL, 'bs', 4, 'Squats', 5, '6-12'");
            setupInsertToExerciseList("NULL, 'bs', 4, 'Good-Morning', 4, '8-12'");
            setupInsertToExerciseList("NULL, 'bs', 4, 'Leg Press', 4, '8-12'");
            setupInsertToExerciseList("NULL, 'bs', 4, 'Leg Extensions', 4, '12-15'");
            setupInsertToExerciseList("NULL, 'bs', 4, 'Ham-Curls', 4, '8-12'");
            setupInsertToExerciseList("NULL, 'bs', 4, 'Calf-Raises', 4, '10-12'");
            setupInsertToExerciseList("NULL, 'bs', 4, 'Any AB Circuit', 1, '-'");
//            bs day 5
            setupInsertToExerciseList("NULL, 'bs', 5, 'EZ Bar Curls', 4, '8-12'");
            setupInsertToExerciseList("NULL, 'bs', 5, 'Tricep Dips', 4, '10-15'");
            setupInsertToExerciseList("NULL, 'bs', 5, 'Cable Bicep Curls', 4, '8-12'");
            setupInsertToExerciseList("NULL, 'bs', 5, 'Skull Crushers', 4, '8-12'");
            setupInsertToExerciseList("NULL, 'bs', 5, 'Incline DB Curls', 4, '8-12'");
            setupInsertToExerciseList("NULL, 'bs', 5, 'Rope Push-Downs', 4, '8-12'");
//            fb day 1
            setupInsertToExerciseList("NULL, 'fb', 1, 'Romanian Deadlift', 4, '6-10'");
            setupInsertToExerciseList("NULL, 'fb', 1, 'Squats', 4, '6-10'");
            setupInsertToExerciseList("NULL, 'fb', 1, 'Pull-Ups', 3, '6-10'");
            setupInsertToExerciseList("NULL, 'fb', 1, 'Lat Pulldown', 4, '8-12'");
            setupInsertToExerciseList("NULL, 'fb', 1, 'Rows', 4, '8-12'");
            setupInsertToExerciseList("NULL, 'fb', 1, 'Bench Press', 4, '8-12'");
            setupInsertToExerciseList("NULL, 'fb', 1, 'Overhead Press', 3, '6-10'");
            setupInsertToExerciseList("NULL, 'fb', 1, 'Skull Crushers', 4, '8-12'");
            setupInsertToExerciseList("NULL, 'fb', 1, 'Alt. Close/Wide BB Curls', 4, '8-12'");
//            fb day 2
            setupInsertToExerciseList("NULL, 'fb', 2, 'Incline Bench Press', 4, '8-12'");
            setupInsertToExerciseList("NULL, 'fb', 2, 'Close grip Press', 3, '8-12'");
            setupInsertToExerciseList("NULL, 'fb', 2, 'Dips', 3, '6-10'");
            setupInsertToExerciseList("NULL, 'fb', 2, 'Arnold Press', 3, '8-12'");
            setupInsertToExerciseList("NULL, 'fb', 2, 'Pull-Ups/Chin-Ups', 3, '6-10'");
            setupInsertToExerciseList("NULL, 'fb', 2, 'Seated Rows', 4, '12-15'");
            setupInsertToExerciseList("NULL, 'fb', 2, 'One-Arm DB Rows', 3, '10-12'");
            setupInsertToExerciseList("NULL, 'fb', 2, 'Hamstring-Curls', 3, '8-12'");
            setupInsertToExerciseList("NULL, 'fb', 2, 'Calf-Raise', 3, '10-12'");
            setupInsertToExerciseList("NULL, 'fb', 2, 'Leg Press', 4, '6-8'");
//            hs day 1
            setupInsertToExerciseList("NULL, 'hs', 1, 'BB Bench', 5, '4-6'");
            setupInsertToExerciseList("NULL, 'hs', 1, 'BB Row', 5, '4-6'");
            setupInsertToExerciseList("NULL, 'hs', 1, 'Arnold Press', 3, '8-12'");
            setupInsertToExerciseList("NULL, 'hs', 1, 'Pec-Dec', 3, '8-12'");
            setupInsertToExerciseList("NULL, 'hs', 1, 'Seated Rows', 3, '8-12'");
            setupInsertToExerciseList("NULL, 'hs', 1, 'Lat Pulldown', 3, '8-12'");
            setupInsertToExerciseList("NULL, 'hs', 1, 'Incline DB Curl', 3, '15-18'");
            setupInsertToExerciseList("NULL, 'hs', 1, 'Rope Pushdown', 3, '12-16'");
            setupInsertToExerciseList("NULL, 'hs', 1, 'DB Lateral Raise', 3, '15-18'");
//            hs day 2
            setupInsertToExerciseList("NULL, 'hs', 2, 'Squats', 5, '4-8'");
            setupInsertToExerciseList("NULL, 'hs', 2, 'Deadlift', 5, '4-8'");
            setupInsertToExerciseList("NULL, 'hs', 2, 'Jefferson Squat', 3, '8-10'");
            setupInsertToExerciseList("NULL, 'hs', 2, 'Romanian Deadlift', 3, '8-10'");
            setupInsertToExerciseList("NULL, 'hs', 2, 'Single Leg Press', 3, '16-18'");
            setupInsertToExerciseList("NULL, 'hs', 2, 'Leg Press Calf Raise', 3, '16-18'");
            setupInsertToExerciseList("NULL, 'hs', 2, 'Leg Extensions', 3, '16-18'");
            setupInsertToExerciseList("NULL, 'hs', 2, 'Leg Curls', 3, '16-18'");
//            hs day 3
            setupInsertToExerciseList("NULL, 'hs', 3, 'T-Bar', 3, '10-12'");
            setupInsertToExerciseList("NULL, 'hs', 3, 'Incline DB Rows', 3, '10-12'");
            setupInsertToExerciseList("NULL, 'hs', 3, 'Guillotine Curls', 3, '16-18'");
            setupInsertToExerciseList("NULL, 'hs', 3, 'Lat Pulldown', 3, '10-12'");
            setupInsertToExerciseList("NULL, 'hs', 3, 'Seated Rows', 3, '12-15'");
            setupInsertToExerciseList("NULL, 'hs', 3, 'DB Curls', 3, '10-12'");
            setupInsertToExerciseList("NULL, 'hs', 3, 'Reverse Cable Curl', 3, '10-12'");
            setupInsertToExerciseList("NULL, 'hs', 3, 'Waiter Curls', 3, '12-16'");
//            hs day 4
            setupInsertToExerciseList("NULL, 'hs', 4, 'BB Deadlift', 3, '10-12'");
            setupInsertToExerciseList("NULL, 'hs', 4, 'Leg Extensions', 3, '16-18'");
            setupInsertToExerciseList("NULL, 'hs', 4, 'Jefferson Squats', 3, '10-12'");
            setupInsertToExerciseList("NULL, 'hs', 4, 'Leg Curls', 3, '12-16'");
            setupInsertToExerciseList("NULL, 'hs', 4, 'Leg Press', 3, '10-12'");
            setupInsertToExerciseList("NULL, 'hs', 4, 'Calf Raise', 3, '10-12'");
            setupInsertToExerciseList("NULL, 'hs', 4, 'Bulgarian Split Squats', 3, '10-12'");
            setupInsertToExerciseList("NULL, 'hs', 4, 'Hip Thrusts', 5, '6-8'");
//            hs day 5
            setupInsertToExerciseList("NULL, 'hs', 5, 'Incline DB press', 3, '8-16'");
            setupInsertToExerciseList("NULL, 'hs', 5, 'Pec-Dec', 3, '8-16'");
            setupInsertToExerciseList("NULL, 'hs', 5, 'Cable Fly', 3, '8-16'");
            setupInsertToExerciseList("NULL, 'hs', 5, 'Arnold Press', 3, '8-16'");
            setupInsertToExerciseList("NULL, 'hs', 5, 'DB Lateral Raise', 3, '8-16'");
            setupInsertToExerciseList("NULL, 'hs', 5, 'Rear Delt Cable Fly', 3, '8-16'");
            setupInsertToExerciseList("NULL, 'hs', 5, 'Overhead Triceps Extensions', 3, '8-16'");
            setupInsertToExerciseList("NULL, 'hs', 5, 'Skull Crushers', 3, '8-16'");
            setupInsertToExerciseList("NULL, 'hs', 5, 'V Bar Pushdown', 3, '8-16'");
        }
        db.close();
    }

    private void populateExerciseList(RecyclerView recyclerView, String routineNameId, int dayId) {
        DBAdapter db = new DBAdapter(this);
        db.open();

        String fields[] = new String[]{
                "exName",
                "setsR",
                "repsR"
        };

        Cursor exerciseListCursor = db.select("routineExerciseList", "routineName", routineNameId, "day", dayId);
        ArrayList<ExerciseModel> arrayList = new ArrayList<>();

        int categoriesCount = exerciseListCursor.getCount();
        for (int i = 0; i < categoriesCount; i++) {

            String exName = exerciseListCursor.getString(exerciseListCursor.getColumnIndex("exName"));
            int setsR = exerciseListCursor.getInt(exerciseListCursor.getColumnIndex("setsR"));
            String repsR = exerciseListCursor.getString(exerciseListCursor.getColumnIndex("repsR"));

            ExerciseModel exerciseModel = new ExerciseModel(exName, setsR, repsR);
            arrayList.add(exerciseModel);

            exerciseListCursor.moveToNext();
            db.close();
        }
        RoutineExerciseListAdapter routineExerciseListAdapter = new RoutineExerciseListAdapter(this, arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(routineExerciseListAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_routine);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        initViews();
        insertAllExercise();
        setTitles();

    }
}