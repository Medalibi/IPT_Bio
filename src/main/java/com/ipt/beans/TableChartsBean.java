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
import com.ipt.dao.ServerDAO;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import org.primefaces.component.tabview.Tab;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean(name = "pFBindingBean")
@RequestScoped
public class TableChartsBean implements Serializable {

    private TabView tv;
    int index;
    Tab currentTab;

    @PostConstruct
    public void init() {
        tv = new TabView();

        ServerDAO serverDao = new ServerDAO();
        ArrayList<ServerBean> serverList = serverDao.getServerList();
        for (int i = 0; i < serverList.size(); i++) {
            Tab tab = new Tab();
            tab.setTitle(serverList.get(i).getServerName());

            HtmlOutputLabel out1 = new HtmlOutputLabel();
            out1.setValue(serverList.get(i).serverName);
            tab.getChildren().add(out1);
            
            FaceletContext faceletContext = (FaceletContext) FacesContext.getCurrentInstance().getAttributes().get(FaceletContext.FACELET_CONTEXT_KEY);
            try {
                faceletContext.setAttribute("server", serverList.get(i));
                ServerBean sb = (ServerBean) faceletContext.getAttribute("server");
               
                faceletContext.includeFacelet(tab, "RamPieChartModel.xhtml");
              
                  
            } catch (IOException ex) {
                Logger.getLogger(TableChartsBean.class.getName()).log(Level.SEVERE, null, ex);
            }

            tv.getChildren().add(tab);

        }
    }
        //Setting Tab1
    //Setting tab2
        /*  Tab tab2 = new Tab();
     tab2.setTitle("Hello again");

     HtmlOutputLabel name = new HtmlOutputLabel();
     name.setValue("Name:");
     HtmlOutputLabel surname = new HtmlOutputLabel();
     surname.setValue("Surname:");
     InputText inputName = new InputText();
     InputText inputSurname = new InputText();

     //Setting the panelgrid
     HtmlPanelGrid pg = new HtmlPanelGrid();
     pg.setColumns(2);

     pg.getChildren().add(name);
     pg.getChildren().add(inputName);
     pg.getChildren().add(surname);
     pg.getChildren().add(inputSurname);

     //Adding the panelgrid to tab2
     tab2.getChildren().add(pg);*/
        //Adding both tabs to the tabview
 

    public TabView getTv() {
        return tv;
    }

    public void setTv(TabView tv) {
        this.tv = tv;
    }

    /*    public int getIndex() {
     return index;
     }
 
     public void setIndex(int index) {
     this.index = index;
     }*/
   
}
