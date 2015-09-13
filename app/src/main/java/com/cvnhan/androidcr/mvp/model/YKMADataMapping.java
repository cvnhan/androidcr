package com.cvnhan.androidcr.mvp.model;


import com.cvnhan.androidcr.mvp.model.local.YKMADBRadio;
import com.cvnhan.androidcr.mvp.model.local.YKMADBSong;
import com.cvnhan.androidcr.mvp.model.remote.YKMARMRadio;

/**
 * Created by NhanCao on 13-Sep-15.
 */
public class YKMADataMapping {

    public static YKMADBSong clone(YKMADBSong dbSong) {
        YKMADBSong result = new YKMADBSong();
        result.setPath(dbSong.getPath());
        result.setName(dbSong.getName());
        result.setAlbum(dbSong.getAlbum());
        result.setArtist(dbSong.getArtist());
        result.setAuthor(dbSong.getAuthor());
        result.setDateCreated(dbSong.getDateCreated());
        result.setDuration(dbSong.getDuration());
        result.setIsPlaying(dbSong.isPlaying());
        return result;
    }

    public static YKMADBRadio clone(YKMADBRadio radio) {
        YKMADBRadio dbRadio = new YKMADBRadio();
        dbRadio.setPath(radio.getPath());
        dbRadio.setName(radio.getName());
        dbRadio.setAvatarUrl(radio.getAvatarUrl());
        dbRadio.setGenre(radio.getGenre());
        dbRadio.setInfo(radio.getInfo());
        dbRadio.setIsPlaying(radio.isPlaying());
        return dbRadio;
    }

    public static YKMADBRadio clone(YKMARMRadio radio) {
        YKMADBRadio dbRadio = new YKMADBRadio();
        dbRadio.setPath(radio.path);
        dbRadio.setName(radio.name);
        dbRadio.setAvatarUrl(radio.avatarUrl);
        dbRadio.setGenre(radio.genre);
        dbRadio.setInfo(radio.info);
        return dbRadio;
    }
}
