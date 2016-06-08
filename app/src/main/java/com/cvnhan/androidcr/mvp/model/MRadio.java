package com.cvnhan.androidcr.mvp.model;

import com.cvnhan.androidcr.MyApp;
import com.cvnhan.androidcr.mvp.model.local.MLRadio;
import com.cvnhan.androidcr.mvp.model.remote.MRRadio;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;

import static com.cvnhan.androidcr.utils.RxHelper.onCompleted;
import static com.cvnhan.androidcr.utils.RxHelper.onError;
import static com.cvnhan.androidcr.utils.RxHelper.onNext;

/**
 * Created by NhanCao on 13-Sep-15.
 */
public class MRadio {
    private final String TAG = MRadio.class.getSimpleName();
    private final android.app.Application application;
    private final MApi apiModel;

    public MRadio(android.app.Application application, MApi apiModel) {
        this.application = application;
        this.apiModel = apiModel;
    }

    public Observable<ArrayList<MLRadio>> getAllRadio() {
        return Observable.mergeDelayError(getAllRadioFromLocal(), getAllRadioFromRemote());
    }

    public Observable<ArrayList<MLRadio>> getAllRadioFromRemote() {
        return Observable.defer(() -> apiModel.getAllRadio().map(rmRadioResponse -> rmRadioResponse.radioList).map(rmRadios -> {
            ArrayList<MLRadio> radioList = null;
            if (rmRadios != null && rmRadios.size() > 0) {
                Realm realm = null;
                try {
                    realm = Realm.getInstance(((MyApp) application).getConfig0());
                    realm.beginTransaction();
                    radioList = new ArrayList<>();
                    for (MRRadio rmRadio : rmRadios) {
                        final MLRadio dbRadio = MDataMapping.clone(rmRadio);
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

    public Observable<ArrayList<MLRadio>> getAllRadioFromLocal() {
        return Observable.defer(() -> Observable.create(subscriber -> {
            Realm realm = null;
            try {
                realm = Realm.getInstance(((MyApp) application).getConfig0());
                final RealmResults<MLRadio> dbRadios = realm.where(MLRadio.class).findAll();
                ArrayList<MLRadio> radioArrayList = new ArrayList<>();
                for (MLRadio dbRadio : dbRadios) {
                    radioArrayList.add(MDataMapping.clone(dbRadio));
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
