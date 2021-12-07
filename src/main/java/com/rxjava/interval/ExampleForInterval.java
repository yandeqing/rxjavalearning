package com.rxjava.interval;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
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
class ExampleForInterval {


    public static void main(String[] args) {
        int part = 60;
        System.out.println("part = [" + part + "]");
        Observable.intervalRange(1,60, 0,1,TimeUnit.SECONDS, Schedulers.trampoline())
                .map(increaseTime -> {
                    System.out.println("increaseTime = [" + increaseTime + "]");
                    return part - increaseTime.intValue();
                })
//                .take(part + 1)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Throwable {
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
