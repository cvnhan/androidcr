package com.cvnhan.androidcr.mvp.presenter;

import com.cvnhan.androidcr.mvp.model.YKMAModelPlayer;
import com.cvnhan.androidcr.mvp.view.YKMAViewPlayer;
import com.cvnhan.core.utils.NCMCUtilRxHelper;

import rx.Subscription;

/**
 * Created by NhanCao on 13-Sep-15.
 */

public class YKMAPresenterPlayer implements YKMAIPresenterBase<YKMAViewPlayer> {
    private final String TAG = YKMAPresenterPlayer.class.getSimpleName();
    private final YKMAModelPlayer model;
    private Subscription subscription;

    public YKMAPresenterPlayer(YKMAModelPlayer model) {
        this.model = model;
    }

    @Override
    public void onStart(YKMAViewPlayer view) {
        subscription = model.getAllAudioFiles()
                .compose(NCMCUtilRxHelper.applySchedulers())
                .subscribe(
                        songList -> {
                            view.render(songList);
                        },
                        e -> {
                            e.printStackTrace();
                        },
                        () -> {
                        });
    }

    public void getAudioFromLocal(YKMAViewPlayer view) {
        subscription = model.getFromLocal()
                .compose(NCMCUtilRxHelper.applySchedulers())
                .subscribe(
                        songList -> {
                            view.render(songList);
                        },
                        e -> {
                            e.printStackTrace();
                        },
                        () -> {
                        });
    }

    @Override
    public void onStop() {
//        Log.e(TAG, "onStop");
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
