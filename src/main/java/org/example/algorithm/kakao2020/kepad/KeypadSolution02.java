package org.example.algorithm.kakao2020.kepad;

import org.example.algorithm.Solver;

import java.util.*;

public class KeypadSolution02 implements Solver< String, Object[]> {
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
            Set<String>[] left2PadDistances = new HashSet[] {
                new HashSet<>( List.of( "2" )),
                new HashSet<>( List.of( "1", "5" )),
                new HashSet<>( List.of( "4", "8" )),
                new HashSet<>( List.of( "7", "0" )),
                new HashSet<>( List.of( "*" ))
            };
            Set<String>[] left5PadDistances = new HashSet[] {
                new HashSet<>( List.of( "5" )),
                new HashSet<>( List.of( "2", "4", "8" )),
                new HashSet<>( List.of( "1", "7", "0" )),
                new HashSet<>( List.of( "*" ))
            };
            Set<String>[] left8PadDistances = new HashSet[] {
                    new HashSet<>( List.of( "8" )),
                    new HashSet<>( List.of( "5", "7", "0" )),
                new HashSet<>( List.of( "2", "4", "*" )),
                new HashSet<>( List.of( "1" ))
            };
            Set<String>[] left0PadDistances = new HashSet[] {
                    new HashSet<>( List.of( "0" )),
                    new HashSet<>( List.of( "*", "8" )),
                new HashSet<>( List.of( "5", "7" )),
                new HashSet<>( List.of( "2", "4" )),
                new HashSet<>( List.of( "1" ))
            };
            this.leftHand = new Hand(
                    new String[] { "1","4","7" }
                    , left2PadDistances
                    , left5PadDistances
                    , left8PadDistances
                    , left0PadDistances
                    , "*"
            );

            Set<String>[] right2PadDistances = new HashSet[] {
                    new HashSet<>( List.of( "2" )),
                    new HashSet<>( List.of( "3", "5" )),
                    new HashSet<>( List.of( "6", "8" )),
                    new HashSet<>( List.of( "9", "0" )),
                    new HashSet<>( List.of( "#" ))
            };
            Set<String>[] right5PadDistances = new HashSet[] {
                    new HashSet<>( List.of( "5" )),
                    new HashSet<>( List.of( "2", "6", "8" )),
                    new HashSet<>( List.of( "3", "9", "0" )),
                    new HashSet<>( List.of( "#" ))
            };
            Set<String>[] right8PadDistances = new HashSet[] {
                    new HashSet<>( List.of( "8" )),
                    new HashSet<>( List.of( "5", "9", "0" )),
                    new HashSet<>( List.of( "2", "6", "#" )),
                    new HashSet<>( List.of( "3" ))
            };
            Set<String>[] right0PadDistances = new HashSet[] {
                    new HashSet<>( List.of( "0" )),
                    new HashSet<>( List.of( "#", "8" )),
                    new HashSet<>( List.of( "5", "9" )),
                    new HashSet<>( List.of( "2", "6" )),
                    new HashSet<>( List.of( "3" ))
            };
            this.rightHand = new Hand(
                    new String[] { "3","6","9" }
                    , right2PadDistances
                    , right5PadDistances
                    , right8PadDistances
                    , right0PadDistances
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
        private Set<String>[] pad2Distances;
        private Set<String>[] pad5Distances;
        private Set<String>[] pad8Distances;
        private Set<String>[] pad0Distances;
        private String currentPad;
        private Set<String> priorityPads;

        Hand( String[] priorityPads,
              Set<String>[] pad2Distances,
              Set<String>[] pad5Distances,
              Set<String>[] pad8Distances,
              Set<String>[] pad0Distances,
              String currentPad
        ) {
            this.priorityPads = Set.of( priorityPads );
            this.pad2Distances = pad2Distances;
            this.pad5Distances = pad5Distances;
            this.pad8Distances = pad8Distances;
            this.pad0Distances = pad0Distances;
            this.currentPad = currentPad;
        }

        public int getDistance( String pad ) {
            if ( this.priorityPads.contains( pad ) ) {
                return 0;
            }
            Set<String>[] distances = this.getPadDistances( pad );
            if ( null == distances ) {
                return Integer.MAX_VALUE;
            }
            int d = 0;
            for ( Set<String> set : distances ) {
                if ( set.contains( this.currentPad ) ) {
                    return d;
                } else {
                    d++;
                }
            }
            return Integer.MAX_VALUE;
        }

        private Set<String>[] getPadDistances( String pad ) {
            switch ( pad ) {
                case "2" : return this.pad2Distances;
                case "5" : return this.pad5Distances;
                case "8" : return this.pad8Distances;
                case "0" : return this.pad0Distances;
                default: return null;
            }

        }
        public void setCurrentPad( String pad ) {
            this.currentPad = pad;
        }
    }
}
