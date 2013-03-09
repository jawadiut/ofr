package com.ofr.test;


import com.thoughtworks.selenium.DefaultSelenium;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.jboss.arquillian.drone.api.annotation.Drone;

import java.io.File;
import java.net.URL;

/**
* Created with IntelliJ IDEA.
* User: imon
* Date: 3/4/13
* Time: 1:27 PM
* To change this template use File | Settings | File Templates.
*/
@RunWith(Arquillian.class)
public class LoginScreenSeleniumTest  {
    private static final String WEBAPP_SRC = "src/test/webapp";

    @Deployment(testable = false)
    public static WebArchive createDeployment() {

        MavenDependencyResolver resolver = DependencyResolvers
                .use(MavenDependencyResolver.class)
                .loadMetadataFromPom("pom.xml");

        return ShrinkWrap.create(WebArchive.class, "login.war")
                .addClasses(UserTest.class, Credentials.class ,LoginController.class)
                .addAsWebResource(new File(WEBAPP_SRC, "logintest.xhtml"))
                .addAsWebResource(new File(WEBAPP_SRC, "hometest.xhtml"))
                .addAsLibraries(resolver
                        .artifact("org.primefaces:primefaces")
                        .resolveAsFiles())
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource(
                        new StringAsset("<faces-config version=\"2.0\"/>"),
                        "faces-config.xml");
    }

    @Drone
    DefaultSelenium browser;

    @ArquillianResource
    URL deploymentUrl;

    @Test
    public void should_login_successfully() {
        browser.open(deploymentUrl + "logintest.jsf");

        browser.type("id=loginForm:username", "demo");
        browser.type("id=loginForm:password", "demo");
        browser.click("id=loginForm:login");

        browser.waitForPageToLoad("15000");


//        Assert.assertTrue("User should be logged in!",
//                browser.isElementPresent("xpath=//li[contains(text(), 'Welcome')]"));
        Assert.assertTrue("Username should be shown!",
                browser.isElementPresent("xpath=//p[contains(text(), 'You are signed in as demo.')]"));

        }
}
