package apfd;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;


/**
 * This class build the APFD chart in this version is build a single chart
 * for only one technique.
 * 
 * @author Samuel Santos
 * @version 1.0
 * 
 */
public class ChartAPFD extends ApplicationFrame {
     
	final JFreeChart chart;
	final XYSeriesCollection dataset;
	final ChartPanel chartPanel;
    
    /**
     * Build a multiple APFD graph with every techniques.
     * 
     * @param title 
     *          the graph title.
     * @param seriesName 
     *          the techniques names.
     * @param values 
     *          failed test cases position.
     * @param numberOfTests 
     *          the number of test cases in the test suite.
     */
    public ChartAPFD(String title, String [] seriesName, int[][] values, int numberOfTests){
    	super(title);

    	dataset = new XYSeriesCollection();
    	
    	for (int i = 0; i < seriesName.length; i++){
    		XYSeries newSeries = createXYSerie(seriesName[i], values[i], numberOfTests);
    		dataset.addSeries(newSeries);
    	}
    	
    	chart = createChart(dataset);
    	
    	chartPanel = new ChartPanel(chart);
    	chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }
    
    /**
     * This method create a new APFD series.
     * 
     * @param title the technique name for this series.
     * @param values the positions of failed test cases.
     * @param numberOfTests the number of test cases in the Test Suite.
     * 
     * @return a XYseries
     */
    private XYSeries createXYSerie(String title, int [] values, int numberOfTests){
    	final XYSeries series = new XYSeries(title);
    	
        List<Integer> valuesList = new LinkedList<Integer>();
        
        for (int i: values)
            valuesList.add(i);
        
        Collections.sort(valuesList);
        
    	series.add(new Integer(0), new Double(0));
    	
        for (int i=0; i< values.length; i++){
        	int axisX = valuesList.get(i) * 100 / numberOfTests; 
        	double axisY = (i+1) * 100/ valuesList.size();
        	series.add(new Integer(axisX), new Double(axisY));
        }
        
        series.add(new Integer(100), new Double(100));
        
        return series;
    }

    
    /**
     * Creates a chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return A chart.
     */
    private JFreeChart createChart(final XYDataset dataset) {
            
        final JFreeChart chart = ChartFactory.createXYAreaChart(
            "APFD Graph for Prioritized test suite",
            "Percent of Test Suite Executed (X)", "Percent of Faults Detected (Y)",
            dataset,
            PlotOrientation.VERTICAL,
            true,  // legend
            true,  // tool tips
            false  // URLs
        );
        
        chart.setBackgroundPaint(Color.white);
        
        final XYPlot plot = chart.getXYPlot();
        //plot.setOutlinePaint(Color.black);
        plot.setBackgroundPaint(Color.lightGray);
        plot.setForegroundAlpha(0.65f);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        
        final ValueAxis domainAxis = plot.getDomainAxis();
        domainAxis.setTickMarkPaint(Color.black);
        domainAxis.setLowerMargin(0.0);
        domainAxis.setUpperMargin(0.0);
        
        final ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setTickMarkPaint(Color.black);
        
        return chart;
        
    }
    
    /**
     * Get a panel with the chart.
     * 
     * @return an ChartPanel.
     */
    public ChartPanel getChart(){
    	return chartPanel;
    }
    

}

