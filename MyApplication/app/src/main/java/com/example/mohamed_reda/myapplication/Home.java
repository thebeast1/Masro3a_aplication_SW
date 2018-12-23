package com.example.mohamed_reda.myapplication;

import android.app.Application;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Home extends AppCompatActivity {

    ConnectionClass connectionClass = new ConnectionClass();
    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;
    ListView post_list;
    EditText search_content;
    Button search_button;

    ArrayList<Person> Users = new ArrayList<>();
    ArrayList<Item> Items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        search_button = (Button) findViewById(R.id.search_button);
        search_content = (EditText) findViewById(R.id.search_content);

        ownershipService doLogin = new ownershipService();
            doLogin.execute("");

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ownershipService doLogin = new ownershipService();
                doLogin.execute("");

            }
        });

        System.out.println("The ID: "+MainActivity.P_ID);
        dl = (DrawerLayout) findViewById(R.id.dl);
        abdt = new ActionBarDrawerToggle(this,dl,R.string.Open,R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);

        post_list = (ListView) findViewById(R.id.list_post);

        Person p = new Person();
        p.setName("Welcome to shahed app");

        Item item = new Item();
        item.setName("Wallet");
        item.setDesc("i found it in the store");
        Users.add(p);
        Items.add(item);


        dl.addDrawerListener(abdt);
        abdt.syncState();

        final NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if(id == R.id.home) {
                    search_content.setText("");
                    ownershipService doLogin = new ownershipService();
                    doLogin.execute("");

                }else if(id == R.id.profile)
                {

                }else if(id == R.id.addpost)
                {
                    Intent i = new Intent(getApplicationContext(),AddPost.class);
                    startActivity(i);
                }else if(id == R.id.post)
                {

                }else if(id == R.id.about)
                {

                }else if(id == R.id.exit)
                {
                }
                return true;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return abdt.onOptionsItemSelected(item) ||super.onOptionsItemSelected(item);
    }

    public class ownershipService extends AsyncTask<String,String,String>
    {
        String z = "";
        Boolean isSuccess = false;

        String search =search_content.getText().toString();

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(String r) {
            Toast.makeText(Home.this,r,Toast.LENGTH_SHORT).show();

            if(isSuccess) {
                post_list.setAdapter(new PostAdapter(Home.this,Items,Users));
            }

        }

        @Override
        protected String doInBackground(String... params) {

                try {
                    Connection con = connectionClass.CONNecting();

                    if (con == null) {
                        z = "Error in connection with SQL server";
                    } else {
                        ItemDataAccess item=new ItemDataAccess();
                        String query = item.getItems(search);
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        System.out.println("----------------------------------------------------------------");
                        Items.clear();
                        Users.clear();

                        while(rs.next())
                        {
                            Item item1 = new Item();
                            item1.setName(rs.getString("Name"));
                            item1.setDesc(rs.getString("Description"));
                            item1.setID(rs.getInt("ItemID"));

                            Person person = new Person();
                            person.setId(rs.getInt("PersonID"));


                            Users.add(person);
                            Items.add(item1);

                            isSuccess=true;
                            System.out.println("----------------------------------------------------555----------------");
                            System.out.println("The name of the Item: "+item1.getName()+" the id of the person is: "+person.getId()+ "the name: "+person.getName());

                        }
                        con.close();
                    }
                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    z = "Exceptions";
                }

            return z;
        }
    }
}