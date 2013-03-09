package com.ofr.controller;

import com.ofr.ejb.dao.IssueDao;
import com.ofr.entities.Issue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Created with IntelliJ IDEA.
 * User: imon
 * Date: 2/26/13
 * Time: 11:16 AM
 * To change this template use File | Settings | File Templates.
 */
@Named
@RequestScoped
public class DonateController {

    protected final Log log = LogFactory.getLog(getClass());

    private Issue issue;
    private int userId;

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @EJB
    IssueDao issueDao;

    @PostConstruct
    public void init() {
//        Map<String, String> params =
//                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//        System.out.println("getting param "+params.get("issue"));
//        issue = issueDao.getIssue(Integer.parseInt(params.get("issue")));
//        userId = Integer.parseInt(params.get("user"));
    }


}
