package com.example.mattershmily.myapplication;

import android.content.Intent;
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
        edPassword= (EditText) findViewById(R.id.edPassword);
        final String password=edPassword.getText().toString();
        btn_commit= (Button) findViewById(R.id.commit);
        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(password=="password")
                {
                    Intent o1 = new Intent(PasswordActivity.this, MainActivity.class);
                    startActivity(o1);
                }
                else
                {
                    Toast.makeText(PasswordActivity.this,"Nhập lại mật khẩu",Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
