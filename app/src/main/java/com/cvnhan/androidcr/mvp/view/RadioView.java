package com.cvnhan.androidcr.mvp.view;

import com.cvnhan.androidcr.mvp.model.local.YKMADBRadio;

import java.util.ArrayList;

/**
 * Created by NhanCao on 13-Sep-15.
 */
public interface RadioView {
    void render(ArrayList<YKMADBRadio> songList);
}
