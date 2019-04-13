package com.example.androideatit1.Model;

public class User
{
    private String Name;
    private String Password;

    public User() {}

    public User(String name, String password)
    {
        this.Name = name;
        this.Password = password;
    }

    public String getName()
    {
        return Name;
    }

    public void setName(String name)
    {
        this.Name=name;
    }

    public String getPassword()
    {
        return this.Password;
    }

    public void setPassword(String password)
    {
        this.Password = password;
    }

}
