/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipt.beans;

import com.ipt.dao.TraitementDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author sabrine
 */
@Named("TraitementBean")
@SessionScoped
public class TraitementBean  implements Serializable {
    
    int idTraitement;
    String nameTraitement;
    String dcpTraitement;
    int nbOfInputFiles;
    List<TraitmentOptions> selectedTraitementOptionList ; 
    List<TraitmentOptions> traitementOptionList ; 


    public int getIdTraitement() {
        return idTraitement;
    }

    public void setIdTraitement(int idTraitement) {
        this.idTraitement = idTraitement;
    }

    public String getNameTraitement() {
        return nameTraitement;
    }

    public void setNameTraitement(String nameTraitement) {
        this.nameTraitement = nameTraitement;
    }

    public String getDcpTraitement() {
        return dcpTraitement;
    }

    public void setDcpTraitement(String scpTraitement) {
        this.dcpTraitement = scpTraitement;
    }

    public int getNbOfInputFiles() {
        return nbOfInputFiles;
    }

    public void setNbOfInputFiles(int nbOfInputFiles) {
        this.nbOfInputFiles = nbOfInputFiles;
    }

    public List<TraitmentOptions> getSelectedTraitementOptionList() {
        return this.selectedTraitementOptionList;
    }

    public void setSelectedTraitementOptionList( List<TraitmentOptions> selectedTraitementOptionList ) {
        this.selectedTraitementOptionList =  selectedTraitementOptionList;
    }
    public List<TraitmentOptions> getTraitementOptionList() {
        return this.selectedTraitementOptionList;
    }
     public void setTraitementOptionList() {
        TraitementDAO traitement = new TraitementDAO();
        this.traitementOptionList= traitement.getTraitementOptionList(idTraitement);
    }
    
    
}
