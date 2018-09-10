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
import ij.ImageStack;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ForkJoinPool;
import static java.util.concurrent.ForkJoinTask.invokeAll;
import java.util.concurrent.RecursiveAction;
import org.scijava.plugin.Plugin;
import static vtea._vtea.MORPHOLOGICALMAP;
import static vtea._vtea.SEGMENTATIONMAP;
import static vtea._vtea.getInterleavedStacks;
import vtea.objects.Segmentation.AbstractSegmentation;
import vtea.objects.Segmentation.LayerCake3DSingleThreshold;
import vtea.objects.layercake.microRegion;
import vtea.objects.morphology.AbstractMorphology;
import vteaobjects.MicroObject;

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

    public SegmentationProcessor() {

        VERSION = "0.0";
        AUTHOR = "Seth Winfree";
        COMMENT = "Processor for segmentation processing";
        NAME = "Segmentation Processor";
        KEY = "SegmentationProcessor";

    }

    public SegmentationProcessor(String str, ImagePlus imp, ArrayList p) {

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

        firePropertyChange("progress", 0, 1);
        firePropertyChange("comment", key, ("Starting segmentation...  "));

        /**
         * segmentation and measurement protocol redefining. 0: title text, 1:
         * method (as String), 2: channel, 3: ArrayList of JComponents used for
         * analysis 4: ArrayList of Arraylist for morphology determination
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

                //System.out.println("PROFILING: Instance of " + SEGMENTATIONMAP.get(protocol.get(1)) + ", created.");
            } catch (NullPointerException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                System.out.println("EXCEPTION: new instance decleration error... ");
            }

        } catch (NullPointerException | ClassNotFoundException ex) {
            System.out.println("EXCEPTION: new class decleration error... ");
        }

        ((AbstractSegmentation) iImp).addListener(this);

        try {
            ((AbstractSegmentation) iImp).process(getInterleavedStacks(impOriginal), protocol, false);

            volumes = ((AbstractSegmentation) iImp).getObjects();
        } catch (Exception ex) {

        }

        long start_time = System.currentTimeMillis();

        ProcessorForkPool pfp = new ProcessorForkPool(protocol, 0, volumes.size());
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(pfp);

        long end_time = System.currentTimeMillis();

        System.out.println("PROFILING: Morphological operations time: " + (end_time - start_time));

        //morphology processor
//        //System.out.println("PROFILING: Processing " + MORPHOLOGICALMAP.size() +" morphological filters.");
//        Collection values = MORPHOLOGICALMAP.values();
//
//        //System.out.println("PROFILING: processing " + MORPHOLOGICALMAP.size() +" morphological filters.");
//        Iterator<String> itr = values.iterator();
//
//        iImp = new Object();
//
//        while (itr.hasNext()) {
//
//            try {
//                Class<?> c;
//
//                String str = (String) itr.next();
//
//                c = Class.forName(str);
//                Constructor<?> con;
//                try {
//                    con = c.getConstructor();
//                    iImp = con.newInstance();
//
//                    //System.out.println("PROFILING: Instance of " + str + ", created.");
//                } catch (NullPointerException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
//                    System.out.println("EXCEPTION: new instance decleration error... ");
//                }
//
//            } catch (NullPointerException | ClassNotFoundException ex) {
//                System.out.println("EXCEPTION: new class decleration error... ");
//            }
//            try {
//                //LOOP THROUGH ALL MICROOBJECTS with MorphologyProcessor.
//                //this is hardwired for mask only and GUI is NOT considered
//                ListIterator<MicroObject> itr_vol = volumes.listIterator();
//
//                int size = volumes.size();
//                int count = 1;
//
//                double progress = 1;
//
//                MicroObject obj;
//                ArrayList<ArrayList<Number>> result;
//                int morph = 0;
//
//                while (itr_vol.hasNext()) {
//                    obj = itr_vol.next();
//
//                    progress = 100 * ((double) count / (double) size);
//                    firePropertyChange("progress", 0, ((int) progress));
//                    firePropertyChange("comment", key, ("Performing morphological operations...  "));
//
//                    count++;
//
//                    result = ((AbstractMorphology) iImp).process(obj.getPixelsX(), obj.getPixelsY(), obj.getPixelsZ(), "", "1");
//
//                    ListIterator<ArrayList<Number>> itr_der = result.listIterator();
//
//                    int voxel = 0;
//                    int[] x = new int[result.size()];
//                    int[] y = new int[result.size()];
//                    int[] z = new int[result.size()];
//
//                    ArrayList<Integer> xAr = new ArrayList();
//                    ArrayList<Integer> yAr = new ArrayList();
//                    ArrayList<Integer> zAr = new ArrayList();
//
//                    while (itr_der.hasNext()) {
//                        ArrayList<Number> positions = itr_der.next();
//                        xAr.add((Integer) positions.get(0));
//                        yAr.add((Integer) positions.get(1));
//                        zAr.add((Integer) positions.get(2));
//                    }
//
//                    obj.setMorphological(String.valueOf(morph), xAr, yAr, zAr);
//
//                    morph++;
//
//                }
//
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
        firePropertyChange("progress", 0, 100);
        firePropertyChange("segmentationDone", key, "Segmentation done...  ");
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

    @Override
    public void FireProgressChange(String str, double db) {

        //System.out.println("Building progress..." + db);
        firePropertyChange("progress", 0, (int) db);
        firePropertyChange("comment", key, str);
    }

    private class ProcessorForkPool extends RecursiveAction {

        private int start;
        private int stop;

        private ArrayList protocol;

        ProcessorForkPool(ArrayList p, int start, int stop) {

            this.start = start;
            this.stop = stop;
            this.protocol = p;

        }

        @Override
        protected void compute() {

            long processors = Runtime.getRuntime().availableProcessors();
            processors = 1;

            long length = volumes.size() / processors;

            if (volumes.size() < processors) {
                length = volumes.size();
            }
            if (stop - start > length) {

                invokeAll(new ProcessorForkPool(protocol, start, start + ((stop - start) / 2)),
                        new ProcessorForkPool(protocol, start + ((stop - start) / 2) + 1, stop));

            } else {
                doThis();
                //doThat();
            }
        }

        private void doThis() {

            //System.out.println("PROFILING: processing " + MORPHOLOGICALMAP.size() +" morphological filters.");
            /**
             * segmentation and measurement protocol redefining. 0: title text,
             * 1: method (as String), 2: channel, 3: ArrayList of JComponents
             * used for analysis 4: ArrayList of Arraylist for morphology
             * determination
             */
            /**
             * morphological determinants 0:Channel 1:Operation 2:Value
             */
            //descriptors for derived volumes
            int size = volumes.size();
            int count = 1;

            double progress = 1;

            ArrayList<ArrayList> morphologies = (ArrayList) protocol.get(4);

            Iterator itr = morphologies.iterator();

            Object iImp = new Object();

            while (itr.hasNext()) {

                ArrayList morphology = (ArrayList) itr.next();

                String arg = (String) morphology.get(2);

                try {

                    Class<?> c;

                    String str = (String) morphology.get(1);

                    c = Class.forName(MORPHOLOGICALMAP.get(str));
                    Constructor<?> con;

                    con = c.getConstructor();
                    iImp = con.newInstance();

                    
                    ArrayList<ArrayList<Number>> result;
                    int morph = 0;

                    int volumecount = 1;

                    List sub_volumes = volumes.subList(start, stop);

                    ListIterator<MicroObject> itr_vol = sub_volumes.listIterator();

                    while (itr_vol.hasNext()) {
                        MicroObject obj = new MicroObject();
                        obj = itr_vol.next();

                        progress = 100 * ((double) count / (double) size);
                        firePropertyChange("progress", 0, ((int) progress));
                        firePropertyChange("comment", key, ("Performing morphological operations...  "));

                        count++;

                        /**
                         * result. This is an arraylist of arraylist of numbers.
                         *
                         */
                        result = ((AbstractMorphology) iImp).process(obj.getPixelsX(), obj.getPixelsY(), obj.getPixelsZ(), "", arg);
                          
                        ArrayList<Number> xAr = result.get(0);
                        ArrayList<Number> yAr = result.get(1);
                        ArrayList<Number> zAr = result.get(2);
                        
                        int[] x = new int[xAr.size()];
                        int[] y = new int[xAr.size()];
                        int[] z = new int[xAr.size()];
                        
                        for(int i = 0; i < xAr.size(); i++){
                            x[i] = xAr.get(i).intValue();
                            y[i] = yAr.get(i).intValue();
                            z[i] = zAr.get(i).intValue();
                        }

                        obj.setMorphological(String.valueOf(morph), x, y, z);
                        
                                 

                        System.out.println("PROFILING: For object " + volumecount + ", of size " + obj.getPixelCount() + ", added morphology " + (String) morphology.get(1) + "  of size: " + x.length);
               
                        
                        volumecount++;
                        morph++;

                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

        }
    }
}
