package com.cvnhan.androidcr.mvp.view;

import com.cvnhan.androidcr.mvp.model.local.YKMADBSong;

import java.util.ArrayList;

/**
 * Created by NhanCao on 13-Sep-15.
 */
public interface PlayerView {
    void render(ArrayList<YKMADBSong> songList);
}
