package com.cvnhan.androidcr.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.AudioManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.cvnhan.androidcr.background.ServiceBase;

import java.text.Normalizer;
import java.util.Random;
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
            int start = normalizedText.indexOf(search.toLowerCase());
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


    /**
     * Get random color
     *
     * @param alpha (0f - 1f)
     * @return
     */
    public static int randomColor(float alpha) {
        int a = Math.round(255 * alpha);
        int r = (int) (0xff * Math.random());
        int g = (int) (0xff * Math.random());
        int b = (int) (0xff * Math.random());
        return Color.argb(a, r, g, b);
    }

    public static int randomColor(float alpha, int[] colorArr) {
        return colorArr[new Random().nextInt(colorArr.length)];
    }

    public static int adjustAlpha(int color, float factor) {
        int alpha = Math.round(Color.alpha(color) * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }


    public static void showKeyboard(final View editText, final boolean force) {
        showKeyboard(editText, force, 50);
    }

    public static void showKeyboard(final View editText, final boolean force, final int delayTime) {
        if (editText != null) {
            // Delay some time to get focus(error occurs on HTC Android)
            editText.postDelayed(new Runnable() {
                @Override
                public void run() {
                    InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (force) {
                        imm.showSoftInput(editText, 0);
                    } else {
                        imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
                    }
                }
            }, delayTime);
        }
    }

    public static void hideKeyboard(final View editText) {
        if (editText != null) {
            // Delay some time to get focus(error occurs on HTC Android)
            editText.postDelayed(new Runnable() {
                @Override
                public void run() {
                    InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                }
            }, 50);
        }
    }

    public static boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px      A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }
}
