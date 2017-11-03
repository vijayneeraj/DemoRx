package android.anative.com.demoadvance;

import android.anative.com.demoadvance.utilities.ApplicationUtils;
import android.anative.com.demoadvance.volley.ApiListeners;
import android.anative.com.demoadvance.volley.Models.CommentsModel;
import android.anative.com.demoadvance.volley.VolleyClient;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Neeraj VijayVargiya on 3/11/17.
 */

public class MainActivity extends AppCompatActivity implements ApiListeners {
    VolleyClient volleyClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        volleyClient = new VolleyClient(this, this);
        volleyClient.getComments();
    }

    @Override
    public void onSucess(String response, int apiid, long startTimeStamp, long endTimeStamp) {
        if (ApplicationUtils.checkValidStrin(response)) {
            List<CommentsModel> commentsModelList = new Gson().fromJson(response, new TypeToken<List<CommentsModel>>() {
            }.getType());
            if (commentsModelList != null) {
                Toast.makeText(getApplicationContext(), "name = " + commentsModelList.get(0).getName(), Toast.LENGTH_LONG).show();
                ActiveAndroid.beginTransaction();
                try {
                    for (CommentsModel commentsModel :
                            commentsModelList) {
                        commentsModel.saveToDb();
                    }
                    ActiveAndroid.setTransactionSuccessful();
                } finally {
                    ActiveAndroid.endTransaction();
                }
            }
        }
    }

    @Override
    public void onFailure(String error) {

    }
}
