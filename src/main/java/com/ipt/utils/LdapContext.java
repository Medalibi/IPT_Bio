/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipt.utils;

import java.util.Hashtable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

/**
 *
 * @author sabrine
 */

@ManagedBean(name = "user")
@SessionScoped
public class LdapContext {
    
     public DirContext ctxtDir = null;
    

    public DirContext LdapContextCreation(String uid , String LDAP_PASSWORD) throws NamingException {
        String LDAP_CONTEXT_FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";
        String LDAP_CNX_POOL = "com.sun.jndi.ldap.connect.pool";
        String LDAP_SERVER_URL = "ldap://192.168.8.100:389"; // connexion openLDAP
        String LDAP_BASE_DN = "dc=pasteur,dc=tn";
        String LDAP_AUTHENTICATION_MODE = "simple";
        String LDAP_REFERRAL_MODE = "follow";
        
        String LDAP_USER = "uid="+uid+",ou=people,dc=pasteur,dc=tn";
       

        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, LDAP_CONTEXT_FACTORY);
        env.put(Context.PROVIDER_URL, LDAP_SERVER_URL + "/" + LDAP_BASE_DN);
        env.put(Context.SECURITY_AUTHENTICATION, LDAP_AUTHENTICATION_MODE);
        env.put(Context.SECURITY_PRINCIPAL, LDAP_USER);
        env.put(Context.SECURITY_CREDENTIALS, LDAP_PASSWORD);
        env.put(Context.REFERRAL, LDAP_REFERRAL_MODE);

       
        // connexion LDAP
        ctxtDir = new InitialDirContext(env);
        System.out.println("InitialDirContext: ok\n");
        return (ctxtDir);
    }
}
