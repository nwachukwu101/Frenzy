package com.dotdex.frenzy.adapters;/**
 * Created by DABBY(3pleMinds) on 12-Feb-16.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dotdex.frenzy.R;
import com.dotdex.frenzy.model.MenuOption;

import java.util.ArrayList;

/**
 * DABBY(3pleMinds) 12-Feb-16 7:49 AM 2016 02
 * 12 07 49 UnnMobile
 **/
public class OverFlowAdapter extends ArrayAdapter {


    private ArrayList<MenuOption> mOptions;
    private LayoutInflater mInflater;
    public OverFlowAdapter(Context context, ArrayList options) {
        super(context, R.layout.menu_line,options);

        mOptions = options;

        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null)
            convertView = mInflater.inflate(R.layout.menu_line, null);

        MenuOption item = mOptions.get(position);

        if (item != null) {
            ((ImageView) convertView.findViewById(R.id.img_icon)).setImageDrawable(getContext().getResources().getDrawable(item.getIconId()));
            ((TextView) convertView.findViewById(R.id.txt_name)).setText(item.getTitle());
            ((TextView) convertView.findViewById(R.id.txt_sub)).setText(item.getSub());

            return convertView;
        }

        return null;
    }
}
