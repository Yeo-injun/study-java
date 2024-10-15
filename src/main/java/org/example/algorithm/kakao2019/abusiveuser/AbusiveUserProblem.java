package org.example.algorithm.kakao2019.abusiveuser;

import org.example.algorithm.AlgorithmTester;
import org.example.algorithm.TestTarget;
import org.example.algorithm.kakao2021.corona.SocialDistancingSolution02;
import org.example.study.StudyLauncher;

import java.util.List;
import java.util.Arrays;

public class AbusiveUserProblem implements StudyLauncher {
    private static final String PROBLEM_URL = "https://school.programmers.co.kr/learn/courses/30/lessons/64064";

    /**
     * 풀이방법
     */
    @Override
    public void launch() {
        AlgorithmTester tester = new AlgorithmTester(
            Arrays.asList( new Target[] {
                new Target(
                    new String[][] {
                        new String[] { "frodo", "fradi", "crodo", "abc123", "frodoc" },
                        new String[] { "fr*d*", "abc1**" },
                    },
                    2
                ),
                new Target(
                        new String[][] {
                            new String[] { "frodo", "fradi", "crodo", "abc123", "frodoc" },
                            new String[] { "*rodo", "*rodo", "******" },
                        },
                        2
                ),
                new Target(
                        new String[][] {
                            new String[] { "frodo", "fradi", "crodo", "abc123", "frodoc" },
                            new String[] { "fr*d*", "*rodo", "******", "******" },
                        },
                        3
                ),
            })
        );
        tester.test( new SocialDistancingSolution02() );
    }

    class Target implements TestTarget<String[][], Integer> {

        private final String[][] input;
        private final Integer expected;

        public Target( String[][] input, Integer expected ) {
            this.input      = input;
            this.expected   = expected;
        }
        @Override
        public String[][] getInput() {
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
