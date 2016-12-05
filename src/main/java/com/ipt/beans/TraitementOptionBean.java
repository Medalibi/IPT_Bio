/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipt.beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author sabrine
 */
@Named("TraitementOptionBean ")
@SessionScoped
public class TraitementOptionBean implements Serializable{
    
    private String nameOption ;
    private String typeOption ;
    private String dscOption ;
    private String argument ;
    private int idTraitement ;
    private int idTraitmentOption;

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    public String getNameOption() {
        return nameOption;
    }

    public void setNameOption(String nameOption) {
        this.nameOption = nameOption;
    }

    public String getTypeOption() {
        return typeOption;
    }

    public void setTypeOption(String typeOption) {
        this.typeOption = typeOption;
    }

    public String getDscOption() {
        return dscOption;
    }

    public void setDscOption(String dscOption) {
        this.dscOption = dscOption;
    }

    public int getIdTraitement() {
        return idTraitement;
    }

    public void setIdTraitement(int idTraitement) {
        this.idTraitement = idTraitement;
    }

    public int getIdTraitmentOption() {
        return idTraitmentOption;
    }

    public void setIdTraitmentOption(int idTraitmentOption) {
        this.idTraitmentOption = idTraitmentOption;
    }
    
    
    
}
