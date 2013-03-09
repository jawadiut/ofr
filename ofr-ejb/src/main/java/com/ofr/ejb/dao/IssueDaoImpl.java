package com.ofr.ejb.dao;

import com.ofr.entities.Issue;
import com.ofr.entities.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: imon
 * Date: 2/22/13
 * Time: 12:02 PM
 * To change this template use File | Settings | File Templates.
 */

@Stateless
public class IssueDaoImpl implements IssueDao {

    protected final Log log = LogFactory.getLog(getClass());
    @PersistenceContext
    EntityManager em;

    @Override
    public List<Issue> getRecentIssue() {
        return (List<Issue>) em.createQuery(
                "SELECT i FROM Issue i WHERE i.status LIKE :active")
                .setParameter("active", "active")
                .getResultList();
    }

    @Override
    public void saveIssue(int userId, Issue issue) {
        System.out.println("in saveIssue ");
        User user = em.find(User.class, userId);
        System.out.println("get issue list " + user.getIssueList().size());
        user.getIssueList().add(issue);
        em.persist(user);
    }

    @Override
    public List<Issue> getPendingIssues() {
        return em.createQuery(
                "SELECT i FROM Issue i WHERE i.status LIKE :pending")
                .setParameter("pending", "pending")
                .getResultList();
    }

    @Override
    public List<Issue> getActiveIssues() {
        return em.createQuery(
                "SELECT i FROM Issue i WHERE i.status LIKE :active")
                .setParameter("active", "active")
                .getResultList();

    }

    @Override
    public List<Issue> getClosedIssues() {
        return em.createQuery(
                "SELECT i FROM Issue i WHERE i.status LIKE :close")
                .setParameter("close", "close")
                .getResultList();
    }

    @Override
    public Issue findIssue(int issueId) {
        return em.find(Issue.class, issueId);
    }

    @Override
    public void activateIssue(int issueId) {
        Issue issue = findIssue(issueId);
        issue.setStatus("active");
        em.merge(issue);

    }

    @Override
    public void closeIssue(int issueId) {
        Issue issue = findIssue(issueId);
        issue.setStatus("close");
        em.merge(issue);
    }

    @Override
    public Issue getIssue(int issueId) {
        return em.find(Issue.class, issueId);
    }

    @Override
    public List<Issue> searchActiveIssueByTitle(String issueTitle) {
        return em.createQuery(
                "SELECT i FROM Issue i WHERE i.title LIKE :title AND i.status like :active")
                .setParameter("title", "%"+issueTitle+"%")
                .setParameter("active","active")
                .getResultList();
    }
}
