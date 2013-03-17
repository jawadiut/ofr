package com.ofr.ejb.dao;

import com.ofr.entities.Issue;

import javax.ejb.Local;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: imon
 * Date: 2/22/13
 * Time: 11:53 AM
 * To change this template use File | Settings | File Templates.
 */
@Local
public interface IssueDao {

    public void updateIssue(Issue issue);

    public List<Issue> getRecentIssue();

    public void saveIssue(int userId, Issue issue);

    public List<Issue> getPendingIssues();

    public List<Issue> getActiveIssues();

    public List<Issue> getClosedIssues();

    public Issue findIssue(int issueId);

    public void activateIssue(int issueId);

    public void closeIssue(int issueId);

    public Issue getIssue(int issueId);

    public List<Issue> searchActiveIssueByTitle(String issueTitle);
}
