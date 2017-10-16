package top.moverco.mokhttp.utils;

import android.util.Log;

/**
 * Created by Moverco.
 */

public class Logger {
    private static final boolean DEBUG = true;

    /**
     * Log message
     * @param msg
     */
    public static void debug(String msg){
        if (DEBUG){
            Log.d("LOG from HTTP",msg);
        }
    }
}
