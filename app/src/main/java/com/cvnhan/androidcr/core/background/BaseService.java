package com.cvnhan.androidcr.core.background;

/**
 * Created by NhanCao on 13-Sep-15.
 */

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;


public abstract class BaseService extends Service {
    public static final String ACTIVITY_RECEIVE = "com.cvnhan.androidcr.core.utils.Activity.RECEIVE";
    public static final String SERVICE_RECEIVE = "com.cvnhan.androidcr.core.utils.Service.RECEIVE";
    ServiceReceiver myReceiver;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        myReceiver = new ServiceReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(BaseService.SERVICE_RECEIVE);
        registerReceiver(myReceiver, filter);
        onStartCommand(intent);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }

    protected abstract void onReceive(Context context, Intent intent);

    protected abstract void onStartCommand(Intent intent);

    class ServiceReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            BaseService.this.onReceive(context, intent);
        }
    }
}
