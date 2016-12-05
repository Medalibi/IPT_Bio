/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipt.beans;

import com.ipt.dao.ServerDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
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
@ManagedBean(name = "ResourcesBean")
@SessionScoped
public class ResourcesBean implements Serializable {

    private static final long serialVersionUID = 7526472295622776147L;

    private final DefaultMenuModel model;
    private ArrayList<ServerBean> serverList;

    public ResourcesBean() {

        model = new DefaultMenuModel();
        ServerDAO serverDAO = new ServerDAO();
        serverList = new ArrayList<>();

        serverList = serverDAO.getServerList();
        DefaultSubMenu submenu = new DefaultSubMenu("Avaibles Servers");

        for (int i = 0; i < serverList.size(); i++) {

            DefaultMenuItem item = new DefaultMenuItem(serverList.get(i).getServerName());
            item.setAjax(true);
            item.setParam("NameServer" , serverList.get(i).serverName);
            item.setCommand("#{ResourcesBean.getClickedServer()}");
            item.setOnclick("PF('dlg3').show()");
           
      
            submenu.addElement(item);

        }
        model.addElement(submenu);
    }

    public MenuModel getModel() {
        return model;
    }

    public String getClickedServer() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        return params.get("NameServer");
    }

}
