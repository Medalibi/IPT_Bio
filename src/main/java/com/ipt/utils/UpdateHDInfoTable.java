/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipt.utils;

import com.ipt.beans.HardDiskBean;
import java.util.ArrayList;

/**
 *
 * @author sabrine
 */
public class UpdateHDInfoTable {

    public String serverName = "";
    public JavaRunCommand javaRunCommand;

    String[] arrayHardDisk = null;
    HardDiskBean[] arrayHardDiskBean = null;
    ArrayList<HardDiskBean> listHardDisk = new ArrayList<>();

    public ArrayList<HardDiskBean> getHardDiskList() {

        javaRunCommand = new JavaRunCommand("localhost", "head -1 /etc/hostname");
        serverName = javaRunCommand.cmdResults.trim();

        javaRunCommand = new JavaRunCommand("localhost", "df -B G | nawk '/dev.*sd.*/ || /dev.*md.*/  {print $1,$2,$5,$6}'");

        String resultCMD = javaRunCommand.cmdResults;
        arrayHardDisk = resultCMD.split("/dev/");

        for (int i = 1; i < arrayHardDisk.length; i++) {
            String[] tmp = arrayHardDisk[i].split(" ");
            System.out.println(arrayHardDisk[i]);
            HardDiskBean hd = new HardDiskBean();
            hd.setServerName(serverName);
            hd.setHDname(tmp[0]);
            hd.setTotalSpace(Float.parseFloat(tmp[1].replace("G","")));
            hd.setMountPoint(tmp[3]);
            hd.setUsedSpace(Float.parseFloat(tmp[2].replace("%", "")));
            
            listHardDisk.add(hd);
        }

        return listHardDisk;
    }
     public ArrayList<HardDiskBean> getHardDiskList(String ipAddress) {

        javaRunCommand = new JavaRunCommand(ipAddress, "head -1 /etc/hostname");
        serverName = javaRunCommand.cmdResults.trim();

        javaRunCommand = new JavaRunCommand(ipAddress, "df -B G | nawk '/dev.*sd.*/ || /dev.*md.*/ {print $1,$2,$5,$6}'");

        String resultCMD = javaRunCommand.cmdResults;
        arrayHardDisk = resultCMD.split("/dev/");

        for (int i = 1; i < arrayHardDisk.length; i++) {
            String[] tmp = arrayHardDisk[i].split(" ");
            System.out.println(arrayHardDisk[i]);
            HardDiskBean hd = new HardDiskBean();
            hd.setServerName(serverName);
            
            hd.setHDname(tmp[0]);
            hd.setTotalSpace(Float.parseFloat(tmp[1].replace("G","")));
            hd.setMountPoint(tmp[3]);
            hd.setUsedSpace(Float.parseFloat(tmp[2].replace("%", "")));
            
            listHardDisk.add(hd);
        }

        return listHardDisk;
    }
   

}
