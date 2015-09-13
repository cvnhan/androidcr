package com.cvnhan.androidcr.mvp.model;

import android.app.Application;

import com.cvnhan.androidcr.NCMCCoreApp;
import com.cvnhan.androidcr.mvp.model.local.YKMADBRadio;
import com.cvnhan.androidcr.mvp.model.remote.YKMARMRadio;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;

import static com.cvnhan.core.utils.NCMCUtilRxHelper.onCompleted;
import static com.cvnhan.core.utils.NCMCUtilRxHelper.onError;
import static com.cvnhan.core.utils.NCMCUtilRxHelper.onNext;

/**
 * Created by NhanCao on 13-Sep-15.
 */
public class YKMAModelRadio {
    private final String TAG = YKMAModelRadio.class.getSimpleName();
    private final Application application;
    private final YKMAIModelApi apiModel;

    public YKMAModelRadio(Application application, YKMAIModelApi apiModel) {
        this.application = application;
        this.apiModel = apiModel;
    }

    public Observable<ArrayList<YKMADBRadio>> getAllRadio() {
        return Observable.mergeDelayError(getAllRadioFromLocal(), getAllRadioFromRemote());
    }

    public Observable<ArrayList<YKMADBRadio>> getAllRadioFromRemote() {
        return Observable.defer(() -> apiModel.getAllRadio().map(rmRadioResponse -> rmRadioResponse.radioList).map(rmRadios -> {
            ArrayList<YKMADBRadio> radioList = null;
            if (rmRadios != null && rmRadios.size() > 0) {
                Realm realm = null;
                try {
                    realm = Realm.getInstance(((NCMCCoreApp) application).getConfig0());
                    realm.beginTransaction();
                    radioList = new ArrayList<>();
                    for (YKMARMRadio rmRadio : rmRadios) {
                        final YKMADBRadio dbRadio = YKMADataMapping.clone(rmRadio);
                        realm.copyToRealmOrUpdate(dbRadio);
                        radioList.add(dbRadio);
                    }
                    realm.commitTransaction();
                } catch (Exception e) {

                } finally {
                    if (realm != null) {
                        realm.close();
                    }
                }
            }
            return radioList;
        }));
    }

    public Observable<ArrayList<YKMADBRadio>> getAllRadioFromLocal() {
        return Observable.defer(() -> Observable.create(subscriber -> {
            Realm realm = null;
            try {
                realm = Realm.getInstance(((NCMCCoreApp) application).getConfig0());
                final RealmResults<YKMADBRadio> dbRadios = realm.allObjects(YKMADBRadio.class);
//                Log.e(TAG, dbRadios.size() + " getFromLocal");
                ArrayList<YKMADBRadio> radioArrayList = new ArrayList<>();
                for (YKMADBRadio dbRadio : dbRadios) {
                    radioArrayList.add(YKMADataMapping.clone(dbRadio));
                }
                onNext(subscriber, radioArrayList);
            } catch (Exception e) {
                onError(subscriber, e);
            } finally {
                if (realm != null) {
                    realm.close();
                }
                onCompleted(subscriber);
            }
        }));
    }


}
