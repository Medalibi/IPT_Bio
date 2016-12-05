/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipt.utils;

/**
 *
 * @author sabrine
 */
import java.io.File;
import java.io.FileInputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

public class SFTPUploadFile {

    public String SFTPUSER;
    public int SFTPPORT = 22;
    String SFTPHOST;

    public SFTPUploadFile() {

    }

    /**
     * @param args
     */
    public void setSFTPUSER() {
        HttpSession httpSession = LoginSession.getSession();
        Object obj = httpSession.getAttribute("SessionName");
        this.SFTPUSER = obj.toString();
    }

    public String getSFTPUSER() {
        return this.SFTPUSER;
    }

    public void handleSFTPUploadFile() {

        String SFTPWORKINGDIR = "/home/sabrine/";
        String FILETOTRANSFER = "/home/sabrine/base.sh";
        Session session = null;
        Channel channel = null;
        ChannelSftp channelSftp = null;
        File f = null;
        FileInputStream fis = null;
        try {
            JSch jsch = new JSch();
            jsch.addIdentity("/home/sabrine/.ssh/id_rsa");
            session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
            System.out.println("GEt identity");
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            System.out.println("Session GEt identity");
            channel = session.openChannel("sftp");
            channel.connect();
            System.out.println("Channel GEt identity");
            channelSftp = (ChannelSftp) channel;
            channelSftp.cd(SFTPWORKINGDIR);
            f = new File(FILETOTRANSFER);
            fis = new FileInputStream(f);
            channelSftp.put(fis, f.getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                    System.out.println("TERMINATED");
                    channel.disconnect();
                    session.disconnect();

                } catch (IOException ex) {
                    Logger.getLogger(SFTPUploadFile.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

}
