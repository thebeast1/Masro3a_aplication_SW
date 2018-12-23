package com.example.mohamed_reda.myapplication;

import java.sql.Date;

public class Item {
    int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    String Name;
    String Desc;
    Date Datte;
    int [][] Pic;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public Date getDatte() {
        return Datte;
    }

    public void setDatte(Date datte) {
        Datte = datte;
    }

    public int[][] getPic() {
        return Pic;
    }

    public void setPic(int[][] pic) {
        Pic = pic;
    }
}
