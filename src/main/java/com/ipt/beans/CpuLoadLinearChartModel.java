/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipt.beans;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import org.primefaces.model.chart.MeterGaugeChartModel;
//import org.primefaces.model.chart.MeterGaugeChartModel;

/**
 *
 * @author sabrine
 */
@ManagedBean(name = "CpuLoadLinearChartModel")
@RequestScoped
public class CpuLoadLinearChartModel {

    private MeterGaugeChartModel meterGaugeModel;
    private ServerBean s = new ServerBean();

    @PostConstruct
    public void init() {

        createMeterGaugeModel();
    }

    public MeterGaugeChartModel getMeterGaugeModel() {
        return meterGaugeModel;
    }

    private MeterGaugeChartModel initMeterGaugeModel() {

       // ServerDAO serverDAO = new ServerDAO();
       // ArrayList<ServerBean> serverList = new ArrayList<>();
       // serverList = serverDAO.getServerList();

        FaceletContext faceletContext = (FaceletContext) FacesContext.getCurrentInstance().getAttributes().get(FaceletContext.FACELET_CONTEXT_KEY);
        ServerBean sb = (ServerBean) faceletContext.getAttribute("server");
        
        List<Number> intervals = new ArrayList<Number>() {
            {
                add(20);
                add(40);
                add(80);
                add(100);
            }
        };

        return new MeterGaugeChartModel(sb.cpuLoad, intervals);
    }

    private void createMeterGaugeModel() {

        meterGaugeModel = initMeterGaugeModel();
        meterGaugeModel.setTitle("CPU Load");

       // meterGaugeModel.setGaugeLabel("CPU load");
        // meterGaugeModel.setGaugeLabelPosition("");
        meterGaugeModel.setSeriesColors("E7E658,66cc66,93b75f,cc6666");
        meterGaugeModel.setShowTickLabels(true);
        meterGaugeModel.setMouseoverHighlight(true);

    }
}
