package com.ofr.ejb.service;

import com.ofr.entities.Issue;

import javax.ejb.Local;

/**
 * Created with IntelliJ IDEA.
 * User: imon
 * Date: 3/17/13
 * Time: 2:28 PM
 * To change this template use File | Settings | File Templates.
 */
@Local
public interface IssueService {

    public void updateIssue(int issueId, int amount);
}
