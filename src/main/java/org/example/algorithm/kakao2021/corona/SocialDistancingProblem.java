package org.example.algorithm.kakao2021.corona;

import org.example.algorithm.AlgorithmTester;
import org.example.algorithm.TestTarget;
import org.example.algorithm.kakao2021.strtonum.StringToNumberSolution02;
import org.example.study.StudyLauncher;

import java.util.Arrays;

public class SocialDistancingProblem implements StudyLauncher {
    private static final String PROBLEM_URL = "https://school.programmers.co.kr/learn/courses/30/lessons/81302#fnref1";
    @Override
    public void launch() {
        AlgorithmTester tester = new AlgorithmTester(
            Arrays.asList( new Target[] {
                new Target(
                    new String[][] {
                        new String[] { "POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP" },
                        new String[] { "POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP" },
                        new String[] { "PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX" },
                        new String[] { "OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO" },
                        new String[] { "PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP" },
                    },
                    new int[] { 1,0,1,1,1 } )
            })
        );
        tester.test( new SocialDistancingSolution01() );
    }

    class Target implements TestTarget<String[][], int[]> {

        private final String[][] input;
        private final int[] expected;

        public Target( String[][] input, int[] expected ) {
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
        public boolean isEqualToExpected( int[] answer ) {
            int idx = 0;
            for ( int i : answer ) {
                if ( answer[ idx ] != this.expected[ idx ] ) {
                    return false;
                }
            }
            return true;
        }
    }
}
