package com.ofr.test;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.*;

import com.ofr.entities.Issue;
import com.ofr.entities.User;
import junit.framework.Assert;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created with IntelliJ IDEA.
 * User: imon
 * Date: 3/2/13
 * Time: 12:34 PM
 * To change this template use File | Settings | File Templates.
 */


@RunWith(Arquillian.class)
public class UserDaoTest {

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

    private void insertData() throws Exception {
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

        issue1.setTitle("x");
        issue1.setStatus("pending");
        issue1.setHelpFor("x");
        issue1.setDescription("x");
        issue1.setRequiredAmount(100);
        issue1.setCollectedAmount(0);
        issue1.setCreatedDate(Calendar.getInstance().getTime());

        return issue1;
    }

    @Test
    @InSequence(value = 1)
    public void verifyUserTest() throws Exception {
        System.out.println("in verifyUserTest **************");
        insertData();

        User user = (User) em.createQuery(
                "SELECT u FROM User u WHERE u.email LIKE :email and  u.password LIKE :password")
                .setParameter("email", "a@a.com")
                .setParameter("password", "1")
                .getSingleResult();
        Assert.assertEquals(user.getUserId(), 1);
    }

    @Test
    @InSequence(value = 2)
    public void findUserTest() {
        System.out.println("in findUserTest **************");
        User user = em.find(User.class, 1);
        Assert.assertEquals("a", user.getUserName());
    }

    @Test
    @InSequence(value = 3)
    public void getUserFromEmailTest() {
        System.out.println("in getUserFromEmailTest **************");
        User user = (User) em.createQuery("SELECT u FROM User u WHERE u.email LIKE :email")
                .setParameter("email", "a@a.com")
                .getSingleResult();
        Assert.assertEquals(user.getUserId(), 1);
    }

    @Test
    @InSequence(4)
    public void saveIssueTest() throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        System.out.println("in saveIssueTest **************");

        utx.begin();

        User user = em.find(User.class, 1);
        user.getIssueList().add(preparePendingIssue());

        em.persist(user);

        User user1 = em.find(User.class,1);
        int size = user1.getIssueList().size();

        utx.commit();

        Assert.assertEquals(size, 1);
    }

    @Test
    @InSequence(5)
    public void getUserWithIssueListTest() {
        System.out.println("in getUserWithIssueListTest **************");
        User user = (User) em.createQuery("SELECT u FROM User u JOIN FETCH u.issueList WHERE u.userId=:userId ORDER BY u.userId DESC")
                .setParameter("userId", 1).getSingleResult();
        int size = user.getIssueList().size();

        Assert.assertEquals(size, 1);

    }

}
