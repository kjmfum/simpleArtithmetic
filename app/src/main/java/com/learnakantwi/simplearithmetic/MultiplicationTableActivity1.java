package com.learnakantwi.simplearithmetic;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
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

public class MultiplicationTableActivity1 extends AppCompatActivity  implements MyAdapter.onClickRecycle{

    int multiplicationNumber;
    ListView lvTimesTable;

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

    int firstNumberInt;
    int secondNumberInt;
    int answerNumberInt;
    int count=-1;
    int slideStartNumberInt=1;
    int slideEndNumberInt=100;

    int loopInt;
    long delayTime=10000;

    Handler handler1;

    Runnable ranable;

    Button btSlideshow;
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
                        Toast.makeText(getApplicationContext(), "No Internet 1", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getApplicationContext(), "No Internet" + entry.getValue(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else{
               // Toast.makeText(this, "Hi "+ entry.getValue() , Toast.LENGTH_SHORT).show();
                Log.i("Number1", entry.getValue());
            }
        }

    }

    public void getNumbersInt(int position){

        myText = timesTableArray.get(position);
        tvSlides.setText(myText);

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
        // if (firstNumberInt <= 10){
        playStraight(convertFirstNumber);

        Log.i("Number3", convertFirstNumber+ ":2  "+ convertSecondNumber);
       // playStraight("eight");
        //}

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
                                                if (100< answerNumberInt && Integer.parseInt(answerNumber.substring(1)) > 0){

                                                String convertAnswer1 = Numbers.get(20);
                                                    String convertAnswer2 = Numbers.get(Integer.parseInt(answerNumber.substring(1)));
                                                playNumbers(convertAnswer1, convertAnswer2);
                                                Log.i("Here", "Yes"+ convertAnswer2 );
                                               // delayTime = 12000;
                                            }
                                            else{
                                                // Toast.makeText(this, "Not here: "+ answerNumberInt, Toast.LENGTH_SHORT).show();
                                                // convertAnswerNumber = Numbers.get(answerNumberInt);
                                                playStraight(convertAnswerNumber);
                                                Log.i("Here", "Nothing"+ answerNumberInt);
                                               // delayTime = 10000;
                                                // playNumbers();
                                            }
                                        }
                                    });
                                }
                            });

                                }
                            });




                            //}

                        }
                    });
                }
                else{
                    // if (secondNumberInt <= 10) {
                    playStraight("times");
                    Log.i("times", "played");
                    //Toast.makeText(MultiplicationTableActivity.this, "Not Not", Toast.LENGTH_SHORT).show();
                    //}
                }




             /*   new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        playStraight(convertSecondNumber);
                    }


                },3000);*/


                /*new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        playStraight("equals");

                    }
                },5000);*/

          /*      new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // if (answerNumberInt <= 10){
                        // playStraight(convertAnswerNumber);  //correct line
                        //}

                        if (100< answerNumberInt && answerNumberInt <200){
                            String convertAnswer1 = Numbers.get(20);
                            String convertAnswer2 = Numbers.get(Integer.parseInt(answerNumber.substring(1)));
                            playNumbers(convertAnswer1, convertAnswer2);
                            Log.i("Here", "Yes"+ convertAnswer2 );
                            delayTime = 12000;
                        }
                        else{
                            // Toast.makeText(this, "Not here: "+ answerNumberInt, Toast.LENGTH_SHORT).show();
                            // convertAnswerNumber = Numbers.get(answerNumberInt);
                            playStraight(convertAnswerNumber);
                            Log.i("Here", "Nothing"+ answerNumberInt);
                            delayTime = 10000;
                            // playNumbers();
                        }

                    }
                },7000);*/
            }
        }, 1000);

    }
    public void playNumbers(String convertAnswerNumber1 , final String convertAnswerNumber2){
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


        //}
        playFromDevice.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                playFromDevice.start();
                playStraight(convertAnswerNumber2);
            }
        });

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
                slideStartNumberInt = Integer.parseInt(etFirstNumber.getText().toString());
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
                slideEndNumberInt = Integer.parseInt(etEndNumber.getText().toString());
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
            onMyItemClick(slideStartNumberInt-1, view);
        }


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


           // handler1.postDelayed(ranable, delayTime);
        handler1.postDelayed(ranable, 2);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication_table_rv_copy);

