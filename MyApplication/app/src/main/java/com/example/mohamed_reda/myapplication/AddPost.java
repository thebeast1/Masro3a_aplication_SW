package com.example.mohamed_reda.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class AddPost extends AppCompatActivity {

    //samy

    ConnectionClass connection = new ConnectionClass();

    EditText ItemName;
    EditText ItemDesc;
    TextView next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        ItemName = (EditText) findViewById(R.id.ItemName);
        ItemDesc = (EditText) findViewById(R.id.ItemDesc);


    }

}
