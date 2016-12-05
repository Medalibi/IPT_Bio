/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipt.beans;

import com.ipt.dao.HardDiskDAO;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
//import javafx.scene.chart.Axis;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import org.primefaces.model.chart.*;

/**
 *
 * @author sabrine
 */
@ManagedBean(name = "HardDiskBarViewModel")
@RequestScoped
public class HardDiskBarViewModel implements Serializable {

    private HorizontalBarChartModel horizontalBarModel;

    @PostConstruct
    public void init() {
        createHorizontalBarModel();
    }

    public HorizontalBarChartModel getHorizontalBarModel() {
        return horizontalBarModel;
    }

    private void createHorizontalBarModel() {
        horizontalBarModel = new HorizontalBarChartModel();
        ArrayList<HardDiskBean> hardDiskList = new ArrayList<>();

        ChartSeries totalSpace = new ChartSeries();
        ChartSeries usedSpace = new ChartSeries();
        usedSpace.setLabel("Used Space");
        totalSpace.setLabel("Total Space");

        FaceletContext faceletContext = (FaceletContext) FacesContext.getCurrentInstance().getAttributes().get(FaceletContext.FACELET_CONTEXT_KEY);
        ServerBean sb = (ServerBean) faceletContext.getAttribute("server");

        HardDiskDAO hardDiskDAO = new HardDiskDAO();
        System.out.println("MEEEEE HardDiks");
        System.out.println(sb.serverName);
        System.out.println("MEEEEE");
        hardDiskList = hardDiskDAO.getHardDiskList(sb.serverName);
        for (int i = 0; i < hardDiskList.size(); i++) {
            float t = (hardDiskList.get(i).diskSpace * hardDiskList.get(i).usedSpace) / 100;
            totalSpace.set(hardDiskList.get(i).HDname, hardDiskList.get(i).diskSpace);
            usedSpace.set(hardDiskList.get(i).HDname, t);

        }

        horizontalBarModel.addSeries(totalSpace);
        horizontalBarModel.addSeries(usedSpace);

        horizontalBarModel.setLegendPosition("outside");
        horizontalBarModel.setStacked(true);
        Axis xAxis = horizontalBarModel.getAxis(AxisType.X);
        Axis yAxis = horizontalBarModel.getAxis(AxisType.Y);

    }
}
