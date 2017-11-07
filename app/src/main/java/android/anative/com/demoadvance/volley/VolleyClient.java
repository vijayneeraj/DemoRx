package android.anative.com.demoadvance.volley;

import android.anative.com.demoadvance.ui.launcherscreen.model.ApiDataModel;
import android.content.Context;

/**
 * Created by Neeraj VijayVargiya on 3/11/17.
 */

public class VolleyClient {
    private Context context;
    private ApiListeners apiListeners;

    public VolleyClient() {
    }

    public VolleyClient(ApiListeners apiListeners) {
        this.apiListeners = apiListeners;
    }

    public void getComments() {
        new BaseVolleyClient(apiListeners)
                .showDialog(true).
                startTimeStamp(System.currentTimeMillis())
                .tag(ApiDataModel.API_COMMENTS)
                .type(BaseVolleyClient.GET_REQ)
                .url(Api.COMMENT_URL)
                .execute();
    }

    public void getPhotos() {
        new BaseVolleyClient(apiListeners)
                .showDialog(true).
                startTimeStamp(System.currentTimeMillis())
                .tag(ApiDataModel.API_PHOTOS)
                .type(BaseVolleyClient.GET_REQ)
                .url(Api.PHOTOS_URL)
                .execute();
    }

    public void getTodos() {
        new BaseVolleyClient(apiListeners)
                .showDialog(true).
                startTimeStamp(System.currentTimeMillis())
                .tag(ApiDataModel.API_TODO)
                .type(BaseVolleyClient.GET_REQ)
                .url(Api.TODO_URL)
                .execute();
    }

    public void getPosts() {
        new BaseVolleyClient(apiListeners)
                .showDialog(true).
                startTimeStamp(System.currentTimeMillis())
                .tag(ApiDataModel.API_POSTS)
                .type(BaseVolleyClient.GET_REQ)
                .url(Api.POST_URL)
                .execute();
    }

}
