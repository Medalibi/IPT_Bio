/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipt.beans;

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
public class ServerChoiceListBean {

    public String serverName;
    public String ipAddress;
  

    public ArrayList<ServerChoiceListBean> getServerNameList() {

        ArrayList<ServerChoiceListBean> serverChoiceList = new ArrayList<>();
          ConnectMe connectMe = new ConnectMe();

        try {
            String sqlSelect = "SELECT serverName , IPAddress from ServerInfo";
            Statement stmt = connectMe.connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlSelect);
            while (rs.next()) {
                ServerChoiceListBean serverChoiceListBean = new ServerChoiceListBean();
                serverChoiceListBean.ipAddress = rs.getString("IPAddress");
                serverChoiceListBean.serverName = rs.getString("serverName");
                serverChoiceList.add(serverChoiceListBean);

            }

        } catch (SQLException ex) {
            Logger.getLogger(UpdateServerInfoTable.class.getName()).log(Level.SEVERE, null, ex);
        }

        return serverChoiceList;
    }

}