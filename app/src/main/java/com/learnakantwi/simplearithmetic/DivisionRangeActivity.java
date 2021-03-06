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
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Random;

public class DivisionRangeActivity extends AppCompatActivity {

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
        //firstNumber = random.nextInt(200);
        firstNumber = (random.nextInt(range))+min;
        firstNumberString = String.valueOf(firstNumber);
        tvFirstNumber.setText(firstNumberString);


        firstNumber = (random.nextInt(range)+1)+min;;
        secondNumberString= String.valueOf(secondNumber);
        tvSecondNumber = findViewById(R.id.tvSecondNumber);
        //secondNumber = 3;

        //firstNumber = random.nextInt(20);
        //firstNumber = random.nextInt(30)+10000;


        while ((float)firstNumber%secondNumber !=0 || firstNumber==0 || firstNumber == secondNumber){
            firstNumber = random.nextInt(range)+min;
        }

        questionAnswer = firstNumber / secondNumber;

        //questionAnswerLong = (long)firstNumber / secondNumber;
        //questionAnswerDouble = (double)firstNumber / secondNumber;
       // questionAnswerFloat = (float)firstNumber / (float)secondNumber;

        answerText = String.valueOf(questionAnswer);
        answerLength = answerText.length();

        firstNumberString = String.valueOf(firstNumber);
        tvFirstNumber.setText(firstNumberString);

        secondNumberString = String.valueOf(secondNumber);
        tvSecondNumber.setText(secondNumberString);



        int remainder = firstNumber%secondNumber;

        // Toast.makeText(this,  questionAnswerFloat + " is Answer\nLong: "+ questionAnswerLong +"\nDouble: "+ questionAnswerDouble  , Toast.LENGTH_SHORT).show();
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

        if (answerLength==1) {
            etAnswer.setText(firstPick);

            int userAnswerInt = Integer.parseInt(number);
            if (questionAnswer == userAnswerInt) {
                correctAnswer();
            } else {
                wrongAnswer();
            }
        }
        else if (answerLength==2) {
            if (counter == 1) {
                one = firstPick;
                etAnswer.setText(firstPick);
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
                    correctAnswer();
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
            toast.setText("ABOVE SCOPE OF APP");
        }
        sb= sb.append(firstPick);
        // etAnswer.setText(sb, TextView.BufferType.NORMAL);

        Editable editable = etAnswer.getText();
        etAnswer.setSelection(editable.length());

        sb.setLength(answerLength);

        //userAnswer = sb.toString();

        int userAnswerInt = Integer.parseInt(number);
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


       /* int length = editText.getText().length();
        if (length > 0) {
            editText.getText().delete(length - 1, length);
        }*/
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_division_range);

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


        Intent intent = getIntent();
        min= intent.getIntExtra("min",1);
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


        tvFirstNumber = findViewById(R.id.tvFirstNumber);

        tvSecondNumber = findViewById(R.id.tvSecondNumber);

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
}
