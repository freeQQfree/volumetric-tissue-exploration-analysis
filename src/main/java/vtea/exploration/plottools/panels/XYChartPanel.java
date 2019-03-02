/* 
 * Copyright (C) 2016-2018 Indiana University
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package vtea.exploration.plottools.panels;

import ij.ImagePlus;
import ij.gui.Roi;
import ij.gui.RoiListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JFrame;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.LookupPaintScale;
import org.jfree.chart.renderer.xy.XYShapeRenderer;
import org.jfree.chart.title.PaintScaleLegend;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYZDataset;
import org.jfree.chart.ui.RectangleEdge;
import vtea.exploration.listeners.UpdatePlotWindowListener;
import vtea.jdbc.H2DatabaseEngine;
import vteaobjects.MicroObject;
import vteaobjects.MicroObjectModel;

/**
 *
 * @author vinfrais
 */
public class XYChartPanel implements RoiListener {
    private static final String title = "XY Chart";
    private static boolean ImageGate = false;
    
    public static double xMin = -1;
    public static double xMax = -1;
    public static double yMin = -1;
    public static double yMax = -1;
    
    public static boolean xLinear = true;
    public static boolean yLinear = true;
    
    public static boolean globalAxes = false;

    public static int XAXIS = 1;
    public static int YAXIS = 2;

    static Color ZEROPERCENT = new Color(0x999999);
    static Color TENPERCENT = new Color(0x8a8aa3);
    static Color TWENTYPERCENT = new Color(0x7b7bad);
    static Color THIRTYPERCENT = new Color(0x6666bb);
    static Color FORTYPERCENT = new Color(0x5050ca);
    static Color FIFTYPERCENT = new Color(0x4c4ccc);
    static Color SIXTYPERCENT = new Color(0x3d3dd6);
    static Color SEVENTYPERCENT = new Color(0x2e2ee0);
    static Color EIGHTYPERCENT = new Color(0x1f1feb);
    static Color NINETYPERCENT = new Color(0x0f0ff5);
    static Color ALLPERCENT = new Color(0x0000ff);

    private ChartPanel chartPanel;
    private List measurements = new ArrayList();
    private ArrayList<MicroObject> objects = new ArrayList<MicroObject>();
    //private ArrayList<ArrayList<Number>> ImageGateOverlay = new ArrayList<ArrayList<Number>>();
    private ArrayList<Number> ImageGateOverlay = new ArrayList<Number>();
    private ArrayList<UpdatePlotWindowListener> UpdatePlotWindowListeners = new ArrayList<UpdatePlotWindowListener>();
    private ImagePlus impoverlay;

    private int size = 4;
    private int xValues;
    private int yValues;
    private int lValues;
    private String xValuesText;
    private String yValuesText;
    private String lValuesText;
    private boolean imageGate;
    private Color imageGateOutline;
    
    private Connection connection;
    
    public XYChartPanel() {

    }

    public XYChartPanel(ArrayList objects, List measurements, int x, int y, int l, String xText, String yText, String lText, int size, ImagePlus ip, boolean imageGate, Color imageGateColor) {

        impoverlay = ip;
        this.imageGate = imageGate;
        imageGateOutline = imageGateColor;
        this.measurements = measurements;
        this.objects = objects;
        this.size = size;
        xValues = x;
        yValues = y;
        lValues = l;
        xValuesText = xText;
        yValuesText = yText;
        lValuesText = lText;

       // process(xValues, yValues, lValues, xValuesText, yValuesText, lValuesText);
    }
    
    public XYChartPanel(ArrayList objects, int x, int y, int l, String xText, String yText, String lText, int size, ImagePlus ip, boolean imageGate, Color imageGateColor) {

        impoverlay = ip;
        this.imageGate = imageGate;
        imageGateOutline = imageGateColor;
        this.measurements = measurements;
        this.objects = objects;
        this.size = size;
        xValues = x;
        yValues = y;
        lValues = l;
        xValuesText = xText;
        yValuesText = yText;
        lValuesText = lText;

        //process(xValues, yValues, lValues, xValuesText, yValuesText, lValuesText);
        
        process(connection, xValues, yValues, lValues, xValuesText, yValuesText, lValuesText);
    }
    
    public void process(Connection cn, int x, int y, int l, String xText, String yText, String lText) {

        chartPanel = createChart(connection, x, y, l, xText, yText, lText, imageGateOutline);
        JFrame f = new JFrame(title);
        f.setTitle(title);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout(0, 5));
        f.add(chartPanel, BorderLayout.CENTER);

