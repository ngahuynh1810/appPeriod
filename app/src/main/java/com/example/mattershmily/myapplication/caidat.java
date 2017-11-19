package com.example.mattershmily.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class caidat extends AppCompatActivity {
    Toolbar toolbar;
    ListView lvDb;
    ArrayList<item_caidat> arrayDb;
    adapter_caidat Adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caidat);
        final String DATABASE_NAME="c.sqlite";
        SQLiteDatabase Database;
        Database=database.initDatabase(this,DATABASE_NAME);
        Cursor cursor=Database.rawQuery("SELECT * FROM caidat",null);
        cursor.moveToFirst();
        final String s=cursor.getString(1);
        final String s2=cursor.getString(2);
        //Khởi tạo toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Không hiện tiêu đề
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Cài đặt");
        //Hiện nút back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            lvDb=(ListView) findViewById(R.id.lv);
            arrayDb=new ArrayList<>();
            //gan du lieu cho mang trong listview
            arrayDb.add(new item_caidat("Độ dài kinh nguyệt","",R.drawable.icon_hoa));
            arrayDb.add(new item_caidat("Độ dài chu kỳ","",R.drawable.clock));
            arrayDb.add(new item_caidat("Giai đoạn hoàng thể","",R.drawable.book));
            arrayDb.add(new item_caidat("Nhắc nhở","",R.drawable.nhacnho));
            arrayDb.add(new item_caidat("Triệu chứng và tâm trạng","",R.drawable.book));
            arrayDb.add(new item_caidat("Mật khẩu","",R.drawable.home));
            arrayDb.add(new item_caidat("Ngôn ngữ","",R.drawable.caidat));
            Adapter=new adapter_caidat(this,R.layout.item_caidat,arrayDb);
            lvDb.setAdapter(Adapter);
            lvDb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Intent o = new Intent(caidat.this, updateDDCKActivity.class);
                    o.putExtra("loichao",s);
                    o.putExtra("duongdan", s2);
                    startActivity(o);

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



}
