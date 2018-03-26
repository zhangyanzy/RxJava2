package com.zhangyan.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRxJava();
//        consumerDemo();
    }

    private void initRxJava() {
//        //1 创建一个被观察者
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });
        //2、创建观察者
        Observer<Integer> observer = new Observer<Integer>() {

            private Disposable mDisposable;//切断
            private int i;

            //订阅
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe: 被观察者和观察者已经订阅");
                mDisposable = d;
            }

            //处理
            @Override
            public void onNext(Integer value) {
                Log.i(TAG, "onNext: 处理被观察者的事件" + value);
                i++;
                if (i == 2) {
                    Log.i(TAG, "dispose: i = 2被切断");
                    mDisposable.dispose();
                    Log.d(TAG, "isDisposed : " + mDisposable.isDisposed());
                }
            }

            //报错
            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: 发生错误");
            }

            //完成
            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: 事件处理完成");
            }
        };
        //
        observable.subscribe(observer);


        /**
         * Disposable的用法
         */
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                e.onNext(1);
//                e.onNext(2);
//                e.onNext(3);
//                e.onComplete();
//
//            }
//        }).subscribe(new Observer<Integer>() {
//
//            private Disposable mDisposable;
//            private int i;
//
//            @Override
//            public void onSubscribe(Disposable d) {
//                mDisposable = d;
//            }
//
//            @Override
//            public void onNext(Integer value) {
//                i++;
//                if (value == 2) {
//                    mDisposable.dispose();
//                }
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
//        });


    }

    /**
     * 如果说观察者只想处理OnNext事件  对其他事情不关心
     */
    private void consumerDemo() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onComplete();
                emitter.onNext(3);


            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.i(TAG, "accept: 观察者处理" + integer);
            }
        });

    }


}

