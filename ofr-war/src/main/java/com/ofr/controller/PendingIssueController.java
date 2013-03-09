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
 * Time: 12:47 PM
 * To change this template use File | Settings | File Templates.
 */
@Named
@RequestScoped
public class PendingIssueController {
    private List<Issue> pendingIssues;

    @EJB
    IssueDao issueDao;

    public List<Issue> getPendingIssues() {
        return pendingIssues;
    }

    public void setPendingIssues(List<Issue> pendingIssues) {
        this.pendingIssues = pendingIssues;
    }

    public String activateIssue(int issueId){
        issueDao.activateIssue(issueId);
        return "issue activated";
    }

    public String closeIssue(int issueId){
        issueDao.closeIssue(issueId);
        return "issue closed";
    }

    @PostConstruct
    public void init(){
        pendingIssues = issueDao.getPendingIssues();
    }
}