//        groupSlides = findViewById(R.id.groupSlide);

        btSlideshow = findViewById(R.id.btSlideShow);
        tvSlides = findViewById(R.id.tvSlides);



//        tvFirstNumber = findViewById(R.id.tvStartNumber);
  //      tvEndNumber = findViewById(R.id.tvEndNumber);

       //tvFirstNumber.setVisibility(View.INVISIBLE);

//        etFirstNumber = (EditText)findViewById(R.id.etStartNumber);
  //      etEndNumber = (EditText)findViewById(R.id.etEndNumber);

       // btSlides.setVisibility(View.INVISIBLE);
      // groupSlides.setVisibility(View.INVISIBLE);

        tvSlides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // btSlides.setVisibility(View.INVISIBLE);
               // groupSlides.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });

        btSlideshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               String btSlideshowText = btSlideshow.getText().toString().toLowerCase();

               if (btSlideshowText.equals("slideshow")){
                   Log.i("slide", "Yes");
                   btSlideshow.setText("Start Slideshow");
                   if (etFirstNumber==null){
                       etFirstNumber.setText("2");
                       slideStartNumberInt =2;
                   }
                   if (etEndNumber==null){
                       etEndNumber.setText("12");
                       slideEndNumberInt =12;
                   }

                 //  onMyItemClick(slideStartNumberInt-1, view);
                   recyclerView.setVisibility(View.INVISIBLE);
                   tvSlides.setVisibility(View.VISIBLE);
                   // groupSlides.setVisibility(View.VISIBLE);
                   etFirstNumber.setVisibility(View.VISIBLE);
                   etEndNumber.setVisibility(View.VISIBLE);
                   tvFirstNumber.setVisibility(View.VISIBLE);
                   tvEndNumber.setVisibility(View.VISIBLE);

               }
               else{
                   Log.i("slide", "No");
                   btSlideshow.setText("Slideshow");
                   startSlideShow(view);
               }

            }
        });


        handler1 = new Handler();
        Handler handler2 = new Handler();

        ranable = new Runnable() {
            @Override
            public void run() {
              //  getNumbersInt(count);
               // playNumbers();
                // if (count < timesTableArray.size()){

                if (count < slideEndNumberInt) {
                    getNumbersInt(count);
                    playNumbers();
                    if (answerNumberInt>100){
                       // playNumbers();
                        delayTime = 12000;
                       // playNumbers();
                        Log.i("info1", "Hi: "+delayTime+ " :"+ answerNumberInt);
                    }
                    else{
                        delayTime = 10000;
                        Log.i("info1","hi: "+ delayTime+ " :"+ answerNumberInt);
                    }

                    count++;
                    handler1.postDelayed(ranable, delayTime);
                }

            }
        };

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

     }



        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);

        storageReference = FirebaseStorage.getInstance().getReference();

        downloadAll();

        recyclerView = findViewById(R.id.recyclerView);

        if (MainActivity.Lifetime!=0) {


        }

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        Intent intent = getIntent();
        multiplicationNumber = intent.getIntExtra("vowel",1);

        tvHeading = findViewById(R.id.tvTwoLetterHeader);
        tvHeading.setText("Multiplication Table for Number "+ multiplicationNumber);

        tvHeading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (handler1 != null){
                    handler1.removeCallbacks(ranable);
                    Toast.makeText(MultiplicationTableActivity1.this, "Yes o", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MultiplicationTableActivity1.this, "No o", Toast.LENGTH_SHORT).show();
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

    }
}
