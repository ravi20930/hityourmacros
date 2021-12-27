package com.itisravi.hym;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodAdapterRecycle extends RecyclerView.Adapter<FoodAdapterRecycle.viewHolder> {

    Context context;
    ArrayList<FoodEatenModel> foodEatenModelArrayList;
    FoodEatenModel foodEatenModel;

    public FoodAdapterRecycle(Context context, ArrayList foodEatenArrayList) {
        this.context = context;
        this.foodEatenModelArrayList = foodEatenArrayList;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_eaten_recycler_view, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {

        foodEatenModel = foodEatenModelArrayList.get(position);
        int _id = foodEatenModel.get_id();

        holder.txtViewCals.setText(""+ foodEatenModel.getKcals());
        holder.txtViewFood.setText(foodEatenModel.getFood());
        holder.itemView.setTag(_id);

    }

    @Override
    public int getItemCount() {
        return this.foodEatenModelArrayList.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder {

        private TextView txtViewFood, txtViewCals;

        public viewHolder(View itemView) {
            super(itemView);

            txtViewFood = itemView.findViewById(R.id.name);
            txtViewCals = itemView.findViewById(R.id.cals);

        }
    }
}
