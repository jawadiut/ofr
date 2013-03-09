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
 * Time: 4:48 PM
 * To change this template use File | Settings | File Templates.
 */
@Named
@RequestScoped
public class ClosedIssueController {

    private List<Issue> closedIssueList;

    @EJB
    IssueDao issueDao;

    public List<Issue> getClosedIssueList() {
        return closedIssueList;
    }

    public void setClosedIssueList(List<Issue> closedIssueList) {
        this.closedIssueList = closedIssueList;
    }

    @PostConstruct
    public void init(){
        closedIssueList = issueDao.getClosedIssues();
    }

    public String activateClosedIssue(int issueId){
        issueDao.activateIssue(issueId);
        return "closed issue activated";
    }
}
