package com.learnakantwi.simplearithmetic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
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

import com.appodeal.ads.Appodeal;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Random;

public class SubtractionRangeActivity extends AppCompatActivity {

    AdView mAdView;

    Random random = new Random();

    int min=0;
    int max = 9;
    int range =(max-min)+1;
    int counter=0;
    int numberToAdd;

    String firstPick;
    StringBuilder sb = new StringBuilder();
    String userAnswer;
    String testID;
    Toast toast;


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

    long stoppedTime=0;

    Button btReset;
    Button btPause;

    public InterstitialAd mInterstitialAd;

    Boolean negative;
    Boolean timerCheck=true;

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



    public void changeNumber(){

        firstNumber = random.nextInt(range)+min;
        firstNumberString = String.valueOf(firstNumber);
        //tvFirstNumber.setText(firstNumberString);

        secondNumber = numberToAdd;

        secondNumberString= String.valueOf(secondNumber);
        tvSecondNumber = findViewById(R.id.tvSecondNumber);

        int prob = random.nextInt(10);
        if (prob<5){
            questionAnswer = firstNumber - secondNumber;
            tvFirstNumber.setText(firstNumberString);
            tvSecondNumber.setText(secondNumberString);
        }
        else{
            questionAnswer = secondNumber - firstNumber;
            tvFirstNumber.setText(secondNumberString);
            tvSecondNumber.setText(firstNumberString);
        }

        answerText = String.valueOf(questionAnswer);

        answerLength = answerText.length();

       // tvSecondNumber.setText(secondNumberString);

        //  Toast.makeText(this, answerLength+ " Length", Toast.LENGTH_SHORT).show();

    }

    public void correctAnswer(){

        if (timerCheck){
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


    }

    public void wrongAnswer(){

        if (timerCheck){
            playWrongSound = MediaPlayer.create(getApplicationContext(),R.raw.wrongsound);
            playWrongSound.start();

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

    }


    public void numberClick(View view){
        int idview = view.getId();
        String number;
        boolean valid =true;

        Button btNumber = findViewById(idview);
        number =  btNumber.getText().toString();

       // Toast.makeText(this, "Hi" + questionAnswer, Toast.LENGTH_SHORT).show();


        if (playWrongSound!=null){
            playWrongSound.release();

        }

        if (playCorrectSound!=null){
            playCorrectSound.release();

        }





        if (counter>answerLength){
            counter=0;
        }

        firstPick =  String.valueOf(number);

        if (answerLength>1 && counter>0){

            try {
                Integer.parseInt(number);
               // Toast.makeText(this, "Hi "+ number , Toast.LENGTH_SHORT).show();
            } catch (Exception e){
                wrongAnswer();
               // Toast.makeText(this, "Hi failed"+ number.substring(1) + " okay" , Toast.LENGTH_SHORT).show();
                valid = false;
            }
        }



        if (valid) {

            counter++;

            if (answerLength == 1) {
                etAnswer.setText(firstPick);
                int userAnswerInt =0;
                try {
                    userAnswerInt = Integer.parseInt(number);
                    if (questionAnswer == userAnswerInt) {
                        correctAnswer();
                    } else {
                        wrongAnswer();
                    }
                }catch (Exception e){
                    wrongAnswer();
                }

               /* if (questionAnswer == userAnswerInt) {
                    correctAnswer();
                } else {
                    wrongAnswer();
                }*/
            } else if (answerLength == 2) {
                if (counter == 1) {
                    one = firstPick;
                    etAnswer.setText(firstPick);
                    // Toast.makeText(this, String.valueOf(counter), Toast.LENGTH_SHORT).show();
                }
                if (counter == 2) {
                    two = one + firstPick;
                    etAnswer.setText(two);
                    counter = 0;
                    // try {
                    int userAnswerInt = Integer.parseInt(two);
                    //}
                    //catch (Exception e){
                    //  etAnswer.setText("");
                    //}
                    if (questionAnswer == userAnswerInt) {
                        correctAnswer();
                    } else {
                        wrongAnswer();
                    }
                }
            } else if (answerLength == 3) {
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
            } else if (answerLength == 4) {
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
            } else if (answerLength == 5) {
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
                        wrongAnswer();
                    }
                }
            } else if (answerLength == 6) {
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
            } else if (answerLength == 7) {
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
            } else if (answerLength == 8) {
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
                        correctAnswer();
                    } else {
                        wrongAnswer();
                    }
                }
            } else if (answerLength == 9) {
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
            } else {
                toast.setText("ABOVE SCOPE OF APP");
            }
            sb = sb.append(firstPick);
            // etAnswer.setText(sb, TextView.BufferType.NORMAL);

