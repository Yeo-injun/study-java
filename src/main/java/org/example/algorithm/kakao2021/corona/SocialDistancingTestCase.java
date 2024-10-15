package org.example.algorithm.kakao2021.corona;

import org.example.algorithm.core.Testable;

public class SocialDistancingTestCase implements Testable< String[][], int[] > {

    private final String[][] input;
    private final int[] expected;

    public SocialDistancingTestCase( String[][] input, int[] expected ) {
        this.input      = input;
        this.expected   = expected;
    }
    @Override
    public String[][] getInput() {
        return this.input;
    }

    @Override
    public int[] getExpected() {
        return this.expected;
    }

    @Override
    public boolean isPass( int[] answer ) {
        int idx = 0;
        for ( int i : answer ) {
            if ( answer[ idx ] != this.expected[ idx ] ) {
                return false;
            }
        }
        return true;
    }
}
