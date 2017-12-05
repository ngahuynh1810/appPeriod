package com.example.mattershmily.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.Date;

public class adddateActivity extends AppCompatActivity {
TextView tx;
Button btn,huy;
    final String DATABASE_NAME="a.sqlite";
    SQLiteDatabase Database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddate);
        tx= (TextView) findViewById(R.id.hienthingaythang);
        btn= (Button) findViewById(R.id.themchukymoi);
        huy= (Button) findViewById(R.id.huy);
        Intent i = getIntent();
        String dateString = i.getExtras().getString("longdate","khong tim thay");

        final Long longdate=Long.parseLong(dateString);
        Date date=new Date(longdate);
        CalendarDay calendarDay = CalendarDay.from(date);
        tx.setText(date.toString());
        //luu bien long nay vao database,sau khi luu chuyen intent sang lich Activity
       Toast.makeText(adddateActivity.this,"gia tri truyen qua la"+longdate, Toast.LENGTH_LONG).show();
       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               ContentValues contentValues=new ContentValues();
               contentValues.put("BeginPeriod",longdate);
               Database=database.initDatabase(adddateActivity.this,DATABASE_NAME);
               Database.insert("AddPeriod",null,contentValues);
               Database.close();
               Intent intent=new Intent(adddateActivity.this,lich.class);
               startActivity(intent);
           }
       });
       huy.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent1=new Intent(adddateActivity.this,lich.class);
               startActivity(intent1);
           }
       });



    }
}
