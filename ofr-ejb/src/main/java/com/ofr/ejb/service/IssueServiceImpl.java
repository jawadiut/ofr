package com.ofr.ejb.service;

import com.ofr.ejb.dao.IssueDao;
import com.ofr.entities.Issue;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Created with IntelliJ IDEA.
 * User: imon
 * Date: 3/17/13
 * Time: 2:28 PM
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class IssueServiceImpl implements IssueService {

    @EJB
    IssueDao issueDao;


    @Override
    public void updateIssue(int issueId, int amount) {
        Issue issue = issueDao.getIssue(issueId);
        issue.setCollectedAmount(issue.getCollectedAmount()+amount);

        if(issue.getRequiredAmount()<= issue.getCollectedAmount()){
            issue.setStatus("close");
        }

        issueDao.updateIssue(issue);

    }
}
