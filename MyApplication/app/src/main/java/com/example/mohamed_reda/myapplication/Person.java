package com.example.mohamed_reda.myapplication;

public class Person {
    int id;
    String Name;
    String Email;
    String Password;
    String NId;
    String PhoneNum;
    int[][] Pic;
    float Rate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getNId() {
        return NId;
    }

    public void setNId(String NId) {
        this.NId = NId;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }

    public int[][] getPic() {
        return Pic;
    }

    public void setPic(int[][] pic) {
        Pic = pic;
    }

    public float getRate() {
        return Rate;
    }

    public void setRate(float rate) {
        Rate = rate;
    }
}
