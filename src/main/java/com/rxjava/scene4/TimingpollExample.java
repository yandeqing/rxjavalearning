package com.rxjava.scene4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;


/**
 * 版权:上海屋聚 版权所有
 * author: yandeqing
 * version: 3.0.0
 * date:2018/11/19 15:44
 * 备注:定时轮询
 * Timing polling
 */
public class TimingpollExample {

    public static void main(String[] args) {
        interval();
        timer();
    }

    private static void timer() {
        Observable.interval(3, TimeUnit.SECONDS, Schedulers.trampoline())
                .subscribe(timer -> System.out.println("timer = " + timer));
    }

    private static void interval() {
        AtomicInteger lastTick = new AtomicInteger(5);
        Observable.interval(1, TimeUnit.SECONDS, Schedulers.trampoline())
                .map(tick -> lastTick.getAndDecrement())
                .takeUntil(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Throwable {
                        return integer==0;
                    }
                })
                .subscribe(tick -> System.out.println("tick = " + tick));
    }


}
