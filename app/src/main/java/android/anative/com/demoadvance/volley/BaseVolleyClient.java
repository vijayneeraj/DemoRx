package android.anative.com.demoadvance.volley;

import android.anative.com.demoadvance.MyApplication;
import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Neeraj VijayVargiya on 3/11/17.
 */

public class BaseVolleyClient {

    public static final String TAG = BaseVolleyClient.class.getName();

    public static final int POST_REQ = 1;
    public static final int GET_REQ = 0;
    private boolean isShowProgressDialog = false;
    private Context context;
    private String apiId;
    private long startTimeStamp;
    private int reqType;
    private String url;
    private static BaseVolleyClient instance;
    private Map<String, String> params;
    ApiListeners apiListeners;
    private Map<String, String> headers;

    public BaseVolleyClient(ApiListeners apiListeners) {
        instance = this;
        this.apiListeners = apiListeners;
        params = new HashMap<>();
        headers = new HashMap<>();
    }

    public BaseVolleyClient addHeader(String key, String value) {
        headers.put(key, value);
        return instance;
    }

    public BaseVolleyClient showDialog(boolean showDialog) {
        this.isShowProgressDialog = showDialog;
        return instance;
    }

    public BaseVolleyClient tag(String apiId) {
        this.apiId = apiId;
        return instance;
    }

    public BaseVolleyClient url(String url) {
        this.url = url;
        return instance;
    }

    public BaseVolleyClient type(int requestType) {
        this.reqType = requestType;
        return instance;
    }

    public BaseVolleyClient startTimeStamp(long startTimeStamp) {
        this.startTimeStamp = startTimeStamp;
        return instance;
    }


    public BaseVolleyClient addPostParams(String key, String value) {
        params.put(key, value);
        return instance;
    }

    public void execute() {
        try {
            if (!requestValidation()) {
                return;
            }
        } catch (VolleyException e) {
            printLog(e.getMessage());
        }
        printRequest();
        StringRequest stringRequest = new StringRequest(reqType, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                printLog(url + "\n" + "ApiId = " + apiId + "\n" + "response:" + response);
                if (apiListeners != null) {
                    apiListeners.onSucess(response, apiId, startTimeStamp, System.currentTimeMillis());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                printLog(url + "\n" + "ApiId = " + apiId + "\n" + "error:" + error.getMessage());
                if (apiListeners != null) {
                    apiListeners.onFailure(error.getMessage());
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers;
            }
        };
        MyApplication.getInstance().addToRequestQueue(stringRequest);
    }

    private boolean requestValidation() throws VolleyException {
        boolean isValid = true;
        if (reqType != 0 && reqType != 1) {
            isValid = false;
            throw new VolleyException("Invalid request type please check 0 for GET or 1 for POST.");

        }
        if (url == null) {
            isValid = false;
            throw new VolleyException("Web service url is null.");
        }
        return isValid;
    }

    private void printLog(String message) {
        Log.e(TAG, message);
    }

    private void printRequest() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(url + "\n");
        stringBuilder.append("type = " + reqType + "  apiId = " + apiId + "\n");
        for (String key : params.keySet()
                ) {
            stringBuilder.append(key + " = " + params.get(key));
        }
        printLog(stringBuilder.toString());
    }


}
