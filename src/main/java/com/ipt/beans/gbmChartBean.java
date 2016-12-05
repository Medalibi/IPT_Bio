
import com.ipt.beans.HardDiskBean;
import com.ipt.beans.ServerBean;
import com.ipt.dao.HardDiskDAO;
import com.ipt.dao.ServerDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.MeterGaugeChartModel;
import org.primefaces.model.chart.PieChartModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author sabrine
 */

@Named("gbmChartBean")
@RequestScoped
public class gbmChartBean implements Serializable {

    private PieChartModel pieModel;
    private MeterGaugeChartModel meterGaugeModel;
    private HorizontalBarChartModel horizontalBarModel;
    private ServerBean server;

    @PostConstruct
    public void init() {
        ServerDAO serverDAO = new ServerDAO();
        server = new ServerBean();
        server = serverDAO.getServerInfo("gbm");
        createPieModel();
        createMeterGaugeModel();
        createHorizontalBarModel();

    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    private MeterGaugeChartModel initMeterGaugeModel() {
        List<Number> intervals = new ArrayList<Number>() {
            {
                add(20);
                add(40);
                add(80);
                add(100);
            }
        };

        return new MeterGaugeChartModel(server.cpuLoad, intervals);
    }

    private void createMeterGaugeModel() {
        meterGaugeModel = initMeterGaugeModel();
        meterGaugeModel.setTitle("CPU Load");
        meterGaugeModel.setSeriesColors("E7E658,66cc66,93b75f,cc6666");
        meterGaugeModel.setShowTickLabels(true);
        meterGaugeModel.setMouseoverHighlight(true);

    }

    public MeterGaugeChartModel getMeterGaugeModel() {
        return meterGaugeModel;
    }

    private void createPieModel() {
        pieModel = new PieChartModel();
        pieModel.set("Free Ram", server.freeRam);
        pieModel.set("UsedRam", server.usedRam);
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
        HardDiskDAO hardDiskDAO = new HardDiskDAO();
        hardDiskList = hardDiskDAO.getHardDiskList("gbm");
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
