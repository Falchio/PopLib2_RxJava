package ru.falchio.myrxjavaexample.observer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import ru.falchio.myrxjavaexample.R;

public class ObserverActivity extends AppCompatActivity {
    public String TAG = this.getClass().getSimpleName();
    private Spamer spamer = new Spamer();
    private Subscriber subscriber = new Subscriber();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observer);

    }

    public void subscribe(View view) {
        spamer.registerObserver(subscriber);
    }

    public void unsubscribe(View view) {
        spamer.unregisterObserver(subscriber);
    }

    public void sendSpam(View view) {
        spamer.newSpam("spam", "#1");
    }
}
