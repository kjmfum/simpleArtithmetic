package com.learnakantwi.simplearithmetic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
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
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchaseHistoryRecord;
import com.android.billingclient.api.PurchaseHistoryResponseListener;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;
import com.appodeal.ads.Appodeal;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hotchemi.android.rate.AppRate;
import static com.learnakantwi.simplearithmetic.MultiplicationTableActivity.Numbers;

public class MainActivity extends AppCompatActivity implements PurchasesUpdatedListener, PurchaseHistoryResponseListener {

Button btAddition;
Button btSubtraction;
Button btMultiplication;
Button btDivision;
    Button btMultiplicationTable;


Button btNoAds;
Toast toast;
    AdView mAdView;

    StorageReference storageReference;

    BillingClient billingClient;

    String premiumUpgradePrice;
    SharedPreferences sharedPreferencesAds;



    int SPLASH_TIME_OUT = 3000;
    int times =0;
    static int Lifetime=5;

    static final String APPODEAL_KEY = "28ebc6036493e3cc8eac32b49c843db3d75d13f26ee3f832";



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

    public void goToMultiplicationTable(){
        Intent intent = new Intent(this, MultiplicationTableMain.class);
        startActivity(intent);
    }

    public void goToDivision(){
        Intent intent = new Intent(this, DivisionMain.class);
        startActivity(intent);
    }

