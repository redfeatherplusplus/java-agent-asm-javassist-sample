package agent;

import java.io.*;
import java.util.Arrays;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntSet;
import it.unimi.dsi.fastutil.ints.IntSortedSet;
/**
 * Created by HL on 3/26/17.
 */

public class JUnitListener extends RunListener {

	// Called before all tests
	public void testRunStarted(Description description) throws Exception {
		if (null == CoverageCollector.testCaseCoverages)
		{
			CoverageCollector.testCaseCoverages = new Object2ObjectOpenHashMap<String, Object2ObjectOpenHashMap<String, IntSet>>();
		}
		
        System.out.println("\n\nStart!");
    }
	
	// Called before each @Test
    public void testStarted(Description description) {
    	// Note: Java is pass by value, so this works
    	CoverageCollector.testCaseName = "[TEST] " + description.getClassName() + ":" + description.getMethodName();
    	CoverageCollector.testCaseCoverage = new Object2ObjectOpenHashMap<String, IntSet>();
    }
    
    // Called after each @Test Finishes
    public void testFinished(Description description) {
    	CoverageCollector.testCaseCoverages.put(CoverageCollector.testCaseName, CoverageCollector.testCaseCoverage);
    }
    
    // Called after all tests finish
    public void testRunFinished(Result result) throws IOException {
        System.out.println("Done!\n\n");
        
        // Write to stmt-cov.txt
        File fout = new File("stmt-cov.txt");
        FileOutputStream fos = new FileOutputStream(fout);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        StringBuilder builder = new StringBuilder();
        for (String testCaseName : CoverageCollector.testCaseCoverages.keySet()) {
        	builder.append(testCaseName + "\n");
        	
        	Object2ObjectOpenHashMap<String, IntSet> caseCoverage = 
        			CoverageCollector.testCaseCoverages.get(testCaseName);
        	
            for (String className : caseCoverage.keySet()) {
            	int[] lines = caseCoverage.get(className).toIntArray();
            	
            	Arrays.sort(lines);
            	for (int i = 0; i < lines.length; i++) {
                	builder.append(className + ":" + lines[i] + "\n");
				}
            }
        }
        
        bw.write(builder.toString());
        bw.close();
    }
}
