package com.rxjava.scene3;


import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

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
        Observable o1 = Observable.just(1,8).map(new Function<Integer, Object>() {
            @Override
            public Object apply(Integer integer) throws Exception {
                return integer * 2 + 1;
            }
        }).subscribeOn(Schedulers.trampoline());
        Observable o2 = Observable.just(3, 6).map(new Function<Integer, Object>() {
            @Override
            public Object apply(Integer integer) throws Exception {
                return integer * 4 + 1;
            }
        }).subscribeOn(Schedulers.trampoline());
        Observable.zip(o1, o2, new BiFunction<Integer, Integer, String>() {
            @Override
            public String apply(Integer o, Integer o2) throws Throwable {
                return o + "," + o2;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String o) throws Throwable {
                System.out.println("o =  " + o + " ");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {

            }
        });

    }


}
