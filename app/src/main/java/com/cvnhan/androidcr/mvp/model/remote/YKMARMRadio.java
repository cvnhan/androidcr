package com.cvnhan.androidcr.mvp.model.remote;

import com.google.gson.annotations.SerializedName;

/**
 * Created by NhanCao on 13-Sep-15.
 */
public class YKMARMRadio {
    @SerializedName("path")
    public String path;
    @SerializedName("name")
    public String name;
    @SerializedName("avatarUrl")
    public String avatarUrl;
    @SerializedName("genre")
    public String genre;
    @SerializedName("info")
    public String info;
}
