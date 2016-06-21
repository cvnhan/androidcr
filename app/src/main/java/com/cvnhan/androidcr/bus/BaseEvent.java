package com.cvnhan.androidcr.bus;

/**
 * Created by nhancao on 6/21/16.
 */
public class BaseEvent {
    public Class key;
    public Object data;

    public BaseEvent(Class key) {
        this.key = key;
        this.data = null;
    }

    public BaseEvent(Class key, Object data) {
        this.key = key;
        this.data = data;
    }
}
