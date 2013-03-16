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

    private int issueId;
    private int userId;

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
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

    }


}
