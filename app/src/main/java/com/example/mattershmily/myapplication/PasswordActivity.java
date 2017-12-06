package com.example.mattershmily.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordActivity extends AppCompatActivity {
EditText edPassword;
Button btn_commit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        final String DATABASE_NAME_2 = "a.sqlite";
        final SQLiteDatabase Database2;
        Database2 = database.initDatabase(PasswordActivity.this, DATABASE_NAME_2);

        Cursor cursor = Database2.rawQuery("SELECT * FROM Setting", null);
        cursor.moveToFirst();
        final String password = cursor.getString(4);
        final String set = cursor.getString(5);
        cursor.close();
        if (set.equals(0)) {
            Intent intent = new Intent(PasswordActivity.this, MainActivity.class);
            startActivity(intent);
        } else {


            btn_commit = (Button) findViewById(R.id.commit);


            btn_commit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    edPassword = (EditText) findViewById(R.id.edPassword);
                    final String ed_password = edPassword.getText().toString();
                    if (ed_password.equals(password)) {
                        Intent o1 = new Intent(PasswordActivity.this, MainActivity.class);
                        startActivity(o1);
                    } else {
                        Toast.makeText(PasswordActivity.this, "Nhập lại mật khẩu", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }

        Database2.close();
    }
    public void onPause() {
        super.onPause();
        finish();
    }
}
