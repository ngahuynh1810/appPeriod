package com.example.mattershmily.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
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
    //load list tu database
    public void loadlist()
    {

        final String DATABASE_NAME="Period.sqlite";
        SQLiteDatabase Database;
        Database=database.initDatabase(this,DATABASE_NAME);
        Cursor cursor=Database.rawQuery("SELECT * FROM AddPeriod",null);
        cursor.moveToFirst() ;
            do {
                final Long long_date=cursor.getLong(1);
                Date date=new Date(long_date);
              //  Toast.makeText(lich.this,long_date+"Ngay load tu database là: "+date,Toast.LENGTH_LONG).show();

                CalendarDay calendarDay = CalendarDay.from(date);

                list.add(calendarDay);
            }
            while (cursor.moveToNext());
            materialCalendarView.addDecorator(new EventDecorator(myColor,list,lich.this));

        }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich);
        btn_reset= (Button) findViewById(R.id.btn_reset);
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String DATABASE_NAME="cin.sqlite";
                final SQLiteDatabase Database;
                Database=database.initDatabase(lich.this,DATABASE_NAME);

                Database.delete("Period","",null);
            }
        });
        final MaterialCalendarView materialCalendarView=(MaterialCalendarView) findViewById(R.id.calendarView);
        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setMinimumDate(CalendarDay.from(1970, 4, 3))
                .setMaximumDate(CalendarDay.from(2030, 5, 12))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
       materialCalendarView.setSelectionColor(Color.parseColor("#00BCD4"));
        final String DATABASE_NAME_2="c.sqlite";
        final SQLiteDatabase Database2;
        Database2=database.initDatabase(this,DATABASE_NAME_2);

        Cursor cursor2=Database2.rawQuery("SELECT * FROM caidat",null);
        cursor2.moveToFirst();
        final int ddkn=cursor2.getInt(1);
        final int ddck=cursor2.getInt(2);

        final String DATABASE_NAME="cin.sqlite";
        final SQLiteDatabase Database;
        Database=database.initDatabase(this,DATABASE_NAME);

        Cursor cursor=Database.rawQuery("SELECT * FROM Period",null);

            long recent_day = 0;
            cursor.moveToFirst();
//
//        CalendarDay calendarDay1 = CalendarDay.from(2017,11,10);
//        CalendarDay calendarDay = CalendarDay.from(2017,12,10);
//        long d=calendarDay.getCalendar().getTime().getTime()- calendarDay1.getCalendar().getTime().getTime();
//
//        Toast.makeText(lich.this, "time"+d, Toast.LENGTH_LONG).show();

        if(cursor.moveToFirst()) {
            do {
                final Long long_date = cursor.getLong(1);
                if (long_date > recent_day) {
                    recent_day = long_date;
                }
                //  Toast.makeText(lich.this,long_date+"Ngay load tu database là: "+date,Toast.LENGTH_LONG).show();
                for (int i = 0; i < ddkn; i++) {

                    Date date = new Date(long_date + i * 86400000);

                    //Toast.makeText(lich.this, "date:"+date, Toast.LENGTH_LONG).show();
                    CalendarDay calendarDay = CalendarDay.from(date);
                    list.add(calendarDay);
                }
                //Toast.makeText(lich.this,long_date+"Ngay load tu database là: "+calendarDay,Toast.LENGTH_LONG).show();
            }
            while (cursor.moveToNext());
            materialCalendarView.addDecorator(new EventDecorator(myColor, list,lich.this));

//du doan chu ky trong 9 tháng tiếp theo
            for (long k = 1; k < 10; k++) {

               dulieududoan = (recent_day+86400000*k*ddck);
                for (int i = 0; i < ddkn; i++) {

                    Date date = new Date(dulieududoan + i * 86400000);
                    //Toast.makeText(lich.this, "date:"+date, Toast.LENGTH_LONG).show();
                    CalendarDay calendarDay = CalendarDay.from(date);
                    Preditlist.add(calendarDay);
                }
               // Toast.makeText(lich.this,"PHEP TINH"+recent_day+"+("+86400000+"*"+ddck+"*"+j+"="+dulieududoan, Toast.LENGTH_LONG).show();
                Date date3 = new Date(dulieududoan);
               // Toast.makeText(lich.this,"PHEP TINH"+dulieududoan, Toast.LENGTH_LONG).show();
                CalendarDay calendarDay = CalendarDay.from(date3);

                Preditlist.add(calendarDay);
            }

            materialCalendarView.addDecorator(new EventDecorator(R.color.colorPredict, Preditlist,lich.this));
        }
         materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
             @Override
             public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                 Calendar calendar= Calendar.getInstance();
                 Long longdate=date.getCalendar().getTimeInMillis();
                 Toast.makeText(lich.this,"longdate la"+longdate,Toast.LENGTH_LONG).show();

//                 Date date1=new Date(longdate);
//                 Date date2=new Date(date1.getYear()+0,date1.getMonth()-1,date1.getDay()+0);
//
//                 CalendarDay calendarDay = CalendarDay.from(date2);
//                 Calendar cal1 = date.getCalendar();
//                 cal1.set(date.getYear(), date.getMonth(), date.getDay());
//                 Calendar cal2 = Calendar.getInstance();
//                 cal2.set(date.getYear(), date.getMonth(), date.getDay());
//
//                 HashSet<CalendarDay> setDays = getCalendarDaysSet(cal1,cal2);
                 Intent xuLy = new Intent(lich.this, adddateActivity.class);
                 xuLy.putExtra("longdate", longdate.toString());

                 startActivity(xuLy);



                // materialCalendarView.addDecorator(new EventDecorator(myColor,list));

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

//        calendar = (CalendarView) findViewById(R.id.calendar);
//        calendar.setFirstDayOfWeek(2);
//        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(CalendarView view, final int i, final int i1, final int i2) {
//                final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yy");
//                Toast.makeText(getApplicationContext(), i2 + "/" + i1 + "/" + i, Toast.LENGTH_LONG).show();
//                Button btn_ghichu=(Button) findViewById(R.id.btn_ghichu);
//                btn_ghichu.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        AlertDialog dialog = new AlertDialog.Builder(lich.this)
//                                .setTitle(i2+"thang"+i1+"nam"+i)
//                                .setMessage("Thêm chu kỳ mới")
//                                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialogInterface, int i3) {
//                                     //   Toast.makeText(lich.this,i2+"thang"+i1+"nam"+i,Toast.LENGTH_LONG).show();
//                                        Date date = null; // You will need try/catch around this
//
//                                        try {
//                                            date = simpleDateFormat.parse(i2+"/"+i1+"/"+i);
//
//                                        } catch (ParseException e) {
//                                            e.printStackTrace();
//                                        }
//                                        long millis = date.getTime();
//
//                                        //Date last = new Date(millis.getTime());
//                                        Toast.makeText(lich.this,millis+" ",Toast.LENGTH_LONG).show();
//
//
//                                    }
//                                })
//                                .create();
//                        dialog.show();
//
//                    }
//                });
//            }
//        });
//
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
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */






}
