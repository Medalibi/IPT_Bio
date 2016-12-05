/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipt.beans;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author sabrine
 */
@ManagedBean (name = "ProcessListBean")
@SessionScoped
public class ProcessListBean implements Serializable{


   private final int homePage = 1;
   private final int standardPageTraitement = 2;
   private final int Mail = 3;
   private final int Resources = 4;
   private int selectedOption;

   public ProcessListBean() {
     selectedOption = homePage;
   }

    public int getMail() {
        return Mail;
    }

    public int getResources() {
        return Resources;
    }

   public int getSelectedOption() {
      return selectedOption;
   }

   public String setSelectedOption(int selectedOption) {
       System.out.println("MEEE before " + this.getSelectedOption());
      this.selectedOption = selectedOption;
      System.out.println("MEEE before " + this.getSelectedOption());
      return "";
   }

   public int getHomePage() {
      return homePage;
   }

   public int getStandardPageTraitement() {
      return standardPageTraitement;
   }
}

