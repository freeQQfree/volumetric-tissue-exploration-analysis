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
package vtea.protocol.menus;

import vtea.protocol.listeners.BatchStateListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;

/**
 *
 * @author vinfrais
 */
public class AvailableWorkflowsMenu extends JPopupMenu implements ActionListener, ItemListener {
                JRadioButtonMenuItem SingleFile;
                JRadioButtonMenuItem MultipleFiles;
                JMenuItem Item2;
                JMenuItem Item3;
                boolean batch;
                private ArrayList<BatchStateListener> listeners = new ArrayList<BatchStateListener>();
                
                public AvailableWorkflowsMenu(){
                    //batch = multiple;
                     MultipleFiles = new JRadioButtonMenuItem("Multiple Files");
                     //MultipleFiles.setSelected(multiple);
                     MultipleFiles.setActionCommand("Multiple");
                     MultipleFiles.addItemListener(this);
                     MultipleFiles.addActionListener(this);
                     Item2 = new JMenuItem("Item2");
                     Item3 = new JMenuItem("Item3");

                    add(MultipleFiles);
                    addSeparator();
                    add(Item2);
                    add(Item3);
                } 

    @Override
    public void itemStateChanged(ItemEvent ie) {
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
 
        }
    
    

                }

