package com.learnakantwi.simplearithmetic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MultiplicationTableActivity extends AppCompatActivity  implements MyAdapter.onClickRecycle{

    int multiplicationNumber;
    ListView lvTimesTable;

    public InterstitialAd mInterstitialAd;

    static ArrayList<String> timesTableArray;
   static HashMap<Integer, String> Numbers;

    TextView tvHeading;

    AdView mAdView;

    RecyclerView recyclerView;
    StorageReference storageReference;
    Toast toast;

    MediaPlayer playFromDevice;
    MediaPlayer mp1;

    String myText;
    String convertFirstNumber;
    String convertSecondNumber;
    String convertAnswerNumber;
    String answerNumber;
    String answerNumber20;
    String testID;

    int firstNumberIntReal =1;
    int slideStartNumber = firstNumberIntReal-1;

    int lastNumberIntReal =10;
    int slideEndNumber = lastNumberIntReal=1;

    int firstNumberInt;
    int lastNumberInt;
    int secondNumberInt;
    int answerNumberInt;
    int count=-1;
   // int slideStartNumberInt=1;
   // int slideEndNumberInt=100;
    boolean slideshowBool = false;
    boolean repeat = false;
    boolean pause= false;
    boolean loop= true;

    int loopInt;
    long delayTime=10000;

    Handler handler1;

    Runnable ranable;

    Button btSlideshow;
    ImageButton btPlayNext;
    ImageButton btPlayPrevious;
    ImageButton btPlayPause;
    ImageButton btPlayRepeatOne;
    ImageButton btPlay;
    //ImageButton btPlayRepeatOne;

    TextView tvSlides;

    Group groupSlides;

    EditText etFirstNumber;
    EditText etEndNumber;

    TextView tvFirstNumber;
    TextView tvEndNumber;


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
    public void playFromFirebase(StorageReference musicRef) {

        if (Build.VERSION.SDK_INT > 22) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        if (isNetworkAvailable()) {

            try {
                final File localFile = File.createTempFile("aduonu", "m4a");

                if (localFile != null) {
                    musicRef.getFile(localFile)
                            .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                                    if (mp1 != null) {
                                        mp1.stop();
                                        mp1.reset();
                                        mp1.release();
                                    }
                                    mp1 = new MediaPlayer();
                                    try {
                                        mp1.setDataSource(getApplicationContext(), Uri.fromFile(localFile));
                                        mp1.prepareAsync();
                                        mp1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                            @Override
                                            public void onPrepared(MediaPlayer mp) {
                                                mp.start();
                                            }
                                        });
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle failed download
                            // ...
                        }
                    });
                } else {
                    toast.setText("Unable to download now. Please try later");
                    toast.show();
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            toast.setText("Please Connect to the Internet");
            toast.show();
        }

    }


    public void playStraight1(final String filename) {
        final StorageReference musicRef = storageReference.child("/MathNumbers/" + filename + ".m4a");
        musicRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                playFromFirebase(musicRef);
                downloadFile(getApplicationContext(), filename, ".m4a", url);
                        /*toast.setText(appearText);
                        toast.show();*/
                //Toast.makeText(getApplicationContext(), appearText, Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_SHORT).show();
            }
        });
