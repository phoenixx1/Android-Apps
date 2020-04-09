package com.nstg.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView resultTextView, pointsTextView, textView;
    TextView sumTextView, timerTextView;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfAnswer;
    Button button0, button1, button2, button3, playAgainButton;
    int score = 0, numberOfQues = 0;
    GridLayout gridLayout;
    AdView adView, adView1;
    ConstraintLayout layout1;

    public void playAgain(View view){
        score = 0;
        numberOfQues = 0;
        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        playAgainButton.setVisibility(View.INVISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        adView1.setVisibility(View.INVISIBLE);
        pointsTextView.setVisibility(View.VISIBLE);
        timerTextView.setVisibility(View.VISIBLE);
        sumTextView.setVisibility(View.VISIBLE);
        resultTextView.setText("");
        generateQues();

        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long msUntilFinish){
                timerTextView.setText(String.valueOf(msUntilFinish/1000)+"s");
            }

            public void onFinish(){

                playAgainButton.setVisibility(View.VISIBLE);
                adView1.setVisibility(View.INVISIBLE);
                timerTextView.setText("0s");
                String ms = null;
                if(score>20){
                    ms = "Your Score" + "\nTotal Ques: " + numberOfQues + "\nCorrect: "+ Integer.toString(score) + "\nIncorrect: " + Integer.toString(numberOfQues-score) + "\nGOOD";
                }
                else{
                    ms = "Your Score" + "\nTotal Ques: " + numberOfQues + "\nCorrect: "+ Integer.toString(score) + "\nIncorrect: " + Integer.toString(numberOfQues-score) + "\nPOOR";
                }
                resultTextView.setText(ms);
                gridLayout.setVisibility(View.INVISIBLE);
                pointsTextView.setVisibility(View.INVISIBLE);
                timerTextView.setVisibility(View.INVISIBLE);
                sumTextView.setVisibility(View.INVISIBLE);

            }
        }.start();

    }

    public void generateQues(){

        Random random = new Random();

        int a = random.nextInt(21);
        int b = random.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfAnswer = random.nextInt(4);
        answers.clear();
        int incorrectAns;

        for(int i = 0; i<4; i++){

            if(i == locationOfAnswer){

                answers.add(a + b);

            }else{
                incorrectAns = random.nextInt(41);
                while (incorrectAns == a + b){

                    incorrectAns = random.nextInt(41);

                }

                answers.add(incorrectAns);

            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    public void chooseAnswer(View view){

        if(view.getTag().toString().equals(Integer.toString(locationOfAnswer))){

            score++;
            resultTextView.setText("CORRECT!");

        }else{
            resultTextView.setText("WORNG!");
        }
        numberOfQues++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQues));
        generateQues();

    }

    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);
        adView.setVisibility(View.INVISIBLE);
        layout1.setVisibility(ConstraintLayout.VISIBLE);
        playAgain(playAgainButton);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, "ca-app-pub-9560743405837312~3902987113");
        adView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        adView1 = (AdView)findViewById(R.id.adView2);
        adView1.loadAd(adRequest);

        startButton = (Button)findViewById(R.id.startButton);
        sumTextView = (TextView)findViewById(R.id.sumTextView);
        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        textView = (TextView)findViewById(R.id.textView);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        pointsTextView = (TextView)findViewById(R.id.pointsTextView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        playAgainButton = (Button)findViewById(R.id.playAgainButton);
        layout1 = (ConstraintLayout)findViewById(R.id.layout1);
        gridLayout = (GridLayout)findViewById(R.id.gridLayout);


    }
}
