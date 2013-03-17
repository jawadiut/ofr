package com.ofr.ejb.dao;

import com.ofr.entities.Donation;
import com.ofr.entities.Issue;

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

    @Override
    public void saveDonation(Donation donation) {
        Issue issue = em.find(Issue.class, donation.getIssueId());
        donation.setIssueTitle(issue.getTitle());
        donation.setDonationDate(Calendar.getInstance().getTime());
        em.persist(donation);
    }
}
