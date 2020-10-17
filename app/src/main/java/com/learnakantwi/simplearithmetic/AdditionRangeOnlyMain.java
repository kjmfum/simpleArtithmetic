package com.learnakantwi.simplearithmetic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.appodeal.ads.Appodeal;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class AdditionRangeOnlyMain extends AppCompatActivity {

    Button btGoToAdditionActivity;
    AdView mAdView;
    String stMinimum;
    String stMaximum;
    int minimum = 0;
    int maximum = 10;

    Boolean negative = false;

    TextView tvNegative;


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

    public void goToAddition0to9(int minimum, int maximum, Boolean negative){
        //Intent intent = new Intent(this, additionActivity.class);
        //startActivity(intent);


        // Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.udemy.com/course/learn-akan-twi/?referralCode=6D321CE6AEE1834CCB0F"));
        Intent intent = new Intent(this, additionActivity.class);
        intent.putExtra  ("min", minimum);
        intent.putExtra  ("max", maximum);
        intent.putExtra  ("negative", negative );
        startActivity(intent);
    }

    public void getRangeFromButton(View view){
        int idview = view.getId();

        Button rangeButton = findViewById(idview);

        String rangeButtonText= rangeButton.getText().toString();
        int minimumIndex = rangeButtonText.indexOf("-");
        stMinimum = rangeButtonText.substring(0,minimumIndex);
        stMaximum = rangeButtonText.substring(minimumIndex+1);
        try {
            minimum = Integer.parseInt(stMinimum);
            maximum = Integer.parseInt(stMaximum);
        }catch (Exception e){
            Log.i("Error", e.toString());
        }

        goToAddition0to9(minimum,maximum, negative);


       // Toast.makeText(this, minimum+" "+ maximum, Toast.LENGTH_SHORT).show();


        /*switch (rangeButtonText){
            case "0-9":
                minimum= "0";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + rangeButtonText);
        }*/
    }

    public void toggleNegative (View v){
        if (!negative){
            negative = true;
            tvNegative.setText("Tap to EXCLUDE NEGATIVE Integers");
            tvNegative.setBackgroundColor(Color.RED);
        }
        else{
            negative = false;
            tvNegative.setText("Tap to INCLUDE NEGATIVE Integers");
            tvNegative.setBackgroundColor(Color.GREEN);
        }



    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition_range_only_main);

        tvNegative = findViewById(R.id.tvNegative);
        tvNegative.setText("Tap to INCLUDE NEGATIVE Integers");

       /* if (MainActivity.Lifetime != 0){
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
