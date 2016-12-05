/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipt.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sabrine
 */
@Entity
@Table(name = "Traitement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Traitement.findAll", query = "SELECT t FROM Traitement t"),
    @NamedQuery(name = "Traitement.findByIdTraitement", query = "SELECT t FROM Traitement t WHERE t.idTraitement = :idTraitement"),
    @NamedQuery(name = "Traitement.findByNameTraitement", query = "SELECT t FROM Traitement t WHERE t.nameTraitement = :nameTraitement"),
    @NamedQuery(name = "Traitement.findByNbrOfInputFile", query = "SELECT t FROM Traitement t WHERE t.nbrOfInputFile = :nbrOfInputFile")})
public class Traitement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTraitement")
    private Integer idTraitement;
    @Size(max = 70)
    @Column(name = "nameTraitement")
    private String nameTraitement;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "dscTraitement")
    private String dscTraitement;
    @Column(name = "nbrOfInputFile")
    private Integer nbrOfInputFile;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTraitement")
    private List<TraitmentOptions> traitmentOptionsList;

    public Traitement() {
    }

    public Traitement(Integer idTraitement) {
        this.idTraitement = idTraitement;
    }

    public Integer getIdTraitement() {
        return idTraitement;
    }

    public void setIdTraitement(Integer idTraitement) {
        this.idTraitement = idTraitement;
    }

    public String getNameTraitement() {
        return nameTraitement;
    }

    public void setNameTraitement(String nameTraitement) {
        this.nameTraitement = nameTraitement;
    }

    public String getDscTraitement() {
        return dscTraitement;
    }

    public void setDscTraitement(String dscTraitement) {
        this.dscTraitement = dscTraitement;
    }

    public Integer getNbrOfInputFile() {
        return nbrOfInputFile;
    }

    public void setNbrOfInputFile(Integer nbrOfInputFile) {
        this.nbrOfInputFile = nbrOfInputFile;
    }

    @XmlTransient
    public List<TraitmentOptions> getTraitmentOptionsList() {
        return traitmentOptionsList;
    }

    public void setTraitmentOptionsList(List<TraitmentOptions> traitmentOptionsList) {
        this.traitmentOptionsList = traitmentOptionsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTraitement != null ? idTraitement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Traitement)) {
            return false;
        }
        Traitement other = (Traitement) object;
        if ((this.idTraitement == null && other.idTraitement != null) || (this.idTraitement != null && !this.idTraitement.equals(other.idTraitement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nameTraitement;
    }
    
    
}
