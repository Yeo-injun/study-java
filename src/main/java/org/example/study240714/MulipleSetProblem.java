package org.example.study240714;

import org.example.algorithm.Solver;
import org.example.study.StudyLauncher;

public class MulipleSetProblem implements StudyLauncher {

    //565812 자카드 유사도
    @Override
    public void launch() {
        String[] inputs = new String[] { "FRANCE", "french" };
        Solver<Integer, String[]> solver = new MulipleSetSolution02();
        int answer = solver.solve( inputs );
        System.out.println( answer );
    }
}
