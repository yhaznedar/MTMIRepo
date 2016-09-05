package com.mtmi.carapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sametdundar on 03/09/16.
 */
public class RowAdapter extends BaseAdapter {

    Context context;
    List<RowItem> rowItems;

    public RowAdapter(Context context, List<RowItem> rowItems) {
        this.context = context;
        this.rowItems = rowItems;
    }

    @Override
    public int getCount() {
        return rowItems.size();
    }

    @Override
    public Object getItem(int position) {
        return rowItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rowItems.indexOf(getItem(position));
    }

    private class ViewHolder{
        ImageView car_pic;
        TextView car_name;
        TextView status;
        TextView contactType;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if(convertView==null){
            convertView =mInflater.inflate(R.layout.car_item,null);
            holder = new ViewHolder();

            holder.car_name = (TextView) convertView.findViewById(R.id.car_name);
            holder.car_pic= (ImageView) convertView.findViewById(R.id.car_pic);
            holder.status= (TextView) convertView.findViewById(R.id.status);
            holder.contactType= (TextView) convertView.findViewById(R.id.contactType);

            RowItem row_pos= rowItems.get(position);

            holder.car_pic.setImageResource(row_pos.getCar_pic());
            holder.car_name.setText(row_pos.getCar_name());
            holder.status.setText(row_pos.getStatus());
            holder.contactType.setText(row_pos.getContactType());

            convertView.setTag(holder);


        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }


}
