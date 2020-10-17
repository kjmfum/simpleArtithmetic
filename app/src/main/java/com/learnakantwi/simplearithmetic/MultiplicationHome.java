package com.learnakantwi.simplearithmetic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.appodeal.ads.Appodeal;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MultiplicationHome extends AppCompatActivity {

    AdView mAdView;
    AdView mAdView1;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);


        /*final MenuItem item = menu.findItem(R.id.menusearch);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(AlphabetsActivity.this, query, Toast.LENGTH_SHORT).show();
                return false;
            }
*/
           /* @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Alphabets> results = new ArrayList<>();
                for (Alphabets x: alphabetArray){

                    if(x.getBoth().contains(newText)){
                        results.add(x);
                    }

                    ((AlphabetAdapter)myListView.getAdapter()).update(results);
                }*/


        /*        return false;
            }
        });*/

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()){

            /*case R.id.videoCourse:
                goToWeb();
                return  true;
            case R.id.quiz1:
                goToQuizAlphabets();
                return  true;*/
            case R.id.main:
                goToMain();
                return  true;
            /*case R.id.downloadAudio:
                downloadClick();
                return  true;*/
            default:
                return false;
        }
    }

    public void goToMain(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }


    public void practiseRanges(){
        Intent intent = new Intent(getApplicationContext(), MultiplicationRangeOnlyMain.class);
        startActivity(intent);
    }


    public void multiplicationCountdown(){
        Intent intent = new Intent(getApplicationContext(), MultiplicationCountdownMain.class);
        startActivity(intent);
    }

    public void numberToMultiply (){
        Intent intent = new Intent(getApplicationContext(), MultiplicationNumberRangeMain.class);
        startActivity(intent);
    }

    public void goToHighScores(){
        Intent intent = new Intent(getApplicationContext(), MultiplicationHighScores.class);
        startActivity(intent);
    }

    public  void goToMultiplicationTable(){
      //  Toast.makeText(this, "Getting Ready", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), MultiplicationTableMain.class);
        startActivity(intent);
    }
    public void goToMultiplicationType (View view){
        int idview = view.getId();
        Button me = view.findViewById(idview);

        String arithmeticType = me.getText().toString();

        switch(arithmeticType){
            case "PRACTISE WITH RANGES ONLY" :
                practiseRanges();
                return;
            case "PRACTISE A NUMBER TO A RANGE" :
                numberToMultiply();
                return;
            case "COUNTDOWN TEST" :
                multiplicationCountdown();
                return;
            case "HIGH SCORES" :
                goToHighScores();
                return;
            case "MULTIPLICATION TABLES" :
                goToMultiplicationTable();
                return;
           /* case "DIVISION" :
                goToDivision();
                return;*/
            default:
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();

        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication_home);

      /*  if (MainActivity.Lifetime != 0){
            Appodeal.show(this, Appodeal.BANNER_BOTTOM);
        }*/

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


    }
}

