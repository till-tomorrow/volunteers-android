package vola.systers.com.android;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;
import vola.systers.com.android.utils.ReleaseTree;

/**
 * Created by haroon on 7/9/18.
 */

public class VolunteerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new ReleaseTree());
        }

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}
