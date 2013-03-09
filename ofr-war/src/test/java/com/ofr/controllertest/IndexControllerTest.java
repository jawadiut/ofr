package com.ofr.controllertest;

import com.ofr.controller.IndexController;
import com.ofr.ejb.dao.UserDao;
import com.ofr.entities.User;
import junit.framework.Assert;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


/**
 * Created with IntelliJ IDEA.
 * User: imon
 * Date: 3/6/13
 * Time: 12:38 PM
 * To change this template use File | Settings | File Templates.
 */


public class IndexControllerTest {

    IndexController indexController;
    User user1, user2;
    UserDao userDao;
    FacesContext facesContext;
    HttpSession httpSession;
    ExternalContext externalContext;

    @Before
    public void init() {
        userDao = EasyMock.createMock(UserDao.class);
        externalContext = EasyMock.createMock(ExternalContext.class);
        facesContext = ContextMocker.mockFacesContext();
        httpSession = EasyMock.createMock(HttpSession.class);
        user1 = new User();
        user1.setEmail("a@a.com");
        user1.setPassword("a");

        user2 = new User();
        user2.setUserId(1);
        user2.setRole("user");

        indexController = new IndexController();
        indexController.setUserDao(userDao);
        indexController.setUser(user1);
    }

    @Test
    public void loginTest() {

        EasyMock.expect(userDao.verifyUser(user1)).andReturn(user2);
        EasyMock.replay(userDao);

        EasyMock.expect(facesContext.getExternalContext()).andReturn(externalContext);
        EasyMock.replay(facesContext);


        EasyMock.expect(externalContext.getSession(true)).andReturn(httpSession);
        EasyMock.replay(externalContext);

        httpSession.setAttribute("authenticatedUserId", user2.getUserId());
        httpSession.setAttribute("authenticatedUserRole", user2.getRole());

        EasyMock.replay(httpSession);

        String response = indexController.logIn();

        Assert.assertEquals(response, "log in successful");

    }

}
