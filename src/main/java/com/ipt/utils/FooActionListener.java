/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipt.utils;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

/**
 *
 * @author sabrine
 */
    public class FooActionListener implements ActionListener {

    @Override
    public void processAction(ActionEvent event)
            throws AbortProcessingException {
        System.out.println(event.toString());
    }

}
    

