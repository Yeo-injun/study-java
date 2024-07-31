package org.example;

import org.example.algorithm.kakao2019.tuple.TupleProblem;
import org.example.algorithm.kakao2020.maxformula.MaximizeFormulaProblem;
import org.example.algorithm.kakao2021.corona.SocialDistancingProblem;
import org.example.study.StudyLauncher;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        StudyLauncher launcher = new SocialDistancingProblem();
        launcher.launch();
    }
}