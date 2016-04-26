package com.example.flamealchemist.jsoup_check.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.flamealchemist.jsoup_check.R;

/**
 * Created by Flame Alchemist on 11/10/2015.
 */
public class MyArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;
    private final String[] values2;

    public MyArrayAdapter(Context context, String[] values, String[] values2) {
        super(context, R.layout.rowlayout, values);
        this.context = context;
        this.values = values;
        this.values2 = values2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
        TextView view2 = (TextView) rowView.findViewById(R.id.label2);

        TextView view1 = (TextView)rowView.findViewById(R.id.label);

        view1.setText(values[position]);
        view2.setText(values2[position]);
        return rowView;
    }
}