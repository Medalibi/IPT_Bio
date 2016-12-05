/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipt.beans;

import com.ipt.utils.LdapContext;
import com.ipt.utils.LoginSession;
import java.io.Serializable;
import javax.naming.NamingException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sabrine
 */
@ManagedBean(name = "LoginUserBean")
@SessionScoped
public class LoginUserBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String password;
    private String message, uname;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String loginProject(String uname, String password) throws NamingException {

        LdapContext ldapContext = new LdapContext();
        ldapContext.LdapContextCreation(uname, password);
        if (ldapContext.ctxtDir != null) {
            HttpSession httpSession = LoginSession.getSession();
        httpSession.setAttribute("SessionName", uname);
            return "home";

        } else {

            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Invalid Login!",
                            "Please Try Again!"));
            return "login";

        }
    }

}
