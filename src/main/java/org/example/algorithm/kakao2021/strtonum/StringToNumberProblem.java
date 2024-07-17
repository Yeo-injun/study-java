package org.example.algorithm.kakao2021.strtonum;

import org.example.algorithm.Solver;
import org.example.study.StudyLauncher;
import org.example.study240714.MulipleSetSolution03;

public class StringToNumberProblem implements StudyLauncher {
    @Override
    public void launch() {
        String inputs = "one4seveneight";
        Solver<Integer, String> solver = new StringToNumberSolution01();
        int answer = solver.solve( inputs );
        System.out.println( answer );

    }
}
