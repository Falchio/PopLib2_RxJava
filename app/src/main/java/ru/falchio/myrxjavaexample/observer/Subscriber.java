package ru.falchio.myrxjavaexample.observer;

import android.util.Log;

public class Subscriber implements Observer {
    private String TAG = this.getClass().getSimpleName();

    @Override
    public void updateData(String name, String num) {
        Log.d(TAG, "updateData: " + name + num);
    }
}
