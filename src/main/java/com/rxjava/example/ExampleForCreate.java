package com.rxjava.example;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;


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
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("12");
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                System.out.println("s = [" + s + "]");
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

        Observable.just(false)
                .flatMap(new Function<Boolean, ObservableSource<Boolean>>() {
                    @Override
                    public ObservableSource<Boolean> apply(Boolean aBoolean) throws Throwable {
                        if (aBoolean) {
                            return Observable.just(true);
                        } else {
                            return Observable.error(new Exception("创建会话失败！"));
                        }
                    }
                })
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Throwable {
                        System.out.println("aBoolean = [" + aBoolean + "]");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable o) throws Throwable {
                        System.out.println("Throwable = [" + o + "]");
                    }
                });

        Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        Thread.sleep(4000);
                        emitter.onNext("12");
                    }
                })
                .timeout(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        System.out.println("s = [" + s + "]");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        System.out.println("throwable = [" + throwable + "]");
                    }
                });
    }
}
