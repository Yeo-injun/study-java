package org.example.study240710;

import org.example.study.StudyLauncher;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class StudyAlgorithmProgrammers implements StudyLauncher {
    // 주식가격
    @Override
    public void launch() {
//        int[] prices = { 1, 2, 3, 2, 3 };
//        int[] prices = { 5, 4, 2, 1, 1 };
//        int[] prices = { 4, 3, 2, 1 };
//        int[] prices = { 4, 3, 4, 1, 2, 6, 1 };
//        // 2, 2, 1, 3, 1, 0, 0

//        int[] prices = { 5, 5, 5, 1, 3, 1, 5 };

//        int[] prices = { 1, 5 };
//        int[] prices = { 612, 5 };
//        int[] prices = { 5, 5,5,5,6 };
        int[] prices = { 1, 1,1,2,1 };

        Queue<Integer> queue = new LinkedList<>();
        for ( int p : prices ) {
            queue.add( p );
        }

        int[] answer = new int[ prices.length ];
        int idx = 0;

        while ( !queue.isEmpty() ) {
            int p = queue.poll();
            int notUnderSec   = 0;
            for ( int q : queue ) {
                if ( p <= q ) {
                    notUnderSec++;
                }
            }
            answer[ idx++ ] = notUnderSec;
        }
        System.out.println( answer );
    }

}
