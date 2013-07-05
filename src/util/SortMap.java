package util;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class SortMap {

    public static Map sortMap(Map mapOriginal) {
        
        Map sortedMap = new TreeMap(new ValueComparator(mapOriginal));
        
        for (Iterator iter = mapOriginal.keySet().iterator(); iter.hasNext(); ) {
            
            Object key = iter.next();
            
            sortedMap.put(key, mapOriginal.get(key));
        }
        
        return sortedMap;
    }

}
