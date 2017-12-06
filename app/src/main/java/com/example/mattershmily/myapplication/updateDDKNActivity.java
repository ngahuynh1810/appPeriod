package com.example.mattershmily.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class updateDDKNActivity extends AppCompatActivity {

    Button btn_save;
    Toolbar toolbar;
    TextView txtDoDaiKinhNguyet;
    EditText edDoDaiKinhNguyet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_ddkn);
        addControl();
        // Khởi tạo toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        //Không hiện tiêu đề
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Độ dài kinh nguyệt");
        //Hiện nút back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i = getIntent();
        String title = i.getExtras().getString("ddkn","3");

        edDoDaiKinhNguyet.setText(title);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int ddkn=Integer.parseInt(edDoDaiKinhNguyet.getText().toString());
                final String DATABASE_NAME="a.sqlite";
                SQLiteDatabase Database;
                ContentValues contentValues=new ContentValues();
                contentValues.put("PeriodLength",ddkn);

                Database=database.initDatabase(updateDDKNActivity.this,DATABASE_NAME);
                int id=1;
                Database.update("Setting",contentValues,"id=?",new String[] {id+""});
                Database.close();
                Toast.makeText(updateDDKNActivity.this,"saved",Toast.LENGTH_LONG).show();
                Intent i=new Intent(updateDDKNActivity.this,caidat.class);
                startActivity(i);
            }
        });


    }
    public void addControl() {
        txtDoDaiKinhNguyet = (TextView) findViewById(R.id.txtDoDaichuky);
        edDoDaiKinhNguyet= (EditText) findViewById(R.id.edDoDaiKinhNguyet);
        btn_save= (Button) findViewById(R.id.btnDoDaiKN);
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
    public void onPause() {
        super.onPause();
        finish();
    }
}