        chartPanel.setOpaque(false);
        chartPanel.setMouseWheelEnabled(false);
        chartPanel.setDomainZoomable(false);
        chartPanel.setRangeZoomable(false);
        chartPanel.setPreferredSize(new Dimension(550, 485));
        chartPanel.setBackground(new Color(255, 255, 255, 255));
        chartPanel.revalidate();
        chartPanel.repaint();
        //chartPanel.set

        chartPanel.addChartMouseListener(new ChartMouseListener() {

            @Override
            public void chartMouseClicked(ChartMouseEvent cme) {
                chartPanel.getParent().repaint();
            }

            @Override
            public void chartMouseMoved(ChartMouseEvent cme) {
            }
        });

        chartPanel.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                chartPanel.getParent().repaint();

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                chartPanel.getParent().repaint();
            }
        });

        chartPanel.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
        f.pack();
    }
//    
//    public void process(int x, int y, int l, String xText, String yText, String lText) {
//
//        chartPanel = createChart(x, y, l, xText, yText, lText, imageGateOutline);
//        JFrame f = new JFrame(title);
//        f.setTitle(title);
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.setLayout(new BorderLayout(0, 5));
//        f.add(chartPanel, BorderLayout.CENTER);
//
//        chartPanel.setOpaque(false);
//        chartPanel.setMouseWheelEnabled(false);
//        chartPanel.setDomainZoomable(false);
//        chartPanel.setRangeZoomable(false);
//        chartPanel.setPreferredSize(new Dimension(550, 485));
//        chartPanel.setBackground(new Color(255, 255, 255, 255));
//        chartPanel.revalidate();
//        chartPanel.repaint();
//        //chartPanel.set
//
//        chartPanel.addChartMouseListener(new ChartMouseListener() {
//
//            @Override
//            public void chartMouseClicked(ChartMouseEvent cme) {
//                chartPanel.getParent().repaint();
//            }
//
//            @Override
//            public void chartMouseMoved(ChartMouseEvent cme) {
//            }
//        });
//
//        chartPanel.addMouseMotionListener(new MouseMotionListener() {
//
//            @Override
//            public void mouseDragged(MouseEvent e) {
//                chartPanel.getParent().repaint();
//
//            }
//
//            @Override
//            public void mouseMoved(MouseEvent e) {
//                chartPanel.getParent().repaint();
//            }
//        });
//
//        chartPanel.addMouseListener(new MouseListener() {
//
//            @Override
//            public void mouseClicked(MouseEvent e) {
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//        });
//        f.pack();
//    }
//    
//    //test class for H2 database with canned data
    
    public static void insertFromCSV(Connection connection) throws SQLException {
    
          PreparedStatement createPreparedStatement = null;

        try {
            connection.setAutoCommit(false);
            String ImportQuery = "CREATE TABLE VTEA AS SELECT * FROM CSVREAD('AQtest_LSConnect3D_vtea_022519.csv')";
            createPreparedStatement = connection.prepareStatement(ImportQuery);
            createPreparedStatement.executeUpdate();
            createPreparedStatement.close(); 
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    private ChartPanel createChart(Connection connection, int x, int y, int l, String xText, String yText, String lText, Color imageGateColor) {

        XYShapeRenderer renderer = new XYShapeRenderer();
        XYShapeRenderer rendererGate = new XYShapeRenderer();

        PaintScaleLegend psl = new PaintScaleLegend(new LookupPaintScale(0, 100, new Color(0, 0, 0)), new NumberAxis(""));
        
        
        
        if(l >= 0){
            double max = Math.round(getMaximumOfData(
                    H2DatabaseEngine.getColumn(vtea._vtea.H2_MEASUREMENTS_TABLE, lText), 0));
            double min = Math.round(getMinimumOfData(
                    H2DatabaseEngine.getColumn(vtea._vtea.H2_MEASUREMENTS_TABLE, lText), 0));
            double range = max - min;
        if (max == 0) {
            max = 1;
        }
         
        LookupPaintScale ps = new LookupPaintScale(min, max+1, new Color(0x999999));

        renderer.setPaintScale(ps);
        
        if(range <= 10){
            
        ps.add(0, new Color(255,51,51));
        ps.add(1, new Color(255, 153, 51));
        ps.add(2, new Color(153, 255, 51));
        ps.add(3, new Color(51, 255, 153));
        ps.add(4, new Color(51, 255, 255));
        ps.add(5, new Color(102, 178, 255));
        ps.add(6, new Color(102, 102, 255));
        ps.add(7, new Color(178, 102, 255));
        ps.add(8, new Color(255, 102, 255));
        ps.add(9, new Color(255, 102, 178));
  
        }else{

        ps.add(min + (1 * (range / 10)), XYChartPanel.TENPERCENT);
        ps.add(min + (2 * (range / 10)), XYChartPanel.TWENTYPERCENT);
        ps.add(min + (3 * (range / 10)), XYChartPanel.THIRTYPERCENT);
        ps.add(min + (4 * (range / 10)), XYChartPanel.FORTYPERCENT);
        ps.add(min + (5 * (range / 10)), XYChartPanel.FIFTYPERCENT);
        ps.add(min + (6 * (range / 10)), XYChartPanel.SIXTYPERCENT);
        ps.add(min + (7 * (range / 10)), XYChartPanel.SEVENTYPERCENT);
        ps.add(min + (8 * (range / 10)), XYChartPanel.EIGHTYPERCENT);
        ps.add(min + (9 * (range / 10)), XYChartPanel.NINETYPERCENT);
        ps.add(max, XYChartPanel.ALLPERCENT);
        }
        
        NumberAxis lAxis = new NumberAxis();
        
        lAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        psl = new PaintScaleLegend(ps, lAxis);

        psl.setBackgroundPaint(new Color(255, 255, 255, 255));
        
        psl.setPosition(RectangleEdge.RIGHT);
        psl.setMargin(4, 4, 40, 4);
        psl.setAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
        
       }
        
        Ellipse2D shape = new Ellipse2D.Double(0, 0, size, size);
        Ellipse2D shapeGate = new Ellipse2D.Double(-2, -2, size + 4, size + 4);
      
        renderer.setDefaultShape(shape);
        rendererGate.setDefaultShape(shapeGate);
        

        NumberAxis xAxis = new NumberAxis("");
        NumberAxis yAxis = new NumberAxis("");

        xAxis.setAutoRangeIncludesZero(false);
        yAxis.setAutoRangeIncludesZero(false);

        XYPlot plot = new XYPlot(createXYZDataset(H2DatabaseEngine.getColumns3D(vtea._vtea.H2_MEASUREMENTS_TABLE, xText, yText, lText), 
                xText, yText, lText, l), xAxis, yAxis, renderer);
        
        plot.getDomainAxis();
        plot.getRangeAxis();
        
        plot.setDomainPannable(false);
        plot.setRangePannable(false);

        plot.setRenderer(0, renderer);
        plot.setRenderer(1, rendererGate);
        
        
        //if image gated plot a ring at every object as a second dataset
        
                if (imageGate) {
            roiCreated(impoverlay);
            XYZDataset set = createXYZDataset(ImageGateOverlay, xText, yText, lText, l);
            plot.setDataset(1, set);
           plot.setRenderer(1, new XYShapeRenderer() {
               @Override
               protected java.awt.Paint getPaint(XYDataset dataset, int series, int item){
                   return imageGateOutline;
               }

        @Override
        public Shape getItemShape(int row, int col) {
               return new Ellipse2D.Double(-2, -2, size + 4, size + 4);     
        }
    });

        }

        try {
            if (getRangeofData((ArrayList) measurements, x) > Math.pow(impoverlay.getBitDepth(), 2)) {
                LogAxis logAxisX = new LogAxis();
                logAxisX.setAutoRange(true);
                plot.setDomainAxis(logAxisX);
            }

            if (getRangeofData((ArrayList) measurements, y) > Math.pow(impoverlay.getBitDepth(), 2)) {
                LogAxis logAxisY = new LogAxis();
                logAxisY.setAutoRange(true);
                plot.setRangeAxis(logAxisY);
            }

        } catch (NullPointerException e) {
        };

        JFreeChart chart = new JFreeChart("Plot of " + xText + " vs. " + yText, plot);

        chart.removeLegend();

        chart.setBackgroundPaint(new Color(255, 255, 255, 255));
        
        //LUT 
        if(l >= 0){
        chart.addSubtitle(psl);
        }

        //notifiyUpdatePlotWindowListeners();
        return new ChartPanel(chart, true, true, false, false, true);
    }
//    
//    private ChartPanel createChart(int x, int y, int l, String xText, String yText, String lText, Color imageGateColor) {
//
//        XYShapeRenderer renderer = new XYShapeRenderer();
//        XYShapeRenderer rendererGate = new XYShapeRenderer();
//        
//       
//        
//        PaintScaleLegend psl = new PaintScaleLegend(new LookupPaintScale(0, 100, new Color(0, 0, 0)), new NumberAxis(""));
//        
//        
//        if(l >= 0){
//            double max = Math.round(getMaximumOfData((ArrayList) measurements, l));
//            double min = Math.round(getMinimumOfData((ArrayList) measurements, l));
//            double range = max - min;
//        if (max == 0) {
//            max = 1;
//        }
//         
//        LookupPaintScale ps = new LookupPaintScale(min, max+1, new Color(0x999999));
//
//        renderer.setPaintScale(ps);
//        
//        if(range <= 10){
//            
//        ps.add(0, new Color(255,51,51));
//        ps.add(1, new Color(255, 153, 51));
//        ps.add(2, new Color(153, 255, 51));
//        ps.add(3, new Color(51, 255, 153));
//        ps.add(4, new Color(51, 255, 255));
//        ps.add(5, new Color(102, 178, 255));
//        ps.add(6, new Color(102, 102, 255));
//        ps.add(7, new Color(178, 102, 255));
//        ps.add(8, new Color(255, 102, 255));
//        ps.add(9, new Color(255, 102, 178));
//  
//        }else{
//
//        ps.add(min + (1 * (range / 10)), XYChartPanel.TENPERCENT);
//        ps.add(min + (2 * (range / 10)), XYChartPanel.TWENTYPERCENT);
//        ps.add(min + (3 * (range / 10)), XYChartPanel.THIRTYPERCENT);
//        ps.add(min + (4 * (range / 10)), XYChartPanel.FORTYPERCENT);
//        ps.add(min + (5 * (range / 10)), XYChartPanel.FIFTYPERCENT);
//        ps.add(min + (6 * (range / 10)), XYChartPanel.SIXTYPERCENT);
//        ps.add(min + (7 * (range / 10)), XYChartPanel.SEVENTYPERCENT);
//        ps.add(min + (8 * (range / 10)), XYChartPanel.EIGHTYPERCENT);
//        ps.add(min + (9 * (range / 10)), XYChartPanel.NINETYPERCENT);
//        ps.add(max, XYChartPanel.ALLPERCENT);
//        }
//        
//        NumberAxis lAxis = new NumberAxis();
//        
//        lAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
//
//        psl = new PaintScaleLegend(ps, lAxis);
//
//        psl.setBackgroundPaint(new Color(255, 255, 255, 255));
//        
//        psl.setPosition(RectangleEdge.RIGHT);
//        psl.setMargin(4, 4, 40, 4);
//        psl.setAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
//        
//       }
//        
//        Ellipse2D shape = new Ellipse2D.Double(0, 0, size, size);
//        Ellipse2D shapeGate = new Ellipse2D.Double(-2, -2, size + 4, size + 4);
//      
//        renderer.setDefaultShape(shape);
//        rendererGate.setDefaultShape(shapeGate);
//        
//
//        NumberAxis xAxis = new NumberAxis("");
//        NumberAxis yAxis = new NumberAxis("");
//
//        xAxis.setAutoRangeIncludesZero(false);
//        yAxis.setAutoRangeIncludesZero(false);
//
//        XYPlot plot = new XYPlot(createXYZDataset((ArrayList) measurements, x, y, l), xAxis, yAxis, renderer);
//        
//        plot.getDomainAxis();
//        plot.getRangeAxis();
//        
//        plot.setDomainPannable(false);
//        plot.setRangePannable(false);
//
//        plot.setRenderer(0, renderer);
//        plot.setRenderer(1, rendererGate);
//
//                if (imageGate) {
//            roiCreated(impoverlay);
//            XYZDataset set = createXYZDataset(ImageGateOverlay, x, y, l);
//            plot.setDataset(1, set);
//           plot.setRenderer(1, new XYShapeRenderer() {
//               @Override
//               protected java.awt.Paint getPaint(XYDataset dataset, int series, int item){
//                   return imageGateOutline;
//               }
//
//        @Override
//        public Shape getItemShape(int row, int col) {
//               return new Ellipse2D.Double(-2, -2, size + 4, size + 4);     
//        }
//    });
//
//        }
//
//        plot.setDataset(0, createXYZDataset((ArrayList) measurements, x, y, l));
//        
//
//
//        try {
//            if (getRangeofData((ArrayList) measurements, x) > Math.pow(impoverlay.getBitDepth(), 2)) {
//                LogAxis logAxisX = new LogAxis();
//                logAxisX.setAutoRange(true);
//                plot.setDomainAxis(logAxisX);
//            }
//
//            if (getRangeofData((ArrayList) measurements, y) > Math.pow(impoverlay.getBitDepth(), 2)) {
//                LogAxis logAxisY = new LogAxis();
//                logAxisY.setAutoRange(true);
//                plot.setRangeAxis(logAxisY);
//            }
//
//        } catch (NullPointerException e) {
//        };
//
//        JFreeChart chart = new JFreeChart("Plot of " + xText + " vs. " + yText, plot);
//
//        chart.removeLegend();
//
//        chart.setBackgroundPaint(new Color(255, 255, 255, 255));
//        
//        //LUT 
//        if(l >= 0){
//        chart.addSubtitle(psl);
//        }
//
//        //notifiyUpdatePlotWindowListeners();
//        return new ChartPanel(chart, true, true, false, false, true);
//    }

    private double getRangeofData(ArrayList measurements, int x) {

        ListIterator<ArrayList<Number>> litr = measurements.listIterator();

        //ArrayList<Number> al = new ArrayList<Number>();

        Number low = Math.pow(impoverlay.getBitDepth(), 2);
        Number high = 0;
        Number test;

        while (litr.hasNext()) {
            try {
                ArrayList<Number> al = litr.next();
                
                Number value = al.get(x);
                
                if (value.floatValue() < low.floatValue()) {
                    low = value;
                }
                if (value.floatValue() > high.floatValue()) {
                    high = value;
                }
            } catch (NullPointerException e) {
            }
        }
        return high.longValue() - low.longValue();

    }

    private double getMaximumOfData(ArrayList measurements, int l) {

        ListIterator<ArrayList> litr = measurements.listIterator();


        Number high = 0;

        while (litr.hasNext()) {
            try {
                
                ArrayList<Number> al = litr.next();
              
                if (al.get(l).floatValue() > high.floatValue()) {
                    high = al.get(l).floatValue();
                }
            } catch (NullPointerException e) {
            }
        }
        return high.longValue();

    }

    private double getMinimumOfData(ArrayList measurements, int l) {

        ListIterator<ArrayList> litr = measurements.listIterator();

        //ArrayList<Number> al = new ArrayList<Number>();
        Number low = getMaximumOfData(measurements, l);

        while (litr.hasNext()) {
            try {
                ArrayList<Number> al = litr.next();
                if (al.get(l).floatValue() < low.floatValue()) {
                    low = al.get(l).floatValue();
                }
            } catch (NullPointerException e) {
            }
        }
        return low.longValue();
    }
    
    
    private DefaultXYZDataset createXYZDataset(ArrayList results, 
            String x, String y, String l, int lflag) {
        
        DefaultXYZDataset result = new DefaultXYZDataset();
        int counter = 0;
        
//        ArrayList results = H2DatabaseEngine.getColumns3D( 
//                vtea._vtea.H2_MEASUREMENTS_TABLE, x, y, l);
//        
        //System.out.println("PROFILING: SQL records found: " + results.size());

        double[] xCorrected = new double[results.size()];
        double[] yCorrected = new double[results.size()];
        double[] lCorrected = new double[results.size()];

        ListIterator<ArrayList<Number>> litr = results.listIterator();

        while (litr.hasNext()) {
            
            ArrayList<Number> values = litr.next();
            
            //System.out.println("PROFILING: values: " + values.get(0).doubleValue()
            //+", " + values.get(1).doubleValue() + ", " + values.get(2).doubleValue());
            
            //MicroObjectModel volume = (MicroObjectModel) litr.next();
            xCorrected[counter] = values.get(0).doubleValue();
            yCorrected[counter] = values.get(1).doubleValue(); 
            if(lflag >= 0){
                lCorrected[counter] = values.get(2).doubleValue();
            }
            counter++;
        }

        double[][] series = new double[][]{xCorrected, yCorrected, lCorrected};
        result.addSeries("first", series);

        return result;
    }


//    private DefaultXYZDataset createXYZDataset(
//            ArrayList<ArrayList<Number>> measurements, int x, int y, int l) {
//        
//        DefaultXYZDataset result = new DefaultXYZDataset();
//        int counter = 0;
//
//        double[] xCorrected = new double[measurements.size()];
//        double[] yCorrected = new double[measurements.size()];
//        double[] lCorrected = new double[measurements.size()];
//
//        ListIterator<ArrayList<Number>> litr = measurements.listIterator();
//
//        while (litr.hasNext()) {
//            
//            ArrayList<Number> values = litr.next();
//            
//            //MicroObjectModel volume = (MicroObjectModel) litr.next();
//            xCorrected[counter] = values.get(x).doubleValue();
//            yCorrected[counter] = values.get(y).doubleValue(); 
//            if(l >= 0){
//                lCorrected[counter] = values.get(l).doubleValue();
//            }
//            counter++;
//        }
//
//        double[][] series = new double[][]{xCorrected, yCorrected, lCorrected};
//        result.addSeries("first", series);
//
//        return result;
//    }

    public void setPointSize(int size) {
        this.size = size;
    }

    public ChartPanel getChartPanel() {
        return chartPanel;
    }

    public void setImageGate(boolean gate) {
        imageGate = gate;
    }

    public ChartPanel getUpdatedChartPanel() {
       process(connection, xValues, yValues, lValues, xValuesText, yValuesText, lValuesText);
        return chartPanel;
    }

    public void setChartPanelRanges(int axis, double low, double high) {
        XYPlot plot = (XYPlot) this.chartPanel.getChart().getPlot();
        ValueAxis newaxis = new NumberAxis();
        newaxis.setLowerBound(low);
        newaxis.setUpperBound(high);
        if (axis == XYChartPanel.XAXIS) {
            plot.setDomainAxis(newaxis);
        } else if (axis == XYChartPanel.YAXIS) {
            plot.setRangeAxis(newaxis);
        }
    }

    @Override
    public void roiModified(ImagePlus ip, int i) {
        roiCreated(ip);
    }
    
   
    public void roiMoved() {
        
    }
    
    
    public void roiCreated(ImagePlus ip) {
        ImageGateOverlay.clear();
        
        ArrayList result = new ArrayList();
//        ArrayList<MicroObject> resultFinal = new ArrayList<MicroObject>();
//        ArrayList<MicroObject> volumes = (ArrayList) objects;
//        MicroObjectModel volume;
        
        Roi path = ip.getRoi();
        
        int c = 0;
        
                int xValue = 0;
                int yValue = 0;
                
                ArrayList<ArrayList> resultKey = 
                H2DatabaseEngine.getObjectsInRange2DSubSelect(vtea._vtea.H2_MEASUREMENTS_TABLE, 
                xValuesText,
                yValuesText,
                lValuesText,
                "PosX", path.getBounds().getX(), 
                path.getBounds().getX()+path.getBounds().getWidth(), 
                "PosY", path.getBounds().getY(), 
                path.getBounds().getY()+path.getBounds().getHeight());
          
                ListIterator<ArrayList> itr = resultKey.listIterator();
                try {
                   
                    while(itr.hasNext()){

                        ArrayList inside = itr.next();

                        xValue = ((Number)inside.get(3)).intValue();
                        yValue = ((Number)inside.get(4)).intValue();
                        
                        if (path.contains(xValue, yValue)) {
                            result.add(inside);
                          
                        }
                    }
                } catch (NullPointerException e) {
                }
                
                ImageGateOverlay.addAll(result);
                
 
       
                
        //'result' is a list of image gated

//        ListIterator<ArrayList> Result_itr = result.listIterator();
//        
//        while (Result_itr.hasNext()) {
//           ArrayList al = Result_itr.next();
//            if (ip.getRoi().contains(((Number)al.get(1)).intValue(),((Number)al.get(2)).intValue())) {
//                ImageGateOverlay.add(al);
//            }
//            c++;
//        }
        
//            while (itr.hasNext()) {
//           ArrayList al = itr.next();
//            if (ip.getRoi().contains(((Number)m.getCentroidX()).intValue(),((Number) m.getCentroidY()).intValue())) {
//                ImageGateOverlay.add((ArrayList<Number>)measurements.get(c));
//            }
//            c++;
//        }
        notifiyUpdatePlotWindowListeners();
    }

    public void roiDeleted() {
        ImageGateOverlay.clear();
       this.imageGate = false;
       process(connection, xValues, yValues, lValues, xValuesText, yValuesText, lValuesText);
        
    }

    public void setOverlayImage(ImagePlus ip) {
        impoverlay = ip;
    }

    public void addUpdatePlotWindowListener(UpdatePlotWindowListener listener) {

        UpdatePlotWindowListeners.add(listener);
    }

    public void notifiyUpdatePlotWindowListeners() {
        for (UpdatePlotWindowListener listener : UpdatePlotWindowListeners) {
            listener.onUpdatePlotWindow();
        }
    }

}
