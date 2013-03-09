package com.ofr.test;

/**
 * Created with IntelliJ IDEA.
 * User: imon
 * Date: 3/4/13
 * Time: 5:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserTest {
    private String username;

    public UserTest() {}

    public UserTest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
