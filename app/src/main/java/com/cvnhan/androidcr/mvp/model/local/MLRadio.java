package com.cvnhan.androidcr.mvp.model.local;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by NhanCao on 13-Sep-15.
 */
public class MLRadio extends RealmObject {

    @PrimaryKey
    private String path;
    private String name;
    private String avatarUrl;
    private String genre;
    private String info;
    private boolean isPlaying;

    public static MLRadio newInstance(String path, String name) {
        MLRadio radio = new MLRadio();
        radio.setPath(path);
        radio.setName(name);
        radio.setIsPlaying(false);
        return radio;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setIsPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }
}
