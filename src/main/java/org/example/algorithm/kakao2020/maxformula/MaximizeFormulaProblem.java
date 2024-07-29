package org.example.algorithm.kakao2020.maxformula;

import org.example.algorithm.AlgorithmTester;
import org.example.algorithm.TestTarget;
import org.example.study.StudyLauncher;

import java.util.Arrays;

public class MaximizeFormulaProblem implements StudyLauncher {

    private static final String PROBLEM_URL = "https://school.programmers.co.kr/learn/courses/30/lessons/67257";
    @Override
    public void launch() {
        AlgorithmTester tester = new AlgorithmTester(
            Arrays.asList( new Target[] {
//                new Target( "50*6-3*2", 300L ),
                new Target( "100-200*300-500+20", 60420L ),
            })
        );

        tester.test( new MaximizeFormulaSolution01() );
    }


    class Target implements TestTarget<String, Long> {

        private final String input;
        private final Long expected;

        public Target(String input, Long expected) {
            this.input = input;
            this.expected = expected;
        }

        @Override
        public String getInput() {
            return this.input;
        }

        @Override
        public Long getExpected() {
            return this.expected;
        }

        @Override
        public boolean isEqualToExpected(Long answer) {
            return this.expected.longValue() == answer.longValue();
        }
    }

}
