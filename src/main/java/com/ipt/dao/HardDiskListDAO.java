/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipt.dao;

import com.ipt.beans.HardDiskBean;
import com.ipt.utils.UpdateHDInfoTable;
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
public class HardDiskListDAO {

    public Statement stmt = null;
    public String sqlSelect = "";
    public ResultSet rs = null;
    public ArrayList<String> IpAddressList;

    public ConnectMe connectMe = new ConnectMe();

    public HardDiskListDAO() {

        IpAddressList = new ArrayList();
    }

    public ArrayList<String> getIpAddressListOfExistingServers() {
        try {

            sqlSelect = "SELECT IPAddress from ServerInfo";
            stmt = connectMe.connection.createStatement();
            rs = stmt.executeQuery(sqlSelect);
            while (rs.next()) {
                IpAddressList.add(rs.getString("IPAddress"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UpdateServerInfoTable.class.getName()).log(Level.SEVERE, null, ex);
        }

        return IpAddressList;
    }

    public void GetHardDiskListDAO() {

        IpAddressList = this.getIpAddressListOfExistingServers();

        for (int i = 0; i < IpAddressList.size(); i++) {

            HardDiskDAO hardDiskDAO = new HardDiskDAO();
            ArrayList<HardDiskBean> listHardDisk = new ArrayList<>();
            UpdateHDInfoTable updateHDInfoTable = new UpdateHDInfoTable();

            listHardDisk = updateHDInfoTable.getHardDiskList(IpAddressList.get(i));

            for (int j = 0; j < listHardDisk.size(); j++) {
                try {
                    String serverName = listHardDisk.get(j).serverName;
                    String HDname = listHardDisk.get(j).HDname;
                    if (hardDiskDAO.DoesHDNameExist(serverName, HDname, j)) {
                        hardDiskDAO.HardDiskNameExist(serverName, HDname, j);
                    } else {
                        hardDiskDAO.HardDiskDoesNotExist(serverName, HDname, j);
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(HardDiskDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void main(String[] args) throws SQLException {

        HardDiskListDAO hardDiskListDAO = new HardDiskListDAO();
        hardDiskListDAO.GetHardDiskListDAO();
    }

}
