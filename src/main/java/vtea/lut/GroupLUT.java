/*
 * Copyright (C) 2019 SciJava
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
package vtea.lut;

import java.awt.Color;
import org.jfree.chart.renderer.LookupPaintScale;

/**
 *
 * @author sukhoc
 */
public class GroupLUT extends AbstractLUT {
    
    
public GroupLUT(){
        VERSION = "0.1";
        AUTHOR = "VTEA Developer";
        COMMENT = "Implements group LUTs for less than 22 classes in VTEA";
        NAME = "GroupLUt";
        KEY = "GLUT";
}
    
 @Override
     public LookupPaintScale getPaintScale(double min, double max){
         
        ps = new LookupPaintScale();
         
        double range = max - min;   

        ps.add(0, new Color(255, 51, 51));
        ps.add(1, new Color(255, 153, 51));
        ps.add(2, new Color(153, 255, 51));
        ps.add(3, new Color(51, 255, 153));
        ps.add(4, new Color(51, 255, 255));
        ps.add(5, new Color(102, 178, 255));
        ps.add(6, new Color(102, 102, 255));
        ps.add(7, new Color(178, 102, 255));
        ps.add(8, new Color(255, 102, 255));
        ps.add(9, new Color(255, 102, 178));

        ps.add(10, new Color(255, 51, 153));
        ps.add(11, new Color(255, 153, 153));
        ps.add(12, new Color(153, 255, 153));
        ps.add(13, new Color(51, 255, 51));
        ps.add(14, new Color(51, 255, 178));
        ps.add(15, new Color(102, 178, 178));
        ps.add(16, new Color(178, 102, 178));
        ps.add(17, new Color(255, 102, 153));
        ps.add(18, new Color(255, 102, 51));
        ps.add(19, new Color(178, 153, 51));
        ps.add(20, new Color(153, 178, 51));
   return ps;
     }
}
