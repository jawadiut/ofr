package com.ofr.ejb.dao;

import com.ofr.entities.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * Created with IntelliJ IDEA.
 * User: imon
 * Date: 2/17/13
 * Time: 4:55 PM
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class UserDaoImpl implements UserDao {

    protected final Log log = LogFactory.getLog(getClass());

    @PersistenceContext(unitName = "persistDB")
    EntityManager em;

    @Override
    public void saveUser(User user) {
        em.persist(user);
    }

    @Override
    public User verifyUser(User user) {
        User userFound;
        try{
            userFound = (User) em.createQuery(
                    "SELECT u FROM User u WHERE u.email LIKE :email and  u.password LIKE :password")
                    .setParameter("email", user.getEmail())
                    .setParameter("password", user.getPassword())
                    .getSingleResult();

        }catch (NoResultException ex) {
            userFound = null;
        }
        return userFound;
    }

    @Override
    public User getUserWithIssueList(int userId) {
        User user;
        try {
            user = (User) em.createQuery("SELECT u FROM User u JOIN FETCH u.issueList WHERE u.userId=:userId ORDER BY u.userId DESC")
                    .setParameter("userId", userId).getSingleResult();

        } catch (NoResultException ex) {
            user = em.find(User.class, userId);
            user.getIssueList().size();
        }
        return user;
    }

    @Override
    public User findUser(Integer userId) {
        return em.find(User.class, userId);
    }

    @Override
    public User getUserFromEmail(User user) {
        return (User) em.createQuery("SELECT u FROM User u WHERE u.email LIKE :email")
                .setParameter("email", user.getEmail())
                .getSingleResult();
    }

}
