package com.example.mohamed_reda.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class signup extends AppCompatActivity {

    //reda

    ConnectionClass connectionClass2 = new ConnectionClass();
    TextView create_Account;
    EditText Fn;
    EditText Ln;
    EditText Nid;
    EditText phone;
    EditText Remail;
    EditText Rpass;
    ProgressBar pbbar2;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signup = (Button) findViewById(R.id.signup);
        Remail = (EditText) findViewById(R.id.Remail);
        Fn = (EditText) findViewById(R.id.Fn);
        Ln = (EditText) findViewById(R.id.Ln);
        Nid = (EditText) findViewById(R.id.Nid);
        phone = (EditText) findViewById(R.id.Phone);
        Rpass = (EditText) findViewById(R.id.Rpass);
        pbbar2 = (ProgressBar) findViewById(R.id.pbbar2);
        pbbar2.setVisibility(View.GONE);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


}