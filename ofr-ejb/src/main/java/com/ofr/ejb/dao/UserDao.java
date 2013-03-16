package com.ofr.ejb.dao;

import com.ofr.entities.Issue;
import com.ofr.entities.User;

import javax.ejb.Local;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: imon
 * Date: 2/19/13
 * Time: 11:22 AM
 * To change this template use File | Settings | File Templates.
 */
@Local
public interface UserDao {

    public void saveUser(User user);

    public User verifyUser(User user);

    public User getUserWithIssueList(int userId);

    public User findUser(Integer userId);

    public User getUserFromEmail(User user);

    public User getUserWithDonationList(int userId);

}
