package org.example.study240710;

import org.example.algorithm.Solver;
import org.example.study.StudyLauncher;


public class AlgorithmStockPriceProblem implements StudyLauncher {
    /**
     * 스택/큐 - 주식가격
     * https://school.programmers.co.kr/learn/courses/30/lessons/42584
     * - 떨어지지 않은 기간이 몇초인지를 구하라는 서술이 명확하지 않음.
     * - 현재 시점 기준으로 그 이후에 가격이 오르거나 유지된 총 시간을 구하라는 것인지
     * - 현재 시점 기준으로 가격이 떨어진 시점까지의 시간을 구하라는 것인지
     * - 서술이 명확하지 않았음.
     *
     * 결론은 현재 시점 기준 가격이 떨어진 시점까지의 시간을 구하라는 의미.
     */

    @Override
    public void launch() {
        int[] prices = { 1, 2, 3, 2, 3 };
        Solver<int[], int[]> sol = new StockPriceSolution01();
        int[] answer = sol.solve( prices );
    }

}
