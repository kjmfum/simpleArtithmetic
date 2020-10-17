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
import com.google.android.gms.ads.AdView;

public class SubtractionMain1 extends AppCompatActivity {

    AdView mAdView;

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
        Intent intent = new Intent(getApplicationContext(), SubtractionRangeOnlyMain.class);
        startActivity(intent);
    }

    public void rangesCoundown(View view){
        Intent intent = new Intent(getApplicationContext(), AdditionRangeOnlyMain.class);
        startActivity(intent);
    }

    public void subtrationCountdown(){
        Intent intent = new Intent(getApplicationContext(), SubtractionCountdownMain.class);
        startActivity(intent);
    }

    public void numberToSubtract (){
        Intent intent = new Intent(getApplicationContext(), SubtractionNumberRangeMain.class);
        startActivity(intent);
    }

    public void goToHighScores(){
        Intent intent = new Intent(getApplicationContext(), SubtractionHighScores.class);
        startActivity(intent);
    }

    public void goToSubtractionType (View view){
        int idview = view.getId();
        Button me = view.findViewById(idview);

        String arithmeticType = me.getText().toString();

        switch(arithmeticType){
            case "PRACTISE WITH RANGES ONLY" :
                practiseRanges();
                return;
            case "PRACTISE A NUMBER TO A RANGE" :
                numberToSubtract();
                return;
            case "COUNTDOWN TEST" :
                subtrationCountdown();
                return;
            case "HIGH SCORES" :
                goToHighScores();
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
        setContentView(R.layout.activity_subtraction_main1);

        if (MainActivity.Lifetime != 0){
            Appodeal.show(this, Appodeal.BANNER_BOTTOM);
        }

    }
}