            Editable editable = etAnswer.getText();
            etAnswer.setSelection(editable.length());

            sb.setLength(answerLength);

            //userAnswer = sb.toString();

            // int userAnswerInt = Integer.parseInt(number);
            etAnswer.addTextChangedListener(textWatcher);

        }






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
        chronometer.setBase(SystemClock.elapsedRealtime());
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

        btPause.setText("PAUSE");
        timerCheck=true;
        btReset.setVisibility(View.INVISIBLE);
        chronometer.stop();
        chronometer.start();

    }

    public void pauseGame(View view){

        if (timerCheck){
            stoppedTime = chronometer.getBase() - SystemClock.elapsedRealtime();
            chronometer.stop();
            timerCheck=false;
            btPause.setText("RESUME");
            if(MainActivity.Lifetime !=0){
                advert1();
            }
            btReset.setVisibility(View.VISIBLE);
            String text = etAnswer.getText().toString();
            if (!text.isEmpty()){
                etAnswer.setText("");
            }
            //Toast.makeText(this, "Hi: " + timerCheck, Toast.LENGTH_SHORT).show();
        }else {
            chronometer.setBase(SystemClock.elapsedRealtime() + stoppedTime);
            chronometer.start();
            btPause.setText("PAUSE");
            timerCheck=true;
            btReset.setVisibility(View.INVISIBLE);
            //Toast.makeText(this, "Hi: " + timerCheck, Toast.LENGTH_SHORT).show();

        }


    }

    public void pauseGame(){

        if (timerCheck){
            stoppedTime = chronometer.getBase() - SystemClock.elapsedRealtime();
            chronometer.stop();
            timerCheck=false;
            btPause.setText("RESUME");
            //advert1();
            btReset.setVisibility(View.VISIBLE);
            String text = etAnswer.getText().toString();
            if (!text.isEmpty()){
                etAnswer.setText("");
            }
            //Toast.makeText(this, "Hi: " + timerCheck, Toast.LENGTH_SHORT).show();
        }else {
            chronometer.setBase(SystemClock.elapsedRealtime() + stoppedTime);
            chronometer.start();
            btPause.setText("PAUSE");
            timerCheck=true;
            btReset.setVisibility(View.INVISIBLE);
            //Toast.makeText(this, "Hi: " + timerCheck, Toast.LENGTH_SHORT).show();

        }


    }

    public void advert1() {

        Appodeal.show(this, Appodeal.INTERSTITIAL);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtraction_range);


        testID = getString(R.string.AdUnitInterstitialID);

        if (MainActivity.Lifetime != 0){
            Appodeal.show(this, Appodeal.BANNER_TOP);
        }

        btReset = findViewById(R.id.btReset);
        btReset.setVisibility(View.INVISIBLE);
        btPause = findViewById(R.id.btPause);



        Intent intent = getIntent();
        min= intent.getIntExtra("min",0);
        max= intent.getIntExtra("max",1000);
        numberToAdd = intent.getIntExtra("numberToAdd",2);
        range =(max-min)+1;


        chronometer = findViewById(R.id.chronometer);


        chronometer.start();

        tvCorrect = findViewById(R.id.tvCorrectWrong);
        tvCorrect.setText("");

        tvWrong = findViewById(R.id.tvWrong);
        tvWrong.setText("");

        toast =Toast.makeText(this, "" , Toast.LENGTH_SHORT);

        firstNumber = random.nextInt(range)+min;
        firstNumberString = String.valueOf(firstNumber);
        tvFirstNumber = findViewById(R.id.tvFirstNumber);

        secondNumber = numberToAdd;
        secondNumberString= String.valueOf(secondNumber);
        tvSecondNumber = findViewById(R.id.tvSecondNumber);


       // questionAnswer = firstNumber - secondNumber;
        answerText = String.valueOf(questionAnswer);

        tvFirstNumber.setText(firstNumberString);
        tvSecondNumber.setText(secondNumberString);

        answerLength = answerText.length();

        etAnswer = findViewById(R.id.etAnswerText);

        etAnswer.setText("");

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
    @Override
    protected void onStop() {
        pauseGame();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        int prob = random.nextInt(10);

        if (prob<7){
            if (MainActivity.Lifetime != 0){
                advert1();
            }
        }
        super.onDestroy();
    }
}