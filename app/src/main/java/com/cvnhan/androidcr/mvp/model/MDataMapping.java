package com.cvnhan.androidcr.mvp.model;


import com.cvnhan.androidcr.mvp.model.local.MLRadio;
import com.cvnhan.androidcr.mvp.model.remote.MRRadio;

/**
 * Created by NhanCao on 13-Sep-15.
 */
public class MDataMapping {

    public static MLRadio clone(MLRadio radio) {
        MLRadio dbRadio = new MLRadio();
        dbRadio.setPath(radio.getPath());
        dbRadio.setName(radio.getName());
        dbRadio.setAvatarUrl(radio.getAvatarUrl());
        dbRadio.setGenre(radio.getGenre());
        dbRadio.setInfo(radio.getInfo());
        dbRadio.setIsPlaying(radio.isPlaying());
        return dbRadio;
    }

    public static MLRadio clone(MRRadio radio) {
        MLRadio dbRadio = new MLRadio();
        dbRadio.setPath(radio.path);
        dbRadio.setName(radio.name);
        dbRadio.setAvatarUrl(radio.avatarUrl);
        dbRadio.setGenre(radio.genre);
        dbRadio.setInfo(radio.info);
        return dbRadio;
    }
}
