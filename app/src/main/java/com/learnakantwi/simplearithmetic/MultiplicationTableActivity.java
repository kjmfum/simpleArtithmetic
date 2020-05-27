package com.learnakantwi.simplearithmetic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

public class MultiplicationTableActivity extends AppCompatActivity {

    int multiplictionNumber;
    ListView lvTimesTable;

    ArrayList<String> timesTableArray;

    TextView tvHeading;

    AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication_table);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



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
        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, ReadingTwoLetterAdapter);
        lvTimesTable.setAdapter(readingTwoLetterAdapter);




    }
}
