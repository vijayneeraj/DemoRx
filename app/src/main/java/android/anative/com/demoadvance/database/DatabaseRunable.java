package android.anative.com.demoadvance.database;

import android.anative.com.demoadvance.volley.Models.BaseModel;

import com.activeandroid.ActiveAndroid;

import java.util.List;

/**
 * Created by Neeraj VijayVargiya on 7/11/17.
 */

public class DatabaseRunable implements Runnable {
    private List<BaseModel> baseModelList;
    private DatabaseListeners databaseListeners;
    private String tag;
    private long startTime;
    private long endTime;
    private long startSaveTime;
    private long endSaveTime;

    public DatabaseRunable(List<BaseModel> baseModelList, DatabaseListeners databaseListeners, String tag, long startTime, long endTime, long startSaveTime) {
        this.baseModelList = baseModelList;
        this.databaseListeners = databaseListeners;
        this.tag=tag;
        this.startTime=startTime;
        this.endTime=endTime;
        this.startSaveTime=startSaveTime;
    }

    @Override
    public void run() {
        ActiveAndroid.beginTransaction();
        try {
            for (BaseModel baseModel :
                    baseModelList) {
                baseModel.saveToDb();
            }
            ActiveAndroid.setTransactionSuccessful();
            long endSaveTimeStamp = System.currentTimeMillis();
            if (databaseListeners != null) {
                databaseListeners.entrySucess(endSaveTimeStamp,tag,startTime,endTime,startSaveTime);
            }
        } finally {
            ActiveAndroid.endTransaction();
        }
    }

    public interface DatabaseListeners {
        void entrySucess(long timeStamp, String tag, long startTime, long endTime, long startSaveTime);
    }
}