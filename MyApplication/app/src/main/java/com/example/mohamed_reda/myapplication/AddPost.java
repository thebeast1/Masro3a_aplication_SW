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

        next = (TextView) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostService postService = new PostService();
                postService.execute("The data is saved correctly");

            }
        });


    }
    public class PostService extends AsyncTask<String,String,String>
    {
        String z = "";
        Boolean isSuccess = false;


        String Itemname = ItemName.getText().toString();
        String Itemdesc = ItemDesc.getText().toString();

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(String r) {

            if(isSuccess) {
                Toast.makeText(AddPost.this,r,Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(AddPost.this,AddQ.class);
                startActivity(intent);
            }
        }

        @Override
        protected String doInBackground(String... params) {

            if(Itemname.trim().equals("")|| Itemdesc.trim().equals(""))
                z = "Please enter the name of the item and the description";
            else
            {
                try {

                    Connection Con =connection.CONNecting();

                    if (Con == null) {
                        z = "Error in connection with SQL server";
                    } else {
                        ItemDataAccess Item = new ItemDataAccess();
                        String query = Item.setItem(MainActivity.P_ID,Itemname,Itemdesc);

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
