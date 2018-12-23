package com.example.mohamed_reda.myapplication;


import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ItemDataAccess {

    //boshy
    public String getItems(String search) {
        String query = "";
        if (!search.isEmpty()) {
            query = "select * from Item where name like'%" + search + "%'";
        } else {
            query = "select * from Item";
        }
        return query;
    }

    public String setItem(int P_ID, String Itemname, String Itemdesc) {

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());

        System.out.println("Current time of the day using Date - 12 hour format: " + formattedDate);
        String query = "insert into Item values('" + Itemname + "','" + Itemdesc + "',null,'" + formattedDate + "'," + P_ID + ")";

        return query;
    }

}
