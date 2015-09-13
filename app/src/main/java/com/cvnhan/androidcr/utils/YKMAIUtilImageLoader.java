package com.cvnhan.androidcr.utils;

import android.widget.ImageView;

/**
 * Created by NhanCao on 13-Sep-15.
 */
public interface YKMAIUtilImageLoader {

    void load(String url, ImageView target, int errorId);

    void load(String url, ImageView target, int errorId, Object tag);

    void pauseTag(Object tag);

    void resumeTag(Object tag);

    void cancelTag(Object tag);
}
