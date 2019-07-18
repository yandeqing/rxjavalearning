package com.rxjava.scene1.consumer;

import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * 版权:上海屋聚 版权所有
 * author: yandeqing
 * version: 3.0.0
 * date:2018/11/19 18:42
 * 备注:
 */
public class ErrorConsume implements Consumer<Throwable> {

    private StringBuilder getWhereErrInfoHappened(Throwable ex) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (ex != null) {
            StackTraceElement[] stackTrace = ex.getStackTrace();
            if (stackTrace != null && stackTrace.length > 0) {
                StackTraceElement stackTraceElement = stackTrace[0];
                stringBuilder.append("\nexception=" + ex)
                        .append(";\nfilename=" + stackTraceElement.getFileName())
                        .append(";\nline=" + stackTraceElement.getLineNumber())
                        .append(";\nmethod=" + stackTraceElement.getMethodName());
            } else {
                stringBuilder.append("cannot get WhereErrInfoHappened");
            }
        }
        return stringBuilder;
    }


    @Override
    public void accept(Throwable throwable) throws Exception {
        System.err.println("onError->" + getWhereErrInfoHappened(throwable));
    }
}
