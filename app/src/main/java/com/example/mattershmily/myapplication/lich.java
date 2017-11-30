package com.example.mattershmily.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.CalendarView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;
import java.util.HashSet;


public class lich extends AppCompatActivity {
    Toolbar toolbar;
    CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich);
        final MaterialCalendarView materialCalendarView=(MaterialCalendarView) findViewById(R.id.calendarView);
        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.WEDNESDAY)
                .setMinimumDate(CalendarDay.from(2016, 4, 3))
                .setMaximumDate(CalendarDay.from(2020, 5, 12))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

         materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
             @Override
             public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                 Calendar calendar= Calendar.getInstance();
                // Toast.makeText(lich.this,""+calendar,Toast.LENGTH_SHORT).show();


                 Calendar cal1 = date.getCalendar();
                 cal1.set(date.getYear(), date.getMonth(), date.getDay());
                 Calendar cal2 = Calendar.getInstance();
                 cal2.set(date.getYear(), date.getMonth(), date.getDay());

                 HashSet<CalendarDay> setDays = getCalendarDaysSet(cal1,cal2);
                 int myColor = R.color.colorAccent;
                 materialCalendarView.addDecorator(new EventDecorator(myColor, setDays));

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
