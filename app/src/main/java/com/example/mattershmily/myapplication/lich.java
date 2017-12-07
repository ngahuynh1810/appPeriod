package com.example.mattershmily.myapplication;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;


public class lich extends AppCompatActivity {
    Toolbar toolbar;
    Button btn_reset;
    Long dulieududoan;
    CalendarView calendar;
    MaterialCalendarView materialCalendarView;
    int myColor = R.color.colorAccent;
    ArrayList<CalendarDay> list = new ArrayList<>();
    ArrayList<CalendarDay> Preditlist = new ArrayList<>();
    ArrayList<CalendarDay> list3 = new ArrayList<>();
    ArrayList<CalendarDay> list_ovilation = new ArrayList<>();
    //load list tu database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich);

        final MaterialCalendarView materialCalendarView=(MaterialCalendarView) findViewById(R.id.calendarView);
        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setMinimumDate(CalendarDay.from(2009, 1, 1))
                .setMaximumDate(CalendarDay.from(2030, 5, 12))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
       materialCalendarView.setSelectionColor(Color.parseColor("#00BCD4"));
        materialCalendarView.setBackgroundResource(R.drawable.anh);

        // *************Khai bao database**************
        final String DATABASE_NAME="a.sqlite";
        final SQLiteDatabase Database;
        Database=database.initDatabase(this,DATABASE_NAME);

       Cursor cursor=Database.rawQuery("SELECT * FROM Setting",null);
       cursor.moveToFirst();
        final int ddkn=cursor.getInt(1);
        final int ddck=cursor.getInt(2);
        final int ddht=cursor.getInt(3);
        cursor.close();


           long recent_day = 0;
        Cursor cursor1=Database.rawQuery("SELECT * FROM AddPeriod",null);
        cursor1.moveToFirst();
//*****Trang tri nhung ngay trong chu ky*******
        if(cursor1.moveToFirst()) {
            do {
                final Long long_date = cursor1.getLong(1);
                if (long_date > recent_day) {
                    recent_day = long_date;
                }
                Date date = new Date(long_date);
                 //       Toast.makeText(lich.this,long_date+"Ngay load tu database là: "+date.getDate()+"/"+date.getMonth()+"/"+date.getYear(),Toast.LENGTH_LONG).show();
                for (int i = 0; i < ddkn; i++) {

                    Date date1 = new Date(long_date + i * 86400000);

                    //Toast.makeText(lich.this, "date:"+date, Toast.LENGTH_LONG).show();
                    CalendarDay calendarDay = CalendarDay.from(date1);
                    list.add(calendarDay);
                }

            }
            while (cursor1.moveToNext());
            Date date_recent=new Date(recent_day);
           // Toast.makeText(lich.this,"Ngay load tu database là: "+date_recent.getDate()+"/"+date_recent.getMonth()+"/"+date_recent.getYear(),Toast.LENGTH_LONG).show();
            materialCalendarView.addDecorator(new EventDecorator(myColor, list,lich.this));
            cursor1.close();
//********du doan chu ky trong 3 tháng tiếp theo************
            for (long k = 0; k < 4; k++) {

               dulieududoan = (recent_day+86400000*k*ddck);
                for (int i = 0; i < ddkn; i++) {
                    Date date = new Date(dulieududoan + i * 86400000);
                    //Toast.makeText(lich.this, "date:"+date, Toast.LENGTH_LONG).show();
                    CalendarDay calendarDay = CalendarDay.from(date);
                    Preditlist.add(calendarDay);

                }
                //du doan thoi ky co thai
                for(int j=0;j<=4;j++) {
                    Date date3 = new Date(dulieududoan - ddht * 86400000+j*86400000);
                    Date date4 = new Date(dulieududoan - ddht * 86400000-j*86400000);
                    //Toast.makeText(lich.this, "date:"+date, Toast.LENGTH_LONG).show();
                    CalendarDay calendarDay3 = CalendarDay.from(date3);
                    CalendarDay calendarDay4 = CalendarDay.from(date4);
                    list3.add(calendarDay3);
                    list3.add(calendarDay4);
                }
                materialCalendarView.addDecorator(new EventDecorator3(R.color.colorPrimary,list3,lich.this));
                //them dotSpan vao de trang tri ngay rung trung
                Date date_ovilation = new Date(dulieududoan - ddht * 86400000);
                CalendarDay calendarDay5 = CalendarDay.from(date_ovilation);
                list_ovilation.add(calendarDay5);
                materialCalendarView.addDecorator(new Event_ovilation(R.color.colorPrimary,list_ovilation,lich.this));
               // Toast.makeText(lich.this,"PHEP TINH"+recent_day+"+("+86400000+"*"+ddck+"*"+j+"="+dulieududoan, Toast.LENGTH_LONG).show();
            }
            materialCalendarView.addDecorator(new EventDecorator(R.color.colorPredict, Preditlist,lich.this));
          Database.close();
        }

         materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
             @Override
             public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull final CalendarDay date, boolean selected) {
                 Calendar calendar= Calendar.getInstance();
                 final Long longdate=date.getCalendar().getTimeInMillis();
                 long date_current=System.currentTimeMillis();
                 if(longdate>date_current)
                 {
                     Toast.makeText(lich.this,date.getDay()+"/"+(date.getMonth()+1)+"/"+date.getYear(),Toast.LENGTH_SHORT).show();
                 }
                 else {
                     //  Toast.makeText(lich.this,"longdate "+longdate+"date"+date.getDate()+"/"+date.getMonth()+"/"+date.getYear(),Toast.LENGTH_LONG).show();
//                //Tạo đối tượng
                     AlertDialog.Builder b = new AlertDialog.Builder(lich.this);
//Thiết lập tiêu đề
                     b.setTitle("Thêm vào ngày bắt đầu chu kỳ");

// Nút Ok
                     b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                         public void onClick(DialogInterface dialog, int id) {
                             final String DATABASE_NAME = "a.sqlite";
                             final SQLiteDatabase Database;
                             Database = database.initDatabase(lich.this, DATABASE_NAME);
                             ContentValues contentValues = new ContentValues();
                             contentValues.put("BeginPeriod", longdate);
                             Database.insert("AddPeriod", null, contentValues);
                             Toast.makeText(lich.this, "Đã thêm ngày "+date.getDay()+"/"+(date.getMonth()+1)+"/"+date.getYear(), Toast.LENGTH_LONG).show();
                             Database.close();
                             Intent intent = new Intent(lich.this, lich.class);
                             startActivity(intent);

                         }
                     });
//Nút Cancel
                     b.setNegativeButton("Huỷ bỏ", new DialogInterface.OnClickListener() {
                         public void onClick(DialogInterface dialog, int id) {
                             dialog.cancel();
                         }
                     });
//Tạo dialog
                     AlertDialog al = b.create();
//Hiển thị
                     al.show();
                 }


             }
         });

        //Khởi tạo toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Không hiện tiêu đề
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Lịch");
        //Hiện nút back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    public void onPause() {
        super.onPause();
        finish();
    }
    private HashSet<CalendarDay> getCalendarDaysSet(Calendar cal1, Calendar cal2) {
        HashSet<CalendarDay> setDays = new HashSet<>();
        while (cal1.getTime().before(cal2.getTime())) {
            CalendarDay calDay = CalendarDay.from(cal1);
            setDays.add(calDay);
            cal1.add(Calendar.DATE, 1);
        }

        return setDays;
    }
    public void addEvent(String title,String location, long begin, long end) {
        Calendar cal = Calendar.getInstance();
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", cal.getTimeInMillis());
        intent.putExtra("allDay", true);
        intent.putExtra("rrule", "FREQ=YEARLY");
        intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000);
        intent.putExtra("title", "A Test Event from android app");
        startActivity(intent);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
               Intent intent=new Intent(lich.this,MainActivity.class);
               startActivity(intent);
            default:
                onBackPressed();
                return true;

        }
    }







}
