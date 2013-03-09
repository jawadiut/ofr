package com.ofr.controller;

import com.ofr.ejb.dao.UserDao;
import com.ofr.entities.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * User: imon
 * Date: 2/23/13
 * Time: 1:37 PM
 * To change this template use File | Settings | File Templates.
 */

@Named
@RequestScoped
public class AdminHomeController {

    protected final Log log = LogFactory.getLog(getClass());

    private User user;

    @EJB
    UserDao userDao;

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
        user = userDao.findUser((Integer)httpSession.getAttribute("authenticatedUserId"));
     }



    public String showPendingIssue(){
        return "pending issue";
    }

    public String showActiveIssue(){
        return "active issue";
    }

    public String showClosedIssue(){
        return "closed issue";
    }

    public String logOut() {
        System.out.println("in log out");
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) context.getExternalContext().getSession(false);
        httpSession.invalidate();
        return "log out";
    }
}
