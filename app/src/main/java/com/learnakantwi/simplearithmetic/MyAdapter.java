package com.learnakantwi.simplearithmetic;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    String data1[], data2[] ;
    Context context;
    int image [];

    Arithmetic arithmetic;

    ArrayList<Arithmetic> arithmeticArrayList;
    ArrayList<String> arrayList;
    onClickRecycle onClickRecycle;
    LayoutInflater inflater;

    public MyAdapter(String[] data1, String[] data2, Context context, int[] image) {
        this.data1 = data1;
        this.data2 = data2;
        this.context = context;
        this.image = image;
    }

    public MyAdapter(ArrayList<Arithmetic> arrayList, Context context) {
        this.context = context;
        this.arithmeticArrayList = arrayList;
    }

    public MyAdapter(Context context, ArrayList<String> arrayList, onClickRecycle onClickRecycle) {
        this.context = context;
        this.arrayList = arrayList;
        this.onClickRecycle = onClickRecycle;
        this.inflater = LayoutInflater.from(context);
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater layoutInflater = LayoutInflater.from(context);
       // View view = layoutInflater.inflate(R.layout.readingmain, parent,false);
        View view = inflater.inflate(R.layout.readingmain, parent, false);
        //View view = layoutInflater.inflate(R.layout.cardrow, parent,false);
        return new MyViewHolder(view, onClickRecycle);
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

    public class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        TextView myText1;   //, myText2;
        //ImageView myImage;
        onClickRecycle onClickRecycle;

        public MyViewHolder(@NonNull View itemView, onClickRecycle onClickRecycle) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.tvReadingAdapter1);
            this.onClickRecycle = onClickRecycle;
           // myText2 = itemView.findViewById(R.id.tvDescription);
           // myImage = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
           String myText = myText1.getText().toString();
//
           myText1.setTextColor(Color.GREEN);
           myText1.setBackgroundColor(Color.BLUE);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    myText1.setTextColor(Color.DKGRAY);
                    myText1.setBackgroundColor(Color.WHITE);
                }
            },1500);
           //view.setBackgroundColor(Color.BLUE);
//
//           int firstSpace = myText.indexOf(" ");
//           String firstNumber =  myText.substring(0,firstSpace);
//
//            Toast.makeText(context, "Hi: "+ firstNumber, Toast.LENGTH_SHORT).show();
            onClickRecycle.onMyItemClick(getAdapterPosition(), view);

        }
    }

    public interface onClickRecycle {
        void onMyItemClick(int position, View view);
    }
}
