package com.example.mattershmily.myapplication;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TabHost;

public class nhatky_tabhost extends TabActivity {
TabHost tabHost;
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhatky_tabhost);
        //Khởi tạo toolbar
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
//        setSupportActionBar(toolbar);
//        //Không hiện tiêu đề
//        getSupportActionBar().setDisplayShowTitleEnabled(true);
//        getSupportActionBar().setTitle("Nhật ký");
//        //Hiện nút back
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       tabHost= (TabHost) findViewById(android.R.id.tabhost);
        //TabHost tabHost = getTabHost();

        tabHost.setup(getLocalActivityManager());
        // Tab for Photos
        TabHost.TabSpec photospec = tabHost.newTabSpec("Photos");
        photospec.setIndicator("Photos", getResources().getDrawable(R.drawable.ic_menu_camera));
        Intent photosIntent = new Intent(this, tab1Activity.class);
        photospec.setContent(photosIntent);

        // Tab for Songs
        TabHost.TabSpec songspec = tabHost.newTabSpec("Songs");
        // setting Title and Icon for the Tab
        songspec.setIndicator("Songs", getResources().getDrawable(R.drawable.ic_menu_camera));
        Intent songsIntent = new Intent(this, tab2Activity.class);
        songspec.setContent(songsIntent);


        tabHost.addTab(photospec);
        tabHost.addTab(songspec);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




}
