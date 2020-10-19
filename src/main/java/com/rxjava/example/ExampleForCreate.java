package com.rxjava.example;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;


/**
 * 版权:上海屋聚 版权所有
 * author: yandeqing
 * version: 3.0.0
 * date:2019/11/20 17:09
 * 备注:
 */
class ExampleForCreate {
    public static void main(String[] args) {
//        1.Observable.create
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

            }
        });
//        2.Observable.from
        Observable<String> observablefromArray = Observable.fromArray(new String[]{"1"});
//        3.Observable.just
        Observable<String> observableJust = Observable.just("1");
//        4.Observable.interval
        Observable<Long> interval = Observable.interval(0, TimeUnit.SECONDS);
//        5.Observable.repeat
        Observable<Integer> repeat = Observable.range(0, 2).repeat(2);


    }
}
