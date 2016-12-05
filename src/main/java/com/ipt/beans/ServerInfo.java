/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipt.beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sabrine
 */
@Entity
@Table(name = "ServerInfo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServerInfo.findAll", query = "SELECT s FROM ServerInfo s"),
    @NamedQuery(name = "ServerInfo.findByServerName", query = "SELECT s FROM ServerInfo s WHERE s.serverName = :serverName"),
    @NamedQuery(name = "ServerInfo.findByCPULoad", query = "SELECT s FROM ServerInfo s WHERE s.cPULoad = :cPULoad"),
    @NamedQuery(name = "ServerInfo.findByUsedRam", query = "SELECT s FROM ServerInfo s WHERE s.usedRam = :usedRam"),
    @NamedQuery(name = "ServerInfo.findByIPAddress", query = "SELECT s FROM ServerInfo s WHERE s.iPAddress = :iPAddress")})
public class ServerInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "serverName")
    private String serverName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CPULoad")
    private Float cPULoad;
    @Column(name = "usedRam")
    private Float usedRam;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "IPAddress")
    private String iPAddress;

    public ServerInfo() {
    }

    public ServerInfo(String serverName) {
        this.serverName = serverName;
    }

    public ServerInfo(String serverName, String iPAddress) {
        this.serverName = serverName;
        this.iPAddress = iPAddress;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public Float getCPULoad() {
        return cPULoad;
    }

    public void setCPULoad(Float cPULoad) {
        this.cPULoad = cPULoad;
    }

    public Float getUsedRam() {
        return usedRam;
    }

    public void setUsedRam(Float usedRam) {
        this.usedRam = usedRam;
    }

    public String getIPAddress() {
        return iPAddress;
    }

    public void setIPAddress(String iPAddress) {
        this.iPAddress = iPAddress;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serverName != null ? serverName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServerInfo)) {
            return false;
        }
        ServerInfo other = (ServerInfo) object;
        if ((this.serverName == null && other.serverName != null) || (this.serverName != null && !this.serverName.equals(other.serverName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ipt.beans.ServerInfo[ serverName=" + serverName + " ]";
    }
    
}
