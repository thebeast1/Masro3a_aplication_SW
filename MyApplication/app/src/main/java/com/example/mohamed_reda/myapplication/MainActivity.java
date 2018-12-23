package com.example.mohamed_reda.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    ConnectionClass connectionClass = new ConnectionClass();
    TextView create_Account;
    EditText email;
    EditText pass;
    ProgressBar pbbar;
    Button login;

    public static int P_ID=0;

    Person person = new Person();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        create_Account= (TextView) findViewById(R.id.create_one);
        login = (Button) findViewById(R.id.login);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pass);
        pbbar = (ProgressBar) findViewById(R.id.pbbar);
        pbbar.setVisibility(View.GONE);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegesterService  doLogin = new RegesterService();
                doLogin.execute("");
                //Intent intent =new Intent(v.getContext(),Home.class);
                //startActivity(intent);
            }
        });


        create_Account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(v.getContext(),signup.class);
                startActivity(intent);
            }
        });

    }


    public class RegesterService extends AsyncTask<String,String,String>
    {
        String z = "";
        Boolean isSuccess = false;


        String userid = email.getText().toString();
        String password = pass.getText().toString();


        @Override
        protected void onPreExecute() {
            pbbar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String r) {
            pbbar.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this,r,Toast.LENGTH_SHORT).show();

            if(isSuccess) {
                Toast.makeText(MainActivity.this,r,Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(getApplicationContext(),Home.class);
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
                    Connection con = connectionClass.CONNecting();

                    if (con == null) {
                        z = "Error in connection with SQL server";
                    } else {
                        PersonDataAccess p = new PersonDataAccess();
                        String query = p.getPerson(userid,password);//"select * from Person where Email='" + userid + "' and Password='" + password + "'";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        System.out.println("----------------------------------------------------------------");
                        if(rs.next())
                        {
                            person.setId(rs.getInt("PersonID"));
                            person.setName(rs.getString("Name"));
                            person.setEmail(rs.getString("Email"));
                            person.setPassword(rs.getString("Password"));
                            person.setNId(rs.getString("NID"));
                            person.setPhoneNum(rs.getString("PhoneNum"));
                            person.setRate(rs.getFloat("Rate"));

                            P_ID = person.getId();

                            z = "Login successfull";
                            isSuccess=true;
                            System.out.println("The name of the person: "+person.getName()+"the id is: "+person.getId()+" the rate: "+person.getRate());

                        }
                        else
                        {
                            z = "Invalid Credentials";
                            isSuccess = false;
                        }
                        con.close();
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
