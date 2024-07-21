package org.example.algorithm.kakao2019;

import org.example.algorithm.AlgorithmTester;
import org.example.algorithm.TestTarget;
import org.example.study.StudyLauncher;

import java.util.Arrays;

public class DollClawMachineProblem implements StudyLauncher {

    private static final String PROBLEM_URL = "https://school.programmers.co.kr/learn/courses/30/lessons/64061";
    @Override
    public void launch() {
        AlgorithmTester tester = new AlgorithmTester(
                Arrays.asList( new Target[] {
                        new Target(
                            new Object[] {
                                    new int[][]{
                                        new int[] {0,0,0,0,0},
                                        new int[] {0,0,1,0,3},
                                        new int[] {0,2,5,0,1},
                                        new int[] {4,2,4,4,2},
                                        new int[] {3,5,1,3,1},
                                    },
                                    new int[]{ 1,5,3,5,1,2,1,4 }
                            },
                            4
                        )
                })
        );

        /**
         * 개선사항
         * solution의 소요시간을 줄이기.
         * >> board를 라인별 스택으로 변환시키는 과정없이 바로 처리하는 로직으로 개선 필요
         */
        tester.test( new DollClawMachineSolution01() );
    }


    class Target implements TestTarget<Object[], Integer> {

        private final Object[] input;
        private final int expected;

        public Target( Object[] input, Integer expected ) {
            this.input      = input;
            this.expected   = expected;
        }
        @Override
        public Object[] getInput() {
            return this.input;
        }

        @Override
        public Integer getExpected() {
            return this.expected;
        }

        @Override
        public boolean isEqualToExpected( Integer answer ) {
            return this.expected == answer;
        }
    }
}
