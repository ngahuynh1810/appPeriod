package com.example.mattershmily.myapplication;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/**
 * Created by Matter Shmily on 09/11/2017.
 */

public class adapter_caidat extends ArrayAdapter<item_caidat> {
    private Activity context;
    private int resource;
    private List<item_caidat> objects;
    public adapter_caidat(@NonNull Activity context, @LayoutRes int resource, @NonNull List<item_caidat> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource= resource;
        this.objects = objects;
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_caidat, null);
        }
        //anh xa
        TextView tx_ten = (TextView) convertView.findViewById(R.id.viewName);
        TextView tx_gia=(TextView) convertView.findViewById(R.id.viewNumber);
        ImageView tx_hinh=(ImageView) convertView.findViewById(R.id.viewimage);
        //gan gia tri
        item_caidat db=objects.get(position);
        tx_ten.setText(db.getTen());
        tx_gia.setText(db.getMa());
        tx_hinh.setImageResource(db.getHinh());
        return convertView;
//        return super.getView(position, convertView, parent);
    }

}
