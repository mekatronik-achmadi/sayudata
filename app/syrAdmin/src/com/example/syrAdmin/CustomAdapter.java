package com.example.syrAdmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by farm on 1/28/19.
 */
public class CustomAdapter extends BaseAdapter{
    private Context context;
    private final ArrayList<String> itemString;

    public CustomAdapter(Context context, ArrayList<String> itemString){
        this.context = context;
        this.itemString = itemString;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if(convertView == null){
            gridView = inflater.inflate(R.layout.list_grid,null);

            TextView txtView = (TextView) gridView.findViewById(R.id.txt);
            txtView.setText(itemString.get(position));
        }else{
            gridView = convertView;
        }

        return gridView;
    }

    @Override
    public int getCount(){
        return this.itemString.size();
    }

    @Override
    public Object getItem(int position){
        return null;
    }

    @Override
    public long getItemId(int position){
        return 0;
    }
}
