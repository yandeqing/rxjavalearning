package com.rxjava.exception;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;

/**
 * 版权:上海屋聚 版权所有
 * author: yandeqing
 * version: 3.0.0
 * date:2021/4/1 10:55
 * 备注:
 */
class ExceptionExample {
    public static void main(String[] args) {
        Observable
                .just(1)
                .flatMap(integer -> {
                    System.err.println("integer = [" + integer + "]");
                    if (integer > 0) {
                        throw new IMUserLoginException("");
                    }
                    return Observable.just(integer);
                })
                .onErrorResumeNext((Function<Throwable, ObservableSource<Integer>>) throwable -> {
                    if (throwable != null) {
                        throwable.printStackTrace();
                    }
                    return Observable.just(1);
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Integer aLong) {
                        System.out.println("aLong = [" + aLong + "]");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
