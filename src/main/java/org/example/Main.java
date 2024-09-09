package org.example;

import org.example.algorithm.kakao2019.abusiveuser.AbusiveUserProblem;
import org.example.algorithm.kakao2019.tuple.TupleProblem;
import org.example.algorithm.kakao2020.gems.GemsProblem;
import org.example.algorithm.kakao2020.kepad.KeypadProblem;
import org.example.algorithm.kakao2020.maxformula.MaximizeFormulaProblem;
import org.example.algorithm.kakao2021.corona.SocialDistancingProblem;
import org.example.initialization.InitStudy;
import org.example.jackson.JacksonStudy;
import org.example.study.StudyLauncher;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        StudyLauncher launcher = new InitStudy();
        launcher.launch();
    }
}