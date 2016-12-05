/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipt.beans;

import com.ipt.dao.AdminUserDAO;
import com.ipt.utils.LoginSession;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sabrine
 */
@ManagedBean(name = "AdminUserBean")
@SessionScoped

public class AdminUserBean implements Serializable {

    private String password;
    private String password2;
    private final String userName = "Admin";
    private final String emailAddress = "projectmpsrt@gmail.com";
    private String emailPassword ;

    public String getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }

   

    public String getPassword() {
        return password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getUserName() {
        return userName;
    }

    public String loginProject() {
        boolean result = AdminUserDAO.login(password);
        if (result) {
            HttpSession session = LoginSession.getSession();
            session.setAttribute("AdminName", userName);

            return "/serverInfo/List";

        } else {

            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Invalid Login!",
                            "Please Try Again!"));

            return "login";

        }
    }

    public String changePassword() {

        boolean result = AdminUserDAO.changePassword(password);
        if (result) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Your Password was updated", ""));

            return "/serverInfo/List";

        } else {

            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Invalid Login!",
                            "Please Try Again!"));

            return "/serverInfo/List";

        }
    }

    public String changeEmailPassword() {

        boolean result = AdminUserDAO.changeEmailPassword(password);
        if (result) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Your Email Password was updated", ""));

            return "/serverInfo/List";

        } else {

            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Invalid Login!",
                            "Please Try Again!"));

            return "/serverInfo/List";

        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        HttpSession session = LoginSession.getSession();
        session.invalidate();
        return "login ";
    }
}
