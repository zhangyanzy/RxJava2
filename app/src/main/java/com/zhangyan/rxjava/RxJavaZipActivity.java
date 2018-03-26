package com.zhangyan.rxjava;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

public class RxJavaZipActivity extends AppCompatActivity {

    public static final String TAG = "RxJavaZipActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_zip);
        zipDemo();
    }

    private void zipDemo() {
        Observable<Integer> o1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.i(TAG, "o1线程" + Thread.currentThread().getName());

                Log.i(TAG, "subscribe: 1");
                emitter.onNext(1);
                SystemClock.sleep(1000);

                Log.i(TAG, "subscribe: 2");
                emitter.onNext(2);
                SystemClock.sleep(1000);

                Log.i(TAG, "subscribe: 3");
                emitter.onNext(3);
                SystemClock.sleep(1000);

                Log.i(TAG, "subscribe: 4");
                emitter.onNext(4);
                SystemClock.sleep(1000);

                emitter.onComplete();


            }
        }).subscribeOn(Schedulers.io());

        Observable<String> o2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Log.i(TAG, "02线程" + Thread.currentThread().getName());

                Log.i(TAG, "subscribe: A");
                emitter.onNext("A");
                SystemClock.sleep(1000);

                Log.i(TAG, "subscribe: B");
                emitter.onNext("B");
                SystemClock.sleep(1000);

                Log.i(TAG, "subscribe: C");
                emitter.onNext("C");
                SystemClock.sleep(1000);

                emitter.onComplete();

            }
        }).subscribeOn(Schedulers.io());

        Observable.zip(o1, o2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(String value) {
                        Log.d(TAG, "onNext: " + value);
                        Log.i(TAG, "观察者线程" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });
    }
}
