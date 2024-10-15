package org.example.algorithm.kakao2021.corona;

import org.example.algorithm.core.Problem;
import org.example.algorithm.core.Testable;

import java.util.List;

public class SocialDistancingProblem implements Problem {
    /**
     * 풀이방법
     * 1. 탐색알고리즘으로 구현해보기
     */
    private static final String PROBLEM_URL = "https://school.programmers.co.kr/learn/courses/30/lessons/81302#fnref1";

    @Override
    public List<Testable> getTestCases() {
        return List.of(
            new SocialDistancingTestCase(
                new String[][] {
                    new String[] { "POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP" },
                    new String[] { "POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP" },
                    new String[] { "PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX" },
                    new String[] { "OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO" },
                    new String[] { "PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP" },
                },
                new int[] { 1,0,1,1,1 }
            )
        );
    }
}
