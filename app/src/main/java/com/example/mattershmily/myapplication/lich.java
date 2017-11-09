package com.example.mattershmily.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
public class lich extends AppCompatActivity {
    Toolbar toolbar;
    CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich);
        //Khởi tạo toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Không hiện tiêu đề
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Lịch");
        //Hiện nút back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        calendar = (CalendarView) findViewById(R.id.calendar);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int i, int i1, int i2) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd//mm//yy");
                Toast.makeText(getApplicationContext(), i2 + "/" + i1 + "/" + i, Toast.LENGTH_LONG).show();
            }
        });
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
