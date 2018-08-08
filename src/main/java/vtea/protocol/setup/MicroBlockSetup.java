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
package vtea.protocol.setup;

import ij.ImagePlus;
import vtea.protocol.listeners.MicroBlockSetupListener;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;

/**
 *
 * @author winfrees
 */
public class MicroBlockSetup extends javax.swing.JFrame implements Cloneable {

    private String[] ProcessOptions = {"", "", ""};
    
    protected ArrayList<String> Channels = new ArrayList<String>();
    
    
    protected DefaultComboBoxModel cbm;
    protected DefaultComboBoxModel ccbm;
    public ArrayList<MicroBlockSetupListener> MicroBlockSetupListeners = new ArrayList<MicroBlockSetupListener>();
    protected ArrayList CurrentStepProtocol = new ArrayList();
    protected int step;
    
    protected ArrayList<ArrayList> protocolAll = new ArrayList<ArrayList>();
    
    java.awt.GridBagLayout MethodSelectionLayout = new java.awt.GridBagLayout();

    /**
     * 'CurrentStepProtocol' contains an array of objects for the current
     * protocol, Process Channel or image selection Components for specific
     * plugin or process
     */
    private JPanel[] SetupPanes = new JPanel[3];
    private String CurrentProtocol = new String();
    private int CurrentProcess = 0;

    protected String DefaultTitle;


    protected ArrayList CurrentProcessList;
    protected ArrayList<ArrayList> CurrentProcessItems = new ArrayList<ArrayList>();
    
    protected String[][] ProcessVariables = {{"750", "5", "20", "1000"},{"750", "4095", "20", "1000"}};

    /**
     * Creates new form MicroBlockSetup
     *
     * @param step
     */
    MicroBlockSetup(){
    
    }
    
    MicroBlockSetup(int step, ArrayList channel) {
  
        Channels = channel;

        this.step = step;
        
        for (int i = 0; i < 10; i++) { CurrentProcessItems.add(null);}

        DefaultTitle = "BlockStep_" + step;
        cbm = new DefaultComboBoxModel(ProcessOptions);
        ccbm = new DefaultComboBoxModel(Channels.toArray());
        CurrentProcessList = new ArrayList(10);
        initComponents();
        ChannelComboBox.setVisible(true);
        PreviewButton.setVisible(false);
        pack();
    }
    
