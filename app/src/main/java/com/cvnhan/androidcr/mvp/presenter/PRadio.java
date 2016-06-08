package com.cvnhan.androidcr.mvp.presenter;

import android.util.Log;

import com.cvnhan.androidcr.mvp.model.MRadio;
import com.cvnhan.androidcr.mvp.view.VRadio;
import com.cvnhan.androidcr.utils.RxHelper;

import rx.Subscription;

/**
 * Created by NhanCao on 13-Sep-15.
 */

public class PRadio implements PBase<VRadio> {
    private final String TAG = PRadio.class.getSimpleName();
    private final MRadio model;
    private Subscription subscription;

    public PRadio(MRadio model) {
        this.model = model;
    }

    @Override
    public void onPStart(VRadio view) {
        subscription = model.getAllRadio()
                .compose(RxHelper.applySchedulers())
                .subscribe(
                        dbRadios -> {
                            view.render(dbRadios);
                        },
                        e -> {
                            Log.e(TAG, e.toString());
                            e.printStackTrace();
                        });
    }

    @Override
    public void onPStop() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
