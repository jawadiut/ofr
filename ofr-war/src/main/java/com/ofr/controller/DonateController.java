package com.ofr.controller;

import com.ofr.ejb.dao.DonationDao;
import com.ofr.ejb.dao.IssueDao;
import com.ofr.entities.Donation;
import com.ofr.entities.Issue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.Map;

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

    private int userId;

    private Issue issue;

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
        Map<String, String> params =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        int issueId = Integer.parseInt(params.get("issue"));

        issue = issueDao.getIssue(issueId);
    }

/*
    @EJB
    DonationDao donationDao;

    public void testDonationService(){
        Donation donation = new Donation();
        donation.setAmount(10000);
        donation.setIssueId(12);
        donation.setUserId(10);

        donationDao.saveDonation(donation);
    }
*/

}
