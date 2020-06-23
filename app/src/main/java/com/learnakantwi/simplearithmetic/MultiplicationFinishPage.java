package com.learnakantwi.simplearithmetic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MultiplicationFinishPage extends AppCompatActivity {

    AdView mAdView;

    TextView tvLastScore;
    String playerName;
    String coming;
    Intent intent;
    Group group2;
    EditText etPlayerName;
    Button btGetName;

    int level;
    int lastScore;

    int best1;
    int best2;
    int best3;
    int best4;
    int best5;

    String Best1;
    String Best2;
    String Best3;
    String Best4;
    String Best5;

    SharedPreferences.Editor editor;
    SharedPreferences.Editor editors;


    SharedPreferences preferences;
    SharedPreferences preferences2;
    SharedPreferences preferences3;
    SharedPreferences preferences4;
    SharedPreferences preferences5;

    SharedPreferences withNames;
    SharedPreferences withNames2;
    SharedPreferences withNames3;
    SharedPreferences withNames4;
    SharedPreferences withNames5;

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

    public void level1HighScores(String sharedPref, int level){



        switch (level){
            case 1: preferences = getSharedPreferences(sharedPref, 0);
                editor = preferences.edit();



                withNames = getSharedPreferences(sharedPref, 0);
                editors = withNames.edit();

                // lastScore =  preferences.getInt("lastScore", 0);

                best1 = preferences.getInt("best1",0);
                best2 = preferences.getInt("best2",0);
                best3 = preferences.getInt("best3",0);
                best4 = preferences.getInt("best4",0);
                best5 = preferences.getInt("best5",0);

                Best1 = withNames.getString("Best1",Integer.toString(best1));
                Best2 = withNames.getString("Best2",Integer.toString(best2));
                Best3 = withNames.getString("Best3",Integer.toString(best3));
                Best4 = withNames.getString("Best4",Integer.toString(best4));
                Best5 = withNames.getString("Best5",Integer.toString(best5));
                break;

            case 2:  preferences2 = getSharedPreferences(sharedPref, 0);
                editor = preferences2.edit();

                withNames2 = getSharedPreferences(sharedPref, 0);
                editors = withNames2.edit();

                //lastScore =  preferences.getInt("lastScore", 0);

                best1 = preferences2.getInt("best1",0);
                best2 = preferences2.getInt("best2",0);
                best3 = preferences2.getInt("best3",0);
                best4 = preferences2.getInt("best4",0);
                best5 = preferences2.getInt("best5",0);

                Best1 = withNames2.getString("Best1",Integer.toString(best1));
                Best2 = withNames2.getString("Best2",Integer.toString(best2));
                Best3 = withNames2.getString("Best3",Integer.toString(best3));
                Best4 = withNames2.getString("Best4",Integer.toString(best4));
                Best5 = withNames2.getString("Best5",Integer.toString(best5));
                break;

            case 3:  preferences3 = getSharedPreferences(sharedPref, 0);
                editor = preferences3.edit();

                withNames3 = getSharedPreferences(sharedPref, 0);
                editors = withNames3.edit();

                // lastScore =  preferences2.getInt("lastScore", 0);

                best1 = preferences3.getInt("best1",0);
                best2 = preferences3.getInt("best2",0);
                best3 = preferences3.getInt("best3",0);
                best4 = preferences3.getInt("best4",0);
                best5 = preferences3.getInt("best5",0);

                Best1 = withNames3.getString("Best1",Integer.toString(best1));
                Best2 = withNames3.getString("Best2",Integer.toString(best2));
                Best3 = withNames3.getString("Best3",Integer.toString(best3));
                Best4 = withNames3.getString("Best4",Integer.toString(best4));
                Best5 = withNames3.getString("Best5",Integer.toString(best5));
                break;

            case 4:  preferences4 = getSharedPreferences(sharedPref, 0);
                editor = preferences4.edit();

                withNames4 = getSharedPreferences(sharedPref, 0);
                editors = withNames4.edit();

                // lastScore =  preferences2.getInt("lastScore", 0);

                best1 = preferences4.getInt("best1",0);
                best2 = preferences4.getInt("best2",0);
                best3 = preferences4.getInt("best3",0);
                best4 = preferences4.getInt("best4",0);
                best5 = preferences4.getInt("best5",0);

                Best1 = withNames4.getString("Best1",Integer.toString(best1));
                Best2 = withNames4.getString("Best2",Integer.toString(best2));
                Best3 = withNames4.getString("Best3",Integer.toString(best3));
                Best4 = withNames4.getString("Best4",Integer.toString(best4));
                Best5 = withNames4.getString("Best5",Integer.toString(best5));
                break;

            case 5:  preferences5 = getSharedPreferences(sharedPref, 0);
                editor = preferences5.edit();

                withNames4 = getSharedPreferences(sharedPref, 0);
                editors = withNames5.edit();

                // lastScore =  preferences2.getInt("lastScore", 0);

                best1 = preferences5.getInt("best1",0);
                best2 = preferences5.getInt("best2",0);
                best3 = preferences5.getInt("best3",0);
                best4 = preferences5.getInt("best4",0);
                best5 = preferences5.getInt("best5",0);

                Best1 = withNames5.getString("Best1",Integer.toString(best1));
                Best2 = withNames5.getString("Best2",Integer.toString(best2));
                Best3 = withNames5.getString("Best3",Integer.toString(best3));
                Best4 = withNames5.getString("Best4",Integer.toString(best4));
                Best5 = withNames5.getString("Best5",Integer.toString(best5));
                break;

        }

        /*SharedPreferences preferences = getSharedPreferences(sharedPref, 0);
        SharedPreferences.Editor editor = preferences.edit();

        SharedPreferences withNames = getSharedPreferences(sharedPref, 0);
        SharedPreferences.Editor editors = withNames.edit();

        int lastScore =  preferences.getInt("lastScore", 0);

        int best1 = preferences.getInt("best1",0);
        int best2 = preferences.getInt("best2",0);
        int best3 = preferences.getInt("best3",0);
        int best4 = preferences.getInt("best4",0);
        int best5 = preferences.getInt("best5",0);

        String Best1 = withNames.getString("Best1",Integer.toString(best1));
        String Best2 = withNames.getString("Best2",Integer.toString(best2));
        String Best3 = withNames.getString("Best3",Integer.toString(best3));
        String Best4 = withNames.getString("Best4",Integer.toString(best4));
        String Best5 = withNames.getString("Best5",Integer.toString(best5));*/


        if (lastScore>best1){
            int temp = best1;
            int temp3 = best2;
            int temp4=best3;
            int temp5=best4;
            String tempo = Best1;
            String tempo3 = Best2;
            String tempo4 = Best3;
            String tempo5 = Best4;

            //best1= lastScore;
            Best1 = playerName + ": "+ lastScore;
            best2= temp;


            editor.putInt("best2",temp);
            editor.putInt("best1",lastScore);
            editor.putInt("best3",temp3);
            editor.putInt("best4",temp4);
            editor.putInt("best5",temp5);
            editor.apply();
            editors.putString("Best1", Best1);
            editors.putString("Best2", tempo);
            editors.putString("Best3", tempo3);
            editors.putString("Best4", tempo4);
            editors.putString("Best5", tempo5);
            editors.apply();

            Best2 = tempo;
            Best3=tempo3;
            Best4 = tempo4;
            Best5=tempo5;
        }
        else if(lastScore==best1 && lastScore>best2 ){
            int temp3 = best2;
            int temp4=best3;
            int temp5=best4;
            String tempo3 = Best2;
            String tempo4 = Best3;
            String tempo5 = Best4;


            Best2 = playerName + ": "+ lastScore;


            // editor.putInt("best1",best1);
            editor.putInt("best5",temp5);
            editor.putInt("best4",temp4);
            editor.putInt("best3", temp3);
            editor.putInt("best2",lastScore);
            editor.apply();

            editors.putString("Best5", tempo5);
            editors.putString("Best4", tempo4);
            editors.putString("Best3", tempo3);
            editors.putString("Best2", Best2);
            editors.apply();


            Best3 = tempo3;
            Best4 = tempo4;
            Best5 = tempo5;

        }
        else if(lastScore>best2){

            int temp3 = best2;
            int temp4=best3;
            int temp5=best4;

            String tempo3 = Best2;
            String tempo4 = Best3;
            String tempo5 = Best4;

            Best2 = playerName + ": "+ lastScore;

            editor.putInt("best5",temp5);
            editor.putInt("best4",temp4);
            editor.putInt("best3",temp3);
            editor.putInt("best2",lastScore);
            editor.apply();
            editors.putString("Best5", tempo5);
            editors.putString("Best4", tempo4);
            editors.putString("Best3", tempo3);
            editors.putString("Best2", Best2);
            editors.apply();

            Best3=tempo3;
            Best4 = tempo4;
            Best5=tempo5;


        }
        else if(lastScore==best2 && lastScore>best3){
               /* int temp = best1;
                int temp3 = best2;*/
            int temp4=best3;
            int temp5=best4;


            String tempo4 = Best3;
            String tempo5 =Best4;
            // editor.putInt("best1",best1);
            Best3 = playerName + ": "+ lastScore;

            editor.putInt("best5",temp5);
            editor.putInt("best4",temp4);
            editor.putInt("best3",lastScore);
            editor.apply();
            // editor.putInt("best2",best1);

            editors.putString("Best5", tempo5);
            editors.putString("Best4", tempo4);
            editors.putString("Best3", Best3);
            editors.apply();
            // editors.putString("Best2", Best1);

            Best4 = tempo4;
            Best5 = tempo5;


        }
        else if(lastScore>best3){

            int temp4 = best3;
            int temp5 =best4;


            String tempo4 = Best3;
            String tempo5 = Best4;

            Best3= playerName + ": "+ lastScore;

            editor.putInt("best4",temp4);
            editor.putInt("best5",temp5);
            editor.putInt("best3",lastScore);
            editor.apply();
            editors.putString("Best5", tempo5);
            editors.putString("Best4", tempo4);
            editors.putString("Best3", Best3);
            editors.apply();

            Best4 = tempo4;
            Best5= tempo5;



        }
        else if(lastScore==best3 && lastScore>best4){
            int temp5 = best4;
            String tempo5 = Best4;

            Best4 = playerName + ": "+ lastScore;

            // editor.putInt("best1",best1);
            editor.putInt("best5",temp5);
            editor.putInt("best4",lastScore);
            editor.apply();

            editors.putString("Best5", tempo5);
            editors.putString("Best4", Best4);
            editors.apply();

            Best5 = tempo5;


        }
        else if(lastScore>best4){


            int temp5=best4;

            String tempo5 = Best4;

            Best4= playerName + ": "+ lastScore;

            editor.putInt("best5",temp5);
            editor.putInt("best4",lastScore);
            editor.apply();

            editors.putString("Best5", tempo5);
            editors.putString("Best4", Best4);
            editors.apply();

            Best5=tempo5;
        }
        else if(lastScore==best4 && lastScore>best5){

            // String tempo5 = Best4;

            // editor.putInt("best1",best1);
            Best5 = playerName + ": "+ lastScore;

            editor.putInt("best5",lastScore);
            editor.apply();
            // editor.putInt("best2",best1);

            editors.putString("Best5", Best5);
            editors.apply();

            // Best5 = tempo5;

        }
        else if(lastScore>best5){
            best5= lastScore;
            Best5 = playerName + ": "+ best5;
            editor.putInt("best5", best5);
            editor.apply();

            editors.putString("Best5", Best5);
            editors.apply();
        }

        tvLastScore.setText("Level: "+level+"\n\n"+"High Scores \n\n\t" +Best1 + "\n\t"+Best2+  "\n\t"+Best3+ "\n\t"+Best4+ "\n\t" +Best5+"\n\t");
        // tvLastScore.setText("Level: "+level+"\n\n"+"Your Score:" +lastScore+"\n\n\t"+ "High Scores \n\t" +Best1 + "\n\t"+Best2+  "\n\t"+Best3+ "\n\t"+Best4+ "\n\t" +Best5+"\n\t");

        // tvLastScore.setText(String.valueOf(lastScore));
        //tvLastScore.setText("Your Score:" +lastScore+"\n\t"+ "High Scores \n\t" +best1 + "\n\t"+best2+  "\n\t"+best3+ "\n\t"+best4+ "\n\t" +best5+"\n\t");
    }
    public void newHighScoreList(int level) {
        switch (level){
            case 1:
                level1HighScores("MULTI",1);
                return;
            case 2:
                level1HighScores("MULTI2",2);
                return;
            case 3:
                level1HighScores("MULTI3",3);
                return;
            case 4:
                level1HighScores("MULTI4",4);
                return;
            case 5:
                level1HighScores("MULTI5",5);
                return;
        }
    }


    public void level1HighScoreList(String sharedPref){
        SharedPreferences preferences = getSharedPreferences(sharedPref, 0);
        //SharedPreferences.Editor editor = preferences.edit();
        //int lastScore =  preferences.getInt("lastScore", 5);
        int best1 = preferences.getInt("best1",0);
        int best2 = preferences.getInt("best2",0);
        int best3 = preferences.getInt("best3",0);
        int best4 = preferences.getInt("best4",0);
        int best5 = preferences.getInt("best5",0);


        SharedPreferences withNames = getSharedPreferences(sharedPref, 0);

        String Best1 = withNames.getString("Best1",Integer.toString(best1));
        String Best2 = withNames.getString("Best2",Integer.toString(best2));
        String Best3 = withNames.getString("Best3",Integer.toString(best3));
        String Best4 = withNames.getString("Best4",Integer.toString(best4));
        String Best5 = withNames.getString("Best5",Integer.toString(best5));

        tvLastScore.setText("Level: "+level+"\n\n"+"High Scores \n\n\t" +Best1 + "\n\t"+Best2+  "\n\t"+Best3+ "\n\t"+Best4+ "\n\t" +Best5+"\n\t");
        //tvLastScore.setText("Level: "+level+"\n\n"+"High Scores \n\n\t" +Best1 + "\n\t"+Best2+  "\n\t"+Best3+ "\n\t"+Best4+ "\n\t" +Best5+"\n\t");
    }
    public void HighScoreList(int level){
        switch (level){
            case 1:
                level1HighScoreList("MULTI");
                return;
            case 2:
                level1HighScoreList("MULTI2");
                return;
            case 3:
                level1HighScoreList("MULTI3");
                return;
            case 4:
                level1HighScoreList("MULTI4");
                return;
            case 5:
                level1HighScoreList("MULTI5");
                return;
        }



        // tvLastScore.setText(String.valueOf(lastScore));
        // tvLastScore.setText("High Scores \n\t" +stringBest1 + "\n\t"+best2+  "\n\t"+best3+ "\n\t"+best4+ "\n\t" +best5+"\n\t");
    }


    public void highScoreAfterName(int level){

        group2.setVisibility(View.GONE);
        tvLastScore.setVisibility(View.VISIBLE);

        if (coming!=null && coming.equals("yes")){
            newHighScoreList(level);
        }
        else{
            HighScoreList(level);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition_finish_page);


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


        tvLastScore = findViewById(R.id.tvHighScores);
        tvLastScore.setVisibility(View.INVISIBLE);
        etPlayerName = findViewById(R.id.editText);
        group2 = findViewById(R.id.group2);
        btGetName = findViewById(R.id.btGetName);

        intent = getIntent();
        coming = intent.getStringExtra("from");



        SharedPreferences preferences = getSharedPreferences("MULTI", 0);
        lastScore =  preferences.getInt("lastScore", 5);

        if (coming!=null && coming.equals("yes")){
            group2.setVisibility(View.VISIBLE);
            level = intent.getIntExtra("level",1);
            // Toast.makeText(this, "Hi"+ level, Toast.LENGTH_SHORT).show();
        }
        else{
            group2.setVisibility(View.GONE);
            //level=1;
            level = intent.getIntExtra("level",1);
            tvLastScore.setVisibility(View.VISIBLE);
            HighScoreList(level);
        }

        btGetName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerName = etPlayerName.getText().toString();
                // Toast.makeText(AdditionFinishPage.this, playerName, Toast.LENGTH_SHORT).show();
                group2.setVisibility(View.GONE);
                tvLastScore.setVisibility(View.VISIBLE);

                if (coming!=null && coming.equals("yes")){
                    newHighScoreList(level);
                }
                else{
                    HighScoreList(level);
                }
            }
        });

    }



        @Override
        public void onBackPressed() {

            if (coming!=null && coming.equals("yes")){
                Intent intent = new Intent(getApplicationContext(), MultiplicationCountdownMain.class);
                startActivity(intent);
            }

            super.onBackPressed();
        }
}

