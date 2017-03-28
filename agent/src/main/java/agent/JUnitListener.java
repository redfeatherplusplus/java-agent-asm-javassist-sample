package agent;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashSet;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;
/**
 * Created by HL on 3/26/17.
 */

public class JUnitListener extends RunListener {

	// Called before all tests
	public void testRunStarted(Description description) throws Exception {
		if (null == CoverageCollector.testCaseCoverages)
		{
			CoverageCollector.testCaseCoverages = new HashMap<String, HashMap<String, LinkedHashSet<Integer>>>();
		}
        System.out.println("\n\nStart!");
    }
	
	// Called before each @Test
    public void testStarted(Description description) {
    	// Note: Java is pass by value, so this works
    	CoverageCollector.testCaseName = "[TEST] " + description.getClassName() + " : " + description.getMethodName();
    	CoverageCollector.testCaseCoverage = new HashMap<String, LinkedHashSet<Integer>>();
    	
//        System.out.println(CoverageCollector.testCaseName);
    }
    
    // Called after each @Test Finishes
    public void testFinished(Description description) {
    	if (null == CoverageCollector.testCaseCoverages) {
			CoverageCollector.testCaseCoverages = new HashMap<String, HashMap<String, LinkedHashSet<Integer>>>();
    	}
    	CoverageCollector.testCaseCoverages.put(CoverageCollector.testCaseName, CoverageCollector.testCaseCoverage);

//        System.out.println("Finished .... "+ description.getMethodName() + "with coverage size: " + CoverageCollector.testCaseCoverages.size());
    }
    
    // Called after all tests finish
    public void testRunFinished(Result result) throws IOException {
        System.out.println("Done!\n\n");
        
        //TODO: write to stmt-cov.txt
        File fout = new File("stmt-cov.txt");
        FileOutputStream fos = new FileOutputStream(fout);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        for(String testCaseName : CoverageCollector.testCaseCoverages.keySet()) {
        	bw.write(testCaseName);
        	bw.newLine();
            HashMap<String, LinkedHashSet<Integer>> caseCoverage = CoverageCollector.testCaseCoverages.get(testCaseName);
            for(String className : caseCoverage.keySet()){
                LinkedHashSet<Integer> lines = caseCoverage.get(className);
                for(Integer i : lines){
                	bw.write(className + " : " + i);
                	bw.newLine();
                }
            }
        }
        bw.close();
    }

    public void writeFile() throws IOException {
        File fout = new File("stmt-cov.txt");
        FileOutputStream fos = new FileOutputStream(fout);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        for(String testCaseName : CoverageCollector.testCaseCoverages.keySet()) {
            System.out.println(testCaseName);
            bw.write(testCaseName);
            bw.newLine();
            HashMap<String, LinkedHashSet<Integer>> caseCoverage = CoverageCollector.testCaseCoverages.get(testCaseName);
            for(String className : caseCoverage.keySet()){
                System.out.println(className);
                LinkedHashSet<Integer> lines = caseCoverage.get(className);
                for(Integer i : lines){
                    System.out.print( i + " ");
                }
                System.out.println();
            }
        }
        bw.close();
    }
}
