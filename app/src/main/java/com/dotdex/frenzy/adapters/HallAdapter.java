package com.dotdex.frenzy.adapters;/**
 * Created by DABBY(3pleMinds) on 09-Mar-16.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dotdex.frenzy.R;
import com.dotdex.frenzy.util.Constants;

/**
 * DABBY(3pleMinds) 09-Mar-16 8:34 AM 2016 03
 * 09 08 34 Frenzy
 **/
public class HallAdapter extends BaseAdapter {
    private Context mContext;
    public HallAdapter(Context context)
    {
        mContext = context;
    }

    @Override
    public int getCount() {
        return Constants.HALLS.length;
    }

    @Override
    public String getItem(int i) {
        return Constants.HALLS[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.halls, null, false);
        TextView t = (TextView) v.findViewById(R.id.hall_item_tv);
        t.setText(Constants.HALLS[i]);
        return v;
    }
}
