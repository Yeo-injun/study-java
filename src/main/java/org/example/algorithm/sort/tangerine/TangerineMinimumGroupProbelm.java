package org.example.algorithm.sort.tangerine;

import org.example.algorithm.core.Problem;
import org.example.algorithm.core.Testable;

import java.util.List;

public class TangerineMinimumGroupProbelm implements Problem {
    /**
     * 풀이방법
     * 1. 탐색알고리즘으로 구현해보기
     */
    private static final String PROBLEM_URL = "https://school.programmers.co.kr/learn/courses/30/lessons/138476";

    @Override
    public List<Testable> getTestCases() {
        return List.of(
            new TangerineMinimumGroupTestCase(
                new Object[] { 6, new int[] { 1, 3, 2, 5, 4, 5, 2, 3 } },
                3
            ),
            new TangerineMinimumGroupTestCase(
                new Object[] { 4, new int[] { 1, 3, 2, 5, 4, 5, 2, 3 } },
                2
            ),
            new TangerineMinimumGroupTestCase(
                new Object[] { 2, new int[] { 1, 1, 1, 1, 2, 2, 2, 3 } },
                1
            )
        );
    }
}
