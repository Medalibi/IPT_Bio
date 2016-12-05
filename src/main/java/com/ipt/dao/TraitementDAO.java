/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipt.dao;

import com.ipt.beans.TraitementBean;
import com.ipt.beans.TraitementOptionBean;
import java.io.Serializable;
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


public class TraitementDAO implements Serializable {

    public Statement stmt = null;
    public String sqlUpdate = "";
    public String sqlCheck = "";
    public String sqlInsert = "";
    public ResultSet rs = null;
    public ConnectMe connectMe = null;

    public TraitementDAO() {
        connectMe = new ConnectMe();
    }

    public ArrayList getTraitementList() {

        try {
            ArrayList<TraitementBean> traitementList = new ArrayList<>();
            sqlCheck = "SELECT * from Traitement";
            stmt = connectMe.connection.createStatement();
            rs = stmt.executeQuery(sqlCheck);
            while (rs.next()) {
                TraitementBean traitement = new TraitementBean();
                traitement.setIdTraitement(rs.getInt("idTraitement"));
                traitement.setNameTraitement(rs.getString("nameTraitement"));
                traitement.setDcpTraitement(rs.getString("dscTraitement"));
                traitement.setNbOfInputFiles(rs.getInt("nbrOfInputFile"));

                traitementList.add(traitement);
            }

            return traitementList;
        } catch (SQLException ex) {
            Logger.getLogger(TraitementDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public ArrayList getTraitementOptionList(int traitementId) {
        ArrayList<TraitementOptionBean> traitementOptionList = new ArrayList<>();
        try {
            sqlCheck = "SELECT * from Traitement where idTraitement = " + traitementId;
            stmt = connectMe.connection.createStatement();
            rs = stmt.executeQuery(sqlCheck);
            while (rs.next()) {
                TraitementOptionBean option = new TraitementOptionBean();
                option.setIdTraitement(traitementId);
                option.setNameOption(rs.getString("nameOption"));
                option.setDscOption(rs.getString("dscOption"));
                option.setIdTraitmentOption(rs.getInt("idTraitementOptions"));
                option.setTypeOption(rs.getString("typeOption"));

                traitementOptionList.add(option);
            }

            return traitementOptionList;
        } catch (SQLException ex) {
            Logger.getLogger(TraitementDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

}
