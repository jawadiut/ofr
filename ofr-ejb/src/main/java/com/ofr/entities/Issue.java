package com.ofr.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


/**
 * Created with IntelliJ IDEA.
 * User: imon
 * Date: 2/17/13
 * Time: 4:49 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Issue implements Serializable {
    @Id
    @GeneratedValue
    private int issueId;
    @NotNull
    private String title;
    @NotNull
    private String helpFor;
    @NotNull
    private String description;
    @NotNull
    private String status;
    @NotNull
    private int requiredAmount;
    @NotNull
    private int collectedAmount;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getCollectedAmount() {
        return collectedAmount;
    }

    public void setCollectedAmount(int collectedAmount) {
        this.collectedAmount = collectedAmount;
    }

    public int getRequiredAmount() {
        return requiredAmount;
    }

    public void setRequiredAmount(int requiredAmount) {
        this.requiredAmount = requiredAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHelpFor() {
        return helpFor;
    }

    public void setHelpFor(String helpFor) {
        this.helpFor = helpFor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }
}
