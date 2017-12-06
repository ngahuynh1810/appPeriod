package com.example.mattershmily.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.Date;

public class nhatky_tabhost extends AppCompatActivity {
TabHost tabHost;
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhatky_tabhost);
        //Khởi tạo toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        //Không hiện tiêu đề
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Nhật ký");
        //Hiện nút back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final String DATABASE_NAME="a.sqlite";
        final SQLiteDatabase Database;
        Database=database.initDatabase(this,DATABASE_NAME);

        Cursor cursor=Database.rawQuery("SELECT * FROM AddPeriod",null);
        if(cursor.moveToFirst()) {
            do {
                final long longdate = cursor.getLong(1);
                Date date = new Date(longdate);
                Toast.makeText(nhatky_tabhost.this, date.getDate() + " tháng " + date.getMonth() + " năm " + date.getYear(), Toast.LENGTH_LONG).show();

            }while (cursor.moveToNext());
            cursor.close();
            }
            Database.close();

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
