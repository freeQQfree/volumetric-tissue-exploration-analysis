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
package vtea.objects.measurements;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 *
 * @author sethwinfree
 */
public class Count extends AbstractMeasurement{
    protected String VERSION = "1.0";
    protected String AUTHOR = "Seth Winfree";
    protected String COMMENT = "Calculate number of pixels";
    protected String NAME = "Sum";
    protected String KEY = "Sum";
    
    @Override
    public Number process(ArrayList al, ArrayList values)  {
    
    Integer n = 0;
    ListIterator<Number> itr = values.listIterator();   
    while(itr.hasNext()){
        n++;
    }   
    return n;
};
    
    
}
