package org.example.study240710;

import org.example.algorithm.Solver;

import java.util.Stack;

public class StockPriceSolution01  implements Solver< int[], int[] > {
    @Override
    public int[] solve( int[] prices ) {

        Stack<Integer> beginIdxs = new Stack<>();
        int idx = 0;
        int priceCount = prices.length;
        int[] answer = new int[ priceCount ];

        beginIdxs.push( idx );
        for ( idx = 1; idx < priceCount; idx++ ) {
            while( !beginIdxs.empty() && prices[ idx ] < prices[ beginIdxs.peek() ] ) {
                int beginIdx = beginIdxs.pop();
                answer[ beginIdx ] = idx - beginIdx;
            }
            beginIdxs.push( idx );
        }

        while ( !beginIdxs.empty() ) {
            int beginIdx = beginIdxs.pop();
            answer[ beginIdx ] = idx - beginIdx - 1;
        }
        return answer;
    }
}
