package com.cvnhan.androidcr.mvp.model.local;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by NhanCao on 13-Sep-15.
 */
public class YKMADBSong extends RealmObject {

    @PrimaryKey
    private String path;
    private String name;
    private String album;
    private String artist;
    private String author;
    private String dateCreated;
    private int duration;
    private boolean isPlaying;

    public static YKMADBSong newInstance(String path, String name, String album, String artist, String author, String dateCreated, int duration) {
        YKMADBSong dbSong = new YKMADBSong();
        dbSong.path = path;
        dbSong.name = name;
        dbSong.album = album;
        dbSong.artist = artist;
        dbSong.author = author;
        dbSong.dateCreated = dateCreated;
        dbSong.duration = duration;
        dbSong.isPlaying = false;
        return dbSong;
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

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setIsPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }
}
