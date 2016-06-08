package com.cvnhan.androidcr.compat;

/**
 * Created by NhanCao on 13-Sep-15.
 */

public class NCMCPlatformImpSpecificFactory {
    /**
     * Create a new StrictMode instance.
     *
     * @return StrictMode
     */
    public static NCMCIStrictMode getStrictMode() {
        if (NCMCPlatform.SUPPORTS_HONEYCOMB)
            return new NCMCStrictModeHoneycomb();
        else if (NCMCPlatform.SUPPORTS_GINGERBREAD)
            return new NCMCStrictModeLegacy();
        else
            return null;
    }
}
