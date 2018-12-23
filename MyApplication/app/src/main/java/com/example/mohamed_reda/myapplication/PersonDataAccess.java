package com.example.mohamed_reda.myapplication;

public class PersonDataAccess {

    //samy
    public String getPerson(String userid,String password)
    {
        String query = "select * from Person where Email='" + userid + "' and Password='" + password + "'";
        return query;
    }

    public String setPerson(String userid,String password,String Fname,String  Lname,String phoneN,String NID)
    {
        String query = "insert into Person values('"+userid+"','"+Fname+" "+Lname+"','"+password+"','"+NID+"','"+phoneN+"',"+0+",null)";
                                //"select * from Person where Email='" + userid + "' and Password='" + password + "'";
        return query;
    }
}
