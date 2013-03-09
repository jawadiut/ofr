package com.ofr.test;


import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;


/**
* Created with IntelliJ IDEA.
* User: imon
* Date: 3/4/13
* Time: 2:35 PM
* To change this template use File | Settings | File Templates.
*/

@Named
@SessionScoped
public class LoginController implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String SUCCESS_MESSAGE = "Welcome";
    private static final String FAILURE_MESSAGE =
            "Incorrect username and password combination";

    private UserTest currentUser;

    @Inject
    private Credentials credentials;

    public String login() {
        if ("demo".equals(credentials.getUsername()) &&
                "demo".equals(credentials.getPassword())) {
            currentUser = new UserTest("demo");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(SUCCESS_MESSAGE));
            return "hometest.xhtml";
        }

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN,
                        FAILURE_MESSAGE, FAILURE_MESSAGE));
        return null;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    @Produces
    @Named
    public UserTest getCurrentUser() {
        return currentUser;
    }
}
