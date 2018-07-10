package vola.systers.com.android.utils;

import android.util.Log;

import timber.log.Timber;

/**
 * Created by haroon on 7/9/18.
 */

public class ReleaseTree extends Timber.Tree {

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        // Only log WARN, ERROR, and WTF.
        if (priority > Log.INFO) {
            Log.println(priority, tag, message);
        }
    }
}
