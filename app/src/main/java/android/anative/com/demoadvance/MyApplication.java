package android.anative.com.demoadvance;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * Created by Neeraj VijayVargiya on 3/11/17.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}
