package org.example.algorithm.sort.tangerine;


import org.example.algorithm.core.AlgorithmTester;
import org.example.algorithm.core.Tester;

import java.io.IOException;


public class Executor {
    public static void main(String[] args) throws IOException {
        Tester tester = new AlgorithmTester( new TangerineMinimumGroupProbelm() );
        tester.test( new TangerineMinimumGroupSolution01() );
    }
}