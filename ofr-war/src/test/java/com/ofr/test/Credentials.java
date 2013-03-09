package com.ofr.test;

import javax.enterprise.inject.Model;
import java.io.Serializable;

/**
* Created with IntelliJ IDEA.
* User: imon
* Date: 3/4/13
* Time: 5:07 PM
* To change this template use File | Settings | File Templates.
*/
@Model
public class Credentials implements Serializable{
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}