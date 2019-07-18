package com.rxjava.scene1;

import com.rxjava.scene1.consumer.ErrorConsume;
import com.rxjava.scene1.observer.SingleReqObserver;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * 版权:上海屋聚 版权所有
 * author: yandeqing
 * version: 3.0.0
 * date:2018/11/19 15:44
 * 备注:单请求异步处理
 * <p>
 * Single request asynchronous processing
 */
public class SingleReqAsynchProcessExample {


    public static void main(String[] args) {
        Observable
                .create((ObservableOnSubscribe<List<String>>) emitter -> {
                    List<String> strings = new ArrayList<String>(1);
                    strings.add("just1");
                    strings.add("just2");
                    emitter.onNext(strings);
                    emitter.onComplete();
                })
                .subscribe(strings -> {
                    System.out.println("strings = [" + strings + "]");
                }, throwable -> {
                    System.out.println("throwable = [" + throwable + "]");
                });
    }


}
