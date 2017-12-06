package com.example.mattershmily.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity    implements NavigationView.OnNavigationItemSelectedListener{
       final String DATABASE_NAME="a.sqlite";
    SQLiteDatabase Database;
Button btn_lich;
TextView tx_dudoankinhnguyet,tx_dudoanrungtrung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Database=database.initDatabase(this,DATABASE_NAME);
        Cursor cursor=Database.rawQuery("SELECT * FROM Setting",null);
        cursor.moveToFirst();
        final int ddck=cursor.getInt(2);

tx_dudoankinhnguyet= (TextView) findViewById(R.id.tx_dudoankinhnguyet);
tx_dudoanrungtrung= (TextView) findViewById(R.id.tx_dudoanrungtrung);

        long recent_day = 0;
        Cursor cursor1=Database.rawQuery("SELECT * FROM AddPeriod",null);
        cursor1.moveToFirst();

        if(cursor1.moveToFirst()) {
            do {
                final Long long_date = cursor1.getLong(1);
                if (long_date > recent_day) {
                    recent_day = long_date;
                }
            }
            while (cursor1.moveToNext());
        }
        cursor1.close();
        Long date_current=System.currentTimeMillis();
        Date date=new Date(date_current);
        Date date2=new Date(recent_day);

        Toast.makeText(MainActivity.this,"ngay hien tai la "+date+"ngay gan day nhat la"+date2,Toast.LENGTH_LONG);
        long rungtrung_songayconlai_long=recent_day+ddck*86400000-14*86400000;
        long rungtrung_songayconlai=rungtrung_songayconlai_long/86400000;
        long kinhnguyet_songayconlai_long=recent_day+ddck*86400000-date_current;
        long kinhnguyet_songayconlai=kinhnguyet_songayconlai_long/86400000;
        tx_dudoankinhnguyet.setText(String.valueOf(kinhnguyet_songayconlai));
        tx_dudoanrungtrung.setText(String.valueOf(rungtrung_songayconlai));

        Database.close();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_lich) {
            Intent i = new Intent(this,lich.class);
            startActivity(i);
            // Handle the camera action
        } else if (id == R.id.nav_nhatky) {
            Intent k = new Intent(this,nhatky_tabhost.class);
            startActivity(k);
        } else if (id == R.id.nav_bieudo) {

        } else if (id == R.id.nav_manage) {
            Intent u = new Intent(this,caidat.class);
            startActivity(u);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
