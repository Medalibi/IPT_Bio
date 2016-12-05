/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipt.beans;

import com.ipt.dao.DiskChoiceListDAO;
import com.ipt.utils.JavaRunCommand;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author sabrine
 */
@ManagedBean(name = "DiskChoiceList")
@SessionScoped

public class DiskChoiceListBean implements Serializable {

    private static final long serialVersionUID = 7526472295622776147L;
    private final MenuModel model;

    public DiskChoiceListBean() {
        model = new DefaultMenuModel();
       
        ServerChoiceListBean serverChoiceListBean = new ServerChoiceListBean();

        for (int i = 0; i < serverChoiceListBean.getServerNameList().size(); i++) {
            DefaultSubMenu submenu = new DefaultSubMenu(serverChoiceListBean.getServerNameList().get(i).serverName);
           
            
            DiskChoiceListDAO diskChoiceListBean = new DiskChoiceListDAO();
            

            ArrayList<String> hardDiskList = new ArrayList<>();
            hardDiskList = diskChoiceListBean.getHardDiskList(serverChoiceListBean.getServerNameList().get(i).serverName);
            for (int j = 0; j < hardDiskList.size(); j++) {
                DefaultMenuItem item = new DefaultMenuItem(hardDiskList.get(j));
                
                item.setIcon("ui-icon-home");
                item.setAjax(true);
                item.setParam("clickedItem", hardDiskList.get(j));
                item.setParam("submenuIpServer", serverChoiceListBean.getServerNameList().get(i).ipAddress);
                item.setCommand("#{DiskChoiceList.insert()}");
                submenu.addElement(item);
                submenu.setStyle(null);
            }
            model.addElement(submenu);
            
        }
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void insert() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        String command;
        if (params.get("clickedItem").endsWith("/")) {
            command = "mkdir " + params.get("clickedItem") + "NewFolder";
        } else {
            command = "mkdir " + params.get("clickedItem") + "/NewFolder";
        }
        JavaRunCommand javaRunCommand = new JavaRunCommand(params.get("submenuIpServer"), command);
        addMessage("Info", "");

    }

    public MenuModel getModel() {
        return model;
    }

}
