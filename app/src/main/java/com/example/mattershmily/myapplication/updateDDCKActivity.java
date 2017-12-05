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


public class updateDDCKActivity extends AppCompatActivity {
    Button btn_save;
    Toolbar toolbar;
    TextView txtDoDaichuky;
    EditText edDoDaichuky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_ddck);
        addControl();
        // Khởi tạo toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        //Không hiện tiêu đề
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Độ dài chu kỳ");
        //Hiện nút back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i = getIntent();
        String title = i.getExtras().getString("ddck","28");

        edDoDaichuky.setText(title);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int ddck=Integer.parseInt(edDoDaichuky.getText().toString());
                final String DATABASE_NAME="a.sqlite";
                SQLiteDatabase Database;
                ContentValues contentValues=new ContentValues();
                contentValues.put("CycleLength",ddck);

                Database=database.initDatabase(updateDDCKActivity.this,DATABASE_NAME);
                int id=1;
                Database.update("Setting",contentValues,"id=?",new String[] {id+""});
                Toast.makeText(updateDDCKActivity.this,"saved",Toast.LENGTH_LONG).show();
                Database.close();
                Intent i=new Intent(updateDDCKActivity.this,caidat.class);
                startActivity(i);
            }
        });


    }
    public void addControl() {
        txtDoDaichuky = (TextView) findViewById(R.id.txtDoDaichuky);
        edDoDaichuky= (EditText) findViewById(R.id.edDoDaichuky);
        btn_save= (Button) findViewById(R.id.save_ddck);
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

