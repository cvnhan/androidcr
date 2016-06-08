package com.cvnhan.androidcr.compat;
/**
 * Created by NhanCao on 13-Sep-15.
 */

import android.os.StrictMode;

/**
 * Implementation that supports the Strict Mode functionality
 * available Honeycomb.
 */
public class NCMCStrictModeHoneycomb implements NCMCIStrictMode {
    protected static String TAG = "HoneycombStrictMode";

    public void enableStrictMode() {
        StrictMode.setThreadPolicy(
                new StrictMode.ThreadPolicy.Builder()
                        .detectDiskReads()
                        .detectDiskWrites()
                        .detectNetwork()
                        .penaltyLog()
                        .penaltyFlashScreen()
                        .build()
        );
    }
}
