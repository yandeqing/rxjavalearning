package com.rxjava.example;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;


/**
 * 版权:上海屋聚 版权所有
 * author: yandeqing
 * version: 3.0.0
 * date:2019/11/20 17:09
 * 备注:
 */
class ExampleForConcat {
    public static void main(String[] args) {
        Observable
                .concat(Observable.create(emitter -> {
                    emitter.onNext("Observable.create1");
                    emitter.onComplete();
                }), Observable.fromArray("ObservablefromArray1", "ObservablefromArray2"), Observable.just("ObservablefformJust"))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        System.out.println("concat :" + s + "");
                    }
                });
    }
}
