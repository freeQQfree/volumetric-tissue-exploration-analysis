/*
 * Copyright (C) 2018 SciJava
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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.imglib2.RealPoint;
import static vtea._vtea.PROCESSINGMAP;
import vtea.imageprocessing.AbstractImageProcessing;
import vtea.objects.layercake.microVolume;
import vtea.objects.measurements.AbstractMeasurement;
import vteaobjects.MicroObject;

/**
 *
 * @author sethwinfree
 */
public class MeasurementProcessor<T extends Number> extends AbstractProcessor {
    
    private ArrayList<MicroObject> objects;
    private ArrayList<ArrayList> measurements;
    private ArrayList<String> features;
    private ArrayList protocol;
    private String key;
    
    
    private ArrayList objectFeatures;
    
    public MeasurementProcessor(String k, ArrayList<MicroObject> obj, ArrayList prt){
        objects = obj;
        protocol = prt;
        key = k;
        
        //protocol what does it look like.
        
        
    }

    @Override
    protected Void doInBackground() throws Exception {
        //get Array of methods
        
        for(int i = 0; i < vtea._vtea.OBJECTMEASUREMENTOPTIONS.length; i++){
            features.add(vtea._vtea.OBJECTMEASUREMENTOPTIONS[i]); 
        }
        
        
        ListIterator listObjects = objects.listIterator();
        
        while(listObjects.hasNext()){
            
            MicroObject object = (MicroObject)listObjects.next();
            
            
            
            ArrayList<RealPoint> points = calculatePoints(object);
            
            ListIterator listFeature = features.listIterator();
        
            while(listFeature.hasNext()){
                String feature = (String)listFeature.next();
                //calculateMeasurement(points, measurements, feature);
            }
        }
        
        
        
        for(int i = 0; i < vtea._vtea.OBJECTMEASUREMENTOPTIONS.length; i++){
            features.add(vtea._vtea.OBJECTMEASUREMENTOPTIONS[i]); 
        }
        
        //set Arraylist of calaculated data features 
        //loop through volumes
            //get Array of RealPoints
            //calculate per array of methods
            //add arraylist to arraylist
        
        
    return null;
    }

    @Override
    public int process(ArrayList al, String... str) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getChange() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private ArrayList<RealPoint> calculatePoints(MicroObject object){
        
        int[] z = object.getPixelsZ();
        int[] x = object.getPixelsX();
        int[] y = object.getPixelsY();
        
        ArrayList<RealPoint> points = new ArrayList();
        
        
        
        for(int i = 0; i < z.length; i++){
            points.add(new RealPoint(x[i], y[i], z[i]));
        }
        return points;
    }
    
    //@Param positions are the points of the object to be measured
    //values is the arraylist for storing measured values.
    
    private Number calculateMeasurement(ArrayList<RealPoint> positions, ArrayList<T> values, String operation){
        
        Object iImp = new Object();
        
        try {
            Class<?> c;
            c = Class.forName(operation);
            Constructor<?> con;
            try {
                con = c.getConstructor();
                iImp = con.newInstance(); 
                return ((AbstractMeasurement)iImp).process(positions, values);

            } catch ( NullPointerException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(ImageProcessingProcessor.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (NullPointerException | ClassNotFoundException ex) {
            Logger.getLogger(ImageProcessingProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public ArrayList getAvailableData() {
        ArrayList al = new ArrayList();

        //microVolume.Analytics;
        protocol.size();
        for (int i = 0; i < protocol.size(); i++) {
            for (int c = 0; c < microVolume.Analytics.length; c++) {
                String derived = new String();
                String text = new String();
                if (i == 0) {
                    derived = " ";
                } else {
                    derived = "_d" + ((ArrayList) protocol.get(i)).get(1) + " ";
                }
                text = "Ch" + ((int)((ArrayList) protocol.get(i)).get(0)+1) + derived + microVolume.Analytics[c];
                al.add(text);
            }
        }
        return al;
    }
    
    public ArrayList getFeatures(){
        return objectFeatures;
    }
    
    public ArrayList<MicroObject> getObjects(){
        return objects;
    }
    
}
