package com.ofr.ejb.dao;

import com.ofr.ejb.service.IssueService;
import com.ofr.entities.Donation;
import com.ofr.entities.Issue;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: imon
 * Date: 2/25/13
 * Time: 9:59 AM
 * To change this template use File | Settings | File Templates.
 */

@Stateless
public class DonationDaoImpl implements DonationDao {

    @PersistenceContext
    EntityManager em;

    @EJB
    IssueDao issueDao;

    @EJB
    IssueService issueService;

    @Override
    public void saveDonation(Donation donation) {

        issueService.updateIssue(donation.getIssueId(), donation.getAmount());

        donation.setIssueTitle(issueDao.getIssue(donation.getIssueId()).getTitle());

        donation.setDonationDate(Calendar.getInstance().getTime());

        em.persist(donation);
    }
}
