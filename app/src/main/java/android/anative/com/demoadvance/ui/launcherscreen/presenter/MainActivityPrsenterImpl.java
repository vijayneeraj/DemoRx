package android.anative.com.demoadvance.ui.launcherscreen.presenter;

import android.anative.com.demoadvance.ui.launcherscreen.model.ActivityInteractor;
import android.anative.com.demoadvance.ui.launcherscreen.model.ActivityInteractorImpl;
import android.anative.com.demoadvance.ui.launcherscreen.model.ApiDataModel;
import android.anative.com.demoadvance.ui.launcherscreen.view.MainView;
import android.anative.com.demoadvance.utilities.ApplicationUtils;

import java.util.HashMap;

/**
 * Created by Neeraj VijayVargiya on 6/11/17.
 */

public class MainActivityPrsenterImpl implements MainActivityPresenter, ActivityInteractor.BaseListener {
    private MainView mainView;
    private ActivityInteractor activityInteractor;
    private HashMap<String, ApiDataModel> hashMap = new HashMap<>();

    public MainActivityPrsenterImpl(MainView mainView) {
        this.mainView = mainView;
        this.activityInteractor = new ActivityInteractorImpl(this);
    }

    @Override
    public void isValidRequest(String tag) {
        if (ApplicationUtils.checkValidStrin(tag) && mainView != null) {
            mainView.showDialog();
            activityInteractor.requestForData(tag);
        }

    }

    @Override
    public void networkError(String msg) {
        if (mainView != null) {
            mainView.hideDialog();
            mainView.displayErrorMessage(msg);
        }
    }

    @Override
    public void timeOutError(String message) {
        if (mainView != null) {
            mainView.hideDialog();
            mainView.displayErrorMessage(message);
        }
    }

    @Override
    public void successData(ApiDataModel apiDataModels) {
        if (mainView != null) {
            mainView.hideDialog();
            hashMap.put(apiDataModels.getApi_name(), apiDataModels);
            mainView.displayData(hashMap);
        }

    }
}
