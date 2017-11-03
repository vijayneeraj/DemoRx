package android.anative.com.demoadvance.volley;

import android.content.Context;

/**
 * Created by Neeraj VijayVargiya on 3/11/17.
 */

public class VolleyClient {
    private Context context;
    private ApiListeners apiListeners;

    public VolleyClient(Context context) {
    }

    public VolleyClient(Context context, ApiListeners apiListeners) {
        this.apiListeners = apiListeners;
        this.context = context;
    }

    public void getComments() {
        new BaseVolleyClient(context, apiListeners)
                .showDialog(true).
                startTimeStamp(System.currentTimeMillis())
                .tag(Api.ID_COMMENT_API)
                .type(BaseVolleyClient.GET_REQ)
                .url(Api.COMMENT_URL)
                .execute();
    }

    public void getPhotos() {
        new BaseVolleyClient(context, apiListeners)
                .showDialog(true).
                startTimeStamp(System.currentTimeMillis())
                .tag(Api.ID_PHOTOS_URL)
                .type(BaseVolleyClient.GET_REQ)
                .url(Api.PHOTOS_URL)
                .execute();
    }

    public void getTodos() {
        new BaseVolleyClient(context, apiListeners)
                .showDialog(true).
                startTimeStamp(System.currentTimeMillis())
                .tag(Api.ID_TODO_API)
                .type(BaseVolleyClient.GET_REQ)
                .url(Api.TODO_URL)
                .execute();
    }

    public void getPosts() {
        new BaseVolleyClient(context, apiListeners)
                .showDialog(true).
                startTimeStamp(System.currentTimeMillis())
                .tag(Api.ID_POST_API)
                .type(BaseVolleyClient.GET_REQ)
                .url(Api.POST_URL)
                .execute();
    }

}
