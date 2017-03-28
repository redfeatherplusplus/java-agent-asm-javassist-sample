package agent;

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
    	if (testCaseCoverage == null) {
    		return;
    	}
    	
    	IntSortedSet lines = testCaseCoverage.get(className);
        if (lines != null) {
        	lines.add(line);
        }
        else {
        	lines = new IntLinkedOpenHashSet(new int[]{line});
            testCaseCoverage.put(className, lines);
        }
    }
}
