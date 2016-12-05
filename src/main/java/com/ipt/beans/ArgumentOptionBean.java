/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipt.beans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author sabrine
 */
@ManagedBean(name = "ArgumentOptionBean")
@SessionScoped
public class ArgumentOptionBean {
    
    private TraitmentOptions traitmentOption ;
    private String optionArgument ;
    private List<TraitmentOptions> selectedTraitementOptionList = new ArrayList<>();
    
  
    public void setTraitementOption (TraitmentOptions tO )
    {
        this.traitmentOption = tO;
    }
    
    public void setSelectedTraitementOptionList (List<TraitmentOptions> selectedTraitementOptionList)
    {
        this.selectedTraitementOptionList = selectedTraitementOptionList ;
    }
    
    public List<TraitmentOptions> getSelectedTraitementOptionList ()
    {
        return this.selectedTraitementOptionList ;
    }
    
    public TraitmentOptions getTraitementOption ()
    {
       return this.traitmentOption;
    }
    
    public void setOptionArgument(String agr , TraitmentOptions to  )
    {
        this.optionArgument = agr;
    }
    
    public String getOptionArgument()
    {
      
        return this.optionArgument;
    }
    
    public String showTypeOption()
    {
      
        return "this option requires a :";
    }
    
    
    
}
