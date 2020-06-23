package com.learnakantwi.simplearithmetic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MultiplicationTableAdapter extends BaseAdapter {

    Context context;
    ArrayList<Integer> originalArray , tempArray;
    //CustomFilter cf;

    public MultiplicationTableAdapter(Context context, ArrayList<Integer> originalArray) {
        this.context = context;
        this.originalArray = originalArray;
        this.tempArray = originalArray;
    }

    public void update(ArrayList<Integer> results){
        originalArray = new ArrayList<>();
        originalArray.addAll(results);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return originalArray.size();
    }

    @Override
    public Object getItem(int position) {
        return originalArray.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView= inflater.inflate(R.layout.readingmain, null);

        // TextView textView = convertView.findViewById(R.id.tvReadingAdapter);
        TextView textView1 = convertView.findViewById(R.id.tvReadingAdapter1);
        //CardView cardView =convertView.findViewById(R.id.cvCardView);

        //textView.setText(originalArray.get(position));
        //textView1.setText(originalArray.get(position).toLowerCase());
        textView1.setText(originalArray.get(position).toString());
        String text = textView1.getText().toString();

        //if image would have been --- setImageResource.

        return convertView;
    }
}
