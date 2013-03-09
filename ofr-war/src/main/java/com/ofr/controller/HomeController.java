package com.ofr.controller;

import com.ofr.ejb.dao.IssueDao;
import com.ofr.ejb.dao.UserDao;
import com.ofr.entities.Issue;
import com.ofr.entities.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: imon
 * Date: 2/18/13
 * Time: 9:06 PM
 * To change this template use File | Settings | File Templates.
 */
@Named
@RequestScoped
public class HomeController {

    protected final Log log = LogFactory.getLog(getClass());

    private User user;

    private String searchText;

    private List<Issue> recentIssues;

    public List<Issue> getRecentIssues() {
        return recentIssues;
    }

    public void setRecentIssues(List<Issue> recentIssues) {
        this.recentIssues = recentIssues;
    }

    public User getUser() {

        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    @EJB
    UserDao userDao;

    @EJB
    IssueDao issueDao;

    @PostConstruct
    public void init() {
        System.out.println("in home init method");

        FacesContext context = FacesContext.getCurrentInstance();

        HttpSession httpSession = (HttpSession) context.getExternalContext().getSession(false);
        user = userDao.findUser((Integer) httpSession.getAttribute("authenticatedUserId"));

        recentIssues = issueDao.getRecentIssue();
        System.out.println("getting the user name from authenticatedUser :" + user.getUserName());
    }

    public String logOut() {
        System.out.println("in log out");
        FacesContext context = FacesContext.getCurrentInstance();

        HttpSession httpSession = (HttpSession) context.getExternalContext().getSession(false);
        httpSession.invalidate();
        return "log out";
    }

    public String home() {
        return "home";
    }

    public String requestIssue() {
        return "request issue";
    }

    public String myContribution() {
        return "my contribution";
    }

    public String myIssues() {
        return "my issues";
    }

    public String myProfile() {
        return "my profile";
    }

    public String donate() {
        return "donate";
    }

    public void searchIssue(){
        recentIssues = issueDao.searchActiveIssueByTitle(searchText);
    }
}
