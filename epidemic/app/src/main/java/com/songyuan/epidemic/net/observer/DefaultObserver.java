package com.songyuan.epidemic.net.observer;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by niluogege on 2018/8/23.
 * <p>
 * 默认处理请求结果的 Observer
 */

public abstract class DefaultObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        onFail(e);
    }

    @Override
    public void onNext(T t) {
        onsuccess(t);
    }

    /**
     * 数据访问成功
     *
     * @param data
     */
    protected abstract void onsuccess(T data);

    /**
     * 数据访问失败
     *
     * @param error
     */
    protected abstract void onFail(Throwable error);
}
