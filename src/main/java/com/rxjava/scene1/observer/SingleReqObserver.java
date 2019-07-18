package com.rxjava.scene1.observer;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SingleReqObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        System.out.println("t = [" + t + "]");
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onComplete() {

    }
}