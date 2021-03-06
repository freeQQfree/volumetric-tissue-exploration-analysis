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
package vtea.processor;

import ij.ImagePlus;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JWindow;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import org.scijava.plugin.Plugin;
import vtea.gui.CustomTableModel;
import vtea.exploration.plottools.panels.DefaultPlotPanels;
import vtea.exploration.plottools.panels.XYExplorationPanel;
import vtea.jdbc.H2DatabaseEngine;
import vteaexploration.MicroExplorer;

/**
 *
 * @author sethwinfree
 */
@Plugin(type = Processor.class)
public class ExplorerProcessor extends AbstractProcessor {

    ImagePlus impOriginal;
    ArrayList protocol;  //we may want to turn this into a class...

    private ArrayList descriptions;
    private ArrayList descriptionLabels;
    private ArrayList measurements;
    private ArrayList objects;

    private ArrayList plotValues;

    public ExplorerProcessor() {

        VERSION = "0.0";
        AUTHOR = "Seth Winfree";
        COMMENT = "Processor for explorer window.";
        NAME = "Explorer Processor";
        KEY = "ExplorerProcessor";

    }

    /*this constructor should change to:
    
    public ExplorerProcessor(ImagePlus imp, ArrayList volumes, ArrayList measurements)
    
    once SegmentationProcessor exists on its own.
    
     */
    public ExplorerProcessor(String k, ImagePlus imp, ArrayList volumes, ArrayList measurements, ArrayList headers, ArrayList headerLabels) {

        VERSION = "0.0";
        AUTHOR = "Seth Winfree";
        COMMENT = "Processor for explorer window.";
        NAME = "Explorer Processor";
        KEY = "ExplorerProcessor";

        impOriginal = imp;
        objects = volumes;
        this.measurements = measurements;
        descriptions = headers;
        descriptionLabels = headerLabels;
        key = k;

    }

    @Override
    protected Void doInBackground() throws Exception {

        int progress = 0;

        try {

            firePropertyChange("progress", 0, 5);
            firePropertyChange("comment", "", "Starting explorer processing on " + objects.size() + " objects...");

            HashMap<Integer, String> hm = new HashMap<Integer, String>();
            
            for (int i = 0; i < descriptions.size(); i++) {
                hm.put(i, descriptions.get(i).toString());
            }
            
            Connection connection = H2DatabaseEngine.getDBConnection();
            


            
            
            System.out.println("PROFILING: Exploring on dataset: " + key);  
            
            XYExplorationPanel XY = new XYExplorationPanel(key, connection, measurements, descriptions, hm, objects);
            DefaultPlotPanels DPP = new DefaultPlotPanels();

            String title = "Segmentation_" + (impOriginal.getTitle().replace("DUP_", "")).replace(".tif", "");

             MicroExplorer explorer = new MicroExplorer();
//            explorer.setTitle(impOriginal.getTitle().replace("DUP_", ""));
//            explorer.setTitle(explorer.getTitle().replace(".tif", ""));
//            explorer.setTitle(explorer.getTitle().concat("_" + title));

//Get list of data in database

        //Get table of available data... for future use of datasets across 
        //Explorer windows.
            
            
//            JPanel j = new JPanel();
//            JTable t = new JTable();
//            
//             Object[] tableData1 = ((ArrayList)(H2DatabaseEngine.getListOfTables(connection))).toArray();
//             Object[][] tableData = new Object[tableData1.length][3];
//             for(int c = 0; c < tableData1.length; c++){
//                 tableData[c][0] = tableData1[c];
//                 tableData[c][1] = "Custom Name";
//                 tableData[c][2] = false;
//             }
//
//        LocalCustomTableModel ctm = new LocalCustomTableModel();
//        ctm.setData(tableData);
//
//        t = new JTable(ctm);
//
//        t.setShowGrid(true);
//
//        TableColumn column = null;
//        column = t.getColumnModel().getColumn(0);
//        column.setPreferredWidth(50);
//        column = t.getColumnModel().getColumn(1);
//        column.setPreferredWidth(50);
//        column = t.getColumnModel().getColumn(2);
//        column.setPreferredWidth(10);
//       
//        JScrollPane scroll = new JScrollPane(t);
//        scroll.createVerticalScrollBar();
//        scroll.setPreferredSize(new Dimension(200, 180));
//        JFrame frame = new JFrame("Avalaible Datasets");
//        
//        frame.getContentPane().add(scroll, BorderLayout.CENTER);
//        frame.pack();
//        frame.setVisible(true);





//
            explorer.setTitle(title);
            explorer.process(key, impOriginal, title, measurements, XY, DPP, descriptions, descriptionLabels);

            setProgress(100);
            firePropertyChange("comment", "", "Done.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int process(ArrayList al, String... str) {

        return 1;
    }

    @Override
    public String getChange() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}



/**
 *
 * @author sethwinfree
 */
class LocalCustomTableModel extends AbstractTableModel {

    private String[] columnNames = {"Type",
        "Proj", "Do"};
    private Object[][] data = {
        {"Kathy", new Boolean(true)},
        {"John", new Boolean(false)},
        {"Sue", new Boolean(false)},
        {"Jane", new Boolean(false)},
        {"Joe", new Boolean(false)}
    };

    public Object[][] getData() {
        return data;
    }

    public void setColumnNames(String[] columns) {
        columnNames = columns;
    }

    public void setData(Object[][] content) {
        data = content;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
     */
    @Override
    public Class getColumnClass(int c) {
        if (c == 2) {
            return Boolean.class;
        }
        return getValueAt(0, c).getClass();
    }

    /*
         * Don't need to implement this method unless your table's
         * editable.
     */
    @Override
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col < 1) {
            return false;
        } else {
            return true;
        }
    }

    /*
         * Don't need to implement this method unless your table's
         * data can change.
     */
    @Override
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }

}
