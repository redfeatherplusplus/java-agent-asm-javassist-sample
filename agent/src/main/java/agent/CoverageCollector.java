package agent;

import java.util.HashMap;
import java.util.LinkedHashSet;

public class CoverageCollector {
	// Need to Map: Test Case -> Class -> Statement Coverage
	public static HashMap<String, HashMap<String, LinkedHashSet<Integer>>> testCaseCoverages;
	public static HashMap<String, LinkedHashSet<Integer>> testCaseCoverage;
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
            LinkedHashSet<Integer> lines = new LinkedHashSet<>();
            lines.add(line);
            testCaseCoverage.put(className, lines);
        }
    }
}
