/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipt.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author sabrine
 */
public class JavaRunCommand {

    public String command = "";
    public String ipServer = "";
    public String cmdResults = "";
    public  BufferedReader stdInput;
    public   BufferedReader stdError;

    public JavaRunCommand(String ipserver, String command) {

        this.command = command;
        this.ipServer = ipserver;

        String s = "";

        try {

            Process p = Runtime.getRuntime().exec("ssh -t " + ipserver + " " + command);
        
            stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            
            while ((s = stdInput.readLine()) != null) {
                cmdResults = cmdResults.concat(s);
            }
            
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
            p.destroy();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
    
    public JavaRunCommand(String command) {
        
        this.command = command;
        String s = "";
        try {
            Process p = Runtime.getRuntime().exec("ssh -t 192.168.8.1 " + command);
            stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
           stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            while ((s = stdInput.readLine()) != null) {
                cmdResults = cmdResults.concat(s);
            }
            
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
            p.destroy();

        } catch (IOException e) {
           e.printStackTrace();
        }
    }
        
    }


