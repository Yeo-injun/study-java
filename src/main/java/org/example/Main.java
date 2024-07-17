package org.example;

import org.example.algorithm.kakao2021.strtonum.StringToNumberProblem;
import org.example.study.StudyLauncher;
import org.example.study240710.AlgorithmStockPriceProblem;
import org.example.study240714.MulipleSetProblem;


public class Main {
    public static void main(String[] args) throws InterruptedException {
//        StudyLauncher launcher = new Study2406016();
//        StudyLauncher launcher = new StudyImmutability();
//        StudyLauncher launcher = new StudyAlgorithmProgrammers01();
        StudyLauncher launcher = new StringToNumberProblem();
        launcher.launch();
    }
}