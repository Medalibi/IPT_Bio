/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipt.dao;

import com.ipt.dao.ConnectMe;
import com.ipt.utils.UpdateServerInfoTable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sabrine
 */
public class DiskChoiceListDAO {

    public ArrayList<String> getHardDiskList(String serverName) {

        ArrayList<String> hardDiskList = new ArrayList<>();
        ConnectMe connectMe = new ConnectMe();

        try {

            String sqlSelect = "SELECT mountPoint from HDInfo WHERE serverName = '" + serverName + "'";
            Statement stmt = connectMe.connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlSelect);
            while (rs.next()) {
                hardDiskList.add(rs.getString("mountPoint"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UpdateServerInfoTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hardDiskList;
    }
}
