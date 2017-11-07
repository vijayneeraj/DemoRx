package android.anative.com.demoadvance.ui.dialogs;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Neeraj VijayVargiya on 6/11/17.
 */

public class ApiProgressDialog {
    private ProgressDialog progressDialog;

    public ApiProgressDialog(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
    }

    public void showDialog() {
        progressDialog.show();
    }

    public void hideDialog() {
        progressDialog.dismiss();
    }
}
