package com.learnakantwi.simplearithmetic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

public class MultiplicationTableMain extends AppCompatActivity {

    static ArrayList<Integer> numbersArray;
    //CharSequence vowel;
    int vowel;
    HorizontalScrollView horizontalScrollView;
    ListView lvReadingAlphabets;
    AdView mAdView;
    AdView mAdView1;
    Toast toast;


    public void goToTwoLetters() {
        // Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.udemy.com/course/learn-akan-twi/?referralCode=6D321CE6AEE1834CCB0F"));
        Intent intent = new Intent(this, MultiplicationTableActivity.class);
        intent.putExtra("vowel", vowel);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication_table_main);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        numbersArray = new ArrayList<>();

        for (int i=1 ; i<= 100 ; i++){

           // Toast.makeText(this, "Hi "+ i, Toast.LENGTH_SHORT).show();
            numbersArray.add(i);
        }

//        tvLetterA = findViewById(R.id.tvVowelA);
        //      horizontalScrollView = findViewById(R.id.horizontalScrollView);
        lvReadingAlphabets = findViewById(R.id.lvReadingAlphabet);


        // ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, readingAlphabetArray);
        MultiplicationTableAdapter readingMainAdapter = new MultiplicationTableAdapter ( this, numbersArray);
        lvReadingAlphabets.setAdapter(readingMainAdapter);
        //lvReadingAlphabets.setAdapter(arrayAdapter);

        lvReadingAlphabets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                vowel = numbersArray.get(position);
               // Toast.makeText(MultiplicationTableMain.this, "Hi: "+ vowel, Toast.LENGTH_SHORT).show();
                goToTwoLetters();

            }
        });

    }
}
