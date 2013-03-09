package com.ofr.controller;

import com.ofr.ejb.service.ForgotPasswordService;
import com.ofr.ejb.dao.UserDao;
import com.ofr.entities.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Created with IntelliJ IDEA.
 * User: imon
 * Date: 2/25/13
 * Time: 10:59 AM
 * To change this template use File | Settings | File Templates.
 */
@Named
@RequestScoped
public class ForgotPasswordController {

    protected final Log log = LogFactory.getLog(getClass());

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @EJB
    ForgotPasswordService forgotPasswordService;

    @EJB
    UserDao userDao;

    @PostConstruct
    public void init(){
        System.out.println("in forgot password init");
        user = new User();
    }

    public String requestForPassword(){
        User newUser = userDao.getUserFromEmail(user);
        System.out.println();
        System.out.println("after getting User : User Email : " + newUser.getEmail());
        forgotPasswordService.sendMail(newUser);
        return "password request sent";
    }

    public String cancelPasswordRequest(){
        System.out.println("in cancel");
        return "cancel";
    }
}
