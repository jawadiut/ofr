package com.ofr.ejb.service;


import com.ofr.entities.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Properties;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created with IntelliJ IDEA.
 * User: imon
 * Date: 2/25/13
 * Time: 11:31 AM
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class ForgotPasswordService {

    protected final Log log = LogFactory.getLog(getClass());

    public void sendMail(User user) {

        System.out.println("*****************************");

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("from email", "password");
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ofr.imon@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("to email"));
            message.setSubject("FundRaiser Password");
            message.setText("Dear " + user.getUserName() + "," +
                    "\n\n your password :" + user.getPassword());

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

