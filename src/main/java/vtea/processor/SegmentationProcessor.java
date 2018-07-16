/*
 * Copyright (C) 2017 SciJava
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
import ij.ImageStack;
import ij.plugin.ChannelSplitter;
import ij.plugin.RGBStackMerge;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.renjin.sexp.BuiltinFunction;
import org.scijava.plugin.Plugin;
import static vtea._vtea.MORPHOLOGICALMAP;
import static vtea._vtea.SEGMENTATIONMAP;
import vtea.imageprocessing.AbstractImageProcessing;
import vtea.objects.Segmentation.AbstractSegmentation;
import vtea.objects.layercake.SingleThresholdDataModel;



/**
 *
 * @author sethwinfree
 * 
 * 
 */
@Plugin(type = Processor.class)
public class SegmentationProcessor extends AbstractProcessor {
    
    ImagePlus impOriginal;
    ImagePlus impPreview;
    ArrayList protocol;
    int channelProcess;
    
    private ImageStack[] imageStackArray;
    private ArrayList volumes;
    
    
    public SegmentationProcessor(){
        
    VERSION = "0.0";
    AUTHOR = "Seth Winfree";
    COMMENT = "Processor for segmentation processing";
    NAME = "Segmentation Processor";
    KEY = "SegmentationProcessor";
    
    }
    
    public SegmentationProcessor(String str, ImagePlus imp, ArrayList p){
        
    VERSION = "0.0";
    AUTHOR = "Seth Winfree";
    COMMENT = "Processor for segmentation processing";
    NAME = "Segmentation Processor";
    KEY = "SegmentationProcessor";
    
    impOriginal = imp;
    protocol = p;
    key = str;
    
    }

    @Override
    protected Void doInBackground() throws Exception {
         
        ProcessManager(protocol);
      return null;  
      }

    @Override
    public int process(ArrayList al, String... str) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    private void ProcessManager(ArrayList protocol) {
        
          
         /**segmentation and measurement protocol redefining.
         * 0: title text, 1: method (as String), 2: channel, 3: ArrayList of JComponents used 
         * for analysis 4: ArrayList of Arraylist for morphology determination
         */
        
        //get class
        //pass protocol and imageplus to segmentation
        


            Object iImp = new Object();

            try {
                Class<?> c;
                
                c = Class.forName(SEGMENTATIONMAP.get(protocol.get(1)));
                Constructor<?> con;
                try {
                    con = c.getConstructor();
                    iImp = con.newInstance();  
                    
                             System.out.println("PROFILING: Instance of " + SEGMENTATIONMAP.get(protocol.get(1)) + ", created.");
     

                } catch ( NullPointerException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    System.out.println("EXCEPTION: new instance decleration error... ");
                }

            } catch (NullPointerException | ClassNotFoundException ex) {
                System.out.println("EXCEPTION: new class decleration error... ");
            }
           try{   
            ((AbstractSegmentation)iImp).process(getInterleavedStacks(impOriginal), protocol, false);
            
                        } catch (Exception ex) {
                 ex.printStackTrace();         
            }
           
           //morphology processor
    
           ArrayList values = (ArrayList) MORPHOLOGICALMAP.values();
           
           Iterator<String> itr = values.iterator();
           
            iImp = new Object();
            
            while(itr.hasNext()){

            try {
                Class<?> c;
                
                c = Class.forName((String)itr.next());
                Constructor<?> con;
                try {
                    con = c.getConstructor();
                    iImp = con.newInstance();  
              
                } catch ( NullPointerException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    System.out.println("EXCEPTION: new instance decleration error... ");
                }

            } catch (NullPointerException | ClassNotFoundException ex) {
                System.out.println("EXCEPTION: new class decleration error... ");
            }
           try{  
               //LOOP THROUGH ALL MICROOBJECTS
           
            //((AbstractMorphology)iImp)
            
                        } catch (Exception ex) {
                 ex.printStackTrace();         
            }
           
            }
           
            

            firePropertyChange("progress", 0, 100);
            firePropertyChange("segmentationDone", key, "Segmentation done...  ");
            

           
           
           //System.out.println("PROFILING: Done processing method.");
          
    }
    

    @Override
    public String getChange() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void done() {
        
        
        
        
    }

    
    public ArrayList getObjects() {
        return volumes;
    }
    
    public ArrayList getProtocol() {
        return protocol;
    }
    
    
    public static ImageStack[] getInterleavedStacks(ImagePlus imp) {
        ImageStack[] stacks = new ImageStack[imp.getNChannels()];
        ImageStack stack = imp.getImageStack();
        for (int m = 0; m <= imp.getNChannels() - 1; m++) {
            stacks[m] = new ImageStack(imp.getWidth(), imp.getHeight());
            for (int n = m; n <= imp.getStackSize() - 1; n += imp.getNChannels()) {
                stacks[m].addSlice(stack.getProcessor(n + 1));
            }
        }	
        return stacks;
    }
    
}
