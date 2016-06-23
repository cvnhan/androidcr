package com.cvnhan.androidcr.utils;

import android.content.DialogInterface;
import android.view.View;

/**
 * Created by nhancao on 6/22/16.
 */
public class ICollections {

    public interface DialogSimpleInterface {
        void ok(DialogInterface dialog, View button, View rootView);

        void cancel(DialogInterface dialog, View button, View rootView);
    }

    public interface DoingListener {
        void doing(View view);
    }

    public interface CallbackListener {
        void callback();
    }
}
