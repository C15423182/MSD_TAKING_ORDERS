package com.example.hn.assignmentfinal;


public class User
{
    private String username;
    private String name;
    private String password;
    private String email;
    private String business;

    //construct
    public User(String username, String name, String password, String email, String business)
    {
        this.username = username;
        this.name = name;
        this.password = password;
        this.email = email;
        this.business = business;
    }
    //getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

}
