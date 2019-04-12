package com.example.nour.injazplante;

import android.os.CountDownTimer;
import android.util.Log;


import java.util.ArrayList;

public class SysAlert extends CountDownTimer {
    private ArrayList<Integer> semaine;
    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public SysAlert(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }


    @Override
    public void onTick(long millisUntilFinished) {
        Log.i("AlarmTick",String.valueOf(millisUntilFinished));
    }

    @Override
    public void onFinish() {
        new SysAlert(24*60*60*1000,1000).start();
        MainActivity.playMusic();

    }

}
