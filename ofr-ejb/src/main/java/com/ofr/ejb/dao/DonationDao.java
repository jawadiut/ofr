package com.ofr.ejb.dao;

import com.ofr.entities.Donation;

import javax.ejb.Local;

/**
 * Created with IntelliJ IDEA.
 * User: imon
 * Date: 2/25/13
 * Time: 9:58 AM
 * To change this template use File | Settings | File Templates.
 */
@Local
public interface DonationDao {

    public void saveDonation(Donation donation);
}
