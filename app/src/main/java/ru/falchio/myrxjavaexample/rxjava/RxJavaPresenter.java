package ru.falchio.myrxjavaexample.rxjava;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

public class RxJavaPresenter {
    private final String TAG = this.getClass().getSimpleName();

    public Observable<String> sendMessage() {


        Observable<String> observable = Observable.create((ObservableOnSubscribe<String>) emitter -> {
            try {
                for (int i = 0; i < 5; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    String numMsg = "message #" + i;
                    Log.d(TAG, "subscribe: " + numMsg);
                    emitter.onNext(numMsg);
                }
                emitter.onComplete();
            } catch (InterruptedException e){
                Log.e(TAG, "subscribe: not disposed");
            }
        }).subscribeOn(Schedulers.io());
        return observable;

//        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
//                try {
//                    for (int i = 0; i < 5; i++) {
//                        TimeUnit.SECONDS.sleep(1);
//                        String numMsg = "message #" + i;
//                        Log.d(TAG, "subscribe: " + numMsg);
//                        emitter.onNext(numMsg);
//                    }
////                emitter.onError(new NullPointerException());
//                    emitter.onComplete();
//                } catch (InterruptedException e){
//                    Log.e(TAG, "subscribe: not disposed");
//                }
//            }
//        }).subscribeOn(Schedulers.io());

    }


}
