package com.ofr.controller;

import com.ofr.ejb.dao.IssueDao;
import com.ofr.entities.Issue;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: imon
 * Date: 2/24/13
 * Time: 12:24 PM
 * To change this template use File | Settings | File Templates.
 */
@Named
@RequestScoped
public class ActiveIssueController {

    private List<Issue> activeIssues;

    public List<Issue> getActiveIssues() {
        return activeIssues;
    }

    public void setActiveIssues(List<Issue> activeIssues) {
        this.activeIssues = activeIssues;
    }

    @EJB
    IssueDao issueDao;

    @PostConstruct
    public void init(){
        activeIssues = issueDao.getActiveIssues();
    }

    public String closeIssue(int issueId){
        issueDao.closeIssue(issueId);
        return "issue closed";
    }
}
