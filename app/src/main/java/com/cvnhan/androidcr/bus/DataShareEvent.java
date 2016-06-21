package com.cvnhan.androidcr.bus;

/**
 * Created by nhancao on 6/21/16.
 */
public class DataShareEvent {
    public Class source;
    public Class des;
    public Object data;

    public DataShareEvent(Class source, Class des, Object data) {
        this.source = source;
        this.des = des;
        this.data = data;
    }
}
