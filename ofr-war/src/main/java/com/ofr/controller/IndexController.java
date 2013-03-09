package com.ofr.controller;

import com.ofr.ejb.dao.IssueDao;
import com.ofr.ejb.dao.UserDao;
import com.ofr.entities.Issue;
import com.ofr.entities.User;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Created with IntelliJ IDEA.
 * User: imon
 * Date: 2/18/13
 * Time: 11:19 AM
 * To change this template use File | Settings | File Templates.
 */

@Named
@RequestScoped
public class IndexController {

    protected final Log log = LogFactory.getLog(getClass());

    private User user;
    private List<Issue> issueList;
    private String searchKeyword;

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public List<Issue> getIssueList() {
        return issueList;
    }

    public void setIssueList(List<Issue> issueList) {
        this.issueList = issueList;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    @EJB
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @EJB
    private IssueDao issueDao;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @PostConstruct
    public void init() {
        System.out.println("in init method " + user);

        if (user == null) {
            user = new User();
        }
        issueList = issueDao.getRecentIssue();

    }

    public String saveUser() {
        System.out.println("save user: start");
        user.setRole("user");
        userDao.saveUser(user);
        System.out.println("save user: end");

        return "sign up successful";
    }

    public String logIn() {

        System.out.println("in Login  method");

        User authenticatedUser = userDao.verifyUser(user);

        if(authenticatedUser!=null)    {

            System.out.println("after getting data from data base:  " + authenticatedUser.getEmail());

            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            HttpSession httpSession = (HttpSession) externalContext.getSession(true);

            httpSession.setAttribute("authenticatedUserId", authenticatedUser.getUserId());
            httpSession.setAttribute("authenticatedUserRole", authenticatedUser.getRole());

            if (authenticatedUser.getRole().equals("admin")) {
                return "admin log in successful";
            } else {
                return "log in successful";
            }
        }
        return "error";
    }

    public void searchIssue() {
        issueList = issueDao.searchActiveIssueByTitle(searchKeyword);
    }

    public String donate() {
        return "donate";
    }
}
