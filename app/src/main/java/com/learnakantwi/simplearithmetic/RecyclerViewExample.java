package com.learnakantwi.simplearithmetic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class Arithmetic{
    String type;
    String description;
    int image;

    public Arithmetic(String type, String description, int image) {
        this.type = type;
        this.description = description;
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}

public class RecyclerViewExample extends AppCompatActivity {

    String s1 [], s2 [];
    int images [] = {
            R.drawable.ic_add_black_24dp, R.drawable.ic_clear_black_24dp, R.drawable.ic_remove_black_24dp, R.drawable.ic_divisionicon
    };

    RecyclerView recyclerView;

    static ArrayList<Arithmetic> arithmeticArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_example);

        recyclerView = findViewById(R.id.recyclerView);

        arithmeticArrayList = new ArrayList<>();

        arithmeticArrayList.add(new Arithmetic("Addition","Add",R.drawable.ic_add_black_24dp));
        arithmeticArrayList.add(new Arithmetic("Multiplication","Multiply",R.drawable.ic_clear_black_24dp));
        arithmeticArrayList.add(new Arithmetic("Subtraction","Subtract",R.drawable.ic_remove_black_24dp));
        arithmeticArrayList.add(new Arithmetic("Division","Divide",R.drawable.ic_divisionicon));

        s1 = getResources().getStringArray(R.array.ArithmeticTypes);
        s2 = getResources().getStringArray(R.array.Description);

      //  MyAdapter myAdapter = new MyAdapter(s1, s2,this, images);

        MyAdapter myAdapter = new MyAdapter(arithmeticArrayList, this);
        recyclerView.setAdapter(myAdapter);

        //recyclerView.setLayoutManager(new GridLayoutManager(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }
}


