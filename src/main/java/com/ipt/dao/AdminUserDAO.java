/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipt.dao;

import com.ipt.utils.UpdateServerInfoTable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sabrine
 */
public class AdminUserDAO {

    public Statement stmt = null;
    public String sqlCheck = "";
    public ResultSet rs = null;

    public static boolean login(String password) {
        try {
            ConnectMe connectMe = new ConnectMe();
            String sqlSelect = "SELECT password from AdminTable";
            Statement stmt = connectMe.connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlSelect);
            if (rs.next()) {
                System.out.println(rs.getString("password"));
                return true;
            } else {
                return false;
            }

        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            return false;
        }

    }

    public static boolean changePassword(String newPwd) {

        try {
            ConnectMe connectMe = new ConnectMe();
            String sqlUpdate = "UPDATE AdminTable SET password = '" + newPwd + "'";
            PreparedStatement preStmt = connectMe.connection.prepareStatement(sqlUpdate);
            int rs = preStmt.executeUpdate();
            if (rs > 0) {
                System.out.println(newPwd);
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminUserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        }
    }

    public static boolean changeEmailPassword(String newEmailPassword) {

        try {
            ConnectMe connectMe = new ConnectMe();
            String sqlUpdate = "UPDATE AdminTable SET emailPassword = '" + newEmailPassword + "'";
            PreparedStatement preStmt = connectMe.connection.prepareStatement(sqlUpdate);
            int rs = preStmt.executeUpdate();
            if (rs > 0) {
                System.out.println(newEmailPassword);
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminUserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        }
    }

    public String getEmailAddress() throws SQLException {

        String emailAddress = "";
        try {

            ConnectMe connectMe = new ConnectMe();

            sqlCheck = "SELECT emailAddress from AdminTable";
            stmt = connectMe.connection.createStatement();
            rs = stmt.executeQuery(sqlCheck);
            if (rs.next()) {
                emailAddress = rs.getString("emailAddress");
            }
            return emailAddress;

        } catch (SQLException ex) {
            Logger.getLogger(UpdateServerInfoTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return emailAddress;

    }
    
    public String getEmailPassword() throws SQLException {

        String emailPassword = "";
        try {

            ConnectMe connectMe = new ConnectMe();

            sqlCheck = "SELECT emailPassword from AdminTable";
            stmt = connectMe.connection.createStatement();
            rs = stmt.executeQuery(sqlCheck);
            if (rs.next()) {
                emailPassword = rs.getString("emailPassword");
            }
            return  emailPassword;

        } catch (SQLException ex) {
            Logger.getLogger(UpdateServerInfoTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  emailPassword;

    }

}
