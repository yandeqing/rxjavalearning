package com.rxjava.scene2;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 版权:上海屋聚 版权所有
 * author: yandeqing
 * version: 3.0.0
 * date:2018/11/19 15:44
 * 备注:多异步请求连续调用
 * Continuous call for multiple asynchronous requests
 */
public class MultiAsyReqExample {


    public static void main(String[] args) {
        new MultiAsyReqExample().request();
    }

    private void request() {
        Observable
                .<Integer>create(emitter -> {
                    emitter.onNext(1);
                })
                .flatMap(this::updateUserInfo)
                .flatMap(this::uploadImageTask)
                .subscribe(this::updateUI, this::showError);
    }

    private Observable<Integer> updateUserInfo(Integer o) {
        return Observable.create(emitter -> {
            int a = 1;
            System.out.println("updateUserInfo = [" + o + "]");
            emitter.onNext(a);
        });
    }

    private ObservableSource<Integer> uploadImageTask(Integer o) {
        return Observable.create(emitter -> {
            int b = 1;
            int c = 0;
            System.out.println("uploadImageTask = [" + o + "]");
            emitter.onNext(b);
        });
    }

    private void showError(Throwable throwable) {
        System.out.println("throwable = [" + throwable + "]");
    }

    private void updateUI(Integer o) {
        System.out.println("updateUI o = [" + o + "]");
    }


}
