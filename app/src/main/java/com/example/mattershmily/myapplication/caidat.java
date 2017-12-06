package com.example.mattershmily.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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
        final String DATABASE_NAME="a.sqlite";
        SQLiteDatabase Database;
        Database=database.initDatabase(this,DATABASE_NAME);
        Cursor cursor=Database.rawQuery("SELECT * FROM Setting",null);
        cursor.moveToFirst();
        final String ddkn=cursor.getString(1);
        final String ddck=cursor.getString(2);
        final String ddht=cursor.getString(3);
        cursor.close();
        Database.close();
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
            arrayDb.add(new item_caidat("Độ dài kinh nguyệt","ddkn",R.drawable.icon_setting_period));
            arrayDb.add(new item_caidat("Độ dài chu kỳ","ddck",R.drawable.icon_setting_period));
            arrayDb.add(new item_caidat("Rụng trứng","",R.drawable.icon_setting_ovulation));
            arrayDb.add(new item_caidat("Mật khẩu","",R.drawable.icon_setting_password));
            arrayDb.add(new item_caidat("Xoá tất cả dữ liệu lịch","",R.drawable.iconsreturn50));
            Adapter=new adapter_caidat(this,R.layout.item_caidat,arrayDb);
            lvDb.setAdapter(Adapter);
            lvDb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if(i==0) {
                        Intent o = new Intent(caidat.this, updateDDKNActivity.class);
                        //Truyen bien ddkn trong bảng database qua edittext trong file java update
                        o.putExtra("ddkn", ddkn);

                        startActivity(o);
                    }
                    else if(i==1)
                    {
                        Intent o1 = new Intent(caidat.this, updateDDCKActivity.class);
                        o1.putExtra("ddck", ddck);
                        startActivity(o1);
                    }
                    else if(i==2)
                    {
                        Intent o2 = new Intent(caidat.this, updateDDHTActivity.class);
                        o2.putExtra("ddht", ddht);
                        startActivity(o2);
                    }
                    else if(i==3)
                    {
                        Intent intent_matkhau=new Intent(caidat.this,setPasswordActivity.class);
                        startActivity(intent_matkhau);
                    }
                    else if(i==4)
                    {

                        //Tạo đối tượng
                        AlertDialog.Builder b = new AlertDialog.Builder(caidat.this);
//Thiết lập tiêu đề
                        b.setTitle("Xác nhận xoá các dữ liệu");
                        b.setMessage("Thao tác này sẽ không thể hoàn tác");
// Nút Ok
                        b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                final String DATABASE_NAME="a.sqlite";
                                final SQLiteDatabase Database;
                                Database=database.initDatabase(caidat.this,DATABASE_NAME);
                                Database.delete("addPeriod","",null);
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
