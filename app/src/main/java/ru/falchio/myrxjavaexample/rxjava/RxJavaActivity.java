package ru.falchio.myrxjavaexample.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import ru.falchio.myrxjavaexample.R;


public class RxJavaActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    private RxJavaPresenter presenter;
    private Observable<String> observable;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);

        presenter = new RxJavaPresenter();
        observable = presenter.sendMessage();
    }

    public void subscribe(View view) {
        observable.observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: ");
                RxJavaActivity.this.disposable = d;
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: " + Thread.currentThread().getName() + " " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }

        });
        Log.d(TAG, "subscribe: end " + Thread.currentThread().getName());
    }

    public void unsubscribe(View view) {
        if (disposable!=null){
            disposable.dispose();
            Log.d(TAG, "unsubscribe: ");
        } else {
            Log.d(TAG, "Not subscribed yet");
        }

    }
}
