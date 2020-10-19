package com.rxjava.example;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;


/**
 * 版权:上海屋聚 版权所有
 * author: yandeqing
 * version: 3.0.0
 * date:2019/11/20 17:09
 * 备注:
 */
class ExampleForZip {
    public static void main(String[] args) {
        Observable
                .zip(Observable.just(1, 2, 7, 8), Observable.just(3, 4, 5), new BiFunction<Integer, Integer, String>() {
                    @Override
                    public String apply(Integer integer, Integer integer2) throws Throwable {

                        return integer+","+integer2;
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String integer) throws Throwable {
                        System.out.println("integer = [" + integer + "]");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        throwable.printStackTrace();
                    }
                });

    }
}
