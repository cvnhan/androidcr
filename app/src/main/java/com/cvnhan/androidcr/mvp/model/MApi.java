package com.cvnhan.androidcr.mvp.model;

import com.cvnhan.androidcr.mvp.model.remote.MRRadioRes;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by NhanCao on 13-Sep-15.
 */
public interface MApi {
    @GET("/pol/controller/get_list.php")
    Observable<MRRadioRes> getAllRadio();
}
