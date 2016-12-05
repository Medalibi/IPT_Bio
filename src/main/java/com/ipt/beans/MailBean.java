/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipt.beans;

import com.ipt.dao.AdminUserDAO;
import com.ipt.utils.LoginSession;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Properties;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sabrine
 */
@ManagedBean(name = "MailBean")

public class MailBean implements Serializable {

    private String text;

    public String getText() {
   
        return this.text;

    }

    public void setText(String text) throws SQLException {
        HttpSession httpSession = LoginSession.getSession();
        String senderName = (String) httpSession.getAttribute("SessionName");
        this.text = text;
        System.out.println(this.text);
        AdminUserDAO adminUser = new AdminUserDAO();
        final String mailaddress = adminUser.getEmailAddress();
        final String mailpassword = adminUser.getEmailPassword();
        String mailfrom = mailaddress;
        String mailto = mailaddress;
        String subject = "The user  "+ senderName + " request";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(mailaddress, mailpassword);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailfrom));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(mailto));
            message.setSubject(subject);

            message.setText(this.text);

            Transport.send(message);

            System.out.println("Mail sent");
            
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
  
    public void up(ActionEvent actionEvent) {  
        FacesContext context = FacesContext.getCurrentInstance();  
          
        context.addMessage(null, new FacesMessage("Your Message was sent", "Thank you for your Help"));    
    }  

    }

