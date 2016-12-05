/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipt.dao;

import com.ipt.beans.ServerBean;
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
public class ServerDAO {

    public Statement stmt = null;
    public String sqlUpdate = "";
    public String sqlCheck = "";
    public String sqlInsert = "";
    public ResultSet rs = null;
    public ConnectMe connectMe = null;
    public UpdateServerInfoTable updateServerInfo = null;
    public ArrayList<String> IpAddressList;

    public ServerDAO() {

        connectMe = new ConnectMe();
        IpAddressList = new ArrayList<>();

    }
    
    public ArrayList<ServerBean> getServerList(){
         
        ArrayList<ServerBean> serverList = new ArrayList<>();
    
        try {
           
            sqlCheck= "SELECT * from ServerInfo" ;
            stmt = connectMe.connection.createStatement();
            rs = stmt.executeQuery(sqlCheck);
            while (rs.next()) {
                ServerBean server = new ServerBean();
                server.setCPULoad(rs.getFloat("CPULoad"));
                server.setUsedRam(rs.getFloat("usedRam"));
                server.setIPAddress(rs.getString("IPAddress"));
                server.setServerName(rs.getString("serverName"));
                server.setFreeRam();
                
                serverList.add(server);
                }
            
            return serverList;
        } catch (SQLException ex) {
            Logger.getLogger(ServerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
}
   public ServerBean getServerInfo(String NameServer){
        
       ServerBean server = new ServerBean();
         
        try {
           
            sqlCheck= "SELECT * from ServerInfo where serverName = '" + NameServer +"'" ;
            stmt = connectMe.connection.createStatement();
            rs = stmt.executeQuery(sqlCheck);
            while (rs.next()) {
               
                server.setServerName(NameServer);
                server.setCPULoad(rs.getFloat("CPULoad"));
                server.setUsedRam(rs.getFloat("usedRam"));
                server.setIPAddress(rs.getString("IPAddress"));
                server.setFreeRam();
                }
            
            return server;
        } catch (SQLException ex) {
            Logger.getLogger(ServerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
}  
    

    public ArrayList<String> getIpAddressListOfExistingServers() {
        try {
            sqlCheck = "SELECT IPAddress from ServerInfo";
            stmt = connectMe.connection.createStatement();
            rs = stmt.executeQuery(sqlCheck);
            while (rs.next()) {
                IpAddressList.add(rs.getString("IPAddress"));
            
            }

        } catch (SQLException ex) {
            Logger.getLogger(UpdateServerInfoTable.class.getName()).log(Level.SEVERE, null, ex);
        }

        return IpAddressList;
    }
    
     public ArrayList<String> getNameListOfExistingServers() {
        try {
            sqlCheck = "SELECT serverName from ServerInfo";
            stmt = connectMe.connection.createStatement();
            rs = stmt.executeQuery(sqlCheck);
            while (rs.next()) {
                IpAddressList.add(rs.getString("serverName"));
            
            }

        } catch (SQLException ex) {
            Logger.getLogger(UpdateServerInfoTable.class.getName()).log(Level.SEVERE, null, ex);
        }

        return IpAddressList;
    }

    public void ServerNameExist(String serverName, UpdateServerInfoTable updateServerInfo) {

        try {
            sqlUpdate = "UPDATE ServerInfo SET CPULoad = " + updateServerInfo.server.getCPULoad() + ",usedRam = " + updateServerInfo.server.getUsedRam() + " WHERE serverName= '" + serverName + "'";
            System.out.println(sqlUpdate);
            stmt = connectMe.connection.createStatement();
            stmt.executeUpdate(sqlUpdate);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateServerInfoTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ServerNameDoesNotExist(String serverName , UpdateServerInfoTable updateServerInfo) {

        try {
            sqlInsert = "INSERT INTO ServerInfo (serverName,CPULoad,usedRam,IPAddress) VALUES ('" + updateServerInfo.server.serverName + "'," + updateServerInfo.server.cpuLoad + "," + updateServerInfo.server.usedRam + ", '127.0.0.1')";
            stmt = connectMe.connection.createStatement();
            stmt.executeUpdate(sqlInsert);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateServerInfoTable.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean DoesServerNameExist(String serverName , UpdateServerInfoTable updateServerInfo) throws SQLException {

        try {
            sqlCheck = "SELECT * from ServerInfo WHERE serverName = '" + serverName + "'";
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

    public void DoYourJob() {
        
        ServerDAO s = new ServerDAO();

        IpAddressList = s.getIpAddressListOfExistingServers();

        for (int i = 0; i < IpAddressList.size(); i++) {
            try {
                UpdateServerInfoTable u = new UpdateServerInfoTable((IpAddressList.get(i)));
                if (s.DoesServerNameExist(u.server.getServerName(), u)) {
                    s.ServerNameExist(u.server.getServerName(), u);
                } else {
                    s.ServerNameDoesNotExist(u.server.getServerName() , u);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ServerDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
