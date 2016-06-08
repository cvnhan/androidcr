package com.cvnhan.androidcr.mvp.model.remote;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by NhanCao on 13-Sep-15.
 */
public class MRRadioRes {
    @SerializedName("radioList")
    public ArrayList<MRRadio> radioList;
    @SerializedName("status")
    public int status;
}
