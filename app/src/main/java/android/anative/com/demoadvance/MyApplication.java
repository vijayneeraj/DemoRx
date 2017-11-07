package android.anative.com.demoadvance;

import android.anative.com.demoadvance.volley.LruBitmapCache;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.facebook.stetho.Stetho;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by Neeraj VijayVargiya on 3/11/17.
 */

public class MyApplication extends com.activeandroid.app.Application {
    public static final String TAG = MyApplication.class.getName();
    public RequestQueue requestQueue;
    public ImageLoader imageLoader;
    private static MyApplication instance;
    private ExecutorService dataBaseEcecutor;

    public MyApplication() {
        dataBaseEcecutor = Executors.newSingleThreadExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, "demo backgroundExecutor");
                thread.setPriority(Thread.MIN_PRIORITY);
                thread.setDaemon(true);
                return thread;
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Stetho.initializeWithDefaults(this);
        // ActiveAndroid.initialize(this);
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(this);
        }
        return requestQueue;
    }

    public void runInBackGrounnd(Runnable runnable) {
        dataBaseEcecutor.submit(runnable);
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (imageLoader == null) {
            imageLoader = new ImageLoader(this.requestQueue, new LruBitmapCache());
        }
        return this.imageLoader;
    }

    public <T> void addToRequestQueue(Request<T> request, String tag) {
        request.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(request);
    }

    public <T> void addToRequestQueue(Request<T> request) {
        request.setTag(TAG);
        getRequestQueue().add(request);
    }

    public void cancelPendingRequests(Object tag) {
        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
    }

    public synchronized boolean isConnectingToInternet() {
        boolean netconnected = false;
        ConnectivityManager connectivity = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        netconnected = true;

                    }
                }
            }

        }
        return netconnected;
    }
}
