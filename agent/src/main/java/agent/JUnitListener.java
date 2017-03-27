package agent;

import java.util.HashMap;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;
/**
 * Created by HL on 3/26/17.
 */

public class JUnitListener extends RunListener {
	// Need to Map: Test Case -> Class -> Statement Coverage
	private static HashMap<String, HashMap<String, Integer>> testCaseCoverages;
	private static HashMap<String, Integer> testCaseCoverage;
	private static String testCaseName;

	// Called before all tests
	public void testRunStarted(Description description) throws Exception {
		testCaseCoverages = new HashMap<String, HashMap<String, Integer>>();
		
        System.out.println("\n\nStart!");
    }
	
	// Called before each @Test
    public void testStarted(Description description) {
    	// Note: Java is pass by value, so this works
    	testCaseName = "[TEST] " + description.getClassName() + " : " + description.getMethodName();
    	testCaseCoverage = new HashMap<String, Integer>();
    	
        System.out.println(testCaseName);
    }
    
    // Called after each @Test Finishes
    public void testFinished(Description description) {
    	testCaseCoverages.put(testCaseName, testCaseCoverage);
    	
        System.out.println("Finished .... "+ description.getMethodName());
    }
    
    // Called after all tests finish
    public void testRunFinished(Result result) {
        System.out.println("Done!\n\n");
        
        //TODO: write to stmt-cov.txt
        for(String testCaseName : testCaseCoverages.keySet()) {
        	System.out.println(testCaseName);
        }
    }
}
