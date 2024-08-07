package org.example.algorithm.kakao2020.gems;

import org.example.algorithm.AlgorithmTester;
import org.example.algorithm.TestTarget;
import org.example.study.StudyLauncher;

import java.util.Arrays;

public class GemsProblem implements StudyLauncher {

    private static final String PROBLEM_URL = "https://school.programmers.co.kr/learn/courses/30/lessons/67258";
    @Override
    public void launch() {
        AlgorithmTester tester = new AlgorithmTester(
            Arrays.asList( new Target[] {
                new Target(
                    new String[] { "DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA" },
                    new int[] {3,7}
                ),
                new Target(
                    new String[] { "AA", "AB", "AC", "AA", "AC" },
                    new int[] {1,3}
                ),
                new Target(
                    new String[] { "XYZ", "XYZ", "XYZ" },
                    new int[] {1,1}
                ),
                new Target(
                    new String[] { "ZZZ", "YYY", "NNNN", "YYY", "BBB" },
                    new int[] {1,5}
                ),
            })
        );

        /**
         * 다른 solution 풀이 방향
         * - {}을 풀고, 전체 원소별 갯수를 기준으로 정렬
         */
        tester.test( new GemsSolution01() );
    }


    class Target implements TestTarget<String[], int[]> {

        private final String[] input;
        private final int[] expected;

        public Target( String[] input, int[] expected ) {
            this.input      = input;
            this.expected   = expected;
        }
        @Override
        public String[] getInput() {
            return this.input;
        }

        @Override
        public int[] getExpected() {
            return this.expected;
        }

        @Override
        public boolean isEqualToExpected( int[] answer ) {
            int idx = 0;
            int itemCount = this.expected.length;
            while ( idx < itemCount ) {
                if ( this.expected[ idx ] != answer[ idx ] ) {
                    return false;
                }
                idx++;
            }
            return true;
        }
    }
}
