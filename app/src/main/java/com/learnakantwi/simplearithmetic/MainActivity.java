package com.learnakantwi.simplearithmetic;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchaseHistoryRecord;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.List;

import hotchemi.android.rate.AppRate;

public class MainActivity extends AppCompatActivity {

Button btAddition;
Button btSubtraction;
Button btMultiplication;
Button btDivision;
Toast toast;
    AdView mAdView;

    BillingClient billingClient;

    String premiumUpgradePrice;
    SharedPreferences sharedPreferencesAds;



    int SPLASH_TIME_OUT = 3000;
    int times =0;
    int Lifetime=5;


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu1, menu);

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
                return  true;*/
            case R.id.oneMinuteTraining:
                goToOneMinuteTraining();
                return  true;
            case R.id.main:
                goToMain();
                return  true;
            case R.id.dailyNotification:
                //Log.i("Menu Item Selected", "Alphabets");
                notificationPreference();
                return  true;
            case R.id.dailyNotificationOff:
                //Log.i("Menu Item Selected", "Alphabets");
                notificationPreferenceOff();
                return  true;
            default:
                return false;
        }
    }

    private void notificationPreference(){

        final SharedPreferences sharedPreferences = this.getSharedPreferences("com.learnakantwi.simplearithmetic", Context.MODE_PRIVATE);

        String dailyTwiPreference = sharedPreferences.getString("DailyTrainingPreference", "Yes");
        sharedPreferences.edit().putString("DailyTrainingPreference", "Yes").apply();

        Toast.makeText(this, "Daily Training Alerts Turned On", Toast.LENGTH_SHORT).show();

      /*  //assert dailyTwiPreference != null;
        //if (!dailyTwiPreference.equals("Yes")) {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.simplemathicon)
                .setTitle("Daily Training")
                .setMessage("Would You Like to Receive Daily Alerts for one Minute Training?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sharedPreferences.edit().putString("DailyTrainingPreference", "Yes").apply();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sharedPreferences.edit().putString("DailyTrainingPreference", "No").apply();
                            }
                        }
                )
                .show();*/
    }

    private void notificationPreferenceOff(){

        final SharedPreferences sharedPreferences = this.getSharedPreferences("com.learnakantwi.simplearithmetic", Context.MODE_PRIVATE);

        String dailyTwiPreference = sharedPreferences.getString("DailyTrainingPreference", "Yes");
        sharedPreferences.edit().putString("DailyTrainingPreference", "No").apply();

        //assert dailyTwiPreference != null;
        //if (!dailyTwiPreference.equals("Yes")) {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.simplemathicon)
                .setTitle("Daily Training")
                .setMessage("Would You Like to Receive Daily Alerts for one Minute Training?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sharedPreferences.edit().putString("DailyTrainingPreference", "Yes").apply();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sharedPreferences.edit().putString("DailyTrainingPreference", "No").apply();
                            }
                        }
                )
                .show();
    }

    public void goToMain(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
    public void goToOneMinuteTraining(){
        Intent intent = new Intent(getApplicationContext(), OneMinuteMain.class);
        startActivity(intent);
    }


    public void goToAddition(){
    Intent intent = new Intent(this, AdditionHome.class);
    startActivity(intent);
}

    public void goToSubtraction(){
        Intent intent = new Intent(this, SubtractionMain1.class);
        startActivity(intent);
    }

    public void goToMultiplication(){
        Intent intent = new Intent(this, MultiplicationHome.class);
        startActivity(intent);
    }

    public void goToDivision(){
        Intent intent = new Intent(this, DivisionMain1.class);
        startActivity(intent);
    }


public void goToAritmetic(View view){
    int idview = view.getId();
    Button me = view.findViewById(idview);

    String arithmeticType = me.getText().toString();

    switch(arithmeticType){
        case "ADDITION" :
            goToAddition();
            return;
        case "SUBTRACTION" :
            goToSubtraction();
            return;
        case "MULTIPLICATION" :
            goToMultiplication();
            return;
        case "DIVISION" :
            goToDivision();
            return;
        default:
            toast.setText(arithmeticType);
            toast.show();
    }
}




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    /*    Intent intent = new Intent(this, RecyclerViewExample.class);
        startActivity(intent);*/



        AppRate.with(this)
                .setInstallDays(0)
                .setLaunchTimes(2)
                .setRemindInterval(2)
                .monitor();

        AppRate.showRateDialogIfMeetsConditions(this);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

       toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);



        btAddition = findViewById(R.id.btAddition);
        btSubtraction = findViewById(R.id.btSubtraction);
        btMultiplication = findViewById(R.id.btMultiplication);
        btDivision = findViewById(R.id.btDivision);
    }
}
