/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipt.beans;

import com.ipt.dao.DiskChoiceListDAO;
import com.ipt.utils.JavaRunCommand;
import com.ipt.utils.LoginSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sabrine
 */
@ManagedBean(name = "multiSelectView")
@ViewScoped
public class MultiSelectView implements Serializable {

    private List<SelectItem> servers;
    private String selectionServer;
    private String selection;
    private String listExistingFile = null;

    @PostConstruct
    public void init() {

        servers = new ArrayList<SelectItem>();
        ServerChoiceListBean serverChoiceListBean = new ServerChoiceListBean();

        for (int i = 0; i < serverChoiceListBean.getServerNameList().size(); i++) {
            SelectItemGroup group = new SelectItemGroup(serverChoiceListBean.getServerNameList().get(i).serverName);
            DiskChoiceListDAO diskChoiceListBean = new DiskChoiceListDAO();
            ArrayList<String> hardDiskList = new ArrayList<>();
            hardDiskList = diskChoiceListBean.getHardDiskList(serverChoiceListBean.getServerNameList().get(i).serverName);
            SelectItem[] selectItem = new SelectItem[10];
            for (int j = 0; j < hardDiskList.size() && hardDiskList.get(j).equals("/") == false; j++) {
                String t = serverChoiceListBean.getServerNameList().get(i).ipAddress.concat(";");
                t = t.concat(serverChoiceListBean.getServerNameList().get(i).serverName.concat(";"));
                SelectItem option = new SelectItem(t.concat(hardDiskList.get(j)), hardDiskList.get(j));
                selectItem[j] = new SelectItem(t.concat(hardDiskList.get(j)), hardDiskList.get(j));
                this.setSelectionServer(serverChoiceListBean.getServerNameList().get(i).serverName);
            }
            SelectItem[] CT = correctTableItem(selectItem);
            group.setSelectItems(CT);
            servers.add(group);
        }

    }

    public String getSelectionServer() {
        return selectionServer;
    }

    public void setSelectionServer(String selectionServer) {
        this.selectionServer = selectionServer;

    }

    public SelectItem[] correctTableItem(SelectItem[] selectItem) {
        int sizeC = 0;

        for (int i = 0; i < selectItem.length; i++) {
            if (selectItem[i] != null) {
                sizeC++;
            }

        }
        SelectItem[] cT = new SelectItem[sizeC];
        for (int i = 0; i < sizeC; i++) {
            cT[i] = selectItem[i];

        }
        return cT;

    }

    public List<SelectItem> getServers() {
        return servers;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public String CreateFolders() {

        String tmp = selection;
        String[] tapTmp = tmp.split(";");
        String ipServer = tapTmp[0];
        String hardDisk = tapTmp[2];
        HttpSession httpSession = LoginSession.getSession();
        Object obj = httpSession.getAttribute("SessionName");
        String nameUser = obj.toString();
        JavaRunCommand javaRunCommand1 = new JavaRunCommand(ipServer, "mkdir " + hardDisk + "/" + nameUser);

        JavaRunCommand javaRunCommand2 = new JavaRunCommand(ipServer, "ls -m " + hardDisk + "/" + nameUser + " | sed -r 's/,/ \\<br\\/\\>/g'");
        if ("".equals(javaRunCommand2.cmdResults)) {
            this.listExistingFile = "You have no existing file";
        } else {
            this.listExistingFile = javaRunCommand2.cmdResults;
        }
        //this.wait(5000);
        return null;

    }

    public String getlistExistingFiles() {

        return this.listExistingFile;
    }

    public String getOutputText() {
        String tmp = selection;
        String[] tapTmp = tmp.split(";");
        String nameServer = tapTmp[1];
       System.out.println(nameServer);
        String hardDisk = tapTmp[2];
         System.out.println(hardDisk);
        String t = "You have choosen the location : " + hardDisk + " at this server : " + nameServer;
        return t;

    }
    
     public void info() {
         String tmp = selection;
        String[] tapTmp = tmp.split(";");
        String nameServer = tapTmp[1];
       System.out.println(nameServer);
        String hardDisk = tapTmp[2];
         System.out.println(hardDisk);
        String t = "You have choosen the location : " + hardDisk + " at this server : " + nameServer;
         
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", t));
    }

}
