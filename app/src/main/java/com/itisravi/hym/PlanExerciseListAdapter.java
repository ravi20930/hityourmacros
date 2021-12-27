package com.itisravi.hym;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlanExerciseListAdapter extends RecyclerView.Adapter<PlanExerciseListAdapter.viewHolder> {

    Context context;
    ArrayList<ExerciseModel> exerciseModelArrayList;
    ExerciseModel exerciseModel;

    public PlanExerciseListAdapter(Context context, ArrayList<ExerciseModel> exerciseModelArrayList) {
        this.context = context;
        this.exerciseModelArrayList = exerciseModelArrayList;
    }

    @Override
    public PlanExerciseListAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_exercise_list_layout, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlanExerciseListAdapter.viewHolder holder, int position) {

        exerciseModel = exerciseModelArrayList.get(position);

        holder.exName.setText(""+exerciseModel.getName());
        holder.sets.setText(""+exerciseModel.getSets());
        holder.reps.setText(""+exerciseModel.getReps());
        holder.rest.setText(""+exerciseModel.getRest());
        holder.load.setText(""+exerciseModel.getLoad());


    }

    @Override
    public int getItemCount() {
        return this.exerciseModelArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        private TextView exName, sets, reps, rest, load;

        public viewHolder(View itemView) {
            super(itemView);

            exName = itemView.findViewById(R.id.exName);
            sets = itemView.findViewById(R.id.sets);
            reps = itemView.findViewById(R.id.reps);
            rest = itemView.findViewById(R.id.rest);
            load = itemView.findViewById(R.id.load);

        }
    }
}