//    } else {
//        toast.setText("Please connect to Internet to download audio");
//        toast.show();
//    }
    }

    public void playStraight(final String filename) {

        if (Build.VERSION.SDK_INT > 22)
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

       // Toast.makeText(this, "I'm here o: "+ filename, Toast.LENGTH_SHORT).show();

        File myFile = new File("/storage/emulated/0/Android/data/com.learnakantwi.simplearithmetic/files/Music/MathNumbers/" + filename + ".m4a");
        if (myFile.exists()) {

            try {
               if (playFromDevice != null) {
                    playFromDevice.stop();
                    playFromDevice.reset();
                    playFromDevice.release();
                }
                playFromDevice = new MediaPlayer();


                playFromDevice.setDataSource(myFile.toString());
                playFromDevice.prepareAsync();
                playFromDevice.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                       /* toast.setText(appearText);
                        toast.show();*/
                    }
                });
            } catch (IOException e) {
                Log.i("Except", "e: "+ e) ;
            e.printStackTrace();
            }
        } else {
            if (isNetworkAvailable()) {
                final StorageReference musicRef = storageReference.child("/MathNumbers/" + filename + ".m4a");
                musicRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String url = uri.toString();
                        playFromFirebase(musicRef);
                        downloadFile(getApplicationContext(), filename, ".m4a", url);
                        /*toast.setText(appearText);
                        toast.show();*/
                        //Toast.makeText(getApplicationContext(), appearText, Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_SHORT).show();

                    }
                });
            } else {
                toast.setText("Please connect to Internet to download audio");
                toast.show();
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


    public void downloadAll(){


//        Map<String, String> map = ...
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + "/" + entry.getValue());
//        }

        if (!downloaded()){

                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

            for (final Map.Entry<Integer, String> entry : Numbers.entrySet()) {

                //  Toast.makeText(this, "I came", Toast.LENGTH_SHORT).show();
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
                            // Log.i("meOnly", entry.getValue());
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


    }

    public boolean downloaded(){
        int checkNumber=0;

        for (Map.Entry<Integer, String> entry : Numbers.entrySet()) {

            //  Toast.makeText(this, "I came", Toast.LENGTH_SHORT).show();
            File myFile = new File("/storage/emulated/0/Android/data/com.learnakantwi.simplearithmetic/files/Music/MathNumbers/" + entry.getValue() + ".m4a");
            if (myFile.exists()) {
                checkNumber++;
            }
           /* else{
                Toast.makeText(this, "Hi "+ entry.getValue(), Toast.LENGTH_SHORT).show();
            }*/
        }

        if (checkNumber == Numbers.size()){
            Log.i("Download", "All downloaded");
            return  true;
            //Toast.makeText(this, "All downloaded "+ Numbers.size(), Toast.LENGTH_SHORT).show();
        }
        else{
           // Toast.makeText(this, "Please Download Audio", Toast.LENGTH_SHORT).show();
            return false;

        }
    }

    public void getNumbersInt(int position){

        myText = timesTableArray.get(position);
        tvSlides.setText(myText);
        tvSlides.setTextColor(Color.GREEN);
        tvSlides.setBackgroundColor(Color.BLUE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tvSlides.setTextColor(Color.DKGRAY);
                tvSlides.setBackgroundColor(Color.WHITE);
            }
        },delayTime-500);

        int firstSpace = myText.indexOf(" ");
        String firstNumber =  myText.substring(0,firstSpace);
        firstNumberInt = Integer.parseInt(firstNumber);



        int secondSpace = myText.indexOf(" ",firstSpace+1);
        int thirdSpace = myText.indexOf(" ",secondSpace+1);
        String secondNumber = myText.substring(secondSpace, thirdSpace ).trim();
        secondNumberInt = Integer.parseInt(secondNumber);

        int fourthSpace = myText.indexOf(" ",thirdSpace+1);
        answerNumber = myText.substring(fourthSpace).trim();
        answerNumberInt = Integer.parseInt(answerNumber);

        convertFirstNumber = Numbers.get(firstNumberInt);
        convertSecondNumber = Numbers.get(secondNumberInt);

        convertAnswerNumber = Numbers.get(answerNumberInt);

        Log.i("Number2", convertFirstNumber+ ":  "+ convertSecondNumber);
       // playNumbers();

    }

    public void playNumbers(){
        playStraight(convertFirstNumber);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (playFromDevice != null){
                    playFromDevice.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            playFromDevice.start();

                            //Toast.makeText(MultiplicationTableActivity.this, "Completed", Toast.LENGTH_SHORT).show();
                            //if (secondNumberInt <= 10) {
                            playStraight("times");

                            playFromDevice.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mediaPl) {
                                    playFromDevice.start();
                                    playStraight(convertSecondNumber);

                                    playFromDevice.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mediaPl) {
                                    playFromDevice.start();
                                    playStraight("equals");


                                    playFromDevice.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mediaPlayer) {
                                           // playFromDevice.start();

                                          //  if (100< answerNumberInt && answerNumberInt <200){
                                                if (100< answerNumberInt && answerNumberInt<1000 && Integer.parseInt(answerNumber.substring(1)) > 0){

                                               // String convertAnswer1 = Numbers.get(20);
                                                    int convertAnswer1 = 5;
                                                    String convertAnswer2 = Numbers.get(Integer.parseInt(answerNumber.substring(1)));

                                                playNumbers(convertAnswer1, convertAnswer2);
                                                Log.i("Here", "Yes"+ convertAnswer2 );
                                               // delayTime = 12000;
                                            }
                                                else if (answerNumberInt>1000 && answerNumberInt<10000 && Integer.parseInt(answerNumber.substring(1))!=0){
                                                   // String convertAnswer1 = Numbers.get(Integer.parseInt(answerNumber.substring(1)));
                                                    if (Integer.parseInt(answerNumber.substring(2))!=0){
                                                        int convertAnswer1 = Integer.parseInt(answerNumber.substring(1));
                                                        String convertAnswer2 = Numbers.get(Integer.parseInt(answerNumber.substring(2)));
                                                        playNumbers(convertAnswer1, convertAnswer2);
                                                    }
                                                    else{
                                                        int convertAnswer1 = 0;
                                                        String convertAnswer2 = Numbers.get(Integer.parseInt(answerNumber.substring(1)));
                                                        playNumbers(convertAnswer1, convertAnswer2);
                                                    }


                                            }
                                            else{
                                                playStraight(convertAnswerNumber);
                                                Log.i("Here", "Nothing"+ answerNumberInt);
                                            }
                                        }
                                    });
                                }
                            });

                                }
                            });
                        }
                    });
                }
                else{
                    playStraight("times");
                }

            }
        }, 1000);

    }

    public void playHundreds(int hundred){
        if (hundred <200){
            playStraight("onehundredand");
        }
        else if (hundred>200 && hundred<300){
            playStraight("twohundredand");
        }
        else if (hundred>300 && hundred<400){
            playStraight("threehundredand");
        }
        else if (hundred>400 && hundred<500){
            playStraight("fourhundredand");
        }
        else if (hundred>500 && hundred<600){
            playStraight("fivehundredand");
        }
        else if (hundred>600 && hundred<700){
            playStraight("sixhundredand");
        }
        else if (hundred>700 && hundred<800){
            playStraight("sevenhundredand");
        }
        else if (hundred>800 && hundred<900){
            playStraight("eighthundredand");
        }
        else if (hundred>900 && hundred<1000){
            playStraight("ninehundredand");
        }
    }
    public void playNumbers(final int convertAnswerNumber1 , String convertAnswerNumber2){
        // if (firstNumberInt <= 10){
        //playStraight(convertAnswerNumber1);

        if (answerNumberInt<200){
            playStraight("onehundredand");
        }
        else if (answerNumberInt>200 && answerNumberInt<300){
            playStraight("twohundredand");
        }
        else if (answerNumberInt>300 && answerNumberInt<400){
            playStraight("threehundredand");
        }
        else if (answerNumberInt>400 && answerNumberInt<500){
            playStraight("fourhundredand");
        }
        else if (answerNumberInt>500 && answerNumberInt<600){
            playStraight("fivehundredand");
        }
        else if (answerNumberInt>600 && answerNumberInt<700){
            playStraight("sixhundredand");
        }
        else if (answerNumberInt>700 && answerNumberInt<800){
            playStraight("sevenhundredand");
        }
        else if (answerNumberInt>800 && answerNumberInt<900){
            playStraight("eighthundredand");
        }
        else if (answerNumberInt>900 && answerNumberInt<1000){
            playStraight("ninehundredand");
        }
        else if (answerNumberInt>1000 && answerNumberInt<2000){
           // convertAnswerNumber2 = answerNumber.substring(2);
            playStraight("onethousand");
        }
        else if (answerNumberInt>2000 && answerNumberInt<3000){
            // convertAnswerNumber2 = answerNumber.substring(2);
            playStraight("twothousands");
        }
        else if (answerNumberInt>3000 && answerNumberInt<4000){
            // convertAnswerNumber2 = answerNumber.substring(2);
            playStraight("threethousands");
        }
        else if (answerNumberInt>4000 && answerNumberInt<5000){
            // convertAnswerNumber2 = answerNumber.substring(2);
            playStraight("fourthousands");
        }
        else if (answerNumberInt>5000 && answerNumberInt<6000){
            // convertAnswerNumber2 = answerNumber.substring(2);
            playStraight("fivethousands");
        }
        else if (answerNumberInt>6000 && answerNumberInt<7000){
            // convertAnswerNumber2 = answerNumber.substring(2);
            playStraight("sixthousands");
        }
        else if (answerNumberInt>7000 && answerNumberInt<8000){
            // convertAnswerNumber2 = answerNumber.substring(2);
            playStraight("seventhousands");
        }
        else if (answerNumberInt>8000 && answerNumberInt<9000){
            // convertAnswerNumber2 = answerNumber.substring(2);
            playStraight("eightthousands");
        }
        else if (answerNumberInt>9000 && answerNumberInt<10000){
            // convertAnswerNumber2 = answerNumber.substring(2);
            playStraight("ninethousands");
        }

        final String ConvertAnswerNumber = convertAnswerNumber2;
      /*  if (answerNumberInt>1000 && answerNumberInt<2000){
            Log.i("came", "hi");
            Toast.makeText(this, "No way", Toast.LENGTH_SHORT).show();
            convertAnswerNumber = convertAnswerNumber2.substring(1);
        }*/
        playFromDevice.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
               // playFromDevice.start();
                Log.i("came", "hi1");
               // Toast.makeText(MultiplicationTableActivity.this, "Yes o"+ ConvertAnswerNumber, Toast.LENGTH_SHORT).show();
                if (answerNumberInt>1000 && answerNumberInt<10000 && convertAnswerNumber1 > 100){
                    playHundreds(convertAnswerNumber1);
                   // Toast.makeText(MultiplicationTableActivity.this, "Hi: "+ convertAnswerNumber1, Toast.LENGTH_SHORT).show();
                    playFromDevice.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            playStraight(ConvertAnswerNumber);
                        }
                    });
                }
                else{
                    playStraight(ConvertAnswerNumber);
                }
            }
        });

        /////////

       /* new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                playStraight(convertAnswerNumber2);
            }
        }, 2000);*/

    }

    public void startSlideShow(View view){
        int ready1= 0;
        int ready2=0;
        if (etFirstNumber!=null){
            try {
                //int startNumberInt = Integer.parseInt(etFirstNumber.getText().toString());
                slideStartNumber = Integer.parseInt(etFirstNumber.getText().toString());
                ready1 = 1;
            }catch (Exception e){
                Toast.makeText(this, "Not a Valid Number: "+e, Toast.LENGTH_SHORT).show();
            }

        }
        else{
            toast.setText("Please Enter the Start Number");
            toast.show();
        }
        if (etEndNumber!=null){
            try {
                //int startNumberInt = Integer.parseInt(etFirstNumber.getText().toString());
                slideEndNumber = Integer.parseInt(etEndNumber.getText().toString());
                ready2=1;
            }catch (Exception e){
                Toast.makeText(this, "Not a Valid Number: "+e, Toast.LENGTH_SHORT).show();
            }
        }
        else{
            ready2=0;
            toast.setText("Please Enter the End Number");
            toast.show();
        }

        if (ready1==1 && ready2==1){
            onMyItemClick(slideStartNumber-1, view);
        }


    }
    public void startSlideShow1(View view){
        btPlay.setVisibility(View.INVISIBLE);
        btPlayPause.setVisibility(View.VISIBLE);
        /*if (repeat){
            repeat=false;
            count++;
        }*/


        pause = false;
        onMyItemClick(0, view);
    }

    @Override
    public void onMyItemClick(int position, View view) {


        Log.i("Hello", "Count: "+count +" position: "+position);

        if(count != -1 ){
            handler1.removeCallbacks(ranable);
            if (playFromDevice!=null){
                playFromDevice.stop();
            }

        }

//        handler1 = new Handler();
        count =position;

        if (slideshowBool){
            handler1.postDelayed(ranable, 2);
        }
        else{
            getNumbersInt(count);
            playNumbers();
        }
    }


    public void advert1() {

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(testID);
        mInterstitialAd.loadAd(new Builder().build());

    }

    public void playBackOne(){

        if (!repeat) {
            btPlay.setVisibility(View.INVISIBLE);
            btPlayPause.setVisibility(View.VISIBLE);

            if (handler1 != null) {
                handler1.removeCallbacks(ranable);
            }
            if (playFromDevice != null) {
                playFromDevice.stop();
            }
            if (count == 1 || count == 0) {
                Log.i("Count1", "Hi " + count);
                count = 0;
            } else {
                Log.i("Count1", "Hi 1 " + count);
                count -= 2;
            }


            try {
                handler1.postDelayed(ranable, 2);
            } catch (Exception e) {
                Log.i("MyException", e.toString());
            }
        }
    }

    public void playForwardOne(){

        btPlay.setVisibility(View.INVISIBLE);
        btPlayPause.setVisibility(View.VISIBLE);
        //repeat=false;


        if (handler1 !=null){
            handler1.removeCallbacks(ranable);
        }
        if (playFromDevice!=null){
            playFromDevice.stop();
        }

       // count;

        try {
            handler1.postDelayed(ranable, 2);
            btPlay.setVisibility(View.INVISIBLE);
            btPlayPause.setVisibility(View.VISIBLE);
        }
        catch (Exception e){
            Log.i("MyException", e.toString());
        }
    }

    public void playRepeatOne(){

        btPlay.setVisibility(View.INVISIBLE);
        btPlayPause.setVisibility(View.VISIBLE);
        pause = false;

        if (handler1 !=null){
            handler1.removeCallbacks(ranable);
        }
        if (playFromDevice!=null){
            playFromDevice.stop();
        }

        repeat = !repeat;
        if (!repeat){
            btPlayRepeatOne.setBackgroundColor(Color.WHITE);
        }
        else{
            btPlayRepeatOne.setBackgroundColor(Color.GREEN);
        }

        if (count>0){
            count--;
        }

       try {
            handler1.postDelayed(ranable, 2);
        }
        catch (Exception e){
            Log.i("MyException", e.toString());
        }

    }

    public void play(){

        /*if (!repeat){
            btPlayRepeatOne.setBackgroundColor(Color.WHITE);
        }*/

        if (repeat){
            repeat=false;
            count++;
        }


        pause = false;
        try {
            handler1.postDelayed(ranable, 2);
        }
        catch (Exception e){
            Log.i("MyException", e.toString());
        }
        btPlay.setVisibility(View.INVISIBLE);
        btPlayPause.setVisibility(View.VISIBLE);
        if (count!=0){
            count--;
        }
    }

    public void playPause() {

       /* if (count ==9){
            btPlay.setVisibility(View.VISIBLE);
            btPlayPause.setVisibility(View.INVISIBLE);
        }*/

        btPlayRepeatOne.setBackgroundColor(Color.WHITE);
        repeat=false;

        pause = !pause;

       /* if (handler1 !=null){
            handler1.removeCallbacks(ranable);
        }
        if (playFromDevice!=null){
            playFromDevice.stop();
        }

        if (!pause){
          play();
        }
        else{*/
            btPlay.setVisibility(View.VISIBLE);
            btPlayPause.setVisibility(View.INVISIBLE);
            //Toast.makeText(MultiplicationTableActivity.this, "Paused", Toast.LENGTH_SHORT).show();
            if (handler1 !=null){
                handler1.removeCallbacks(ranable);
            }
            if (playFromDevice!=null){
                playFromDevice.stop();
            }
        //}

    }

    public void deleteDuplicatelDownload(){

        File myFiles = new File("/storage/emulated/0/Android/data/com.learnakantwi.simplearithmetic/files/Music/MathNumbers/");

        File [] files1 = myFiles.listFiles();

        if (files1.length>0){
            for (File file : files1) {

                if (file.getName().contains("-")) {

                    boolean wasDeleted = file.delete();

                }
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication_table_rv_copy);

        if (5>2) {
            Numbers = new HashMap<>();
            Numbers.put(1, "one");
            Numbers.put(2, "two");
            Numbers.put(3, "three");
            Numbers.put(4, "four");
            Numbers.put(5, "five");
            Numbers.put(6, "six");
            Numbers.put(7, "seven");
            Numbers.put(8, "eight");
            Numbers.put(9, "nine");
            Numbers.put(10, "ten");
            Numbers.put(11, "eleven");
            Numbers.put(12, "twelve");
            Numbers.put(13, "thirteen");
            Numbers.put(14, "fourteen");
            Numbers.put(15, "fifteen");
            Numbers.put(16, "sixteen");
            Numbers.put(17, "seventeen");
            Numbers.put(18, "eighteen");
            Numbers.put(19, "nineteen");
            Numbers.put(20, "twenty");

            Numbers.put(21, "twentyone");
            Numbers.put(22, "twentytwo");
            Numbers.put(23, "twentythree");
            Numbers.put(24, "twentyfour");
            Numbers.put(25, "twentyfive");
            Numbers.put(26, "twentysix");
            Numbers.put(27, "twentyseven");
            Numbers.put(28, "twentyeight");
            Numbers.put(29, "twentynine");

            Numbers.put(30, "thirty");
            Numbers.put(31, "thirtyone");
            Numbers.put(32, "thirtytwo");
            Numbers.put(33, "thirtythree");
            Numbers.put(34, "thirtyfour");
            Numbers.put(35, "thirtyfive");
            Numbers.put(36, "thirtysix");
            Numbers.put(37, "thirtyseven");
            Numbers.put(38, "thirtyeight");
            Numbers.put(39, "thirtynine");

            Numbers.put(40, "forty");
            Numbers.put(41, "fortyone");
            Numbers.put(42, "fortytwo");
            Numbers.put(43, "fortythree");
            Numbers.put(44, "fortyfour");
            Numbers.put(45, "fortyfive");
            Numbers.put(46, "fortysix");
            Numbers.put(47, "fortyseven");
            Numbers.put(48, "fortyeight");
            Numbers.put(49, "fortynine");


            Numbers.put(50, "fifty");
            Numbers.put(51, "fiftyone");
            Numbers.put(52, "fiftytwo");
            Numbers.put(53, "fiftythree");
            Numbers.put(54, "fiftyfour");
            Numbers.put(55, "fiftyfive");
            Numbers.put(56, "fiftysix");
            Numbers.put(57, "fiftyseven");
            Numbers.put(58, "fiftyeight");
            Numbers.put(59, "fiftynine");

            Numbers.put(60, "sixty");
            Numbers.put(61, "sixtyone");
            Numbers.put(62, "sixtytwo");
            Numbers.put(63, "sixtythree");
            Numbers.put(64, "sixtyfour");
            Numbers.put(65, "sixtyfive");
            Numbers.put(66, "sixtysix");
            Numbers.put(67, "sixtyseven");
            Numbers.put(68, "sixtyeight");
            Numbers.put(69, "sixtynine");
            Numbers.put(70, "seventy");


            Numbers.put(71, "seventyone");
            Numbers.put(72, "seventytwo");
            Numbers.put(73, "seventythree");
            Numbers.put(74, "seventyfour");
            Numbers.put(75, "seventyfive");
            Numbers.put(76, "seventysix");
            Numbers.put(77, "seventyseven");
            Numbers.put(78, "seventyeight");
            Numbers.put(79, "seventynine");
            Numbers.put(80, "eighty");

            Numbers.put(81, "eightyone");
            Numbers.put(82, "eightytwo");
            Numbers.put(83, "eightythree");
            Numbers.put(84, "eightyfour");
            Numbers.put(85, "eightyfive");
            Numbers.put(86, "eightysix");
            Numbers.put(87, "eightyseven");
            Numbers.put(88, "eightyeight");
            Numbers.put(89, "eightyeight");

            Numbers.put(90, "ninety");
            Numbers.put(91, "ninetyone");
            Numbers.put(92, "ninetytwo");
            Numbers.put(93, "ninetythree");
            Numbers.put(94, "ninetyfour");
            Numbers.put(95, "ninetyfive");
            Numbers.put(96, "ninetysix");
            Numbers.put(97, "ninetyseven");
            Numbers.put(98, "ninetyeight");
            Numbers.put(99, "ninetynine");


            Numbers.put(100, "onehundred");
            Numbers.put(200, "twohundred");
            Numbers.put(300, "threehundred");
            Numbers.put(400, "fourhundred");
            Numbers.put(500, "fivehundred");
            Numbers.put(600, "sixhundred");
            Numbers.put(700, "sevenhundred");
            Numbers.put(800, "eighthundred");
            Numbers.put(900, "ninehundred");
            Numbers.put(1000, "onethousand");


            Numbers.put(-100, "onehundredand");
            Numbers.put(-200, "twohundredand");
            Numbers.put(-300, "threehundredand");
            Numbers.put(-400, "fourhundredand");
            Numbers.put(-500, "fivehundredand");
            Numbers.put(-600, "sixhundredand");
            Numbers.put(-700, "sevenhundredand");
            Numbers.put(-800, "eighthundredand");
            Numbers.put(-900, "ninehundredand");

          //  Numbers.put(-1000, "onethousands");
            Numbers.put(-2000, "twothousands");
            Numbers.put(-3000, "threethousands");
            Numbers.put(-4000, "fourthousands");
            Numbers.put(-5000, "fivethousands");
            Numbers.put(-6000, "sixthousands");
            Numbers.put(-7000, "seventhousands");
            Numbers.put(-8000, "eightthousands");
            Numbers.put(-9000, "ninethousands");


            Numbers.put(2000, "twothousand");
            Numbers.put(3000, "threethousand");
            Numbers.put(4000, "fourthousand");
            Numbers.put(5000, "fivethousand");
            Numbers.put(6000, "sixthousand");
            Numbers.put(7000, "seventhousand");
            Numbers.put(8000, "eightthousand");
            Numbers.put(9000, "ninethousand");
            Numbers.put(10000, "tenthousand");

        }

        if(downloaded()){
            Toast.makeText(this, "Tap to Listen", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Not All Audio Downloaded", Toast.LENGTH_SHORT).show();
        }



        handler1 = new Handler();
        Handler handler2 = new Handler();

        ranable = new Runnable() {
            @Override
            public void run() {
                //  getNumbersInt(count);
                // playNumbers();
                // if (count < timesTableArray.size()){

                // if (count < slideEndNumberInt) {
                if (count < 100) {
                    Log.i("Count", "Hi "+ count);
                    getNumbersInt(count);
                    playNumbers();
                    if (answerNumberInt>100 & answerNumberInt<1000){
                        // playNumbers();
                        delayTime = 12000;
                        // playNumbers();
                        Log.i("info1", "Hi: "+delayTime+ " :"+ answerNumberInt);
                    }
                    else if (answerNumberInt>=1000 & answerNumberInt<10000){
                        delayTime = 14800;
                        // playNumbers();
                        Log.i("info1", "Hi: "+delayTime+ " :"+ answerNumberInt);
                    }
                    else{
                        delayTime = 10000;
                        //Log.i("info1","hi: "+ delayTime+ " :"+ answerNumberInt);
                    }

                   if(count < 100){
                         // Log.i("MeOnly", "Hi"+ lastNumberInt+ ": "+ lastNumberIntReal);
                           handler1.postDelayed(ranable, delayTime);
                       }
                   else{
                      // Toast.makeText(MultiplicationTableActivity.this, "Hi "+ count, Toast.LENGTH_SHORT).show();
                       playPause();
                   }

                    if (!repeat){
                        count++;
                    }



                }

            }
        };


        btPlayNext = findViewById(R.id.playNext);
        btPlayNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playForwardOne();
            }
        });

        btPlayPause = findViewById(R.id.pauseButton);
        btPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playPause();
            }
        });

        btPlayPrevious = findViewById(R.id.playPrevious);
        btPlayPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playBackOne();
            }
        });

        btPlay = findViewById(R.id.playButton);
        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play();
            }
        });

        btPlayRepeatOne = findViewById(R.id.playRepeatOne);
        btPlayRepeatOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playRepeatOne();
            }
        });


        btPlayNext.setVisibility(View.INVISIBLE);
        btPlayPause.setVisibility(View.INVISIBLE);
        btPlayPrevious.setVisibility(View.INVISIBLE);
        btPlay.setVisibility(View.INVISIBLE);
        btPlayRepeatOne.setVisibility(View.INVISIBLE);


