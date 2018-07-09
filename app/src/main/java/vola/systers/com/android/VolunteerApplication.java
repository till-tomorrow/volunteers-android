package vola.systers.com.android;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by haroon on 7/9/18.
 */

public class VolunteerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}
