package com.example.mattershmily.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by HuynhNga on 07/12/2017.
 */

public class adapter_recyclerView extends RecyclerView.Adapter<adapter_recyclerView.ViewHolder> {
    ArrayList<oneitem_recyclerview> oneitem_recyclerviews;
    Context context;


    public adapter_recyclerView(ArrayList<oneitem_recyclerview> oneitem_recyclerviews, Context context) {
        this.oneitem_recyclerviews = oneitem_recyclerviews;
        this.context = context;
    }

    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itemview=layoutInflater.inflate(R.layout.item_recyclerview,parent,false);

        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
     holder.tx_showngay.setText(String.valueOf(oneitem_recyclerviews.get(position).getNgay()));
        holder.tx_showthang.setText(String.valueOf(oneitem_recyclerviews.get(position).getThang()));
        holder.tx_shownam.setText(String.valueOf(oneitem_recyclerviews.get(position).getNam()));

    }

    @Override
    public int getItemCount() {
        return oneitem_recyclerviews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tx_showngay,tx_showthang,tx_shownam;
    public ViewHolder(View itemView) {
        super(itemView);
        tx_showngay= (TextView) itemView.findViewById(R.id.show_ngay);
        tx_showthang=(TextView) itemView.findViewById(R.id.show_thang);
        tx_shownam=(TextView) itemView.findViewById(R.id.show_nam);
    }
}
}
