package com.itisravi.hym;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RoutineListAdapter extends ArrayAdapter<String> {
    Context context;
    ArrayList<String> arrayListTitles;
    ArrayList<String> arrayListSubtitles;

    public RoutineListAdapter(Context context, ArrayList<String> arrayListTitles, ArrayList<String> arrayListSubtitles) {
        super(context, R.layout.routine_rv_layout, R.id.routineTitle, arrayListTitles);
        this.context = context;
        this.arrayListTitles = arrayListTitles;
        this.arrayListSubtitles = arrayListSubtitles;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.routine_rv_layout, parent, false);

        TextView routineTitle = view.findViewById(R.id.routineTitle);
        TextView routineSubtitle = view.findViewById(R.id.routineSubtitle);

        routineTitle.setText(arrayListTitles.get(position));
        routineSubtitle.setText(arrayListSubtitles.get(position));

        return view;
    }
}
