package com.learnakantwi.simplearithmetic;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

public class MultiplicationTableActivityBase extends AppCompatActivity {

    int multiplictionNumber;
    ListView lvTimesTable;

    ArrayList<String> timesTableArray;

    TextView tvHeading;

    AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication_table);

        if (MainActivity.Lifetime != 0){
            MobileAds.initialize(this, new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(InitializationStatus initializationStatus) {
                }
            });
            mAdView = findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }



        Intent intent = getIntent();
        multiplictionNumber = intent.getIntExtra("vowel",1);

        tvHeading = findViewById(R.id.tvTwoLetterHeader);
        tvHeading.setText("Multiplication Table for Number "+ multiplictionNumber);


        timesTableArray = new ArrayList<>();

        for (int i =1 ; i<= 100 ; i++){

            int product = multiplictionNumber * i;

            timesTableArray.add(multiplictionNumber+ " x " + i + " = "+ product );

        }



        lvTimesTable = findViewById(R.id.lvtwoLetters);
        MultiplicationTableAdapterString readingTwoLetterAdapter = new MultiplicationTableAdapterString(this, timesTableArray);
       // ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, ReadingTwoLetterAdapter);
        //lvTimesTable.setAdapter(readingTwoLetterAdapter);

        lvTimesTable.setAdapter(readingTwoLetterAdapter);

//        lvTimesTable.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                view.getId();
//                TextView textView = view.findViewById(view.getId());
//
//                String text = textView.getText().toString();
//                String text1 = text.substring(0,2);
//
//                Toast.makeText(MultiplicationTableActivity.this, "Hi: "+ text, Toast.LENGTH_SHORT).show();
//            }
//        });




    }
}
