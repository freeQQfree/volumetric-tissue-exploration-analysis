/* 
 * Copyright (C) 2016 Indiana University
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
package vteaexploration.plottools.panels;


import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.plaf.basic.BasicLabelUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 
 * 
 * from: http://stackoverflow.com/questions/92781/how-do-i-present-text-vertically-in-a-jlabel-java-1-6
 */
public class VerticalLabelUI extends BasicLabelUI {
  static {
    labelUI = new VerticalLabelUI(false);
  }

  protected boolean clockwise;

  public VerticalLabelUI( boolean clockwise ){
    super();
    this.clockwise = clockwise;
  }


  @Override
  public Dimension getPreferredSize(JComponent c){
    Dimension dim = super.getPreferredSize(c);
    return new Dimension( dim.height, dim.width );
  }

  private static Rectangle paintIconR = new Rectangle();
  private static Rectangle paintTextR = new Rectangle();
  private static Rectangle paintViewR = new Rectangle();
  private static Insets paintViewInsets = new Insets(0, 0, 0, 0);

  @Override
  public void paint(Graphics g, JComponent c){
    JLabel label = (JLabel)c;
    String text = label.getText();
    Icon icon = (label.isEnabled()) ? label.getIcon() : label.getDisabledIcon();

    if ((icon == null) && (text == null)) {
      return;
    }

    FontMetrics fm = g.getFontMetrics();
    paintViewInsets = c.getInsets(paintViewInsets);

    paintViewR.x = paintViewInsets.left;
    paintViewR.y = paintViewInsets.top;

    // Use inverted height & width
    paintViewR.height = c.getWidth() - (paintViewInsets.left + paintViewInsets.right);
    paintViewR.width = c.getHeight() - (paintViewInsets.top + paintViewInsets.bottom);

    paintIconR.x = paintIconR.y = paintIconR.width = paintIconR.height = 0;
    paintTextR.x = paintTextR.y = paintTextR.width = paintTextR.height = 0;

    String clippedText =
            layoutCL(label, fm, text, icon, paintViewR, paintIconR, paintTextR);

    Graphics2D g2 = (Graphics2D) g;
    AffineTransform tr = g2.getTransform();
    if( clockwise )
    {
      g2.rotate( Math.PI / 2 );
      g2.translate( 0, - c.getWidth() );
    }
    else
    {
      g2.rotate( - Math.PI / 2 );
      g2.translate( - c.getHeight(), 0 );
    }

    if (icon != null) {
      icon.paintIcon(c, g, paintIconR.x, paintIconR.y);
    }

    if (text != null) {
      int textX = paintTextR.x;
      int textY = paintTextR.y + fm.getAscent();

      if (label.isEnabled()) {
        paintEnabledText(label, g, clippedText, textX, textY);
      }
      else {
        paintDisabledText(label, g, clippedText, textX, textY);
      }
    }

    g2.setTransform( tr );
  }
}
    
    
    

