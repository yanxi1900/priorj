package util;

/*
* PriorJ: JUnit Test Case Prioritization.
* 
* Copyright (C) 2012-2013  Samuel T. C. Santos
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
* 
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
import java.util.Map;

public class ValueComparator implements java.util.Comparator {

    private Map m = null;

    public ValueComparator(Map m) {
        this.m = m;
    }

    public int compare(Object a, Object b) {
        
        if ((Double) m.get(a) < (Double) m.get(b)) {
            
            return 1;
        } else if ((Double) m.get(a) == (Double) m.get(b)) {
            
            return 0;
        } else {
            
            return -1;
        }
    }

}