//        groupSlides = findViewById(R.id.groupSlide);
        testID = getString(R.string.AdUnitInterstitialID);

        btSlideshow = findViewById(R.id.btSlideShow);
        tvSlides = findViewById(R.id.tvSlides);

        tvSlides.setVisibility(View.INVISIBLE);

//        tvFirstNumber = findViewById(R.id.tvStartNumber);
  //      tvEndNumber = findViewById(R.id.tvEndNumber);

       //tvFirstNumber.setVisibility(View.INVISIBLE);

        //etFirstNumber = (EditText)findViewById(R.id.etStartNumber);
        //etEndNumber = (EditText)findViewById(R.id.etEndNumber);

       // btSlides.setVisibility(View.INVISIBLE);
      // groupSlides.setVisibility(View.INVISIBLE);

       tvSlides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //play();


                //////////
               // btSlides.setVisibility(View.INVISIBLE);
               // groupSlides.setVisibility(View.INVISIBLE);
               // tvSlides.setTextColor(Color.BLUE);

                ///start
                /*new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tvSlides.setTextColor(Color.BLACK);
                    }
                },1500);*/

                //////Here
                //recyclerView.setVisibility(View.VISIBLE);

                ///////////
            }
        });

        btSlideshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               String btSlideshowText = btSlideshow.getText().toString().toLowerCase();

               if (btSlideshowText.equals("start slideshow")){
                   Log.i("slide", "Yes");
                   btSlideshow.setText("End Slideshow");
                   slideshowBool = true;
                   startSlideShow1(view);
                   /*if (etFirstNumber==null){
                       etFirstNumber.setText("2");
                       slideStartNumberInt =2;
                   }
                   if (etEndNumber==null){
                       etEndNumber.setText("12");
                       slideEndNumberInt =12;
                   }*/

                 //  onMyItemClick(slideStartNumberInt-1, view);
                   recyclerView.setVisibility(View.INVISIBLE);
                   tvSlides.setVisibility(View.VISIBLE);
                   btPlayNext.setVisibility(View.VISIBLE);
                   btPlayPause.setVisibility(View.VISIBLE);
                   btPlayPrevious.setVisibility(View.VISIBLE);
                   btPlayRepeatOne.setVisibility(View.VISIBLE);
                  // btPlay.setVisibility(View.VISIBLE);
                   // groupSlides.setVisibility(View.VISIBLE);
                  // etFirstNumber.setVisibility(View.VISIBLE);
                   //etEndNumber.setVisibility(View.VISIBLE);
                   //tvFirstNumber.setVisibility(View.VISIBLE);
                   //tvEndNumber.setVisibility(View.VISIBLE);

               }
               else{
                   Log.i("slide", "No: "+ btSlideshowText);
                   btSlideshow.setText("Start Slideshow");
                   slideshowBool = false;
                   //startSlideShow1(view);

                   if (handler1 !=null){
                       handler1.removeCallbacks(ranable);
                   }
                   if (playFromDevice!=null){
                       playFromDevice.stop();
                   }
                   recyclerView.setVisibility(View.VISIBLE);
                   tvSlides.setVisibility(View.INVISIBLE);
                   btPlayNext.setVisibility(View.INVISIBLE);
                   btPlayPause.setVisibility(View.INVISIBLE);
                   btPlayPrevious.setVisibility(View.INVISIBLE);
                   btPlay.setVisibility(View.INVISIBLE);
                   btPlayRepeatOne.setVisibility(View.INVISIBLE);
                   btPlayRepeatOne.setBackgroundColor(Color.WHITE);
                   repeat=false;

               }

            }
        });






        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);

        storageReference = FirebaseStorage.getInstance().getReference();


        try{
            downloadAll();
            deleteDuplicatelDownload();
            Log.i("Check", "Worked: ");
        }
        catch(Exception e){
            Log.i("Check", "failed: "+ e.toString());
        }



        recyclerView = findViewById(R.id.recyclerView);


        if (MainActivity.Lifetime != 0){

            mInterstitialAd = new InterstitialAd(this);
            mInterstitialAd.setAdUnitId(testID);
            mInterstitialAd.loadAd(new Builder().build());


            MobileAds.initialize(this, new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(InitializationStatus initializationStatus) {
                }
            });
            mAdView = findViewById(R.id.adView);
            AdRequest adRequest = new Builder().build();
            mAdView.loadAd(adRequest);
        }



        Intent intent = getIntent();
        multiplicationNumber = intent.getIntExtra("vowel",1);

        tvHeading = findViewById(R.id.tvTwoLetterHeader);
        tvHeading.setText("Multiplication Table for Number "+ multiplicationNumber);

        tvHeading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (handler1 != null){
                    handler1.removeCallbacks(ranable);
                   // handler2.removeCallbacks(ranable);
                    //Toast.makeText(MultiplicationTableActivity.this, "Yes o", Toast.LENGTH_SHORT).show();
                }
                else{
                    //Toast.makeText(MultiplicationTableActivity.this, "No o", Toast.LENGTH_SHORT).show();
                }

            }
        });


        timesTableArray = new ArrayList<>();

        for (int i =1 ; i<= 100 ; i++){

            int product = multiplicationNumber * i;

            timesTableArray.add(multiplicationNumber+ " x " + i + " = "+ product );

        }



       // lvTimesTable = findViewById(R.id.lvtwoLetters);
        MyAdapter readingTwoLetterAdapter = new MyAdapter(this, timesTableArray, this);
        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, ReadingTwoLetterAdapter);
        recyclerView.setAdapter(readingTwoLetterAdapter);

        //recyclerView.setLayoutManager(gridLayoutManager);
       // GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        lvTimesTable.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                view.getId();
