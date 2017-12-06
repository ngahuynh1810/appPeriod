package com.example.mattershmily.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class updateDDHTActivity extends AppCompatActivity {
EditText ed_ddht;
Button btn_save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_ddht);
        ed_ddht= (EditText) findViewById(R.id.edDoDaiHoangThe);
        btn_save= (Button) findViewById(R.id.btnDoDaiHT);
        // Khởi tạo toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        //Không hiện tiêu đề
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Độ dài Hoàng thể");
        //Hiện nút back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        String title = i.getExtras().getString("ddht","14");

        ed_ddht.setText(title);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int ddht=Integer.parseInt(ed_ddht.getText().toString());
                final String DATABASE_NAME="a.sqlite";
                SQLiteDatabase Database;
                ContentValues contentValues=new ContentValues();
                contentValues.put("Ovulation",ddht);

                Database=database.initDatabase(updateDDHTActivity.this,DATABASE_NAME);
                int id=1;
                Database.update("Setting",contentValues,"id=?",new String[] {id+""});
                Toast.makeText(updateDDHTActivity.this,"saved",Toast.LENGTH_LONG).show();
                Database.close();
                Intent i=new Intent(updateDDHTActivity.this,caidat.class);
                startActivity(i);
            }
        });


    }
}
