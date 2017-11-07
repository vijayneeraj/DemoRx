package android.anative.com.demoadvance.ui;

import android.anative.com.demoadvance.volley.ApiListeners;

/**
 * Created by Neeraj VijayVargiya on 6/11/17.
 */

public interface ActivityInteractor extends ApiListeners {
    void requestForData(String Tag);

    interface BaseListener{
        void networkError(String msg);
        void timeOutError(String message);
        void successData(ApiDataModel apiDataModels);
    }

}
