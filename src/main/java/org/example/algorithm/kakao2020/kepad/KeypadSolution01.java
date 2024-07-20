package org.example.algorithm.kakao2020.kepad;

import org.example.algorithm.Solver;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class KeypadSolution01 implements Solver< String, Object[]> {
    @Override
    public String solve(Object[] inputs ) {
        int[] numbers   = ( int[] ) inputs[0];
        String hand     = ( String ) inputs[1];

        StringBuilder sb = new StringBuilder();
        KeyStroker stroker = new KeyStroker( hand );
        for ( int num : numbers ) {
            sb.append( stroker.typeHand( Integer.toString( num ) ) );
        }
        return sb.toString();
    }

    class KeyStroker {
        private String mainHand;
        private Hand leftHand;
        private Hand rightHand;

        public KeyStroker( String mainHand ) {
            switch ( mainHand ) {
                case "right" : this.mainHand = "R"; break;
                case "left" : this.mainHand = "L"; break;
                default: this.mainHand = "error";
            }
            Map<String, Integer> pad2 = new HashMap<>();
            pad2.put( "2", 0 );
            pad2.put( "1", 1 );
            pad2.put( "5", 1 );
            pad2.put( "4", 2 );
            pad2.put( "8", 2 );
            pad2.put( "7", 3 );
            pad2.put( "0", 3 );
            pad2.put( "*", 4 );

            Map<String, Integer> pad5 = new HashMap<>();
            pad5.put( "5", 0 );
            pad5.put( "2", 1 );
            pad5.put( "4", 1 );
            pad5.put( "8", 1 );
            pad5.put( "1", 2 );
            pad5.put( "7", 2 );
            pad5.put( "0", 2 );
            pad5.put( "*", 3 );

            Map<String, Integer> pad8 = new HashMap<>();
            pad8.put( "8", 0 );
            pad8.put( "5", 1 );
            pad8.put( "7", 1 );
            pad8.put( "0", 1 );
            pad8.put( "2", 2 );
            pad8.put( "4", 2 );
            pad8.put( "*", 2 );
            pad8.put( "1", 3 );

            Map<String, Integer> pad0 = new HashMap<>();
            pad0.put( "0", 0 );
            pad0.put( "8", 1 );
            pad0.put( "*", 1 );
            pad0.put( "7", 2 );
            pad0.put( "5", 2 );
            pad0.put( "4", 3 );
            pad0.put( "2", 3 );
            pad0.put( "1", 4 );
            this.leftHand = new Hand(
                    new String[] { "1","4","7" }
                    ,pad2, pad5, pad8, pad0
                    , "*"
            );
            Map<String, Integer> rightPad2 = new HashMap<>();
            Map<String, Integer> rightPad5 = new HashMap<>();
            Map<String, Integer> rightPad8 = new HashMap<>();
            Map<String, Integer> rightPad0 = new HashMap<>();
            rightPad2.put( "2", 0 );
            rightPad2.put( "3", 1 );
            rightPad2.put( "5", 1 );
            rightPad2.put( "6", 2 );
            rightPad2.put( "8", 2 );
            rightPad2.put( "9", 3 );
            rightPad2.put( "0", 3 );
            rightPad2.put( "#", 4 );

            rightPad5.put( "5", 0 );
            rightPad5.put( "2", 1 );
            rightPad5.put( "6", 1 );
            rightPad5.put( "8", 1 );
            rightPad5.put( "3", 2 );
            rightPad5.put( "9", 2 );
            rightPad5.put( "0", 2 );
            rightPad5.put( "#", 3 );

            rightPad8.put( "8", 0 );
            rightPad8.put( "5", 1 );
            rightPad8.put( "9", 1 );
            rightPad8.put( "0", 1 );
            rightPad8.put( "2", 2 );
            rightPad8.put( "6", 2 );
            rightPad8.put( "#", 2 );
            rightPad8.put( "3", 3 );

            rightPad0.put( "0", 0 );
            rightPad0.put( "8", 1 );
            rightPad0.put( "#", 1 );
            rightPad0.put( "9", 2 );
            rightPad0.put( "5", 2 );
            rightPad0.put( "6", 3 );
            rightPad0.put( "2", 3 );
            rightPad0.put( "3", 4 );
            this.rightHand = new Hand(
                    new String[] { "3","6","9" }
                    ,rightPad2, rightPad5, rightPad8, rightPad0
                    , "#"
            );
        }

        public String typeHand( String pad ) {
            int leftDistance = this.leftHand.getDistance( pad );
            int rightDistance = this.rightHand.getDistance( pad );
            if ( leftDistance < rightDistance ) {
                this.leftHand.setCurrentPad( pad );
                return "L";
            } else if ( leftDistance > rightDistance ) {
                this.rightHand.setCurrentPad( pad );
                return "R";
            } else {
                switch ( this.mainHand ) {
                    case "R" : this.rightHand.setCurrentPad( pad ); break;
                    case "L" : this.leftHand.setCurrentPad( pad ); break;
                }
                return this.mainHand;
            }
        }
    }

    public class Hand {
        private Map<String, Integer> pad2;
        private Map<String, Integer> pad5;
        private Map<String, Integer> pad8;
        private Map<String, Integer> pad0;
        private String currentPad;
        private Set<String> priorityPads;

        Hand( String[] priorityPads,
              Map<String, Integer> pad2,
              Map<String, Integer> pad5,
              Map<String, Integer> pad8,
              Map<String, Integer> pad0,
              String currentPad
        ) {
            this.priorityPads = Set.of( priorityPads );
            this.pad2 = pad2;
            this.pad5 = pad5;
            this.pad8 = pad8;
            this.pad0 = pad0;
            this.currentPad = currentPad;
        }

        public int getDistance( String pad ) {
            if ( this.priorityPads.contains( pad ) ) {
                return 0;
            }
            switch ( pad ) {
                case "2" : return this.pad2.get( this.currentPad );
                case "5" : return this.pad5.get( this.currentPad );
                case "8" : return this.pad8.get( this.currentPad );
                case "0" : return this.pad0.get( this.currentPad );
            }
            return Integer.MAX_VALUE;
        }

        public void setCurrentPad( String pad ) {
            this.currentPad = pad;
        }
    }
}