    MicroBlockSetup(int step){
        this.step = step;
        for (int i = 0; i< 10; i++) { CurrentProcessItems.add(null);}
        DefaultTitle = "BlockStep_" + step;
        cbm = new DefaultComboBoxModel(ProcessOptions);
        ccbm = new DefaultComboBoxModel(ProcessOptions);
        CurrentProcessList = new ArrayList(10);
        initComponents();
        ChannelComboBox.setVisible(true);
        PreviewButton.setVisible(false);
        pack();
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

        methodBuild = new javax.swing.JPanel();
        AlgorithmStyle = new javax.swing.JPanel();
        methodSelection = new javax.swing.JPanel();
        TitleText = new javax.swing.JTextField();
        PositionText = new javax.swing.JLabel();
        comments = new javax.swing.JPanel();
        notesPane = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        ProcessNotes = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        tablePane = new javax.swing.JLayeredPane();
        secondaryObjects = new javax.swing.JLabel();
        tableScrollPane = new javax.swing.JScrollPane();
        secondaryTable = new javax.swing.JTable();
        buttonPanel = new javax.swing.JPanel();
        PreviewProgress = new javax.swing.JLabel();
        PreviewButton = new javax.swing.JButton();
        BlockSetupOK = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 32), new java.awt.Dimension(10, 32), new java.awt.Dimension(10, 32));
        BlockSetupCancel = new javax.swing.JButton();
        channelSelection = new javax.swing.JPanel();
        ProcessText = new javax.swing.JLabel();
        ProcessSelectComboBox = new javax.swing.JComboBox();
        Approach = new javax.swing.JPanel();
        ChannelSelection = new javax.swing.JLabel();
        ChannelComboBox = new javax.swing.JComboBox();
        methodMorphology = new javax.swing.JPanel();
        MethodDetails = new javax.swing.JPanel();

        setBackground(vtea._vtea.BACKGROUND);
        setBounds(new java.awt.Rectangle(110, 160, 378, 282));
        setMinimumSize(vtea._vtea.BLOCKSETUP);
        setName("BlockOptionFrame"); // NOI18N
        setSize(new java.awt.Dimension(378, 282));
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 1, 0};
        layout.rowHeights = new int[] {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0};
        getContentPane().setLayout(layout);

        methodBuild.setBackground(vtea._vtea.BACKGROUND);
        methodBuild.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        methodBuild.setMaximumSize(new java.awt.Dimension(359, 300));
        methodBuild.setMinimumSize(new java.awt.Dimension(359, 300));
        methodBuild.setPreferredSize(new java.awt.Dimension(359, 300));
        methodBuild.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(methodBuild, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        getContentPane().add(AlgorithmStyle, gridBagConstraints);

        methodSelection.setBackground(vtea._vtea.BACKGROUND);
        methodSelection.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        methodSelection.setPreferredSize(new java.awt.Dimension(359, 40));
        methodSelection.setLayout(new java.awt.GridBagLayout());

        TitleText.setEditable(false);
        TitleText.setBackground(vtea._vtea.BACKGROUND);
        TitleText.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        TitleText.setText("Processing");
        TitleText.setBorder(null);
        TitleText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                TitleTextFocusLost(evt);
            }
        });
        TitleText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TitleTextActionPerformed(evt);
            }
        });
        TitleText.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                TitleTextPropertyChange(evt);
            }
        });
        TitleText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TitleTextKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        methodSelection.add(TitleText, gridBagConstraints);

        PositionText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        PositionText.setText(" ");
        PositionText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                PositionTextMousePressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        methodSelection.add(PositionText, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(methodSelection, gridBagConstraints);

        comments.setBackground(vtea._vtea.BACKGROUND);
        comments.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        comments.setMaximumSize(new java.awt.Dimension(359, 100));
        comments.setMinimumSize(new java.awt.Dimension(359, 100));
        comments.setPreferredSize(new java.awt.Dimension(359, 100));
        comments.setLayout(new javax.swing.BoxLayout(comments, javax.swing.BoxLayout.LINE_AXIS));

        notesPane.setBackground(vtea._vtea.BACKGROUND);
        notesPane.setLayout(new java.awt.BorderLayout(10, 0));

        jLabel3.setText("Notes");
        jLabel3.setAlignmentX(0.5F);
        notesPane.add(jLabel3, java.awt.BorderLayout.CENTER);

        ProcessNotes.setMaximumSize(new java.awt.Dimension(150, 50));
        ProcessNotes.setMinimumSize(new java.awt.Dimension(150, 50));
        ProcessNotes.setPreferredSize(new java.awt.Dimension(340, 90));
        ProcessNotes.setRequestFocusEnabled(false);

        jTextPane1.setMaximumSize(new java.awt.Dimension(150, 50));
        jTextPane1.setMinimumSize(new java.awt.Dimension(150, 50));
        jTextPane1.setPreferredSize(new java.awt.Dimension(150, 50));
        ProcessNotes.setViewportView(jTextPane1);

        notesPane.add(ProcessNotes, java.awt.BorderLayout.PAGE_END);

        comments.add(notesPane);

        tablePane.setMaximumSize(new java.awt.Dimension(360, 90));
        tablePane.setMinimumSize(new java.awt.Dimension(360, 90));
        tablePane.setLayout(new java.awt.GridBagLayout());

        secondaryObjects.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        secondaryObjects.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        secondaryObjects.setText("Object measurements");
        secondaryObjects.setMinimumSize(new java.awt.Dimension(180, 30));
        secondaryObjects.setPreferredSize(new java.awt.Dimension(180, 20));
        secondaryObjects.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                secondaryObjectsMousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                secondaryObjectsMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        tablePane.add(secondaryObjects, gridBagConstraints);

        tableScrollPane.setMinimumSize(new java.awt.Dimension(360, 90));
        tableScrollPane.setPreferredSize(new java.awt.Dimension(360, 220));

        secondaryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        secondaryTable.setMaximumSize(new java.awt.Dimension(2147483647, 1000));
        secondaryTable.setMinimumSize(new java.awt.Dimension(340, 70));
        secondaryTable.setPreferredSize(new java.awt.Dimension(340, 220));
        secondaryTable.setRequestFocusEnabled(false);
        tableScrollPane.setViewportView(secondaryTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        tablePane.add(tableScrollPane, gridBagConstraints);

        comments.add(tablePane);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.ipadx = 19;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(comments, gridBagConstraints);

        buttonPanel.setMinimumSize(vtea._vtea.BLOCKSETUPPANEL);
        buttonPanel.setLayout(new java.awt.GridBagLayout());
        buttonPanel.add(PreviewProgress, new java.awt.GridBagConstraints());

        PreviewButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/eye.png"))); // NOI18N
        PreviewButton.setToolTipText("Preview Segmentation");
        PreviewButton.setMaximumSize(vtea._vtea.SMALLBUTTONSIZE);
        PreviewButton.setMinimumSize(vtea._vtea.SMALLBUTTONSIZE);
        PreviewButton.setPreferredSize(vtea._vtea.SMALLBUTTONSIZE);
        PreviewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PreviewButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        buttonPanel.add(PreviewButton, gridBagConstraints);

        BlockSetupOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/dialog-apply.png"))); // NOI18N
        BlockSetupOK.setToolTipText("Accept changes");
        BlockSetupOK.setMaximumSize(vtea._vtea.SMALLBUTTONSIZE);
        BlockSetupOK.setMinimumSize(vtea._vtea.SMALLBUTTONSIZE);
        BlockSetupOK.setPreferredSize(vtea._vtea.SMALLBUTTONSIZE);
        BlockSetupOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BlockSetupOKActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        buttonPanel.add(BlockSetupOK, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        buttonPanel.add(filler1, gridBagConstraints);

        BlockSetupCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/edit-delete-6_24.png"))); // NOI18N
        BlockSetupCancel.setToolTipText("Cancel changes");
        BlockSetupCancel.setMaximumSize(vtea._vtea.SMALLBUTTONSIZE);
        BlockSetupCancel.setMinimumSize(vtea._vtea.SMALLBUTTONSIZE);
        BlockSetupCancel.setPreferredSize(vtea._vtea.SMALLBUTTONSIZE);
        BlockSetupCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BlockSetupCancelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        buttonPanel.add(BlockSetupCancel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.ipadx = 21;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(buttonPanel, gridBagConstraints);

        channelSelection.setBackground(vtea._vtea.BACKGROUND);
        channelSelection.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        channelSelection.setMinimumSize(new java.awt.Dimension(359, 31));
        channelSelection.setPreferredSize(new java.awt.Dimension(359, 33));
        channelSelection.setLayout(new java.awt.GridBagLayout());

        ProcessText.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        ProcessText.setText("Object formation ");
        ProcessText.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 0);
        channelSelection.add(ProcessText, gridBagConstraints);

        ProcessSelectComboBox.setBackground(vtea._vtea.BACKGROUND);
        ProcessSelectComboBox.setModel(this.cbm);
        ProcessSelectComboBox.setMaximumSize(new java.awt.Dimension(150, 27));
        ProcessSelectComboBox.setMinimumSize(new java.awt.Dimension(150, 27));
        ProcessSelectComboBox.setPreferredSize(new java.awt.Dimension(200, 27));
        ProcessSelectComboBox.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                ProcessSelectComboBoxCaretPositionChanged(evt);
            }
        });
        ProcessSelectComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProcessSelectComboBoxActionPerformed(evt);
            }
        });
        channelSelection.add(ProcessSelectComboBox, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(channelSelection, gridBagConstraints);

        Approach.setBackground(vtea._vtea.BACKGROUND);
        Approach.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Approach.setMinimumSize(new java.awt.Dimension(359, 41));
        Approach.setPreferredSize(new java.awt.Dimension(359, 41));

        ChannelSelection.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        ChannelSelection.setText("Segment on Channel");
        Approach.add(ChannelSelection);

        ChannelComboBox.setModel(ccbm);
        ChannelComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChannelComboBoxActionPerformed(evt);
            }
        });
        Approach.add(ChannelComboBox);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(Approach, gridBagConstraints);

        methodMorphology.setBackground(vtea._vtea.BACKGROUND);
        methodMorphology.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        methodMorphology.setMaximumSize(new java.awt.Dimension(359, 300));
        methodMorphology.setMinimumSize(new java.awt.Dimension(359, 100));
        methodMorphology.setPreferredSize(new java.awt.Dimension(359, 75));

        defaultProtocolPanel();
        MethodDetails.setBackground(vtea._vtea.BACKGROUND);
        MethodDetails.setMaximumSize(new java.awt.Dimension(340, 250));
        MethodDetails.setMinimumSize(new java.awt.Dimension(340, 50));
        MethodDetails.setPreferredSize(new java.awt.Dimension(350, 50));
        MethodDetails.setLayout(new java.awt.GridBagLayout());
        methodMorphology.add(MethodDetails);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(methodMorphology, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ProcessSelectComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProcessSelectComboBoxActionPerformed
        updateProtocolPanel(evt);
    }//GEN-LAST:event_ProcessSelectComboBoxActionPerformed

    private void BlockSetupCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BlockSetupCancelActionPerformed

        this.setVisible(false);
    }//GEN-LAST:event_BlockSetupCancelActionPerformed

    private void BlockSetupOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BlockSetupOKActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        blockSetupOKAction();
        
    }//GEN-LAST:event_BlockSetupOKActionPerformed

    private void ProcessSelectComboBoxCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_ProcessSelectComboBoxCaretPositionChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_ProcessSelectComboBoxCaretPositionChanged

    private void ChannelComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChannelComboBoxActionPerformed
        updateProtocolPanel(evt);
    }//GEN-LAST:event_ChannelComboBoxActionPerformed

    private void PositionTextMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PositionTextMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PositionTextMousePressed

    private void TitleTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TitleTextKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TitleTextKeyPressed

    private void TitleTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TitleTextFocusLost
        if (TitleText.getText().length() == 0) {
            TitleText.setText(DefaultTitle);
        } else {
            
        }
        updateTitles();
        pack();
    }//GEN-LAST:event_TitleTextFocusLost

    private void secondaryObjectsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_secondaryObjectsMousePressed
    
