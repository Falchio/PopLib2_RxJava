package ru.falchio.myrxjavaexample.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import ru.falchio.myrxjavaexample.R;


public class RxJavaActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    private RxJavaPresenter presenter;
    private Observable<String> observable;
    private Disposable disposable;
//    @BindView(R.id.subscribe)
//    Button subscribe;
//    @BindView(R.id.unsubscribe)
//    Button unsubscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);

        presenter = new RxJavaPresenter();
        observable = presenter.sendMessage();
        ButterKnife.bind(this);

//        subscribe.setOnClickListener(v -> subscribe());
//        unsubscribe.setOnClickListener(v -> unsubscribe());
    }

    @OnClick(R.id.subscribe)
    public void subscribe() {
        disposable = observable.observeOn(AndroidSchedulers.mainThread()).subscribe(
                string-> Log.d(TAG, "onNext: " + string),
                throwable -> Log.e(TAG, "onError"),
                () -> Log.d(TAG, "onComplete: "));
        Log.d(TAG, "subscribe: end " + Thread.currentThread().getName());
    }

    @OnClick(R.id.unsubscribe)
    public void unsubscribe() {
        Log.d(TAG, "unsubscribe: ");
        disposable.dispose();
    }
}
