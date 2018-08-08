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
package vtea.feature;

import vtea.feature.listeners.RepaintFeatureListener;
import vtea.protocol.listeners.RebuildPanelListener;
import vtea.protocol.blockstepgui.FeatureStepBlockGUI;
import vtea.protocol.listeners.UpdateProgressListener;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.ListIterator;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import javax.swing.JOptionPane;
import vtea.processor.FeatureProcessor;
import vtea.protocol.listeners.DeleteBlockListener;

/**
 * Window for analysis methods. Keeps track of analysis methods that are added 
 * and removed and submits the methods with parameters to get results. 
 * @author drewmcnutt
 */
public class FeatureFrame extends javax.swing.JFrame implements PropertyChangeListener, UpdateProgressListener, RebuildPanelListener, DeleteBlockListener, RepaintFeatureListener{
    
    protected ArrayList<FeatureStepBlockGUI> FeatureStepsList;
    ArrayList availabledata;
    double[][] features;
    int nvol;           //number of volumes
    
    protected GridLayout FeatureLayout = new GridLayout(4, 1, 0, 0);

    /**
     * Constructor.
     * Creates new form FeatureFrame
     * @param AvailableData
     * @param table 
     */
    public FeatureFrame(ArrayList AvailableData, double[][] table) {
        ArrayList pos = new ArrayList();
        pos.add("PosX");
        pos.add("PosY");
        pos.add("PosZ");
        
        AvailableData.addAll(0,pos);
        this.availabledata = AvailableData;
        this.features = table;
        this.nvol = table.length;
                
        this.FeatureStepsList = new ArrayList<>();
        initComponents();
        FeatureStepsPanel.setLayout(FeatureLayout);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        FeatureAnalysis = new javax.swing.JPanel();
        Feature_Header = new javax.swing.JPanel();
        AnalyzeDataText = new javax.swing.JLabel();
        FeatureLabel = new javax.swing.JLabel();
        AddStep = new javax.swing.JButton();
        DeleteAllSteps = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        exploreText = new javax.swing.JLabel();
        Feature_Panel = new javax.swing.JPanel();
        FeatureStepsPanel = new javax.swing.JPanel();
        FeatureGo = new javax.swing.JButton();
        ProgressPanel = new javax.swing.JPanel();
        FeatureComment = new javax.swing.JLabel();
        VTEAProgressBar = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(380, 360));
        setResizable(false);
        setSize(new java.awt.Dimension(30, 381));
        getContentPane().setLayout(new java.awt.FlowLayout());

