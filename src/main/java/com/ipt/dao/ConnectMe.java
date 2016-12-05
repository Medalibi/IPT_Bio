/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipt.dao;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author sabrine
 */
public class ConnectMe {

    public Connection connection = null;

    public ConnectMe() {

        System.out.println("-------- MySQL JDBC Connection Testing ------------");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }

        System.out.println("MySQL JDBC Driver Registered!");

        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/IPTBioInfoDB", "root", "root");

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
    }

    public void CloseCon() {
        try {
            connection.close();
        } catch (Exception ex) {
        }
    }
}
