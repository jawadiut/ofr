package com.ofr.controller;

import com.ofr.ejb.dao.UserDao;
import com.ofr.entities.User;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * User: imon
 * Date: 2/23/13
 * Time: 11:35 AM
 * To change this template use File | Settings | File Templates.
 */
@Named
@RequestScoped
public class MyContributionController {

    @EJB
    UserDao userDao;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) context.getExternalContext().getSession(false);

        user = userDao.getUserWithDonationList((Integer) httpSession.getAttribute("authenticatedUserId"));
    }


}
