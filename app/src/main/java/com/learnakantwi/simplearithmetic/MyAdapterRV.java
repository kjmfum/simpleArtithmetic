package com.learnakantwi.simplearithmetic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterRV extends RecyclerView.Adapter<MyAdapterRV.MyViewHolder> {

    String data1[], data2[] ;
    Context context;
    int image [];

    Arithmetic arithmetic;

    ArrayList<Arithmetic> arithmeticArrayList;
    ArrayList<String> arrayList;

    public MyAdapterRV(String[] data1, String[] data2, Context context, int[] image) {
        this.data1 = data1;
        this.data2 = data2;
        this.context = context;
        this.image = image;
    }

    public MyAdapterRV(ArrayList<Arithmetic> arrayList, Context context) {
        this.context = context;
        this.arithmeticArrayList = arrayList;
    }

    public MyAdapterRV(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.readingmain, parent,false);
        //View view = layoutInflater.inflate(R.layout.cardrow, parent,false);
        return new MyViewHolder(view);
    }

  /*  @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.myText1.setText(data1[position]);
        holder.myText1.setText(data2[position]);
        holder.myImage.setImageResource(image[position]);
    }*/

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.myText1.setText(arrayList.get(position));
        //holder.myImage.setImageResource(arithmeticArrayList.get(position).image);
//        holder.myText1.setText(arithmeticArrayList.get(position).type);
//        holder.myText1.setText(arithmeticArrayList.get(position).description);
//        holder.myImage.setImageResource(arithmeticArrayList.get(position).image);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
        //return arithmeticArrayList.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView myText1;   //, myText2;
        //ImageView myImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.tvReadingAdapter1);
           // myText2 = itemView.findViewById(R.id.tvDescription);
           // myImage = itemView.findViewById(R.id.imageView);


        }
    }

    public interface onClickRecycle {
        void onMyItemClick(int position, View view);
    }
}
