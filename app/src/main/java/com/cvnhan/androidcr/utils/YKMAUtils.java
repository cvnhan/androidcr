package com.cvnhan.androidcr.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.util.Log;
import android.view.WindowManager;

import com.cvnhan.core.utils.NCMCUtils;

import java.util.UUID;

/**
 * Created by NhanCao on 13-Sep-15.
 */
public class YKMAUtils {

    private static final String TAG = "POL_Utils";

    public static void sendBroadcastIntentBaseActivityReceiver(Context context, String message) {
        Intent intent = new Intent();
        intent.setAction(NCMCUtils.ACTIVITY_RECEIVE);
        intent.putExtra(NCMCUtils.ACTIVITY_RECEIVE, message);
        context.sendBroadcast(intent);
    }

    public static void sendBroadcastIntentBaseServiceReceiver(Context context, String message) {
        Log.e(TAG, "sendBroadcast +" + message);
        Intent intent = new Intent();
        intent.setAction(NCMCUtils.SERVICE_RECEIVE);
        intent.putExtra(NCMCUtils.SERVICE_RECEIVE, message);
        context.sendBroadcast(intent);
    }

    public static AudioManager getAudiomanager(Context context) {
        return (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    public static int getMaxAudio(Context context) {
        return getAudiomanager(context).getStreamMaxVolume(AudioManager.STREAM_MUSIC);
    }

    public static int getProgressAudio(Context context) {
        return getAudiomanager(context).getStreamVolume(AudioManager.STREAM_MUSIC);
    }

    public static void setVolume(Context context, int volume) {
        getAudiomanager(context).setStreamVolume(AudioManager.STREAM_MUSIC,
                volume, 0);
    }

    public static void setNoAutoShowKeyboard(Activity activity) {
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public static String getUID() {
        return UUID.randomUUID().toString();
    }
}