//                TextView textView = view.findViewById(view.getId());
//
//                String text = textView.getText().toString();
//                String text1 = text.substring(0,2);
//
//                Toast.makeText(MultiplicationTableActivity.this, "Hi: "+ text, Toast.LENGTH_SHORT).show();
//            }
//        });




    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

            if (handler1 !=null){
                handler1.removeCallbacks(ranable);
            }
            if (playFromDevice!=null){
                playFromDevice.stop();
            }

         Random random = new Random();
        int prob = random.nextInt(10);

        if (prob<8){
            Log.i("advert", "came");
            if (MainActivity.Lifetime != 0){
                Log.i("advert", "came2");
                advert1();
            }
        }


      /*  if (handler1 !=null){
            handler1.removeCallbacks(ranable);
        }*/

    }


    @Override
    protected void onStop() {
        super.onStop();
       if (handler1 !=null){
            handler1.removeCallbacks(ranable);
        }
        if (playFromDevice!=null){
            playFromDevice.stop();
        }

        //onDestroy();

        btSlideshow.setText("Start Slideshow");
        slideshowBool = false;
        //startSlideShow1(view);

        recyclerView.setVisibility(View.VISIBLE);
        tvSlides.setVisibility(View.INVISIBLE);
        btPlayNext.setVisibility(View.INVISIBLE);
        btPlayPause.setVisibility(View.INVISIBLE);
        btPlayPrevious.setVisibility(View.INVISIBLE);
        btPlayRepeatOne.setVisibility(View.INVISIBLE);
    }
}