// TODO add your handling code here:
    }//GEN-LAST:event_secondaryObjectsMousePressed

    private void secondaryObjectsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_secondaryObjectsMouseClicked
        this.secondaryTable.setEnabled(!(this.secondaryTable.isEnabled()));   
        
    }//GEN-LAST:event_secondaryObjectsMouseClicked

    private void TitleTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TitleTextActionPerformed
        this.updateProtocolPanel(evt);
    }//GEN-LAST:event_TitleTextActionPerformed

    private void TitleTextPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_TitleTextPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_TitleTextPropertyChange

    private void PreviewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PreviewButtonActionPerformed
        this.PreviewProgress.setText("Getting segmentation preview...");
        
        getSegmentationPreview();
        
    }//GEN-LAST:event_PreviewButtonActionPerformed
    
    protected void getSegmentationPreview(){
        
    }
    
   public int getProtocolPosition(){
        return ProcessSelectComboBox.getSelectedIndex();
    }
   
    public void setProtocolPosition(int i){
        ProcessSelectComboBox.setSelectedIndex(i);
    }
    
    public void addMicroBlockSetupListener(MicroBlockSetupListener listener) {
        MicroBlockSetupListeners.add(listener);
    }

    protected void notifyMicroBlockSetupListeners(ArrayList al) {
        for (MicroBlockSetupListener listener : MicroBlockSetupListeners) {

            listener.onChangeSetup(al);

        }
    }

    protected ArrayList makeMethodComponentsArray(int position, String[][] values) {

        ArrayList result = new ArrayList();

        return result;
    }
    
    protected ArrayList makeMethodComponentsArray(String method, String[][] str) {
                ArrayList result = new ArrayList();

        return result;
    }
    
    protected ArrayList makeSecondaryComponentsArray(int position) {

        ArrayList result = new ArrayList();

        return result;
    }

    private JPanel defaultProtocolPanel() {
        return new JPanel();
    }
    
    public void cloneMethodComponentsArray(String str, ArrayList al){
         
    }

    protected JPanel makeProtocolPanel(String str) {
  JPanel BuiltPanel = new JPanel();
        return BuiltPanel;
        }

    protected JPanel makeProtocolPanel(int position) {

        
        JPanel BuiltPanel = new JPanel();
        ArrayList ProcessComponents = new ArrayList();

        ProcessComponents = makeMethodComponentsArray(position,ProcessVariables);


        MethodDetails.setVisible(false);
        MethodDetails.removeAll();

        BuiltPanel.setLayout(new GridBagLayout());
        GridBagConstraints layoutConstraints = new GridBagConstraints();

        //MethodDetail
        if (ProcessComponents.size() > 0) {
            layoutConstraints.fill = GridBagConstraints.CENTER;
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 0;
            layoutConstraints.weightx = 1;
            layoutConstraints.weighty = 1;
            MethodDetails.add((Component) ProcessComponents.get(0), layoutConstraints);
        }

        if (ProcessComponents.size() > 1) {
            layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
            layoutConstraints.gridx = 1;
            layoutConstraints.gridy = 0;
            //layoutConstraints.weightx = 1;
            //layoutConstraints.weighty = 1;
            MethodDetails.add((Component) ProcessComponents.get(1), layoutConstraints);
        }

        if (ProcessComponents.size() > 2) {
            layoutConstraints.fill = GridBagConstraints.CENTER;
            layoutConstraints.gridx = 2;
            layoutConstraints.gridy = 0;
            //layoutConstraints.weightx = 1;
            //layoutConstraints.weighty = 1;
            MethodDetails.add((Component) ProcessComponents.get(2), layoutConstraints);
        }
        if (ProcessComponents.size() > 3) {
            layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
            layoutConstraints.gridx = 3;
            layoutConstraints.gridy = 0;
            //layoutConstraints.weightx = 1;
            //layoutConstraints.weighty = 1;
            MethodDetails.add((Component) ProcessComponents.get(3), layoutConstraints);
        }
        if (ProcessComponents.size() > 4) {
            layoutConstraints.fill = GridBagConstraints.CENTER;
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 1;
            //layoutConstraints.weightx = 1;
            //layoutConstraints.weighty = 1;
            MethodDetails.add((Component) ProcessComponents.get(4), layoutConstraints);
        }
        if (ProcessComponents.size() > 5) {
            layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
            layoutConstraints.gridx = 1;
            layoutConstraints.gridy = 1;
            //layoutConstraints.weightx = 1;
            //layoutConstraints.weighty = 1;
            MethodDetails.add((Component) ProcessComponents.get(5), layoutConstraints);
        }
        if (ProcessComponents.size() > 6) {
            layoutConstraints.fill = GridBagConstraints.CENTER;
            layoutConstraints.gridx = 2;
            layoutConstraints.gridy = 1;
            //layoutConstraints.weightx = 1;
            //layoutConstraints.weighty = 1;
            MethodDetails.add((Component) ProcessComponents.get(6), layoutConstraints);
        }
        if (ProcessComponents.size() > 7) {
            layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
            layoutConstraints.gridx = 3;
            layoutConstraints.gridy = 1;
            //layoutConstraints.weightx = 1;
            //layoutConstraints.weighty = 1;
            MethodDetails.add((Component) ProcessComponents.get(7), layoutConstraints);
        }
        //MethodDetails.setSize(VTEAService.BLOCKSETUPPANEL);
//                layoutConstraints.fill = GridBagConstraints.BOTH;
//        layoutConstraints.gridx = 2;
//        layoutConstraints.gridy = 0;
//        layoutConstraints.weightx = -1;
//        layoutConstraints.weighty = -1;
//        layoutConstraints.ipadx = -1;
//        layoutConstraints.ipady = -1;
//        step.add(DeleteButton,layoutConstraints);
//                        layoutConstraints.fill = GridBagConstraints.BOTH;
//        layoutConstraints.gridx = 2;
//        layoutConstraints.gridy = 1;
//        layoutConstraints.weightx = -1;
//        layoutConstraints.weighty = -1;
//        layoutConstraints.ipadx = -1;
//        layoutConstraints.ipady = -1;
//        step.add(EditButton,layoutConstraints);

        pack();
        MethodDetails.setVisible(true);

        if (!(null == this.CurrentProcessList)) {
            this.CurrentProcessList.clear();
        }
        this.CurrentProcessList.add(this.cbm.getSelectedItem());
        this.CurrentProcessList.add(this.ccbm.getSelectedItem());
        //this.CurrentProcessList.add(this.jTextPane1.getText());
        this.CurrentProcessList.addAll(ProcessComponents);

        return MethodDetails;
    }

    protected void blockSetupOKAction() {

        this.CurrentStepProtocol = CurrentProcessList;
        notifyMicroBlockSetupListeners(this.CurrentStepProtocol);

    }
    
    protected void blockSetupCancelAction() {

    }
    
    public ArrayList getProcessList(){
        return new ArrayList();
    }
    protected void setImage(ImagePlus imp){
    }
    
    protected void updateTitles(){}
    
    
    protected void updateProtocolPanel(ActionEvent evt) {
        
 
        MethodDetails.setVisible(false);
        MethodDetails.removeAll();
        
        makeProtocolPanel(ProcessSelectComboBox.getSelectedIndex());

        MethodDetails.revalidate();
        MethodDetails.repaint();
        MethodDetails.setVisible(true);
       
        pack();
//        }
    }
    
    @Override    
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }
 

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(MicroBlockSetup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(MicroBlockSetup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(MicroBlockSetup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(MicroBlockSetup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new MicroBlockSetup().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AlgorithmStyle;
    private javax.swing.JPanel Approach;
    protected javax.swing.JButton BlockSetupCancel;
    protected javax.swing.JButton BlockSetupOK;
    protected javax.swing.JComboBox ChannelComboBox;
    protected javax.swing.JLabel ChannelSelection;
    protected javax.swing.JPanel MethodDetails;
    protected javax.swing.JLabel PositionText;
    public javax.swing.JButton PreviewButton;
    public javax.swing.JLabel PreviewProgress;
    private javax.swing.JScrollPane ProcessNotes;
    protected javax.swing.JComboBox ProcessSelectComboBox;
    protected javax.swing.JLabel ProcessText;
    protected javax.swing.JTextField TitleText;
    public javax.swing.JPanel buttonPanel;
    private javax.swing.JPanel channelSelection;
    protected javax.swing.JPanel comments;
    private javax.swing.Box.Filler filler1;
    protected javax.swing.JLabel jLabel3;
    protected javax.swing.JTextPane jTextPane1;
    protected javax.swing.JPanel methodBuild;
    protected javax.swing.JPanel methodMorphology;
    protected javax.swing.JPanel methodSelection;
    protected javax.swing.JPanel notesPane;
    private javax.swing.JLabel secondaryObjects;
    protected javax.swing.JTable secondaryTable;
    protected javax.swing.JLayeredPane tablePane;
    private javax.swing.JScrollPane tableScrollPane;
    // End of variables declaration//GEN-END:variables

}
