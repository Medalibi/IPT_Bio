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
import javax.annotation.PostConstruct;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import org.primefaces.model.chart.PieChartModel;

@RequestScoped
@Named("RamPieChartModel")
public class RamPieChartModel implements Serializable {

    private PieChartModel pieModel1;
    private float usedRam;
    private float freeRam;
    private ServerBean server ;

    public float getUsedRam() {
        return usedRam;
    }

    public void setUsedRam(float usedRam) {
        this.usedRam = usedRam;
    }

    public float getFreeRam() {
        return freeRam;
    }

    public void setFreeRam() {
        this.freeRam = 100 - this.usedRam;
    }

    @PostConstruct
 
    public void init() {
     //  ServerDAO serverDAO = new ServerDAO();

    //  ArrayList<ServerBean> serverList = new ArrayList<>();
        //    serverList = serverDAO.getServerList();
        FaceletContext faceletContext = (FaceletContext) FacesContext.getCurrentInstance().getAttributes().get(FaceletContext.FACELET_CONTEXT_KEY);
        ServerBean sb = (ServerBean) faceletContext.getAttribute("server");
        System.out.println("MEEEEE PIe Chat");
        System.out.println(sb.serverName);
        System.out.println("MEEEEE");

       // for (int i = 0; i < serverList.size(); i++) {
        //usedRam = this.server.getUsedRam();
        //    freeRam = this.server.getFreeRam();
       // usedRam = sb.getUsedRam();
       // freeRam = sb.getFreeRam();
        
                
       

       
        //createPieModel1();
        // }

    }

    public PieChartModel getPieModel1() {
        FaceletContext faceletContext = (FaceletContext) FacesContext.getCurrentInstance().getAttributes().get(FaceletContext.FACELET_CONTEXT_KEY);
        ServerBean s = (ServerBean) faceletContext.getAttribute("server");
        pieModel1 = new PieChartModel();
        pieModel1.set("Free Ram", s.freeRam);
        pieModel1.set("UsedRam", s.usedRam);
        return pieModel1;
}
    
    public ServerBean getS() {
        FaceletContext faceletContext = (FaceletContext) FacesContext.getCurrentInstance().getAttributes().get(FaceletContext.FACELET_CONTEXT_KEY);
        ServerBean sb = (ServerBean) faceletContext.getAttribute("server");
        return sb ;
}

   /*
        pieModel1 = new PieChartModel();
        pieModel1.set("Free Ram", freeRam);
        pieModel1.set("UsedRam", usedRam);
        

    }*/

}
