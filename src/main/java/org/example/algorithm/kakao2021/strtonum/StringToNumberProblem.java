package org.example.algorithm.kakao2021.strtonum;

import org.example.algorithm.AlgorithmTester;
import org.example.algorithm.TestTarget;
import org.example.study.StudyLauncher;

import java.util.Arrays;

public class StringToNumberProblem implements StudyLauncher {
    private static final String PROBLEM_URL = "https://school.programmers.co.kr/learn/courses/30/lessons/81301";
    @Override
    public void launch() {
        TestTarget[] targets = {
            new Target( "one4seveneight", 1478 ),
            new Target( "23four5six7", 234567 ),
            new Target( "2three45sixseven", 234567 ),
            new Target( "123", 123 ),
        };
        AlgorithmTester tester = new AlgorithmTester( Arrays.asList( targets ) );
        tester.test( new StringToNumberSolution01() );
    }

    class Target implements TestTarget<String, Integer> {

        private String input;
        private Integer expected;

        public Target( String input, Integer expected ) {
            this.input      = input;
            this.expected   = expected;
        }
        @Override
        public String getInput() {
            return this.input;
        }

        @Override
        public Integer getExpected() {
            return this.expected;
        }

        @Override
        public boolean isEqualToExpected( Integer answer ) {
            return this.expected.intValue() == answer.intValue();
        }
    }
}
