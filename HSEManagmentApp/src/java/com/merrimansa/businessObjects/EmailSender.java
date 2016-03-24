/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimansa.businessObjects;

import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Steve
 */
public class EmailSender {

    private int port;
    private String host;
    private String from;
    private boolean auth;
    private String Username;
    private String password;
    private String protocol;
    private boolean debug;

    public EmailSender(int port, String host, String from, boolean auth, String Username, String password, String protocol, boolean debug) {
        this.port = port;
        this.host = host;
        this.from = from;
        this.auth = auth;
        this.Username = Username;
        this.password = password;
        this.protocol = protocol;
        this.debug = debug;
    }

    public void sendMail(String to, String subject, String body) {

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.starttls.enable", true);

        
        Authenticator authenticator = null;
        if (auth) {
            props.put("mail.smtp.auth", true);
            authenticator = new Authenticator() {
                private PasswordAuthentication pa = new PasswordAuthentication(Username, password);

                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    return pa;
                }
            };
        }
       

        Session session = Session.getInstance(props, authenticator);
        session.setDebug(debug);

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(this.from));
            InternetAddress[] address = {new InternetAddress(to)};
            message.setRecipients(Message.RecipientType.TO, address);
            message.setSubject(subject);
            message.setSentDate(new Date());
            message.setText(body);
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
