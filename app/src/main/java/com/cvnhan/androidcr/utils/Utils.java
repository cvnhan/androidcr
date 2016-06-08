package com.cvnhan.androidcr.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.WindowManager;

import com.cvnhan.androidcr.background.ServiceBase;

import java.text.Normalizer;
import java.util.UUID;

/**
 * Created by NhanCao on 13-Sep-15.
 */
public class Utils {

    /**
     * @param duration : 302000
     * @return 5:02
     */
    public static String formatDuration(String duration) {
        String result = duration;
        try {
            long du = Integer.parseInt(result) / 1000;
            result = String.format("%02d:%02d", du / 60, du % 60);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String formatDuration(int duration) {
        String result = "00:00";
        try {
            long du = duration / 1000;
            result = String.format("%02d:%02d", du / 60, du % 60);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static CharSequence highlightText(String search, String originalText) {
        if (search != null && !search.equalsIgnoreCase("")) {
            String normalizedText = Normalizer.normalize(originalText, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
            int start = normalizedText.indexOf(search);
            if (start < 0) {
                return originalText;
            } else {
                Spannable highlighted = new SpannableString(originalText);
                while (start >= 0) {
                    int spanStart = Math.min(start, originalText.length());
                    int spanEnd = Math.min(start + search.length(), originalText.length());
                    highlighted.setSpan(new ForegroundColorSpan(Color.BLUE), spanStart, spanEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    start = normalizedText.indexOf(search, spanEnd);
                }
                return highlighted;
            }
        }
        return originalText;
    }

    public static boolean isContainText(String search, String originalText) {
        if (search != null && !search.equalsIgnoreCase("")) {
            String normalizedText = Normalizer.normalize(originalText, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
            int start = normalizedText.indexOf(search);
            if (start < 0) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void sendBroadcastIntentBaseActivityReceiver(Context context, String message) {
        Intent intent = new Intent();
        intent.setAction(ServiceBase.ACTIVITY_RECEIVE);
        intent.putExtra(ServiceBase.ACTIVITY_RECEIVE, message);
        context.sendBroadcast(intent);
    }

    public static void sendBroadcastIntentBaseServiceReceiver(Context context, String message) {
        Intent intent = new Intent();
        intent.setAction(ServiceBase.SERVICE_RECEIVE);
        intent.putExtra(ServiceBase.SERVICE_RECEIVE, message);
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
