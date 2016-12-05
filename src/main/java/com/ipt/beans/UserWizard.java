/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipt.beans;

/**
 *
 * @author sabrine
 */
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;

@ManagedBean (name = "UserWizard")
public class UserWizard implements Serializable {

    public void save() {
        FacesMessage msg = new FacesMessage("Successful", "Welcome :");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

   

    public String onFlowProcess(FlowEvent event) {
        
            return event.getNewStep();
        }
    
  
 
    
    
    
}
