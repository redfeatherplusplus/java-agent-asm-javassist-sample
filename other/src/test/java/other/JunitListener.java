package other;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

/**
 * Created by HL on 3/24/17.
 */
public class JunitListener extends RunListener {

    public void testStarted(Description description) {
        System.out.println("[TEST] " + description.getClassName() + " : " + description.getMethodName());
    }
    public void testFinished(Description description) {
//        System.out.println("Finished .... "+ description.getMethodName());
    }
    public void testRunFinished(Result result) {
//        System.out.println("\n\n");
    }
}
