package com.ofr.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: imon
 * Date: 2/17/13
 * Time: 4:49 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Donation {
    @Id
    @GeneratedValue
    private int donationId;

    @NotNull
    @Min(value = 1)
    private int amount;

    private String issueTitle;

    private int userId;

    private int issueId;


    @Temporal(TemporalType.DATE)
    private Date donationDate;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public Date getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(Date donationDate) {
        this.donationDate = donationDate;
    }

    public String getIssueTitle() {
        return issueTitle;
    }

    public void setIssueTitle(String issueTitle) {
        this.issueTitle = issueTitle;
    }

    public int getDonationId() {
        return donationId;
    }

    public void setDonationId(int donationId) {
        this.donationId = donationId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
