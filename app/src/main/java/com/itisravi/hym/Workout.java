package com.itisravi.hym;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class Workout extends Fragment {

    View view;
    MaterialCardView plan1, plan2, plan3, plan4, planAbs;
    NonScrollListView routineLV;

    public Workout() {
        // Required empty public constructor
    }

    private void setImages() {
        ImageView getStrong = view.findViewById(R.id.getStrong);
        ImageView bodyRecomp = view.findViewById(R.id.bodyRecomp);
        ImageView fatLoss = view.findViewById(R.id.fatLoss);
        ImageView beginners = view.findViewById(R.id.beginners);
        ImageView abs = view.findViewById(R.id.abs);

        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/hityourmacros.appspot.com/o/workoutImages%2Fimage%20(24).jpg?alt=media&token=138b4293-1c53-4b1e-91bc-501198c51d93")
                .into(getStrong);

        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/hityourmacros.appspot.com/o/workoutImages%2Fimage%20(19).jpg?alt=media&token=3b9627d0-5973-4d61-b091-fb6dba9c5c64")
                .into(bodyRecomp);

        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/hityourmacros.appspot.com/o/workoutImages%2Fimage%20(25).jpg?alt=media&token=59e37145-3736-43a7-af20-d16f1836e447")
                .into(fatLoss);

        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/hityourmacros.appspot.com/o/workoutImages%2Fimage%20(10).jpg?alt=media&token=c3225423-e73f-4167-a45b-68d3bde4774e")
                .into(beginners);

        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/hityourmacros.appspot.com/o/workoutImages%2Fimage%20(26).jpg?alt=media&token=57d68240-baa0-43d9-809f-2de8381db528")
                .into(abs);
    }

    private void planOnClick() {
        plan1 = view.findViewById(R.id.plan1);
        plan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WorkoutPlan.class);
                intent.putExtra("planId", 1);
                startActivity(intent);
            }
        });

        plan2 = view.findViewById(R.id.plan2);
        plan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WorkoutPlan.class);
                intent.putExtra("planId", 2);
                startActivity(intent);
            }
        });

        plan3 = view.findViewById(R.id.plan3);
        plan3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WorkoutPlan.class);
                intent.putExtra("planId", 3);
                startActivity(intent);
            }
        });

        plan4 = view.findViewById(R.id.plan4);
        plan4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WorkoutPlan.class);
                intent.putExtra("planId", 4);
                startActivity(intent);
            }
        });

        planAbs = view.findViewById(R.id.planAbs);
        planAbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AbsWorkoutLayout.class);
                startActivity(intent);
            }
        });
    }

    private void populateRoutine() {
        routineLV = view.findViewById(R.id.routineLV);

        ArrayList<String> arrayListTitles = new ArrayList<>();
        ArrayList<String> arrayListSubtitles = new ArrayList<>();

        arrayListTitles.add("Classic Push Pull Leg");
        arrayListTitles.add("Bro Split");
        arrayListTitles.add("Full Body Workout");
        arrayListTitles.add("Hybrid Split");

        arrayListSubtitles.add("Build insane mass with this PPL routine");
        arrayListSubtitles.add("The most popular split routine");
        arrayListSubtitles.add("Maintain size and build muscles slowly");
        arrayListSubtitles.add("This routine is best when you are bulking up");

        RoutineListAdapter routineListAdapter = new RoutineListAdapter(getContext(), arrayListTitles, arrayListSubtitles);
        routineLV.setAdapter(routineListAdapter);

        routineLV.setOnItemClickListener((parent, view, position, id) -> {

            Intent intent = new Intent(getActivity(), ViewRoutine.class);

            switch (position) {
                case 0:
                    intent.putExtra("id", 0);
                    break;
                case 1:
                    intent.putExtra("id", 1);
                    break;
                case 2:
                    intent.putExtra("id", 2);
                    break;
                case 3:
                    intent.putExtra("id", 3);
                    break;
            }
            startActivity(intent);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_workout, container, false);

        setImages();
        populateRoutine();
        planOnClick();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        insertAllExercise();
    }

    private void setupInsertToExercise(String values) {
        DBAdapter db = new DBAdapter(getContext());
        db.open();
        db.insert("planExerciseList",
                "_id, typeId, name, sets, reps, rest, load",
                values);
        db.close();
    }

    private void insertAllExercise() {
        DBAdapter db = new DBAdapter(getContext());
        db.open();
        if (db.count("planExerciseList") < 1) {
            setupInsertToExercise("NULL, 1, 'Bench press', 8, 5, 120, '80-85%'");
            setupInsertToExercise("NULL, 1, 'Squat', 8, 5, 120, '80-85%'");
            setupInsertToExercise("NULL, 1, 'Seated Rows', 8, 5, 120, '80-85%'");
            setupInsertToExercise("NULL, 1, 'Deadlift', 8, 5, 120, '80-85%'");
            setupInsertToExercise("NULL, 1, 'Weighted Dips', 8, 6, 60, '100%'");
            setupInsertToExercise("NULL, 1, 'Weighted Pull Ups', 8, 6, 60, '100%'");
            setupInsertToExercise("NULL, 2, 'Barbell Squat', 5, 10, 60, '65-70%'");
            setupInsertToExercise("NULL, 2, 'Bench Press', 4, 12, 60, '70-75%'");
            setupInsertToExercise("NULL, 2, 'Push Ups', 4, 16, 180, '100%'");
            setupInsertToExercise("NULL, 2, 'Cable Flies', 4, 16, 30, '80-85%'");
            setupInsertToExercise("NULL, 2, 'Deadlift', 5, 10, 60, '70-75%'");
            setupInsertToExercise("NULL, 2, 'Chin-Ups', 4, 8, 180, '100%'");
            setupInsertToExercise("NULL, 2, 'Arnold Press', 4, 16, 30, '65-70%'");
            setupInsertToExercise("NULL, 2, 'Lat Pulldown (Wide)', 4, 16, 45, '60-70%'");
            setupInsertToExercise("NULL, 3, 'Incline Bench Press', 8, 10, 60, '70-85%'");
            setupInsertToExercise("NULL, 3, 'Barbell Rows', 8, 10, 60, '70-85%'");
            setupInsertToExercise("NULL, 3, 'Deadlift', 8, 10, 60, '70-85%'");
            setupInsertToExercise("NULL, 3, 'Leg Press', 8, 10, 60, '70-85%'");
            setupInsertToExercise("NULL, 3, 'Arnold Press', 8, 10, 60, '70-85%'");
            setupInsertToExercise("NULL, 3, 'Lat Pulldown', 8, 10, 60, '70-85%'");
            setupInsertToExercise("NULL, 4, 'Squat', 6, 5, 0, '80-85%'");
            setupInsertToExercise("NULL, 4, 'Air Squats', 4, 8, 180, '100%'");
            setupInsertToExercise("NULL, 4, 'Bench Press', 6, 5, 0, '80-85%'");
            setupInsertToExercise("NULL, 4, 'Push Ups', 4, 8, 180, '100%'");
            setupInsertToExercise("NULL, 4, 'Deadlifts', 6, 5, 0, '80-85%'");
            setupInsertToExercise("NULL, 4, 'Glute Bridges', 4, 8, 180, '100%'");
            setupInsertToExercise("NULL, 4, 'Barbell Pendlay Rows', 6, 5, 0, '80-85%'");
            setupInsertToExercise("NULL, 4, 'Chin Ups', 4, 8, 180, '100%'");
            setupInsertToExercise("NULL, 5, 'Weighted Sit Ups', 4, 20, 0, '100%'");
            setupInsertToExercise("NULL, 5, 'Leg Raises', 4, 15, 0, '100%'");
            setupInsertToExercise("NULL, 5, 'Side Crunches', 4, 15, 0, '100%'");
            setupInsertToExercise("NULL, 6, 'Hanging Leg Raise', 4, 10, 0, '100%'");
            setupInsertToExercise("NULL, 6, 'Weighted Crunches', 4, 20, 0, '100%'");
            setupInsertToExercise("NULL, 6, 'Lying Leg Raise', 4, 15, 0, '100%'");
            setupInsertToExercise("NULL, 7, 'Exercise Ball Sit-Ups', 4, 20, 0, '100%'");
            setupInsertToExercise("NULL, 7, 'Bicycle Crunches', 4, 20, 0, '100%'");
            setupInsertToExercise("NULL, 7, 'Cable Crunches', 4, 20, 0, '100%'");
        }
        db.close();
    }
}