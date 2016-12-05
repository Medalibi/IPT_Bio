/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipt.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sabrine
 */
@Entity

@Table(name = "TraitmentOptions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TraitmentOptions.findAll", query = "SELECT t FROM TraitmentOptions t"),
    @NamedQuery(name = "TraitmentOptions.findByNameOption", query = "SELECT t FROM TraitmentOptions t WHERE t.nameOption = :nameOption"),
    @NamedQuery(name = "TraitmentOptions.findByTypeOption", query = "SELECT t FROM TraitmentOptions t WHERE t.typeOption = :typeOption"),
    @NamedQuery(name = "TraitmentOptions.findByDscOption", query = "SELECT t FROM TraitmentOptions t WHERE t.dscOption = :dscOption"),
    @NamedQuery(name = "TraitmentOptions.findByIdTraitementOptions", query = "SELECT t FROM TraitmentOptions t WHERE t.idTraitementOptions = :idTraitementOptions")})
public class TraitmentOptions implements Serializable {
    private static final long serialVersionUID = 1L;
   
    
      
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nameOption")
    private String nameOption;
    @Size(max = 45)
    @Column(name = "typeOption")
    private String typeOption;
    @Size(max = 45)
    @Column(name = "dscOption")
    private String dscOption;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTraitementOptions")
    private Integer idTraitementOptions;
    @JoinColumn(name = "idTraitement", referencedColumnName = "idTraitement")
    @ManyToOne(optional = false)
    private Traitement idTraitement;

      
    public TraitmentOptions() {
     
    }

    public TraitmentOptions(Integer idTraitementOptions) {
        this.idTraitementOptions = idTraitementOptions;
    }

    public TraitmentOptions(Integer idTraitementOptions, String nameOption) {
        this.idTraitementOptions = idTraitementOptions;
        this.nameOption = nameOption;
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

    public Integer getIdTraitementOptions() {
        return idTraitementOptions;
    }

    public void setIdTraitementOptions(Integer idTraitementOptions) {
        this.idTraitementOptions = idTraitementOptions;
    }

    public Traitement getIdTraitement() {
        return idTraitement;
    }

    public void setIdTraitement(Traitement idTraitement) {
        this.idTraitement = idTraitement;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTraitementOptions != null ? idTraitementOptions.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TraitmentOptions)) {
            return false;
        }
        TraitmentOptions other = (TraitmentOptions) object;
        if ((this.idTraitementOptions == null && other.idTraitementOptions != null) || (this.idTraitementOptions != null && !this.idTraitementOptions.equals(other.idTraitementOptions))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  nameOption ;
    }
    
    
}
