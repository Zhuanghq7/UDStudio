package com.example.oureda.udstudio.adapter;

import android.content.ContentProvider;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.oureda.udstudio.R;
import com.example.oureda.udstudio.classes.item;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Zhuangh7 on 2016/9/26.
 */
public class mainAdapter extends ArrayAdapter {
    private Context context;
    private List<item> items;


    public mainAdapter(Context context, int resource, List<item> objects) {
        super(context,resource,objects);
        this.context = context;
        items = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.items,null);
        item tempItem = items.get(position);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.itemlayout);
        TextView itemName = new TextView(getContext());
        itemName.setText("111111");
        //itemName.set
        //linearLayout.addView();
        return view;
    }
}
