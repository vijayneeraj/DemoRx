package android.anative.com.demoadvance.ui;

import android.anative.com.demoadvance.MyApplication;
import android.anative.com.demoadvance.utilities.ApplicationUtils;
import android.anative.com.demoadvance.volley.Models.BaseModel;
import android.anative.com.demoadvance.volley.Models.CommentsModel;
import android.anative.com.demoadvance.volley.Models.PhotosModel;
import android.anative.com.demoadvance.volley.Models.PostModel;
import android.anative.com.demoadvance.volley.Models.TodoModel;
import android.anative.com.demoadvance.volley.VolleyClient;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Neeraj VijayVargiya on 6/11/17.
 */

public class ActivityInteractorImpl implements ActivityInteractor, DatabaseRunable.DatabaseListeners {
    private VolleyClient volleyClient;
    private ActivityInteractor.BaseListener baseListener;

    public ActivityInteractorImpl(ActivityInteractor.BaseListener baseListener) {
        this.baseListener = baseListener;
        volleyClient = new VolleyClient(this);
    }

    @Override
    public void onSucess(String response, String tag, long startTimeStamp, long endTimeStamp) {
        if (ApplicationUtils.checkValidStrin(response)) {
            List<BaseModel> baseModelList = null;
            if (tag.equals(ApiDataModel.API_COMMENTS)) {
                baseModelList = new Gson().fromJson(response, new TypeToken<List<CommentsModel>>() {
                }.getType());

            } else if (tag.equals(ApiDataModel.API_PHOTOS)) {
                baseModelList = new Gson().fromJson(response, new TypeToken<List<PhotosModel>>() {
                }.getType());

            } else if (tag.equals(ApiDataModel.API_POSTS)) {
                baseModelList = new Gson().fromJson(response, new TypeToken<List<PostModel>>() {
                }.getType());

            } else if (tag.equals(ApiDataModel.API_TODO)) {
                baseModelList = new Gson().fromJson(response, new TypeToken<List<TodoModel>>() {
                }.getType());

            }

            if (baseModelList != null) {
                long startSaveTimeStamp = System.currentTimeMillis();
                MyApplication.getInstance().runInBackGrounnd(new DatabaseRunable(baseModelList, this, tag, startTimeStamp, endTimeStamp, startSaveTimeStamp));
            }
        } else {
            baseListener.timeOutError("Something Went Wrong!");

        }
    }

    private void setSucess(String tag, long startTime, long endTime, long startSaveTime, long endSaveTime) {
        ApiDataModel apiDataModel = new ApiDataModel();
        apiDataModel.api_name = tag;
        apiDataModel.startTimeStamp = startTime;
        apiDataModel.endTimeStamp = endTime;
        apiDataModel.startSaveTimeStamp = startSaveTime;
        apiDataModel.endSaveTimeStamp =endSaveTime;
        if (baseListener != null) {
            baseListener.successData(apiDataModel);
        }
    }

    @Override
    public void onFailure(String error) {
        if (baseListener != null) {
            baseListener.timeOutError(error);
        }
    }

    @Override
    public void requestForData(String Tag) {
        if (ApplicationUtils.checkValidStrin(Tag)) {
            if (!checkNetwork()) {
                return;
            }
            switch (Tag) {
                case ApiDataModel.API_COMMENTS:
                    volleyClient.getComments();
                    break;
                case ApiDataModel.API_PHOTOS:
                    volleyClient.getPhotos();
                    break;
                case ApiDataModel.API_POSTS:
                    volleyClient.getPosts();
                    break;
                case ApiDataModel.API_TODO:
                    volleyClient.getTodos();
                    break;
            }
        }
    }

    private boolean checkNetwork() {
        if (MyApplication.getInstance().isConnectingToInternet()) {
            return true;
        } else {
            if (baseListener != null) {
                baseListener.networkError("No Internet Available!");
            }
        }
        return false;
    }

    @Override
    public void entrySucess(long timeStamp, String tag, long startTime, long endTime, long startSaveTime) {
        setSucess(tag, startTime, endTime, startSaveTime, timeStamp);

    }
}
