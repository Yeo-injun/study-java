package org.example.study240710;

import org.example.algorithm.Solver;

import java.util.LinkedList;
import java.util.Queue;

public class StockPriceSolution02 implements Solver< int[], int[] > {
    @Override
    public int[] solve( int[] prices ) {
        int size = prices.length;
        int[] answer = new int[ size ];
        int firstIdx = 0;
        int secStartIdx = 1;
        for ( int f : prices ) {
            int count = 0;
            for ( int sIdx=secStartIdx; sIdx < size; sIdx++ ) {
                int s = prices[ sIdx ];
                if ( f <= s ) {
                    count++;
                } else {
                    count++;
                    break;
                }
            }
            answer[ firstIdx ] = count;
            firstIdx++;
            secStartIdx++;
        }
        return answer;
    }
}
