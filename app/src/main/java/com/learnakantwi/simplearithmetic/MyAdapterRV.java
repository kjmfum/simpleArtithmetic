package com.learnakantwi.simplearithmetic;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterRV extends RecyclerView.Adapter<MyAdapterRV.MyViewHolder> implements Filterable {


    Context context;

    ArrayList<String> originalArray , tempArray;
    onClickRecycle onClickRecycle;
    private Filter RecycleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<String> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                //filteredList = originalArray;
                filteredList.addAll(tempArray);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                //ArrayList<Animals> results = new ArrayList<>();
                for (String x : tempArray) {

                    if (x.contains(filterPattern)) {
                        filteredList.add(x);
                    }
                }
            }
            FilterResults results1 = new FilterResults();
            results1.values = filteredList;

            return results1;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results1) {
            originalArray.clear();
            originalArray.addAll((List) results1.values);
            notifyDataSetChanged();
        }
    };



    public MyAdapterRV(ArrayList<String> originalArray, Context context,  onClickRecycle onClickRecycle ) {
        this.context = context;
        this.originalArray = originalArray;
        this.tempArray = originalArray;
        this.onClickRecycle = onClickRecycle;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.readingmain, parent,false);
        return new MyViewHolder(view, onClickRecycle);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.myText1.setText(originalArray.get(position));
    }

    @Override
    public int getItemCount() {
        return originalArray.size();
    }

    @Override
    public Filter getFilter() {
        return RecycleFilter;
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        TextView myText1;
        onClickRecycle onClickRecycle;


        public MyViewHolder(@NonNull View itemView, onClickRecycle onClickRecycle) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.tvReadingAdapter1);
            this.onClickRecycle = onClickRecycle;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            final ColorStateList originalColor = myText1.getTextColors();
            myText1.setBackgroundColor(Color.BLUE);
            myText1.setTextColor(Color.GREEN);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    myText1.setTextColor(originalColor);
                    myText1.setBackgroundColor(Color.WHITE);
                }
            },1500);

            onClickRecycle.onMyItemClick(getAdapterPosition(), view);
        }
    }

    public interface onClickRecycle {
        void onMyItemClick(int position, View view);
    }
}
