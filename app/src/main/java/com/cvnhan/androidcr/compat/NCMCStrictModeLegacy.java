package com.cvnhan.androidcr.compat;
/**
 * Created by NhanCao on 13-Sep-15.
 */

import android.os.StrictMode;

/**
 * Implementation that supports the Strict Mode functionality
 * available for the first platform release that supported Strict Mode.
 */
public class NCMCStrictModeLegacy implements NCMCIStrictMode {

    public void enableStrictMode() {
        StrictMode.setThreadPolicy(
                new StrictMode.ThreadPolicy.Builder()
                        .detectDiskReads()
                        .detectDiskWrites()
                        .detectNetwork()
                        .penaltyLog()
                        .build()
        );
    }
}
