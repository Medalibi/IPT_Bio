/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipt.utils;

import com.ipt.beans.ServerBean;
import java.util.ArrayList;

/**
 *
 * @author sabrine
 */
public class UpdateServerInfoTable {

    public ServerBean server = null;
    public static ArrayList<String> serversNameList;

    public UpdateServerInfoTable() {

        JavaRunCommand javaRunCommand;
        server = new ServerBean();

        javaRunCommand = new JavaRunCommand("192.168.8.1", "free -m | grep Mem | awk '{usage=$3*100/$2} END {print usage}'");
        server.setUsedRam(Float.parseFloat(javaRunCommand.cmdResults));

        javaRunCommand = new JavaRunCommand("192.168.8.1", "echo cat /proc/stat | grep 'cpu ' /proc/stat | awk '{usage=($2+$4)*100/($2+$4+$5)} END {print usage}'");
        server.setCPULoad(Float.parseFloat(javaRunCommand.cmdResults));

        javaRunCommand = new JavaRunCommand("192.168.8.1", "head -1 /etc/hostname");
        server.setServerName(javaRunCommand.cmdResults.trim());

        server.setFreeRam();

    }

    public UpdateServerInfoTable(String ipaddresSserver) {

        JavaRunCommand javaRunCommand;
        server = new ServerBean();

        server.setIPAddress(ipaddresSserver);

        javaRunCommand = new JavaRunCommand(ipaddresSserver, "free -m | grep Mem | awk '{usage=$3*100/$2} END {print usage}'");
        server.setUsedRam(Float.parseFloat(javaRunCommand.cmdResults));

        javaRunCommand = new JavaRunCommand(ipaddresSserver, "echo cat /proc/stat | grep 'cpu ' /proc/stat | awk '{usage=($2+$4)*100/($2+$4+$5)} END {print usage}'");
        server.setCPULoad(Float.parseFloat(javaRunCommand.cmdResults));

        javaRunCommand = new JavaRunCommand(ipaddresSserver, "head -1 /etc/hostname");
        server.setServerName(javaRunCommand.cmdResults.trim());

        server.setFreeRam();

    }
}
