package com.cvnhan.androidcr.utils;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by NhanCao on 13-Sep-15.
 */
public class ImageLoader {

    private final Picasso picasso;

    public ImageLoader(Picasso picasso) {
        this.picasso = picasso;
    }

    public void load(String url, ImageView target, int errorId) {
        picasso.load(url).fit().centerCrop().error(errorId).into(target);
    }

    public void load(String url, ImageView target, int errorId, Object tag) {
        picasso.load(url).fit().centerCrop().error(errorId).tag(tag).into(target);
    }

    public void pauseTag(Object tag) {
        picasso.pauseTag(tag);
    }

    public void resumeTag(Object tag) {
        picasso.resumeTag(tag);
    }

    public void cancelTag(Object tag) {
        picasso.cancelTag(tag);
    }
}
