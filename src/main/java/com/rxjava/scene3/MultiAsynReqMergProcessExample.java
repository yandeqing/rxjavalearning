package com.rxjava.scene3;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 版权:上海屋聚 版权所有
 * author: yandeqing
 * version: 3.0.0
 * date:2018/11/19 15:44
 * 备注:多异步请求合并处理
 * Multiple asynchronous request merging processing
 */
public class MultiAsynReqMergProcessExample {


    public static void main(String[] args) {
        merge();
    }

    private static void merge() {
        Observable o1 = Observable.just(1).map(new Function<Integer, Object>() {
            @Override
            public Object apply(Integer integer) throws Exception {
                return integer*2+100;
            }
        }).subscribeOn(Schedulers.trampoline());
        Observable o2 = Observable.just(1).map(new Function<Integer, Object>() {
            @Override
            public Object apply(Integer integer) throws Exception {
                return integer*4+20;
            }
        }).subscribeOn(Schedulers.trampoline());
        Observable.zip(o1, o2, (BiFunction<Integer, Integer, Integer>) (o, o21) -> o + o21).subscribe(o -> {
            System.out.println("o =  " + o + " ");
        }, throwable -> {
            System.out.println("throwable = [" + throwable + "]");
        });
    }


}
