package org.example.algorithm.kakao2021.corona;


import org.example.algorithm.core.AlgorithmTester;
import org.example.algorithm.core.Tester;

import java.io.IOException;


public class Executor {
    public static void main(String[] args) throws IOException {
        Tester tester = new AlgorithmTester( new SocialDistancingProblem() );
        tester.test( new SocialDistancingSolution01() );
    }
}