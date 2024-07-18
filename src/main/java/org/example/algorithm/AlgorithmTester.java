package org.example.algorithm;

import java.util.List;

public class AlgorithmTester {

    private List<TestTarget> testTargets;
    public AlgorithmTester( List<TestTarget> testTargets ) {
        this.testTargets = testTargets;
    }

    public void test( Solver solution ) {
        for ( TestTarget t : testTargets ) {
            var answer = solution.solve( t.getInput() );
            if ( t.isEqualToExpected( answer ) ) {
                System.out.println( "[ ======================================================================== ]");
                System.out.println( "[ @@ CORRECT @@ Expected: " + t.getExpected() + " | Output : " + answer );
                System.out.println( "[ ======================================================================== ]");
            } else {
                System.out.println( "[ ======================================================================== ]");
                System.out.println( "[ XX  FAIL  XX  Expected: " + t.getExpected() + " | Output : " + answer );
                System.out.println( "[ ======================================================================== ]");
            }
            System.out.println( "  " );
        }
    }
}
