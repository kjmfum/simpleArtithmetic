package com.learnakantwi.simplearithmetic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Random;

public class DivisionActivityCountdown extends AppCompatActivity {

    AdView mAdView;

    Random random = new Random();

    Button btStart;
    Group group;

    int min=0;
    int max = 9;
    int range =(max-min)+1;
    int counter=0;
    int level=1;
    int best5;
    int Lifetime;

    String firstPick;
    StringBuilder sb = new StringBuilder();
    String userAnswer;
    Toast toast;
    String testID;


    TextView tvFirstNumber;
    TextView tvSecondNumber;
    int firstNumber;
    String firstNumberString;

    int secondNumber;
    String secondNumberString;

    int questionAnswer;
    String answerText;
    int answerLength;
    int userAnswerInt;
    int correctScores=0;
    int wrongScores=0;

    TextView tvCorrect;
    TextView tvWrong;
    TextView tvCountdown;

    String one;
    String two;
    String three;
    String four;
    String five;
    String six;
    String seven;
    String eight;
    String nine;
    Chronometer chronometer;


    EditText etAnswer;
    TextWatcher textWatcher;

    MediaPlayer playWrongSound;
    MediaPlayer playCorrectSound;

    CountDownTimer countDownTimer;

    public InterstitialAd mInterstitialAd;


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

    public void changeNumber(View view){
        firstNumber = random.nextInt(range)+min;
        firstNumberString = String.valueOf(firstNumber);
        tvFirstNumber.setText(firstNumberString);

        secondNumber = random.nextInt(range)+min;
        secondNumberString= String.valueOf(secondNumber);
        tvSecondNumber = findViewById(R.id.tvSecondNumber);

        questionAnswer = firstNumber + secondNumber;
        answerText = String.valueOf(questionAnswer);

        answerLength = answerText.length();

        tvSecondNumber.setText(secondNumberString);

        // Toast.makeText(this, answerLength+ " Length", Toast.LENGTH_SHORT).show();
    }
    public void changeNumber(){

        firstNumber = random.nextInt(range)+min;
        firstNumberString = String.valueOf(firstNumber);
        tvFirstNumber.setText(firstNumberString);

        secondNumber = random.nextInt(range)+min;
        secondNumberString= String.valueOf(secondNumber);
        tvSecondNumber = findViewById(R.id.tvSecondNumber);

        questionAnswer = firstNumber + secondNumber;
        answerText = String.valueOf(questionAnswer);

        answerLength = answerText.length();

        tvSecondNumber.setText(secondNumberString);

        //  Toast.makeText(this, answerLength+ " Length", Toast.LENGTH_SHORT).show();

        //firstNumber = random.nextInt(200);
        firstNumber = (random.nextInt(range)+1)+min;
        firstNumberString = String.valueOf(firstNumber);
        tvFirstNumber.setText(firstNumberString);

        secondNumber = (random.nextInt(range)+1)+min;
        secondNumberString= String.valueOf(secondNumber);
        tvSecondNumber = findViewById(R.id.tvSecondNumber);


        while ((float)firstNumber%secondNumber !=0 || firstNumber==0 || firstNumber == secondNumber){
            secondNumber = random.nextInt(range)+min;
            //firstNumber = random.nextInt(20);
        }

        questionAnswer = firstNumber / secondNumber;

        //questionAnswerLong = (long)firstNumber / secondNumber;
        //questionAnswerDouble = (double)firstNumber / secondNumber;
        //questionAnswerFloat = (float)firstNumber / (float)secondNumber;

        answerText = String.valueOf(questionAnswer);
        answerLength = answerText.length();

        firstNumberString = String.valueOf(firstNumber);
        tvFirstNumber.setText(firstNumberString);

        secondNumberString = String.valueOf(secondNumber);
        tvSecondNumber.setText(secondNumberString);

    }