        jPanel1.setMaximumSize(new java.awt.Dimension(445, 381));
        jPanel1.setMinimumSize(new java.awt.Dimension(445, 381));
        jPanel1.setPreferredSize(new java.awt.Dimension(380, 381));
        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel1formKeyPressed(evt);
            }
        });
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        FeatureAnalysis.setMaximumSize(new java.awt.Dimension(750, 353));
        FeatureAnalysis.setMinimumSize(new java.awt.Dimension(750, 353));
        FeatureAnalysis.setLayout(new java.awt.GridBagLayout());

        Feature_Header.setBackground(new java.awt.Color(204, 204, 204));
        Feature_Header.setForeground(new java.awt.Color(102, 102, 102));
        Feature_Header.setAlignmentX(0.0F);
        Feature_Header.setAlignmentY(0.0F);
        Feature_Header.setMaximumSize(new java.awt.Dimension(440, 36));
        Feature_Header.setMinimumSize(new java.awt.Dimension(440, 36));
        Feature_Header.setPreferredSize(new java.awt.Dimension(440, 36));
        Feature_Header.setLayout(new java.awt.GridBagLayout());

        AnalyzeDataText.setText("Analyze Data...");
        AnalyzeDataText.setMaximumSize(new java.awt.Dimension(120, 16));
        AnalyzeDataText.setMinimumSize(new java.awt.Dimension(100, 16));
        AnalyzeDataText.setPreferredSize(new java.awt.Dimension(100, 16));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        Feature_Header.add(AnalyzeDataText, gridBagConstraints);

        FeatureLabel.setBackground(new java.awt.Color(0, 0, 0));
        FeatureLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        FeatureLabel.setText("Feature");
        FeatureLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        FeatureLabel.setMaximumSize(new java.awt.Dimension(150, 28));
        FeatureLabel.setMinimumSize(new java.awt.Dimension(150, 28));
        FeatureLabel.setPreferredSize(new java.awt.Dimension(90, 28));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        Feature_Header.add(FeatureLabel, gridBagConstraints);

        AddStep.setBackground(new java.awt.Color(204, 204, 204));
        AddStep.setForeground(new java.awt.Color(102, 102, 102));
        AddStep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/list-add-3 2.png"))); // NOI18N
        AddStep.setToolTipText("Add an analysis method.");
        AddStep.setMaximumSize(new java.awt.Dimension(34, 34));
        AddStep.setMinimumSize(new java.awt.Dimension(34, 34));
        AddStep.setPreferredSize(new java.awt.Dimension(34, 34));
        AddStep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddStepActionPerformed(evt);
            }
        });
        Feature_Header.add(AddStep, new java.awt.GridBagConstraints());

        DeleteAllSteps.setBackground(new java.awt.Color(204, 204, 204));
        DeleteAllSteps.setForeground(new java.awt.Color(102, 102, 102));
        DeleteAllSteps.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/edit-clear-list_24.png"))); // NOI18N
        DeleteAllSteps.setToolTipText("Delete all analysis methods.");
        DeleteAllSteps.setEnabled(false);
        DeleteAllSteps.setMaximumSize(new java.awt.Dimension(34, 34));
        DeleteAllSteps.setMinimumSize(new java.awt.Dimension(34, 34));
        DeleteAllSteps.setPreferredSize(new java.awt.Dimension(34, 34));
        DeleteAllSteps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteAllStepsActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        Feature_Header.add(DeleteAllSteps, gridBagConstraints);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        Feature_Header.add(jPanel2, new java.awt.GridBagConstraints());

        exploreText.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        exploreText.setForeground(new java.awt.Color(153, 153, 153));
        exploreText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        exploreText.setText("...explore");
        exploreText.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        exploreText.setPreferredSize(new java.awt.Dimension(85, 40));
        exploreText.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Feature_Header.add(exploreText, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 7;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        FeatureAnalysis.add(Feature_Header, gridBagConstraints);

        Feature_Panel.setBackground(new java.awt.Color(204, 204, 204));
        Feature_Panel.setForeground(new java.awt.Color(102, 102, 102));
        Feature_Panel.setAlignmentX(0.0F);
        Feature_Panel.setAlignmentY(0.0F);
        Feature_Panel.setMaximumSize(new java.awt.Dimension(440, 360));
        Feature_Panel.setMinimumSize(new java.awt.Dimension(440, 360));
        Feature_Panel.setPreferredSize(new java.awt.Dimension(440, 300));
        Feature_Panel.setRequestFocusEnabled(false);

        FeatureStepsPanel.setBackground(vtea._vtea.ACTIONPANELBACKGROUND);
        FeatureStepsPanel.setPreferredSize(new java.awt.Dimension(160, 245));

        javax.swing.GroupLayout FeatureStepsPanelLayout = new javax.swing.GroupLayout(FeatureStepsPanel);
        FeatureStepsPanel.setLayout(FeatureStepsPanelLayout);
        FeatureStepsPanelLayout.setHorizontalGroup(
            FeatureStepsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );
        FeatureStepsPanelLayout.setVerticalGroup(
            FeatureStepsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 245, Short.MAX_VALUE)
        );

        FeatureGo.setBackground(vtea._vtea.BUTTONBACKGROUND);
        FeatureGo.setText("Find Features");
        FeatureGo.setToolTipText("Find segmented objects.");
        FeatureGo.setEnabled(false);
        FeatureGo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FeatureGoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Feature_PanelLayout = new javax.swing.GroupLayout(Feature_Panel);
        Feature_Panel.setLayout(Feature_PanelLayout);
        Feature_PanelLayout.setHorizontalGroup(
            Feature_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Feature_PanelLayout.createSequentialGroup()
                .addGroup(Feature_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Feature_PanelLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(FeatureGo, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Feature_PanelLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(FeatureStepsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        Feature_PanelLayout.setVerticalGroup(
            Feature_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Feature_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(FeatureStepsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(FeatureGo)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        FeatureAnalysis.add(Feature_Panel, gridBagConstraints);

        jPanel1.add(FeatureAnalysis);

        ProgressPanel.setMaximumSize(new java.awt.Dimension(380, 30));
        ProgressPanel.setMinimumSize(new java.awt.Dimension(380, 30));
        ProgressPanel.setPreferredSize(new java.awt.Dimension(380, 30));
        ProgressPanel.setRequestFocusEnabled(false);
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING, 1, 1);
        flowLayout1.setAlignOnBaseline(true);
        ProgressPanel.setLayout(flowLayout1);

        FeatureComment.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        ProgressPanel.add(FeatureComment);

        VTEAProgressBar.setPreferredSize(new java.awt.Dimension(200, 20));
        ProgressPanel.add(VTEAProgressBar);

        jPanel1.add(ProgressPanel);

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Adds analysis step.
     * @param evt clicking of AddStep button
     */
    private void AddStepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddStepActionPerformed
        //this.setVisible(false);
        FeatureStepBlockGUI block = new FeatureStepBlockGUI("Feature Step", "", Color.LIGHT_GRAY,FeatureStepsList.size() + 1, availabledata, nvol);
        block.addDeleteBlockListener(this);
        block.addRebuildPanelListener(this);
        //this.notifyRepaintFeatureListeners();

        FeatureStepsPanel.add(block.getPanel());
        FeatureStepsPanel.repaint();
        //this.repaint();
        FeatureStepsList.add(block);

        if (FeatureStepsList.size() <= 2) {
            AddStep.setEnabled(true);
        }
        if (FeatureStepsList.size() >= 4) {
            AddStep.setEnabled(false);
        }
        if (!FeatureStepsList.isEmpty()){
            DeleteAllSteps.setEnabled(true);
        }
        repaintFeature();
        this.setVisible(true);
        
        FeatureGo.setEnabled(true);
    }//GEN-LAST:event_AddStepActionPerformed
    
    /**
     * Delete all analysis steps.
     * @param evt clicking of DeleteAll button
     */
    private void DeleteAllStepsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteAllStepsActionPerformed
        FeatureStepsList.clear();
        FeatureStepsPanel.removeAll();
        AddStep.setEnabled(true);
        DeleteAllSteps.setEnabled(false);
        FeatureGo.setEnabled(false);
        VTEAProgressBar.setValue(0);
        FeatureStepsPanel.repaint();
        //pack();
    }//GEN-LAST:event_DeleteAllStepsActionPerformed
    
    /**
     * Sets up for analysis.
     * @param evt clicking of FeatureGo button
     */
    private void FeatureGoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FeatureGoActionPerformed
        
        findFeatures();
        VTEAProgressBar.setValue(0);
        
    }//GEN-LAST:event_FeatureGoActionPerformed

    private void jPanel1formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1formKeyPressed

    }//GEN-LAST:event_jPanel1formKeyPressed

    /**
     * Rebuilds the panel.
     * @param type 
     */
    @Override
    public void rebuildPanel(int type) {
        this.RebuildPanelFeature();
    }
    
    /**
     * Deletes specific step.
     * @param type useless(left over from DeleteBlockListener)
     * @param position the order number of the step
     */
    @Override
    public void deleteBlock(int type, int position) {
        this.deleteFeatureStep(position);
        if (FeatureStepsList.isEmpty()){
            FeatureGo.setEnabled(false);
        }
    }
    
    /**
     * Sets progress bar reading.
     * @param text string next to progress bar
     * @param min minimum value for progress bar
     * @param max maximum value for progress bar
     * @param position value to set progress bar to
     */
    @Override
    public void changeProgress(String text, int min, int max, int position) {      
        VTEAProgressBar.setMinimum(min);
        VTEAProgressBar.setMaximum(max);
        VTEAProgressBar.setValue(position);
        FeatureComment.setText(text);    
    }
    
    /**
     * Changes progress bar or text.
     * @param evt details what the property change is for
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("progress")) {
            int progress = (Integer) evt.getNewValue();
            VTEAProgressBar.setValue(progress);
            FeatureComment.setText(String.format(
                    "Completed %d%%...\n", progress));
        } 
        if (evt.getPropertyName().equals("comment")){
            FeatureComment.setText((String)evt.getNewValue());
        }
        if (evt.getPropertyName().equals("escape") && !(Boolean)evt.getNewValue()){
            System.out.println("PROFILING: Error is processing, thread terminated early...");
        }
    }

    /**
     * Repaint the window.
     */
    @Override
    public void repaintFeature(){
        this.repaint();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddStep;
    private javax.swing.JLabel AnalyzeDataText;
    private javax.swing.JButton DeleteAllSteps;
    private javax.swing.JPanel FeatureAnalysis;
    public javax.swing.JLabel FeatureComment;
    public javax.swing.JButton FeatureGo;
    private javax.swing.JLabel FeatureLabel;
    public javax.swing.JPanel FeatureStepsPanel;
    private javax.swing.JPanel Feature_Header;
    private javax.swing.JPanel Feature_Panel;
    private javax.swing.JPanel ProgressPanel;
    public javax.swing.JProgressBar VTEAProgressBar;
    private javax.swing.JLabel exploreText;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
    
    /**
     * Reconstruct the blocks.
     */
    public void RebuildPanelFeature() {
        FeatureStepBlockGUI sb;
        ListIterator litr = FeatureStepsList.listIterator();
        while (litr.hasNext()) {
            sb = (FeatureStepBlockGUI) litr.next();
            sb.setPosition(FeatureStepsList.indexOf(sb) + 1);
            FeatureStepsPanel.add(sb.getPanel());   
        }
    }
    
    /**
     * Extracts the Steps.
     * @param sb_al
     * @return 
     */
    static public ArrayList extractSteps(ArrayList sb_al) {

        ArrayList<ArrayList> Result = new ArrayList<>();
        
        FeatureStepBlockGUI fsb;
        ListIterator<Object> litr = sb_al.listIterator();
        while (litr.hasNext()) {
            fsb = (FeatureStepBlockGUI) litr.next();
            Result.add(fsb.getVariables());
        }

        return Result;
    }
    
    /**
     * Retrieve all of the steps.
     * @return list of all of the steps
     */
    public ArrayList getFeatureSteps() {
        return this.FeatureStepsList;
    }
    
    /**
     * Deletes specific step.
     * @param position the position of the step to delete
     */
    private void deleteFeatureStep(int position) {

        //remove from FeatureStepsList
        FeatureStepsList.remove(position - 1);
        FeatureStepsList.trimToSize();
        
        FeatureStepsPanel.removeAll();
        FeatureStepsPanel.setLayout(FeatureLayout);
        
        if (FeatureStepsList.size() < 0) {
        } else {
            RebuildPanelFeature();
        }

        if (FeatureStepsList.size() < 4) {
            AddStep.setEnabled(true);
        }
        if(FeatureStepsList.isEmpty()){
            DeleteAllSteps.setEnabled(false);
        }

        FeatureStepsPanel.repaint();
        pack();

    }
    
    /**
     * Starts the analysis of the features.
     */
    private void findFeatures(){
        FeatureComment.setText("Finding features...");
        ArrayList<ArrayList> protocol = new ArrayList<>();
        //get the arraylist, decide the nubmer of steps, by .steps to do and whether this is a preview or final by .type
        protocol = extractSteps(FeatureStepsList);
        
        FeatureProcessor fp = new FeatureProcessor(features, protocol);
        fp.addPropertyChangeListener(this);
        fp.execute();
        
    }
    
    private ArrayList<Integer> examineColumns(){
        /*
            Makes a new 2D array with the rows equal to every measurement type
            (Makes the transpose of the matrix)
        */
        double[][] columns = new double[features[0].length][features.length];
        for(int i = 0; i < features.length; i++){
            for(int j = 0; j < features[i].length; j++){
                columns[j][i] = (double)features[i][j];
            }
        }
        
        /*
            Compares all measurements and add all duplicate columns to the dupl
            ArrayList if they are not already there
        */
        ArrayList<Integer> dupl = new ArrayList();
        for(int j = 4; j < columns.length; j++){
            for(int k = j + 1; k < columns.length; k++){
                if(java.util.Arrays.equals(columns[j], columns[k]) && !(dupl.contains(k))){
                    dupl.add(k);
                }
                    
            }
        }
        
        Collections.sort(dupl);         //sorts the list into ascending order
        
        return dupl;
    }
    
    /**
     * Gives warning dialog to the user about duplicate columns in the dataset
     * and allows the user to remove them.
     */
    public void giveWarning(){
        int response;
        
        ArrayList<Integer> dupl = examineColumns();

        if(dupl.isEmpty())
            response = JOptionPane.NO_OPTION;
        else{
            StringBuilder sb = new StringBuilder("The following columns are duplicates of existing columns: \n");
            int count = 0;
            for(Integer col: dupl){
                sb.append(availabledata.get(col - 1));
                count++;
                if(count != 0){
                    sb.append(", ");
                    if(count % 6 == 0)
                        sb.append("\n");
                }

            }

            sb.append("\nWould you like to delete these columns from the dataset?");
            response = JOptionPane.showConfirmDialog(this, sb, "Duplicate Columns Detected", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        }
        
        if(response == JOptionPane.YES_OPTION)
            deleteColumns(dupl);
    }
    
    private void deleteColumns(ArrayList<Integer> duplicates){
        double[][] newfeat = new double[features.length][features[0].length - duplicates.size()];
        
        int count = 0;
        int j = 0;
        int curcol = 0;
        
        for(Integer col: duplicates){
                Object removed = availabledata.remove((int)col - count - 1);
//                System.out.println(removed.toString());
                for(int i = 0; i < features.length; i++){
                    j = curcol;
                    for(;j < col;j++){
                        newfeat[i][j-count] = features[i][j];
                    }
                }
                if(j == col){
                    count++;
                    j++;
                    curcol = col + 1;
                }
        }
        
        for(int i = 0; i < features.length; i++){
            for(int k = duplicates.get(duplicates.size() - 1) + 1;k < features[0].length; k++)
                newfeat[i][k-count] = features[i][k];
        }
        
        
        features = newfeat;
    }
}
