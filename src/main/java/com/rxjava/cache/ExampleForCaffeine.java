package com.rxjava.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Consumer;


/**
 * 版权:上海屋聚 版权所有
 * author: yandeqing
 * version: 3.0.0
 * date:2020/9/28
 * 备注:
 */
class ExampleForCaffeine {

    public static void main(String[] args) {
        Cache<String, DataObject> cache = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .maximumSize(2)
                .build();


        String key = "A";
        DataObject dataObject = cache.getIfPresent(key);
        System.out.println("dataObject = [" + dataObject + "]");
        dataObject = cache
                .get(key, k -> DataObject.get("Data for A"));
        System.out.println("dataObject = [" + dataObject + "]");
    }



    static class DataObject {
        private final String data;

        @Override
        public String toString() {
            return "DataObject{" +
                    "data='" + data + '\'' +
                    '}';
        }

        public DataObject(String data) {
            this.data = data;
        }

        private static int objectCounter = 0;
        // standard constructors/getters

        public static DataObject get(String data) {
            objectCounter++;
            return new DataObject(data);
        }
    }
}


