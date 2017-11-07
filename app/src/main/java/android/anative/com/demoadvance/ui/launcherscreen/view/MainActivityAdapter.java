package android.anative.com.demoadvance.ui.launcherscreen.view;

import android.anative.com.demoadvance.R;
import android.anative.com.demoadvance.ui.launcherscreen.model.ApiDataModel;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Neeraj VijayVargiya on 4/11/17.
 */

public class MainActivityAdapter extends BaseAdapter {
    private ArrayList<ApiDataModel> arrayList;
    private TextView api_tag, startTimestamp, endTimestamp, startSaveTimestamp, endSaveTimestamp;

    public MainActivityAdapter(ArrayList<ApiDataModel> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_item, null, false);
        }
        if (i>=arrayList.size()){
            return view;
        }
        api_tag = (TextView) view.findViewById(R.id.api_tag);
        startTimestamp = (TextView) view.findViewById(R.id.startTimestamp);
        endTimestamp = (TextView) view.findViewById(R.id.endTimestamp);
        startSaveTimestamp = (TextView) view.findViewById(R.id.startSaveTimestamp);
        endSaveTimestamp = (TextView) view.findViewById(R.id.endSaveTimestamp);
        api_tag.setText(arrayList.get(i).getApi_name());
        startTimestamp.setText(getTime(arrayList.get(i).getStartTimeStamp()));
        endTimestamp.setText(getTime(arrayList.get(i).getEndTimeStamp()));
        startSaveTimestamp.setText(getTime(arrayList.get(i).getStartSaveTimeStamp()));
        endSaveTimestamp.setText(getTime(arrayList.get(i).getEndSaveTimeStamp()));
        return view;
    }
    private static String getTime(long timestamp){
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(timestamp);
        return DateFormat.format("dd-MM-yyyy hh:mm:ss", cal).toString();
    }

}
