package com.example.womansafety;


public class HelperClass {
    String names ,phone_number,email,password,con_password;

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCon_password() {
        return con_password;
    }

    public void setCon_password(String con_password) {
        this.con_password = con_password;
    }

    public HelperClass(String names,String phone_number,String email,String password,String con_password)
    {

        this.names = names;
        this.phone_number = phone_number;
        this.email = email;
        this.password = password;
        this.con_password = con_password;
    }

}
