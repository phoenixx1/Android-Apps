package com.nstg.eggtimer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.BoringLayout;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar timeSeekBar;
    TextView timerTextView;
    Boolean counterIsActive = false;
    Button button;
    CountDownTimer countDownTimer;

    public void resetTimer(){
        timerTextView.setText("0:30");
        timeSeekBar.setProgress(30);
        countDownTimer.cancel();
        button.setText("GO");
        timeSeekBar.setEnabled(true);
        counterIsActive = false;
    }

    public void updateTimer(int secLeft){
        int min = (int)secLeft / 60;
        int sec = secLeft - min * 60;

        String secString = Integer.toString(sec);
        if(sec <= 9){
            secString = "0" + secString;
        }

        timerTextView.setText(Integer.toString(min) + ":" +secString);
    }

    public void controlTimer(View view){

        if(counterIsActive == false) {

            counterIsActive = true;

            timeSeekBar.setEnabled(false);

            button.setText("STOP");

            countDownTimer = new CountDownTimer(timeSeekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long msUntilFinished) {
                    updateTimer((int) msUntilFinished / 1000);
                }

                public void onFinish() {
                    timerTextView.setText("0:00");
                    MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.airhorn);
                    mediaPlayer.start();
                    resetTimer();
                }
            }.start();
        }else{
            resetTimer();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeSeekBar = (SeekBar)findViewById(R.id.seekBar);
        timerTextView = (TextView)findViewById(R.id.textView);
        button = (Button)findViewById(R.id.button);


        timeSeekBar.setMax(600);
        timeSeekBar.setProgress(30);
        timeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
