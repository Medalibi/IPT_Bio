/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipt.beans;

import java.io.Serializable;

/**
 *
 * @author sabrine
 */

public class ServerBean implements Serializable {

    private static final long serialVersionUID = 1L;

    public float usedRam = 0;
    public float freeRam = 0;
    public String serverName = "";
    public float cpuLoad = 0;
    public String IpAddress = "";

    public float getUsedRam() {
        return usedRam;
    }

    public void setUsedRam(float usedRam) {
        this.usedRam = usedRam;
    }

    public float getFreeRam() {
        return (100 - this.usedRam);
    }

    public void setFreeRam() {
        this.freeRam = 100 - this.usedRam;
    }

    public String getServerName() {
        return this.serverName;
    }

    public void setServerName(String serverName) {
        this.serverName =  serverName;
    }

    public String getIPAddress() {
        return this.IpAddress;
    }

    public void setIPAddress(String IPaddress) {
        this.IpAddress = IPaddress;
    }

    public float getCPULoad() {
        return this.cpuLoad;
    }

    public void setCPULoad(float cpuload) {
        this.cpuLoad = cpuload;
    }
}
