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
public class HardDiskDAO {

    public Statement stmt = null;
    public String sqlUpdate = "";
    public String sqlCheck = "";
    public String sqlInsert = "";
    public ResultSet rs = null;

    public UpdateHDInfoTable updateHDInfoTable = null;
    public ConnectMe connectMe = new ConnectMe();

    public ArrayList<HardDiskBean> listHardDisk = new ArrayList<>();
    public static ArrayList<String> IpAddressList;

    public HardDiskDAO() {

        updateHDInfoTable = new UpdateHDInfoTable();
        listHardDisk = updateHDInfoTable.getHardDiskList();

    }
    
   public ArrayList<HardDiskBean> getHardDiskList(String serverName)
   {
       ArrayList<HardDiskBean> hardDiskList = new  ArrayList<>();
        try {
            sqlCheck = "SELECT * from HDInfo WHERE serverName = '" + serverName + "'";
            stmt = connectMe.connection.createStatement();
            rs = stmt.executeQuery(sqlCheck);
            while (rs.next()) {
                HardDiskBean hd = new HardDiskBean();
                hd.setServerName(serverName);
                hd.setHDname(rs.getString("HDname"));
                hd.setMountPoint(rs.getString("mountPoint"));
                hd.setTotalSpace(rs.getFloat("diskSpace"));
                hd.setUsedSpace(rs.getFloat("usedSpace"));
                hardDiskList.add(hd);
                }
            

        } catch (SQLException ex) {
            Logger.getLogger(UpdateServerInfoTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hardDiskList;
    }

  

    public boolean DoesHDNameExist(String serverName, String HDname, int i) throws SQLException {

        try {
            sqlCheck = "SELECT * from HDInfo WHERE serverName = '" + serverName + "' AND HDname = '" + HDname + "'";
            stmt = connectMe.connection.createStatement();
            rs = stmt.executeQuery(sqlCheck);
            if (rs.next()) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UpdateServerInfoTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void HardDiskNameExist(String serverName, String HDname, int i) {

        try {
            sqlUpdate = "UPDATE HDInfo SET usedSpace = " + listHardDisk.get(i).usedSpace + " WHERE serverName= '" + serverName + "' AND HDname = '" + HDname + "'";
            stmt = connectMe.connection.createStatement();
            stmt.executeUpdate(sqlUpdate);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateServerInfoTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void HardDiskDoesNotExist(String serverName, String HDname, int i) {

        try {
            sqlInsert = "INSERT INTO HDInfo (HDname, usedSpace , mountPoint , serverName , diskSpace) VALUES ('" + HDname + "'," + listHardDisk.get(i).usedSpace + ", '" + listHardDisk.get(i).mountPoint + "' , '" + serverName + "' ," + listHardDisk.get(i).diskSpace + ")";
            System.out.println(sqlInsert);
            stmt = connectMe.connection.createStatement();
            stmt.executeUpdate(sqlInsert);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateServerInfoTable.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

 public static void main(String[] args) throws SQLException {

        HardDiskDAO hh = new HardDiskDAO();

        for (int i = 0; i < hh.listHardDisk.size(); i++) {

            String serverName = hh.listHardDisk.get(i).serverName;
            String HDname = hh.listHardDisk.get(i).HDname;
            if (hh.DoesHDNameExist(serverName, HDname, i)) {
                hh.HardDiskNameExist(serverName, HDname, i);
            } else {
                hh.HardDiskDoesNotExist(serverName, HDname, i);
            }

        }
    }
}
