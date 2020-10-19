package com.rxjava.example;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
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
class ExampleForMerge {
    public static void main(String[] args) {
        Observable
                .merge(Observable.just(1, 2, 7, 8).delay(1, TimeUnit.SECONDS, Schedulers.trampoline()), Observable.just(3, 4, 5))
                .compose(new ObservableTransformer<Integer, Integer>() {
                    @Override
                    public @NonNull ObservableSource<Integer> apply(@NonNull Observable<Integer> upstream) {
                        return upstream.flatMap(new Function<Integer, ObservableSource<Integer>>() {
                            @Override
                            public ObservableSource<Integer> apply(Integer integer) throws Throwable {
                                return Observable.just(integer*2);
                            }
                        });
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        System.out.println("merge :" + integer + "");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        throwable.printStackTrace();
                    }
                });
    }
}
