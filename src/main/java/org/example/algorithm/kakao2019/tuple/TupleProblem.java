package org.example.algorithm.kakao2019.tuple;

import org.example.algorithm.AlgorithmTester;
import org.example.algorithm.TestTarget;
import org.example.study.StudyLauncher;

import java.util.Arrays;

public class TupleProblem implements StudyLauncher {

    private static final String PROBLEM_URL = "https://school.programmers.co.kr/learn/courses/30/lessons/64065";
    @Override
    public void launch() {
        AlgorithmTester tester = new AlgorithmTester(
            Arrays.asList( new Target[] {
                new Target(
                    "{{2},{2,1},{2,1,3},{2,1,3,4}}",
                    new Integer[] { 2,1,3,4 }
                ),
                    new Target(
                            "{{1,2,3},{2,1},{1,2,4,3},{2}}",
                            new Integer[] { 2,1,3,4 }
                    ),
                    new Target(
                            "{{20,111},{111}}",
                            new Integer[] { 111, 20 }
                    ),
                    new Target(
                            "{{123}}",
                            new Integer[] { 123 }
                    ),
                    new Target(
                            "{{4,2,3},{3},{2,3,4,1},{2,3}}",
                            new Integer[] { 3,2,4,1 }
                    ),
            })
        );

        /**
         * 다른 solution 풀이 방향
         * - {}을 풀고, 전체 원소별 갯수를 기준으로 정렬
         */
        tester.test( new TupleSolution01() );
    }


    class Target implements TestTarget<String, Integer[]> {

        private final String input;
        private final Integer[] expected;

        public Target( String input, Integer[] expected ) {
            this.input      = input;
            this.expected   = expected;
        }
        @Override
        public String getInput() {
            return this.input;
        }

        @Override
        public Integer[] getExpected() {
            return this.expected;
        }

        @Override
        public boolean isEqualToExpected( Integer[] answer ) {
            int len = answer.length;
            while ( len > 0 ) {
                int idx = len - 1;
                int a = answer[ idx ];
                int e = this.expected[ idx ];
                if ( a == e ) {
                    len--;
                    continue;
                } else {
                    return false;
                }
            }
            return true;
        }
    }
}
