package com.learnakantwi.simplearithmetic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MultipleOperationsSelect extends AppCompatActivity {

    ImageButton btAddition;
    ImageButton btSubtraction;
    ImageButton btMultiplication;
    ImageButton btDivision;

    ImageView icAddition;
    ImageView icSubtraction;
    ImageView icMultiplication;
    ImageView icDivision;

    Button btContinue;

    AdView mAdView;

    int add =0;
    int subtract =0;
    int multiply = 0;
    int divide = 0;

    public void goToNumberRange(){

            Intent chooseRange = new Intent(getApplicationContext(), MultipleOperationsNumberRangeMain.class);
            chooseRange.putExtra("add",add);
            chooseRange.putExtra("subtract",subtract);
            chooseRange.putExtra("multiply",multiply);
            chooseRange.putExtra("divide", divide);
            startActivity(chooseRange);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mulitiple_operations_select);


        if (MainActivity.Lifetime != 0) {
            MobileAds.initialize(this, new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(InitializationStatus initializationStatus) {
                }
            });
            mAdView = findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }


        icAddition = findViewById(R.id.icAddition);
        icSubtraction = findViewById(R.id.icSubtraction);
        icMultiplication = findViewById(R.id.icMultiplication);
        icDivision = findViewById(R.id.icDivision);

        btAddition = findViewById(R.id.btAddition);
        btSubtraction = findViewById(R.id.btSubtraction);
        btDivision = findViewById(R.id.btDivision);
        btMultiplication = findViewById(R.id.btMultiplication);

        btContinue = findViewById(R.id.btContinue);

        btContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int total = add + subtract + multiply + divide;
                if (total<2){
                    Toast.makeText(MultipleOperationsSelect.this, "Please Select At Least Two Operations", Toast.LENGTH_LONG).show();
                }
                else{
                    goToNumberRange();
                }
            }
        });


        btAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (add == 0){
                    add =1;
                   btAddition.setBackgroundColor(Color.GREEN);
                   icAddition.setVisibility(View.VISIBLE);

                }else{
                    add =0;
                    btAddition.setBackgroundColor(Color.LTGRAY);
                    icAddition.setVisibility(View.INVISIBLE);
                }
            }
        });

        btSubtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (subtract == 0){
                    subtract =1;
                    btSubtraction.setBackgroundColor(Color.GREEN);
                    icSubtraction.setVisibility(View.VISIBLE);

                }else{
                    subtract =0;
                    btSubtraction.setBackgroundColor(Color.LTGRAY);
                    icSubtraction.setVisibility(View.INVISIBLE);
                }
            }
        });

        btMultiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (multiply == 0){
                    multiply =1;
                    btMultiplication.setBackgroundColor(Color.GREEN);
                    icMultiplication.setVisibility(View.VISIBLE);

                }else{
                    multiply =0;
                    btMultiplication.setBackgroundColor(Color.LTGRAY);
                    icMultiplication.setVisibility(View.INVISIBLE);
                }
            }
        });

        btDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (divide == 0){
                    divide =1;
                    btDivision.setBackgroundColor(Color.GREEN);
                    icDivision.setVisibility(View.VISIBLE);

                }else{
                    divide =0;
                    btDivision.setBackgroundColor(Color.LTGRAY);
                    icDivision.setVisibility(View.INVISIBLE);
                }
            }
        });



    }
}