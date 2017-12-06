package com.example.mattershmily.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;
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

        ArrayList<oneitem_recyclerview> arr = new ArrayList<>();;
        ArrayList<oneitem_recyclerview> arr1 = new ArrayList<>();;
        final String DATABASE_NAME="a.sqlite";
        final SQLiteDatabase Database;
        Database=database.initDatabase(this,DATABASE_NAME);

        Cursor cursor1=Database.rawQuery("SELECT * FROM Setting",null);
        cursor1.moveToFirst();
        final long ddck=Long.valueOf(cursor1.getInt(2));

        Cursor cursor=Database.rawQuery("SELECT * FROM AddPeriod",null);
        long recentday=0;
        if(cursor.moveToFirst()) {
            do {
                final long longdate = cursor.getLong(1);
                if(recentday<longdate)
                    recentday=longdate;
                Date date = new Date(longdate);
                CalendarDay calendarDay=CalendarDay.from(date);
                Toast.makeText(nhatky_tabhost.this, date.getDay() + " tháng " + date.getMonth()+1 + " năm " + date.getYear(), Toast.LENGTH_LONG).show();
                arr.add(new oneitem_recyclerview(calendarDay.getDay(),calendarDay.getMonth()+1,calendarDay.getYear()));
            }while (cursor.moveToNext());
            cursor.close();
            }
            Database.close();
for(int i=1;i<=5;i++) {
    long dayPredit = recentday + 86400000 * i * ddck;
    Date date = new Date(dayPredit);
    CalendarDay calendarDay = CalendarDay.from(date);
    arr1.add(new oneitem_recyclerview(date.getDay(),date.getMonth()+1,date.getYear()));
}
        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView recyclerView1= (RecyclerView) findViewById(R.id.recyclerView1);
            initView(recyclerView,arr);
            initView(recyclerView1,arr1);

    }
public void initView( RecyclerView recyclerView, ArrayList<oneitem_recyclerview> arr)
{

    recyclerView.setHasFixedSize(true);
    LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
    recyclerView.setLayoutManager(layoutManager);
//    DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL);
//    Drawable drawable= ContextCompat.getDrawable(getApplicationContext(),R.drawable.background_nau);
//    dividerItemDecoration.setDrawable(drawable);
//    recyclerView.addItemDecoration(dividerItemDecoration);

    adapter_recyclerView adapter_recyclerView=new adapter_recyclerView(arr,getApplicationContext());
    recyclerView.setAdapter(adapter_recyclerView);
    adapter_recyclerView.notifyDataSetChanged();

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
