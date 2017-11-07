package android.anative.com.demoadvance.ui;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

/**
 * Created by Neeraj VijayVargiya on 6/11/17.
 */

public class ApiDataModel {
    public static final String API_COMMENTS = "Comments";
    public static final String API_TODO = "TO-DO";
    public static final String API_POSTS = "Posts";
    public static final String API_PHOTOS = "Photos";
    public static final String PREFS_NAME = "demo_app";
    public static final String DATA_KEY = "data_key";

    long startTimeStamp;
    long endTimeStamp;
    long startSaveTimeStamp;
    long endSaveTimeStamp;
    String api_name;

    public long getStartTimeStamp() {
        return startTimeStamp;
    }

    public void setStartTimeStamp(long startTimeStamp) {
        this.startTimeStamp = startTimeStamp;
    }

    public long getEndTimeStamp() {
        return endTimeStamp;
    }

    public void setEndTimeStamp(long endTimeStamp) {
        this.endTimeStamp = endTimeStamp;
    }

    public long getStartSaveTimeStamp() {
        return startSaveTimeStamp;
    }

    public void setStartSaveTimeStamp(long startSaveTimeStamp) {
        this.startSaveTimeStamp = startSaveTimeStamp;
    }

    public long getEndSaveTimeStamp() {
        return endSaveTimeStamp;
    }

    public void setEndSaveTimeStamp(long endSaveTimeStamp) {
        this.endSaveTimeStamp = endSaveTimeStamp;
    }

    public String getApi_name() {
        return api_name == null ? "" : api_name.equals("null") ? "" : api_name;
    }

    public void setApi_name(String api_name) {
        this.api_name = api_name;
    }

    public static void saveListToPreferences(Context context, HashMap<String, ApiDataModel> apiDatas) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(DATA_KEY, new Gson().toJson(apiDatas));
        editor.commit();
    }

    public static final HashMap<String, ApiDataModel> getListFromPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String data = sharedPreferences.getString(DATA_KEY, null);
        if (data == null) {
            return new HashMap<>();
        }
        return new Gson().fromJson(data, new TypeToken<HashMap<String, ApiDataModel>>() {
        }.getType());
    }
}
