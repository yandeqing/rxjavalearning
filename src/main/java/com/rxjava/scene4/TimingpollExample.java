package com.rxjava.scene4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

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
        Observable.timer(1, TimeUnit.SECONDS, Schedulers.trampoline())
                .subscribe(timer -> System.out.println("timer = " + timer));
    }

    private static void interval() {
        AtomicLong lastTick = new AtomicLong(0L);
        Observable.interval(1, TimeUnit.SECONDS, Schedulers.trampoline())
                .map(tick -> lastTick.getAndAdd(2))
                .takeUntil(aLong -> aLong == 10)
                .subscribe(tick -> System.out.println("tick = " + tick));
    }


}
