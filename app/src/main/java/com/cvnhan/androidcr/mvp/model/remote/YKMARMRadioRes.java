package com.cvnhan.androidcr.mvp.model.remote;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by NhanCao on 13-Sep-15.
 */
public class YKMARMRadioRes {
    @SerializedName("radioList")
    public ArrayList<YKMARMRadio> radioList;
    @SerializedName("status")
    public int status;
}
