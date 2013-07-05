package util;

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