    public void goToMultiple(){
        Intent intent = new Intent(this, MultipleOperationsSelect.class);
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
        case "MULTIPLE OPERATIONS" :
            goToMultiple();
            return;
        case "LISTEN TO AUDIO OF MULTIPLICATION TABLE" :
            goToMultiplicationTable();
            return;
        default:
            toast.setText(arithmeticType);
            toast.show();
    }
}


    public void setUpBillingClient(String subName) {
       // if (subName.contains("remove_ads")){
            // if (subName.contains("likoio")){

            billingClient = BillingClient.newBuilder(this)
                    .setListener(this)
                    .enablePendingPurchases()
                    .build();
            setUpBillingLifetime(subName);

            /*billingClient = BillingClient.newBuilder(this)
                    .setListener(this)
                    .enablePendingPurchases()
                    .build();
            setUpBillingLifetime(subName);
        }else{
            // Toast.makeText(this, "This " +subName, Toast.LENGTH_SHORT).show();
            billingClient = BillingClient.newBuilder(this)
                    .setListener(this)
                    .enablePendingPurchases()
                    .build();
            setUpBillingLifetime(subName);
        }*/


    }

    public void setUpBillingLifetime(final String subName){

        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    // The BillingClient is ready. You can query purchases here.


                    List<String> skuList = new ArrayList<>();
                    skuList.add("remove_ads");
                  //  skuList.add("math_try");
                    // skuList.add("lifetime_full_access");
                    //skuList.add("likoio");
                    // skuList.add("gas");
                    SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
                    params.setSkusList(skuList).setType(BillingClient.SkuType.INAPP);
                    billingClient.querySkuDetailsAsync(params.build(),
                            new SkuDetailsResponseListener() {
                                @Override
                                public void onSkuDetailsResponse(BillingResult billingResult, List<SkuDetails> skuDetailsList) {
                                    // Process the result.

                                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED) {
                                        toast.setText("Already Purchased 1");
                                        toast.show();

                                    } else{
                                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && skuDetailsList != null) {
                                            for (SkuDetails skuDetails : skuDetailsList) {
                                                String sku = skuDetails.getSku();
                                                String price = skuDetails.getPrice();
                                                // Toast.makeText(InAppActivity.this, "Price: "+ price+" " + sku, Toast.LENGTH_SHORT).show();
                                                // if ("reading_club".equals(sku))
                                                // if ("premium_annually".equals(sku))
                                                if (subName.equals(sku))

                                                //if ("likoio".equals(sku))
                                                {
                                                    premiumUpgradePrice = price;

                                                    BillingFlowParams flowParams = BillingFlowParams.newBuilder()
                                                            .setSkuDetails(skuDetails)
                                                            .build();
                                                    billingClient.launchBillingFlow(MainActivity.this, flowParams);
                                                    // BillingResult responseCode = billingClient.launchBillingFlow(InAppActivity.this,flowParams);
                                                    // Toast.makeText(InAppActivity.this, "I got it done "+ subName , Toast.LENGTH_SHORT).show();
                                                } /*else if ("premium_annually".equals(sku)) {
                                                premiumUpgradePrice = price;
                                            }*/
                                            }
                                        }
                                        else
                                        {

                                        }
                                    }



                                }
                            });
                }
            }
            @Override
            public void onBillingServiceDisconnected() {
                Toast.makeText(MainActivity.this, "I got disconnected", Toast.LENGTH_SHORT).show();
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.

                //setUpBillingClient(subName);
            }


        });
        //billingClient.endConnection();
    }

    @Override
    public void onPurchaseHistoryResponse(BillingResult billingResult, List<PurchaseHistoryRecord> list) {

    }

    @Override
    public void onPurchasesUpdated(BillingResult billingResult, @Nullable List<Purchase> purchases) {
        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK
                && purchases != null) {
            for (Purchase purchase : purchases) {
                handlePurchase(purchase);
            }
        } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.USER_CANCELED) {
            // Handle an error caused by a user cancelling the purchase flow.
            Toast.makeText(this, "You cancelled the Purchase", Toast.LENGTH_SHORT).show();
            billingClient.endConnection();}
        else if(billingResult.getResponseCode()== BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED){
            sharedPreferencesAds = getSharedPreferences("AdsDecisionSimpleArithmetic",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferencesAds.edit();
            editor.putInt("Lifetime",0);
            editor.apply();
            Lifetime =0;
            Toast.makeText(this, "Already Purchased", Toast.LENGTH_SHORT).show();
        } else {
            // Handle any other error codes.
            Toast.makeText(this,"Could not complete purchase", Toast.LENGTH_LONG).show();
        }

    }

    void handlePurchase(Purchase purchase) {
        if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED) {
            // Grant entitlement to the user.
            //Toast.makeText(this, "You bought It", Toast.LENGTH_SHORT).show();
            // Acknowledge the purchase if it hasn't already been acknowledged.
            if (!purchase.isAcknowledged()) {
                AcknowledgePurchaseParams acknowledgePurchaseParams =
                        AcknowledgePurchaseParams.newBuilder()
                                .setPurchaseToken(purchase.getPurchaseToken())
                                .build();
                AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener = new AcknowledgePurchaseResponseListener() {
                    @Override
                    public void onAcknowledgePurchaseResponse(BillingResult billingResult) {

                        sharedPreferencesAds = getSharedPreferences("AdsDecisionSimpleArithmetic",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferencesAds.edit();
                        editor.putInt("Lifetime",0);
                        editor.apply();
                        Lifetime =0;
                       // editor.putInt("Sub",1);

                        toast.setText("Acknowleged");
                        toast.show();

                        /*Intent intent = new Intent(getApplicationContext(), SubPHomeMainActivity.class);
                        startActivity(intent);*/
                    }

                };


                billingClient.acknowledgePurchase(acknowledgePurchaseParams, acknowledgePurchaseResponseListener);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        }
    }

    public void setUpBillingClient() {

        billingClient = BillingClient.newBuilder(this)
                .setListener(this)
                .enablePendingPurchases()
                .build();
        //setUpBillingInApp();
        setUpBilling();
    }

    public void setUpBilling(){

        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {

                    List<String> skuList1 = new ArrayList<>();

                    skuList1.add("remove_ads");
                   // skuList1.add("math_try");
                    SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
                    params.setSkusList(skuList1).setType(BillingClient.SkuType.INAPP);

////////OneTimeProducts
                    billingClient.querySkuDetailsAsync(params.build(),
                            new SkuDetailsResponseListener() {
                                @Override
                                public void onSkuDetailsResponse(BillingResult billingResult, List<SkuDetails> skuDetailsList) {
                                    // Process the result.
                                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED) {
                                        toast.setText("Already Purchased");
                                        toast.show();

                                    } else {
                                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && skuDetailsList != null) {
                                            for (SkuDetails skuDetails : skuDetailsList) {
                                                String sku = skuDetails.getSku();
                                                String price = skuDetails.getPrice();
                                                //
                                               // if ("remove_ads".equals(sku) || "math_try".equals(sku)) {
                                                    if ("remove_ads".equals(sku)) {
                                                    //     if ("likoio".equals(sku)) {
                                                    premiumUpgradePrice = price;
                                                    Purchase.PurchasesResult purchasesResult = billingClient.queryPurchases(BillingClient.SkuType.INAPP);
                                                    List<Purchase> purchasesList = purchasesResult.getPurchasesList();
                                                    if (purchasesList != null && !purchasesList.isEmpty()) {
                                                        // if (purchasesList !=null && purchasesList.size()>1){
                                                        //Toast.makeText(MainActivity.this, "Hi "+ sku +" "+ purchasesList.size(), Toast.LENGTH_SHORT).show();
                                                        for (Purchase purchase : purchasesList) {
                                                            String skuName = purchase.getSku();
                                                            // if (Lifetime == 6) {

                                                           // if (skuName.equals("remove_ads") || "math_try".equals(sku)) {
                                                                if (skuName.equals("remove_ads")) {
                                                                //     if (skuName.equals("likoio")) {

                                                                sharedPreferencesAds = getSharedPreferences("AdsDecisionSimpleArithmetic", MODE_PRIVATE);
                                                                SharedPreferences.Editor editor = sharedPreferencesAds.edit();
                                                                editor.putInt("Lifetime", 0);
                                                                editor.apply();
                                                                Lifetime = sharedPreferencesAds.getInt("Lifetime", 5);
                                                                Intent homeIntent = new Intent(getApplicationContext(), MainActivity.class);
                                                                //startActivity(homeIntent);
                                                            } else {
                                                                Lifetime = 4;
                                                                //Toast.makeText(MainActivity.this, "Not Bought", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    }

                                                }
                                            }
                                        }

                                    }

                                }
                            });
                }
            }
            @Override
            public void onBillingServiceDisconnected() {

                times++;
                times++;
                if (times < 2){
                    toast.setText("Internet Disconnected");
                    toast.show();

                    setUpBillingClient();
                }
                else {
                    Intent homeIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(homeIntent);
                    finish();
                }

            }
        });
    }

    public void downloadAll(){

//        Map<String, String> map = ...
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + "/" + entry.getValue());
//        }
        for (final Map.Entry<Integer, String> entry : Numbers.entrySet()) {

            File myFile = new File("/storage/emulated/0/Android/data/com.learnakantwi.simplearithmetic/files/Music/MathNumbers/" + entry.getValue() + ".m4a");
            if (!myFile.exists()) {
                final StorageReference musicRef = storageReference.child("/MathNumbers/" + entry.getValue() + ".m4a");
                musicRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String url = uri.toString();
                        // playFromFirebase(musicRef);
                        downloadFile(getApplicationContext(), entry.getValue(), ".m4a", url);
                        /*toast.setText(appearText);
                        toast.show();*/
                        //Toast.makeText(getApplicationContext(), appearText, Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Toast.makeText(getApplicationContext(), "No Internet" + entry.getValue(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "Unable to downoad Sound\n Please check Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else{
                // Toast.makeText(this, "Hi "+ entry.getValue() , Toast.LENGTH_SHORT).show();
                Log.i("Number1", entry.getValue());
            }
        }

    }
    public void downloadFile(final Context context, final String filename, final String fileExtension, final String url) {

        if (Build.VERSION.SDK_INT > 22) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        if (isNetworkAvailable()) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
                    Uri uri = Uri.parse(url);
                    DownloadManager.Request request = new DownloadManager.Request(uri);
                    request.setVisibleInDownloadsUi(false);
                    request.setDestinationInExternalFilesDir(getApplicationContext(), Environment.DIRECTORY_MUSIC + "/MathNumbers", filename + fileExtension);
                    downloadManager.enqueue(request);
                }
            };
            Thread myThread = new Thread(runnable);
            myThread.start();
        }
        else
        {
            toast.setText("No Internet");
            toast.show();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT > 22) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }


        Appodeal.initialize(this, APPODEAL_KEY, Appodeal.BANNER | Appodeal.INTERSTITIAL, true);

        storageReference= FirebaseStorage.getInstance().getReference();

        btNoAds = findViewById(R.id.btNoAds);

        sharedPreferencesAds = getSharedPreferences("AdsDecisionSimpleArithmetic",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferencesAds.edit();
     // not subscribed
        // editor.putInt("Sub",1);
       // editor.putInt("Lifetime",3);
        // editor.putInt("Lifetime",0); //subscribed
        editor.apply();

        Lifetime = sharedPreferencesAds.getInt("Lifetime",4);

        if (Lifetime == 0){
             btNoAds.setVisibility(View.GONE);
            //btNoAds.setVisibility(View.VISIBLE);
        }
        else{
            setUpBillingClient();
        }


        btNoAds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpBillingClient("remove_ads");
                //setUpBillingClient("math_try");
            }
        });


    /*    Intent intent = new Intent(this, RecyclerViewExample.class);
        startActivity(intent);*/



        AppRate.with(this)
                .setInstallDays(0)
                .setLaunchTimes(3)
                .setRemindInterval(2)
                .monitor();

        AppRate.showRateDialogIfMeetsConditions(this);


        if (Lifetime != 0){


                Appodeal.show(this, Appodeal.BANNER_BOTTOM);
        }

       toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);



        btAddition = findViewById(R.id.btAddition);
        btSubtraction = findViewById(R.id.btSubtraction);
        btMultiplication = findViewById(R.id.btMultiplication);
        btDivision = findViewById(R.id.btDivision);
        btMultiplicationTable = findViewById(R.id.btListenMultiplicationTable);
    }


    @Override
    protected void onResume() {
        super.onResume();
        //downloadAll();

        if (Lifetime == 0){
           // btNoAds.setVisibility(View.VISIBLE);
             btNoAds.setVisibility(View.GONE);
        }

        if (MainActivity.Lifetime != 0){
            Appodeal.show(this, Appodeal.BANNER_BOTTOM);
        }

    }
}