    public void correctAnswer(){

        playCorrectSound = MediaPlayer.create(getApplicationContext(), R.raw.correctsound);
        playCorrectSound.start();

        changeNumber();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                etAnswer.setText("");
            }
        }, 200);
        correctScores++;
        tvCorrect.setText("Correct : "+correctScores);
        tvCorrect.setTextColor(Color.GREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tvCorrect.setTextColor(Color.BLACK);
                //tvCorrect.setBackgroundColor(Color.GREEN);
            }
        },300);

        counter=0;
    }

    public void wrongAnswer(){
        playWrongSound = MediaPlayer.create(getApplicationContext(),R.raw.wrongsound);
        playWrongSound.start();

       /* if (playWrongSound!=null){
            playWrongSound.start();
            playWrongSound.release();

        }
        else{
            playWrongSound.start();
        }*/

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                etAnswer.setText("");
            }
        }, 200);

        wrongScores++;
        tvWrong.setText("Wrong: "+ wrongScores);
        tvWrong.setTextColor(Color.RED);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tvWrong.setTextColor(Color.BLACK);
                //tvCorrect.setBackgroundColor(Color.GREEN);
            }
        },300);
        counter=0;
    }


    public void numberClick(View view){
        int idview = view.getId();
        String number;

        Button btNumber = findViewById(idview);
        number =  btNumber.getText().toString();

        if (playWrongSound!=null){
            playWrongSound.release();

        }

        if (playCorrectSound!=null){
            playCorrectSound.release();

        }

        firstPick =  String.valueOf(number);




        if (counter>answerLength){
            counter=0;
        }
        counter++;
        int answerLengthTest = answerLength;

        if (answerLength==1) {
            etAnswer.setText(firstPick);
            try {
                int userAnswerInt = Integer.parseInt(number);
                if (questionAnswer == userAnswerInt) {
                    correctAnswer();
               /* toast.setText("CORRECT");
                toast.show();*/
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            etAnswer.setText("");
                        }
                    }, 200);
                } else {
                    wrongAnswer();
               /* toast.setText("WRONG");
                toast.show();*/
                }
            }catch (Exception e){
                wrongAnswer();
            }


        }
        else
        {
            try {
                if (counter>1){
                    int TestParse =  Integer.parseInt(number);
                }
                if (answerLength==2) {
                    if (counter == 1) {
                        one = firstPick;
                        etAnswer.setText(firstPick);
                        if (answerText.startsWith("-")){
                            if (!one.equals("-")){
                                // Toast.makeText(this, answerText + ": "+ one, Toast.LENGTH_SHORT).show();
                                wrongAnswer();
                            }
                        }
                        // Toast.makeText(this, String.valueOf(counter), Toast.LENGTH_SHORT).show();
                    }
                    if (counter == 2) {
                        two = one + firstPick;
                        etAnswer.setText(two);
                        counter=0;
                        int userAnswerInt = Integer.parseInt(two);
                        if (questionAnswer == userAnswerInt) {
                            correctAnswer();
                        } else {
                            wrongAnswer();
                        }
                    }
                }
                else if (answerLength==3) {
                    if (counter == 1) {
                        one = firstPick;
                        etAnswer.setText(firstPick);
                        // Toast.makeText(this, String.valueOf(counter), Toast.LENGTH_SHORT).show();
                    }
                    if (counter == 2) {
                        two = one + firstPick;
                        etAnswer.setText(two);
                    }
                    if (counter == 3) {
                        three = two + firstPick;
                        etAnswer.setText(three);
                        counter = 0;
                        int userAnswerInt = Integer.parseInt(three);
                        if (questionAnswer == userAnswerInt) {
                            correctAnswer();
                        } else {
                            wrongAnswer();
                        }
                    }
                }
                else if (answerLength==4) {
                    if (counter == 1) {
                        one = firstPick;
                        etAnswer.setText(firstPick);
                        // Toast.makeText(this, String.valueOf(counter), Toast.LENGTH_SHORT).show();
                    }
                    if (counter == 2) {
                        two = one + firstPick;
                        etAnswer.setText(two);
                    }
                    if (counter == 3) {
                        three = two + firstPick;
                        etAnswer.setText(three);
                    }
                    if (counter == 4) {
                        four = three + firstPick;
                        etAnswer.setText(four);
                        counter = 0;
                        int userAnswerInt = Integer.parseInt(four);
                        if (questionAnswer == userAnswerInt) {
                            correctAnswer();
                        } else {
                            wrongAnswer();
                        }
                    }
                }
                else if (answerLength==5) {
                    if (counter == 1) {
                        one = firstPick;
                        etAnswer.setText(firstPick);
                        // Toast.makeText(this, String.valueOf(counter), Toast.LENGTH_SHORT).show();
                    }
                    if (counter == 2) {
                        two = one + firstPick;
                        etAnswer.setText(two);
                    }
                    if (counter == 3) {
                        three = two + firstPick;
                        etAnswer.setText(three);
                    }
                    if (counter == 4) {
                        four = three + firstPick;
                        etAnswer.setText(four);
                    }
                    if (counter == 5) {
                        five = four + firstPick;
                        etAnswer.setText(five);
                        counter = 0;
                        int userAnswerInt = Integer.parseInt(five);
                        if (questionAnswer == userAnswerInt) {
                            correctAnswer();
                        } else {
                            wrongAnswer();
                        }
                    }
                }
                else if (answerLength==6) {
                    if (counter == 1) {
                        one = firstPick;
                        etAnswer.setText(firstPick);
                        // Toast.makeText(this, String.valueOf(counter), Toast.LENGTH_SHORT).show();
                    }
                    if (counter == 2) {
                        two = one + firstPick;
                        etAnswer.setText(two);
                    }
                    if (counter == 3) {
                        three = two + firstPick;
                        etAnswer.setText(three);
                    }
                    if (counter == 4) {
                        four = three + firstPick;
                        etAnswer.setText(four);
                    }
                    if (counter == 5) {
                        five = four + firstPick;
                        etAnswer.setText(five);
                    }
                    if (counter == 6) {
                        six = five + firstPick;
                        etAnswer.setText(six);
                        counter = 0;
                        int userAnswerInt = Integer.parseInt(six);
                        if (questionAnswer == userAnswerInt) {
                            correctAnswer();
                        } else {
                            wrongAnswer();
                        }
                    }
                }
                else if (answerLength==7) {
                    if (counter == 1) {
                        one = firstPick;
                        etAnswer.setText(firstPick);
                        // Toast.makeText(this, String.valueOf(counter), Toast.LENGTH_SHORT).show();
                    }
                    if (counter == 2) {
                        two = one + firstPick;
                        etAnswer.setText(two);
                    }
                    if (counter == 3) {
                        three = two + firstPick;
                        etAnswer.setText(three);
                    }
                    if (counter == 4) {
                        four = three + firstPick;
                        etAnswer.setText(four);
                    }
                    if (counter == 5) {
                        five = four + firstPick;
                        etAnswer.setText(five);
                    }

                    if (counter == 6) {
                        six = five + firstPick;
                        etAnswer.setText(six);
                    }
                    if (counter == 7) {
                        seven = six + firstPick;
                        etAnswer.setText(seven);
                        counter = 0;
                        int userAnswerInt = Integer.parseInt(seven);
                        if (questionAnswer == userAnswerInt) {
                            correctAnswer();
                        } else {
                            wrongAnswer();
                        }
                    }
                }
                else if (answerLength==8) {
                    if (counter == 1) {
                        one = firstPick;
                        etAnswer.setText(firstPick);
                        // Toast.makeText(this, String.valueOf(counter), Toast.LENGTH_SHORT).show();
                    }
                    if (counter == 2) {
                        two = one + firstPick;
                        etAnswer.setText(two);
                    }
                    if (counter == 3) {
                        three = two + firstPick;
                        etAnswer.setText(three);
                    }
                    if (counter == 4) {
                        four = three + firstPick;
                        etAnswer.setText(four);
                    }
                    if (counter == 5) {
                        five = four + firstPick;
                        etAnswer.setText(five);
                    }

                    if (counter == 6) {
                        six = five + firstPick;
                        etAnswer.setText(six);
                    }

                    if (counter == 7) {
                        seven = six + firstPick;
                        etAnswer.setText(seven);
                    }
                    if (counter == 8) {
                        eight = seven + firstPick;
                        etAnswer.setText(eight);
                        counter = 0;
                        int userAnswerInt = Integer.parseInt(eight);
                        if (questionAnswer == userAnswerInt) {
                            correctAnswer();;

                        } else {
                            wrongAnswer();
                        }
                    }
                }
                else if (answerLength==9) {
                    if (counter == 1) {
                        one = firstPick;
                        etAnswer.setText(firstPick);
                        // Toast.makeText(this, String.valueOf(counter), Toast.LENGTH_SHORT).show();
                    }
                    if (counter == 2) {
                        two = one + firstPick;
                        etAnswer.setText(two);
                    }
                    if (counter == 3) {
                        three = two + firstPick;
                        etAnswer.setText(three);
                    }
                    if (counter == 4) {
                        four = three + firstPick;
                        etAnswer.setText(four);
                    }
                    if (counter == 5) {
                        five = four + firstPick;
                        etAnswer.setText(five);
                    }

                    if (counter == 6) {
                        six = five + firstPick;
                        etAnswer.setText(six);
                    }

                    if (counter == 7) {
                        seven = six + firstPick;
                        etAnswer.setText(seven);
                    }
                    if (counter == 8) {
                        eight = seven + firstPick;
                        etAnswer.setText(eight);
                    }
                    if (counter == 9) {
                        nine = eight + firstPick;
                        etAnswer.setText(nine);
                        counter = 0;
                        int userAnswerInt = Integer.parseInt(nine);
                        if (questionAnswer == userAnswerInt) {
                            correctAnswer();

                        } else {
                            wrongAnswer();
                        }
                    }
                }
                else {
                    if (counter < 0){
                        toast.setText("Not a Valid Number");
                        counter = 0;
                    }
                    else {
                        toast.setText("ABOVE SCOPE OF APP");
                        counter = 0;
                    }

                }
            }
            catch (Exception e){
                toast.setText("Not a Valid Number");
                toast.show();
                String text = etAnswer.getText().toString();
                // etAnswer.setText(text);
                if (!text.isEmpty()) {
                    etAnswer.setText(text.substring(0, (text.length()+1) - 1));
                    counter--;
                }
            }
        }






        sb= sb.append(firstPick);
        // etAnswer.setText(sb, TextView.BufferType.NORMAL);

        Editable editable = etAnswer.getText();
        etAnswer.setSelection(editable.length());

        sb.setLength(answerLength);

        //userAnswer = sb.toString();

        //int userAnswerInt = Integer.parseInt(number);
        etAnswer.addTextChangedListener(textWatcher);

    }

    public void numberClear(View view){
        String text = etAnswer.getText().toString();
        if (!text.isEmpty()){
            etAnswer.setText(text.substring(0, text.length() - 1));
            counter--;
        }


       /* int length = editText.getText().length();
        if (length > 0) {
            editText.getText().delete(length - 1, length);
        }*/
    }

    public void resetGame(View view){
        tvCountdown.setTextColor(Color.BLACK);
        countDownTimer.start();
        counter=0;
        String text = etAnswer.getText().toString();
        if (!text.isEmpty()){
            etAnswer.setText("");
        }
        changeNumber();

        wrongScores=0;
        tvWrong.setText("Wrong: "+ wrongScores);

        correctScores=0;
        tvCorrect.setText("Correct: "+correctScores);


       /* int length = editText.getText().length();
        if (length > 0) {
            editText.getText().delete(length - 1, length);
        }*/
    }

    public  void goToFinishPage(){
        Intent intent = new Intent(getApplicationContext(), DivisionFinishPage.class);
         if (best5 >= correctScores){
        //    if (correctScores > best5){
            intent.putExtra("from","no");
            startActivity(intent);
            // Toast.makeText(AdditionActivityCountdown.this, "Good", Toast.LENGTH_SHORT).show();
        }else{
            intent.putExtra("from","yes");
            intent.putExtra("level",level);
            startActivity(intent);
        }
    }

    public void startTest(){
        btStart.setVisibility(View.GONE);
        group.setVisibility(View.VISIBLE);
         countDownTimer = new CountDownTimer(1001*60*2,1000) {
         //  countDownTimer = new CountDownTimer(1000*10,1000) {
            @Override
            public void onTick(long l) {
                int minutes = ((int)l/60000);
                int seconds = (int)(l- minutes*60000)/1000;
                tvCountdown.setText(minutes+ " : "+ seconds);
                if (minutes==0 && seconds<=30){
                    tvCountdown.setTextColor(Color.RED);
                }
                if (minutes==0 && seconds==1){
                    tvCountdown.setText("00 : 00");
                }
            }



            @Override
            public void onFinish() {

                SharedPreferences preferences = getSharedPreferences("DIV", MODE_PRIVATE);
                SharedPreferences preferences2 = getSharedPreferences("DIV2", MODE_PRIVATE);
                SharedPreferences preferences3 = getSharedPreferences("DIV3", MODE_PRIVATE);
                SharedPreferences preferences4 = getSharedPreferences("DIV4", MODE_PRIVATE);
                SharedPreferences preferences5 = getSharedPreferences("DIV5", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("lastScore", correctScores);
                editor.putInt("attempts", wrongScores+correctScores);
                editor.apply();


                switch (level){
                    case 1:
                        best5 = preferences.getInt("best5",0);
                        goToFinishPage();
                        return;
                    case 2:
                        best5 = preferences2.getInt("best5",0);
                        goToFinishPage();
                        return;
                    case 3:
                        best5 = preferences3.getInt("best5",0);
                        goToFinishPage();
                        return;
                    case 4:
                        best5 = preferences4.getInt("best5",0);
                        goToFinishPage();
                        return;
                    case 5:
                        best5 = preferences5.getInt("best5",0);
                        goToFinishPage();
                        return;
                    default:
                        best5 = 0;
                }

            }
        };
        countDownTimer.start();
    }

    public void advert1() {

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }



        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(testID);
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        //ca-app-pub-7384642419407303/9880404420
        //ca-app-pub-3940256099942544/1033173712 test
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_division_countdown);

        testID = getString(R.string.AdUnitInterstitialID);


        SharedPreferences subscribe = getSharedPreferences("AdsDecisionSimpleArithmetic",MODE_PRIVATE);
        Lifetime = subscribe.getInt("Lifetime", 4);

        if (Lifetime != 0){
            mInterstitialAd = new InterstitialAd(this);
            mInterstitialAd.setAdUnitId(testID);
            mInterstitialAd.loadAd(new AdRequest.Builder().build());


            MobileAds.initialize(this, new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(InitializationStatus initializationStatus) {
                }
            });
            mAdView = findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }

        /*

         */


        countDownTimer = new CountDownTimer(1000 * 60 * 1, 1000) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {

            }
        };

        tvCountdown = findViewById(R.id.tvCountDown);
        group = findViewById(R.id.group);
        group.setVisibility(View.INVISIBLE);

        btStart = findViewById(R.id.btStartTest);
        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTest();
            }
        });


        Intent intent = getIntent();
        min= intent.getIntExtra("min",0);
        max= intent.getIntExtra("max",100);
        level = intent.getIntExtra("level",1);
        range =(max-min)+1;

        chronometer = findViewById(R.id.chronometer);


//        chronometer.start();

        tvCorrect = findViewById(R.id.tvCorrectWrong);
        tvCorrect.setText("Correct : "+correctScores);

        tvWrong = findViewById(R.id.tvWrong);
        tvWrong.setText("Wrong: "+ wrongScores);

        //  toast =Toast.makeText(this, "" , Toast.LENGTH_SHORT);


        etAnswer = findViewById(R.id.etAnswerText);

        etAnswer.setText("");

        tvFirstNumber = findViewById(R.id.tvFirstNumber);
        tvSecondNumber = findViewById(R.id.tvSecondNumber);
        changeNumber();







        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };


    }

   /* @Override
    protected void onResume() {
        startTest();
        super.onResume();
    }*/

    @Override
    protected void onDestroy() {
        int prob = random.nextInt(10);

        if (prob<6){
            if (Lifetime != 0){
                advert1();
            }
        }
        countDownTimer.cancel();
        super.onDestroy();
    }

}
