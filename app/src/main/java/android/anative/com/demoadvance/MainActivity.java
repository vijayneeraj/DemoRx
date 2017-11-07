package android.anative.com.demoadvance;

import android.anative.com.demoadvance.ui.ApiDataModel;
import android.anative.com.demoadvance.ui.MainActivityPresenter;
import android.anative.com.demoadvance.ui.MainActivityPrsenterImpl;
import android.anative.com.demoadvance.ui.MainView;
import android.anative.com.demoadvance.ui.adapter.MainActivityAdapter;
import android.anative.com.demoadvance.volley.VolleyClient;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Neeraj VijayVargiya on 3/11/17.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MainView {
    VolleyClient volleyClient;
    GridView gridview;
    private TextView btn1, btn2, btn3, btn4, unixtime;
    private static ProgressDialog progressDialog;
    private ApiProgressDialog apiProgressDialog;
    private MainActivityPresenter mainActivityPresenter;
    private HashMap<String, ApiDataModel> hashMap;
    private MainActivityAdapter mainActivityAdapter;
    private ArrayList<ApiDataModel> arrayList;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        findViews();
    }

    private void changeDatas() {
        ApiDataModel.saveListToPreferences(this, hashMap);
        Collection<ApiDataModel> collection = hashMap.values();
        ArrayList<ApiDataModel> apiDataModels = new ArrayList<>(collection);
        arrayList.clear();
        arrayList.addAll(apiDataModels);
        mainActivityAdapter.notifyDataSetChanged();
    }

    private void findViews() {
        hashMap = new HashMap<>();
        arrayList = new ArrayList<>();
        apiProgressDialog = new ApiProgressDialog(this);
        mainActivityPresenter = new MainActivityPrsenterImpl(this);
        gridview = (GridView) findViewById(R.id.gridview);
        btn1 = (TextView) findViewById(R.id.btn1);
        btn2 = (TextView) findViewById(R.id.btn2);
        btn3 = (TextView) findViewById(R.id.btn3);
        btn4 = (TextView) findViewById(R.id.btn4);
        unixtime = (TextView) findViewById(R.id.unixtime);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        unixtime.setText("current unix time=" + (System.currentTimeMillis() / 1000) + "seconds");
        mainActivityAdapter = new MainActivityAdapter(arrayList);
        gridview.setAdapter(mainActivityAdapter);

        if (ApiDataModel.getListFromPreferences(this) != null) {
            this.hashMap.putAll(ApiDataModel.getListFromPreferences(this));
            changeDatas();
        }
    }

//    @Override
//    public void onSucess(String response, int apiid, long startTimeStamp, long endTimeStamp) {

//    }
//
//    @Override
//    public void onFailure(String error) {
//
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                if (mainActivityPresenter != null)
                    mainActivityPresenter.isValidRequest(ApiDataModel.API_COMMENTS);
                break;
            case R.id.btn2:
                if (mainActivityPresenter != null)
                    mainActivityPresenter.isValidRequest(ApiDataModel.API_PHOTOS);
                break;
            case R.id.btn3:
                if (mainActivityPresenter != null)
                    mainActivityPresenter.isValidRequest(ApiDataModel.API_TODO);
                break;
            case R.id.btn4:
                if (mainActivityPresenter != null)
                    mainActivityPresenter.isValidRequest(ApiDataModel.API_POSTS);
                break;
        }
    }

    @Override
    public void displayData(HashMap<String, ApiDataModel> hashMap) {
        // todo here display all apis data
        this.hashMap.putAll(hashMap);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                changeDatas();
            }
        });
    }

    @Override
    public void showDialog() {
        if (apiProgressDialog != null) {
            apiProgressDialog.showDialog();
        }
    }

    @Override
    public void hideDialog() {
        if (apiProgressDialog != null) {
            apiProgressDialog.hideDialog();
        }
    }

    @Override
    public void displayErrorMessage(String error) {
        // display error in application
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
}
