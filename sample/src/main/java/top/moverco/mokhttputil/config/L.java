package top.moverco.mokhttputil.config;

import android.util.Log;

/**
 * Created by Moverco.
 */

public class L {
    private static final boolean DEBUG = true;
    private String tag = "HTTP_DEMO";

    public static void debug(String msg) {
        if (DEBUG) {
            Log.d("HTTP_DEMO", msg);
        }
    }
}
