package org.example;

import org.example.study.StudyLauncher;
import org.example.study240710.AlgorithmStockPriceProblem;


public class Main {
    public static void main(String[] args) throws InterruptedException {
//        StudyLauncher launcher = new Study2406016();
//        StudyLauncher launcher = new StudyImmutability();
//        StudyLauncher launcher = new StudyAlgorithmProgrammers01();
        StudyLauncher launcher = new AlgorithmStockPriceProblem();
        launcher.launch();
    }
}