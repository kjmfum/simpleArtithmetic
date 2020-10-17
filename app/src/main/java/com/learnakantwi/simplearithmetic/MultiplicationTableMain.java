package com.learnakantwi.simplearithmetic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.appodeal.ads.Appodeal;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

public class MultiplicationTableMain extends AppCompatActivity implements MyAdapterRV.onClickRecycle {

    static ArrayList<String> numbersArray;
    //CharSequence vowel;
    int vowel;
    HorizontalScrollView horizontalScrollView;
    ListView lvReadingAlphabets;
    AdView mAdView;
    AdView mAdView1;
    Toast toast;
    MyAdapterRV multiplicationMainAdapter;

    RecyclerView recyclerView;


    public void goToTwoLetters() {
        // Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.udemy.com/course/learn-akan-twi/?referralCode=6D321CE6AEE1834CCB0F"));
       // Intent intent = new Intent(this, MultiplicationTableActivityBase.class);
        Intent intent = new Intent(this, MultiplicationTableActivity.class);
        intent.putExtra("vowel", vowel);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //MenuInflater menuInflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.main_menu_search1, menu);

        final MenuItem item = menu.findItem(R.id.menusearch);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                multiplicationMainAdapter.getFilter().filter(newText);


                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()){
            case R.id.main:
                goToMain();
                return  true;
            default:
                return false;
        }
    }

    public void goToMain(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication_table_main);

        recyclerView = findViewById(R.id.recyclerView);

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

        numbersArray = new ArrayList<>();

        for (int i=1 ; i<= 100 ; i++){

            String j = Integer.toString(i);
           // Toast.makeText(this, "Hi "+ i, Toast.LENGTH_SHORT).show();
            numbersArray.add(j);
        }

        multiplicationMainAdapter = new MyAdapterRV(numbersArray, this, this);
        recyclerView.setAdapter(multiplicationMainAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
       // recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(gridLayoutManager);

        //////

 /*       lvReadingAlphabets = findViewById(R.id.lvReadingAlphabet);


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
        });*/

        /////////////

    }

    @Override
    public void onMyItemClick(int position, View view) {
        vowel = Integer.parseInt(numbersArray.get(position));
        System.out.println("Hi5 "+ vowel);
        // Toast.makeText(MultiplicationTableMain.this, "Hi: "+ vowel, Toast.LENGTH_SHORT).show();
        goToTwoLetters();
    }
}
