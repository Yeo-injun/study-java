package org.example.algorithm.kakao2020.kepad;

import org.example.algorithm.Solver;

import java.util.Set;

public class KeypadSolution03 implements Solver< String, Object[]> {
    @Override
    public String solve(Object[] inputs ) {
        int[] numbers   = ( int[] ) inputs[0];
        String hand     = ( String ) inputs[1];

        StringBuilder sb = new StringBuilder();
        KeyStroker stroker = new KeyStroker( hand );
        for ( int num : numbers ) {
            sb.append( stroker.typeHand( num ) );
        }
        return sb.toString();
    }

    class KeyStroker {
        private String mainHand;
        private Hand leftHand;
        private Hand rightHand;

        // ( x, y )
        private int[][] keypad = new int[][] {
                new int[] { 1, 0 },   // 0
                new int[] { 0, 3 },   // 1
                new int[] { 1, 3 },   // 2
                new int[] { 2, 3 },   // 3
                new int[] { 0, 2 },   // 4
                new int[] { 1, 2 },   // 5
                new int[] { 2, 2 },   // 6
                new int[] { 0, 1 },   // 7
                new int[] { 1, 1 },   // 8
                new int[] { 2, 1 },   // 9
                new int[] { 0, 0 },   // *
                new int[] { 2, 0 }    // #
        };
        public KeyStroker( String mainHand ) {
            this.mainHand   = mainHand;
            this.leftHand   = new Hand( new Integer[] { 1, 4, 7 }, 10 );
            this.rightHand  = new Hand( new Integer[] { 3, 6, 9 }, 11 );
        }

        public String typeHand( int num ) {
            int leftDistance    = this.leftHand.getDistance( num, this.keypad );
            int rightDistance   = this.rightHand.getDistance( num, this.keypad );
            if ( leftDistance < rightDistance ) {
                this.leftHand.setCurrentPad( num );
                return "L";
            } else if ( leftDistance > rightDistance ) {
                this.rightHand.setCurrentPad( num );
                return "R";
            } else {
                switch ( this.mainHand ) {
                    case "right" :
                        this.rightHand.setCurrentPad( num );
                        return "R";
                    case "left" :
                        this.leftHand.setCurrentPad( num );
                        return "L";
                }
                return "E";
            }
        }
    }

    public class Hand {
        private int currentPad;
        private Set<Integer> priorityPads;

        Hand( Integer[] priorityPads, Integer currentPad ) {
            this.priorityPads   = Set.of( priorityPads );
            this.currentPad     = currentPad;
        }

        public int getDistance( int num, int[][] keypad ) {
            if ( this.priorityPads.contains( num ) ) {
                return 0;
            }
            int[] numPos = keypad[ num ];
            int[] curPos = keypad[ this.currentPad ];
            return Math.abs( numPos[0] - curPos[0] ) + Math.abs( numPos[1] - curPos[1] );
        }

        public void setCurrentPad( int pad ) {
            this.currentPad = pad;
        }
    }
}
