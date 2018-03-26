package com.zhangyan.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class UseRxJavaThreadActivity extends AppCompatActivity {

    public static String TAG = "UseRxJavaThreadActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_rx_java_thread);
        Log.d(TAG, "onCreate线程名称: " + Thread.currentThread().getName());
//        useUIThreadDemo();
        useThreadDemo();
    }

    private void useThreadDemo() {
//        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                Log.d(TAG, "Observable thread is : " + Thread.currentThread().getName());
//                e.onNext(1);
//                e.onNext(2);
//                e.onComplete();
//            }
//        });
//        Observer<Integer> observer = new Observer<Integer>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Integer value) {
//                Log.d(TAG, "Observer thread is :" + Thread.currentThread().getName());
//                Log.i(TAG, "onNext: " + value);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        };
//        /**
//         * subscribeOn是指被观察者事件的线程
//         * observeOn是主线程事件的线程
//         */
/*
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {

                    }
                })
                .subscribe(observer);
*/


//        observable.subscribeOn(Schedulers.newThread())
//                .subscribeOn(Schedulers.io())
//
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnNext(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        Log.d(TAG, "After observeOn(mainThread)+1, current thread is: " + Thread.currentThread().getName());
//                    }
//                })
//                .observeOn(Schedulers.io())
//                .doOnNext(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        Log.d(TAG, "After observeOn(mainThread)+2, current thread is: " + Thread.currentThread().getName());
//                    }
//                }).subscribe(observer);

        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.d(TAG, "被观察者线程subscribe: " + Thread.currentThread().getName());

            }
        });
        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "观察者线程accept: " + Thread.currentThread().getName());
            }
        };
        observable
//        .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
    }


    private void useUIThreadDemo() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.i(TAG, "Observable 线程名称: " + Thread.currentThread().getName());
                e.onNext(1);
                e.onComplete();
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.i(TAG, "Observer : " + Thread.currentThread().getName());
                Log.i(TAG, "accept: " + integer);
            }
        });
    }


}
