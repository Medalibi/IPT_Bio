/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipt.process;

import com.ipt.utils.JavaRunCommand;

/**
 *
 * @author sabrine
 */
public class QualityOfDataWithFastQC {
    
    private String outputFile = "";
    private String inputFile = "";
    private String cmd = "";
    private int idProcess = 1 ;
  
    public QualityOfDataWithFastQC(String outputFile , String inputFile)
    {
        this.inputFile = inputFile ;
        this.outputFile = outputFile ;
        cmd = "fastQC -o" + outputFile+ ""+  inputFile ;
         
        JavaRunCommand javaRunCommand = new JavaRunCommand ("192.168.8.1" , cmd );
        
    }
    
}