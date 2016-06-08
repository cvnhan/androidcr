package com.cvnhan.androidcr.mvp.presenter;

/**
 * Created by NhanCao on 13-Sep-15.
 */
public interface PBase<V> {

    void onPStart(V view);

    void onPStop();

}
