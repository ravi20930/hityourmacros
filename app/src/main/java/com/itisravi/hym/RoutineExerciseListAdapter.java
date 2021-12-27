package com.itisravi.hym;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RoutineExerciseListAdapter extends RecyclerView.Adapter<RoutineExerciseListAdapter.viewHolder> {

    Context context;
    ArrayList<ExerciseModel> exerciseModelArrayList;
    ExerciseModel exerciseModel;

    public RoutineExerciseListAdapter(Context context, ArrayList<ExerciseModel> exerciseModelArrayList) {
        this.context = context;
        this.exerciseModelArrayList = exerciseModelArrayList;
    }

    @Override
    public RoutineExerciseListAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.routine_exercise_list_layout, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(RoutineExerciseListAdapter.viewHolder holder, int position) {
        exerciseModel = exerciseModelArrayList.get(position);

        holder.exNameR.setText(""+exerciseModel.getExName());
        holder.setsR.setText(""+exerciseModel.getSetsR());
        holder.repsR.setText(""+exerciseModel.getRepsR());
    }

    @Override
    public int getItemCount() {
        return this.exerciseModelArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        private TextView exNameR, setsR, repsR;

        public viewHolder(View itemView) {
            super(itemView);

            exNameR = itemView.findViewById(R.id.exNameR);
            setsR = itemView.findViewById(R.id.setsR);
            repsR = itemView.findViewById(R.id.repsR);

        }
    }
}
