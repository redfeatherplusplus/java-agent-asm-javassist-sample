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
    	if (testCaseCoverage == null) {
        	CoverageCollector.testCaseCoverage = new HashMap<String, LinkedHashSet<Integer>>();
    	}
    	if (testCaseName == null) {
    		CoverageCollector.testCaseName = "[TEST] " + className;
    	}
    	
        if (testCaseCoverage.containsKey(className)) {
        	if (null == testCaseCoverage.get(className)) {
                LinkedHashSet<Integer> lines = new LinkedHashSet<>();
                lines.add(line);
                testCaseCoverage.put(className, lines);
        	}
        	else {
                testCaseCoverage.get(className).add(line);
        	}
        }
        else {
            LinkedHashSet<Integer> lines = new LinkedHashSet<>();
            lines.add(line);
            testCaseCoverage.put(className, lines);
        }
    }
}
