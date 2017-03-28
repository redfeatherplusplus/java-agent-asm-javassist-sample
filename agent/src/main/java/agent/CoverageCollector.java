package agent;

import java.util.LinkedHashSet;

import it.unimi.dsi.fastutil.Maps;
import it.unimi.dsi.fastutil.ints.IntLinkedOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSortedSet;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;

public class CoverageCollector {
	// Need to Map: Test Case -> Class -> Statement Coverage
	public static Object2ObjectOpenHashMap<String, Object2ObjectOpenHashMap<String, IntSortedSet>> testCaseCoverages;
	public static Object2ObjectOpenHashMap<String, IntSortedSet> testCaseCoverage;
	public static String testCaseName;

    // Called whenever executing a line
    public static void addMethodLine(String className, Integer line){
    	if (testCaseCoverage == null || testCaseName == null) {
    		return;
    	}
    	
        if (testCaseCoverage.containsKey(className)) {
            testCaseCoverage.get(className).add(line);
        }
        else {
        	IntSortedSet lines = new IntLinkedOpenHashSet();
            lines.add(line);
            testCaseCoverage.put(className, lines);
        }
    }
}
