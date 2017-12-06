package com.example.mattershmily.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class setPasswordActivity extends AppCompatActivity {
EditText ed_password,ed_retypePassword;
Button btn_save,btn_unset;
Toolbar toolbar;
    final String DATABASE_NAME="a.sqlite";
    SQLiteDatabase Database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);
        toolbar = (Toolbar) findViewById(R.id.toolbar_setPassword);
        setSupportActionBar(toolbar);
        //Không hiện tiêu đề
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Đặt mật khẩu");
        //Hiện nút back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    ed_password= (EditText) findViewById(R.id.password);
    ed_retypePassword= (EditText) findViewById(R.id.password_retype);
    btn_save= (Button) findViewById(R.id.btn_save);
   btn_unset= (Button) findViewById(R.id.btn_unset);

    btn_save.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String password=ed_password.getText().toString();
            String retypePass=ed_retypePassword.getText().toString();
            if(password.equals(retypePass))
            {
                ContentValues contentValues=new ContentValues();
                contentValues.put("password",password);
                Database=database.initDatabase(setPasswordActivity.this,DATABASE_NAME);
                Database.update("Setting",contentValues,"id=?",new String[] {1+""});
                Toast.makeText(setPasswordActivity.this,"Đã lưu mật khẩu",Toast.LENGTH_SHORT);
//  setPassword ve 1-khoi dong man hinh khoa moi khi truy cap vao ung dung
                ContentValues contentValues2=new ContentValues();
                int s=1;
                contentValues2.put("SetPassword",s);
                int id=1;
                Database.update("Setting",contentValues2,"id=?",new String[] {id+""});
                Database.close();
                Intent o1 = new Intent(setPasswordActivity.this, caidat.class);
                startActivity(o1);
            }
            else
            {
                AlertDialog.Builder b = new AlertDialog.Builder(setPasswordActivity.this);
                b.setMessage("Mật khẩu không trùng khớp.Vui lòng nhập lại");
                AlertDialog al = b.create();
                al.show();
            }
        }

    });
    btn_unset.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            ContentValues contentValues1=new ContentValues();
            int s=0;
            contentValues1.put("SetPassword",s);
            int id=1;
            Database=database.initDatabase(setPasswordActivity.this,DATABASE_NAME);
            Database.update("Setting",contentValues1,"id=?",new String[] {id+""});
            Database.close();
            Intent o1 = new Intent(setPasswordActivity.this, caidat.class);
            startActivity(o1);
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
                Intent o2 = new Intent(setPasswordActivity.this, caidat.class);
                startActivity(o2);
                return true;
        }
    }

}
