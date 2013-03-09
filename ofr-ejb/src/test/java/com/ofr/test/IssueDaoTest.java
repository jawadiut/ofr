package com.ofr.test;

import com.ofr.entities.Issue;
import com.ofr.entities.User;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.*;
import java.util.Calendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: imon
 * Date: 3/3/13
 * Time: 12:22 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(Arquillian.class)
public class IssueDaoTest {
    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackages(true, User.class.getPackage(), Issue.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    EntityManager em;

    @Inject
    UserTransaction utx;

    private void insertUser() throws Exception {
        System.out.println("in Insert method ***********");
        utx.begin();
        em.joinTransaction();

        User user1 = new User();
        user1.setEmail("a@a.com");
        user1.setPassword("1");
        user1.setUserName("a");
        user1.setRole("user");
        user1.setNationalId("123");
        user1.setPermanentAddress("a");
        user1.setPresentAddress("a");
        user1.setPhoneNumber("123");

        em.persist(user1);

        User user2 = new User();
        user2.setEmail("b@b.com");
        user2.setPassword("2");
        user2.setUserName("b");
        user2.setRole("admin");
        user2.setNationalId("123");
        user2.setPermanentAddress("b");
        user2.setPresentAddress("b");
        user2.setPhoneNumber("123");

        em.persist(user2);

        utx.commit();
        em.clear();
    }

    public Issue preparePendingIssue() {

        Issue issue1 = new Issue();

        issue1.setTitle("xa");
        issue1.setStatus("pending");
        issue1.setHelpFor("x");
        issue1.setDescription("x");
        issue1.setRequiredAmount(100);
        issue1.setCollectedAmount(0);
        issue1.setCreatedDate(Calendar.getInstance().getTime());

        return issue1;
    }

    public Issue prepareActiveIssue() {

        Issue issue1 = new Issue();

        issue1.setTitle("zzzzzzabbbb");
        issue1.setStatus("active");
        issue1.setHelpFor("x");
        issue1.setDescription("x");
        issue1.setRequiredAmount(100);
        issue1.setCollectedAmount(0);
        issue1.setCreatedDate(Calendar.getInstance().getTime());

        return issue1;
    }

    public Issue prepareClosedIssue() {

        Issue issue1 = new Issue();

        issue1.setTitle("ax");
        issue1.setStatus("close");
        issue1.setHelpFor("x");
        issue1.setDescription("x");
        issue1.setRequiredAmount(100);
        issue1.setCollectedAmount(0);
        issue1.setCreatedDate(Calendar.getInstance().getTime());

        return issue1;
    }

    @Test
    @InSequence(1)
    public void saveIssueTest() throws Exception {
        System.out.println("in saveIssueTest **************");

        insertUser();

        utx.begin();

        User user = em.find(User.class, 1);
        user.getIssueList().add(preparePendingIssue());
        user.getIssueList().add(prepareActiveIssue());
        user.getIssueList().add(prepareClosedIssue());
        em.persist(user);

        User user1 = em.find(User.class,1);
        int size = user1.getIssueList().size();

        utx.commit();

        Assert.assertEquals(size, 3);
    }

    @Test
    @InSequence(2)
    public void getRecentIssueTest(){
        System.out.println("in getRecentIssueTest **************");
        List<Issue> issues = (List<Issue>) em.createQuery(
                "SELECT i FROM Issue i WHERE i.status LIKE :active")
                .setParameter("active", "active")
                .getResultList();
        Assert.assertEquals(1,issues.size());
    }

    @Test
    @InSequence(3)
    public void getPendingIssuesTest() {
        System.out.println("in getPendingIssuesTest **************");
        List<Issue> issues = em.createQuery(
                "SELECT i FROM Issue i WHERE i.status LIKE :pending")
                .setParameter("pending", "pending")
                .getResultList();

        Assert.assertEquals(1, issues.size());
    }

    @Test
    @InSequence(4)
    public void getClosedIssuesTest() {
        System.out.println("in getClosedIssuesTest **************");
        List<Issue> issueList =  em.createQuery(
                "SELECT i FROM Issue i WHERE i.status LIKE :close")
                .setParameter("close", "close")
                .getResultList();
        Assert.assertEquals(1,issueList.size());
    }

    @Test
    @InSequence(5)
    public void getActiveIssuesTest() {
        System.out.println("in getActiveIssuesTest **************");
        List<Issue> issueList =  em.createQuery(
                "SELECT i FROM Issue i WHERE i.status LIKE :active")
                .setParameter("active", "active")
                .getResultList();
        Assert.assertEquals(1,issueList.size());
    }

    @Test
    @InSequence(6)
    public void findIssueTest() {
        System.out.println("in findIssueTest **************");
        Issue issue = em.find(Issue.class, 3);
        Assert.assertEquals("close", issue.getStatus());
    }

    @Test
    @InSequence(7)
    public void activateIssueTest() throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        utx.begin();
        Issue issue = em.find(Issue.class, 3);
        issue.setStatus("active");
        em.merge(issue);

        List<Issue> issueList = em.createQuery(
                "SELECT i FROM Issue i WHERE i.status LIKE :active")
                .setParameter("active", "active")
                .getResultList();
        utx.commit();
        Assert.assertEquals(issueList.size(),2);

    }

    @Test
    @InSequence(8)
    public void closeIssue() throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        utx.begin();
        Issue issue = em.find(Issue.class, 3);
        issue.setStatus("close");
        em.merge(issue);

        List<Issue> issueList =  em.createQuery(
                "SELECT i FROM Issue i WHERE i.status LIKE :close")
                .setParameter("close", "close")
                .getResultList();
        utx.commit();
        Assert.assertEquals(1,issueList.size());
    }

    @Test
    @InSequence(9)
    public void getIssue() {
        Issue issue = em.find(Issue.class, 1);
        Assert.assertEquals("pending", issue.getStatus());
    }

    @Test
    @InSequence(10)
    public void searchIssueByTitle() {
        List<Issue> issueList = em.createQuery(
                "SELECT i FROM Issue i WHERE i.title LIKE :title AND i.status like :active")
                .setParameter("title", "%a%")
                .setParameter("active","active")
                .getResultList();
       Assert.assertEquals(issueList.size(),1);
    }

}
