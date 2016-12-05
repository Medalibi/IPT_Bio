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
public class HardDiskBean  implements Serializable {
    
    private static final long serialVersionUID = 1L;

    public String serverName = "";
    public String HDname = "";
    public float usedSpace = 0;
    public String mountPoint = "";
    public float diskSpace = 0;
    
    
    public String getHDname() {
        return this.HDname;
    }

    public void setHDname(String hdname) {
        this.HDname = hdname;
    }

    ;
    public String getserverName() {
        return this.serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getMountPoint() {
        return this.mountPoint;
    }

    public void setMountPoint(String mountPoint) {
        this.mountPoint = mountPoint;
    }

    public float getUseSpace() {
        return this.usedSpace;
    }

    public void setUsedSpace(float usedSpace) {
        this.usedSpace = usedSpace;
    }
    
    public float getTotalSpace()
    {
        return this.diskSpace;
    }
    
    public void setTotalSpace(float diskSpace)
    {
        this.diskSpace = diskSpace;
    }

}
