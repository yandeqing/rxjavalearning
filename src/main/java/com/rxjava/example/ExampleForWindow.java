package com.rxjava.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Notification;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;


/**
 * 版权:上海屋聚 版权所有
 * author: yandeqing
 * version: 3.0.0
 * date:2019/11/20 17:09
 * 备注:
 */
class ExampleForWindow {

    public static void main(String[] args) {

//        Observable.range(1, 4)
//                // Create windows containing at most 2 items, and skip 3 items before starting a new window.
//                .window(2,1)
//                .flatMapSingle(Observable::toList)
//                .subscribe(new Consumer<List<Integer>>() {
//                    @Override
//                    public void accept(List<Integer> integerList) throws Throwable {
//                        System.out.println("integerList = " + integerList + "");
//                    }
//                });

//        Observable.interval(100, TimeUnit.MILLISECONDS, Schedulers.trampoline())
//                .take(5)
//                .window(250,100, TimeUnit.MILLISECONDS)
//                .flatMapSingle(Observable::toList)
//                .subscribe(new Consumer<List<Long>>() {
//                    @Override
//                    public void accept(List<Long> longs) throws Throwable {
//                        System.out.println("longs = " + longs + "");
//                    }
//                });

        Observable.interval(100, TimeUnit.MILLISECONDS, Schedulers.trampoline())
                .take(5)
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long doOnNext) throws Throwable {
                        System.out.println("doOnNext = [" + doOnNext + "]");
                    }
                })
                .window(Observable.interval(100, TimeUnit.MILLISECONDS).doOnNext(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Throwable {
                                System.out.println("doOnNext period = [" + aLong + "]");
                            }
                        })
                        , aLong -> Observable.timer(250, TimeUnit.MILLISECONDS).doOnNext(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Throwable {
                                System.out.println("doOnNext delay 250 = [" + aLong + "]");
                            }
                        }))
                .flatMapSingle(Observable::toList)
                .subscribe(new Consumer<List<Long>>() {
                    @Override
                    public void accept(List<Long> longs) throws Throwable {
                        System.out.println("longs = " + longs + "");
                    }
                });
    }

}
