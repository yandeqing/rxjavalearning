package com.rxjava.transform;


import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableConverter;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * 版权:上海屋聚 版权所有
 * author: yandeqing
 * version: 3.0.0
 * date:2019/11/20 17:09
 * 备注:
 */
class Transform {

    public static void main(String[] args) {
        Boolean to = Observable
                .create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        emitter.onNext(1);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .to(new ObservableConverter<Integer, Boolean>() {
                    @Override
                    public Boolean apply(Observable<Integer> upstream) {
                        upstream.subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Throwable {

                            }
                        });
                        return false;
                    }
                });
        System.out.println("to = [" + to + "]");
    }
}
