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
                RegesterService2 doLogin = new RegesterService2();
                doLogin.execute("");

            }
        });

    }

    public class RegesterService2 extends AsyncTask<String,String,String>
    {
        String z = "";
        Boolean isSuccess = false;


        String userid = Remail.getText().toString();
        String password = Rpass.getText().toString();
        String Fname = Fn.getText().toString();
        String Lname = Ln.getText().toString();
        String phoneN = phone.getText().toString();
        String NID = Nid.getText().toString();

        @Override
        protected void onPreExecute() {
            pbbar2.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String r) {
            pbbar2.setVisibility(View.GONE);
            Toast.makeText(signup.this,r,Toast.LENGTH_SHORT).show();

            if(isSuccess) {
                Toast.makeText(signup.this,r,Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        }

        @Override
        protected String doInBackground(String... params) {

            if(userid.trim().equals("")|| password.trim().equals(""))
                z = "Please enter User Id and Password";
            else
            {
                try {

                    Connection Con =connectionClass2.CONNecting();

                    if (Con == null) {
                        z = "Error in connection with SQL server";
                    } else {
                        PersonDataAccess p = new PersonDataAccess();
                        String query = p.setPerson(userid,password,Fname,Lname,phoneN,NID);

                        PreparedStatement preparedStatement = Con.prepareStatement(query);
                        preparedStatement.executeUpdate();

                        Statement stmt = Con.createStatement();
                        z = "Added Successfully";

                        Con.close();
                        isSuccess=true;
                    }
                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    z = "Exceptions";
                }
            }
            return z;
        }
    }
}