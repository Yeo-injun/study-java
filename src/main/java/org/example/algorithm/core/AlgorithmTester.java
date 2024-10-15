package org.example.algorithm.core;

import java.util.List;

public class AlgorithmTester implements Tester {

    private Problem problem;

    public AlgorithmTester( Problem problem ) {
        this.problem = problem;
    }

    @Override
    public void test( Solvable solution ) {
        List<Testable> testCases = this.problem.getTestCases();
        for ( Testable testCase : testCases ) {
            long startTime  = System.nanoTime();
            var answer      = solution.solve( testCase.getInput() );
            long endTime    = System.nanoTime();

            var expected = testCase.getExpected();
            long elapsedTime = endTime - startTime;
            if ( testCase.isPass( answer ) ) {
                System.out.println( "[ ======================================================================== ]");
                System.out.println( "[ @@ CORRECT @@ Expected: " + expected + " | Output : " + answer );
                System.out.println( "[ ======================================================================== ]");
            } else {
                System.out.println( "[ ======================================================================== ]");
                System.out.println( "[ XX  FAIL  XX  Expected: " + expected + " | Output : " + answer );
                System.out.println( "[ ======================================================================== ]");
            }
            System.out.println( "Elapsed time : " + elapsedTime + " ns" );
            System.out.println( "  " );
        }
    }
}
