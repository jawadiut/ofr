package com.ofr.controller;

import com.ofr.ejb.dao.IssueDao;
import com.ofr.entities.Issue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: imon
 * Date: 2/21/13
 * Time: 2:58 PM
 * To change this template use File | Settings | File Templates.
 */
@Named
@RequestScoped
public class RequestIssueController {

    protected final Log log = LogFactory.getLog(getClass());

    private Issue issue;

    @EJB
    IssueDao issueDao;

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    @PostConstruct
    public void init() {
        issue = new Issue();
    }

    public String requestIssue() {

        System.out.println("in requestIssue method :");
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) context.getExternalContext().getSession(false);

        issue.setStatus("pending");
        issue.setCreatedDate(Calendar.getInstance().getTime());
        issue.setCollectedAmount(0);

        issueDao.saveIssue((Integer) httpSession.getAttribute("authenticatedUserId"), issue);

        return "request successful";
    }


}
