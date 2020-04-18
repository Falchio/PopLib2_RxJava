package ru.falchio.myrxjavaexample.observer;

import android.util.Log;

import java.util.ArrayList;

public class Spamer implements Observable {
    private String TAG = this.getClass().getSimpleName();
    private ArrayList<Observer> subscribers;
    private String name;
    private String num;

    public Spamer(){
        subscribers= new ArrayList<Observer>();
    }

    public void newSpam(String name, String num){
        this.name = name;
        this.num = num;
        notifyAllObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        subscribers.add(observer);
        Log.d(TAG, "registerObserver: " + observer + " " + Thread.currentThread().getName());
    }

    @Override
    public void unregisterObserver(Observer observer) {
        subscribers.remove(observer);
        Log.d(TAG, "unregisterObserver: " + observer + " " + Thread.currentThread().getName());
    }

    @Override
    public void notifyAllObservers() {
        for (Observer o:subscribers) {
            o.updateData(name, num);
        }
        Log.d(TAG, "notifyAllObservers: " + Thread.currentThread().getName());
    }
}
