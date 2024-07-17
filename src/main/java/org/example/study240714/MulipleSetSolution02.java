package org.example.study240714;

import org.example.algorithm.Solver;

import java.util.*;


public class MulipleSetSolution02 implements Solver< Integer, String[] > {

    @Override
    public Integer solve( String[] strings ) {

        MultiSet ms1 = new MultiSet( strings[0] );
        MultiSet ms2 = new MultiSet( strings[1] );

        // 일반합집합
        
        // 중복집합의 합집합의 수
        // 중복집합의 교집합의 수
        double interSectionCount = ms1.countInterSection( ms2 );
        double unionCount = ms1.countUnion( ms2 );
        if ( interSectionCount < 1 || unionCount < 1 ) {
            return 65536;
        }
        double similarity = interSectionCount / unionCount;
        return (int) ( similarity *  65536 );
    }

    public class MultiSet {
        Map<String, Integer> elements;

        MultiSet( String str ) {
            String[] elements = this.toElements( str );
            Map<String, Integer> elMaps = new HashMap<>();
            for ( String e : elements ) {
                if ( elMaps.containsKey( e ) ) {
                    int count = elMaps.get( e );
                    elMaps.put( e, ++count );
                } else {
                    elMaps.put( e, 1 );
                }
            }
            this.elements = elMaps;
        }

        public String[] toElements( String str ) {
            List<String> elementList = new ArrayList<>();
            char[] charArr = str.toCharArray();
            String firstStr = null;
            for ( char c : charArr ) {
                boolean isEnglishChar = ( c >= 65 && c <= 90 ) || ( c >= 97 && c <=122 );
                if ( isEnglishChar ) {
                    if ( null == firstStr ) {
                        firstStr = String.valueOf( c );
                    } else {
                        String secStr = String.valueOf( c );
                        // 대소문자 상관없으니 보관을 소문자로
                        String element = ( firstStr + secStr ).toLowerCase();
                        elementList.add( element );
                        firstStr = secStr;
                    }
                } else {
                    firstStr = null;
                }
            }
            return elementList.toArray( new String[0] );
        }

        boolean hasElement( String e ) {
            return this.elements.containsKey( e );
        }

        public int getElementCount( String e ) {
            if ( this.elements.containsKey( e ) ) {
                return this.elements.get( e );
            }
            return 0;
        }

        public int countInterSection( MultiSet ms ) {
            int count = 0;
            for ( String e : this.elements.keySet() ) {
                if ( ms.hasElement( e ) ) {
                    int sElementCnt = this.getElementCount( e );
                    int tElementCnt = ms.getElementCount( e );
                    if ( sElementCnt <= tElementCnt ) {
                        count += sElementCnt;
                    }
                }
            }
            return count;
        }

        public int countUnion( MultiSet ms ) {
            Set<String> union = new HashSet<>();
            union.addAll( this.elements.keySet() );
            union.addAll( ms.elements.keySet() );

            int count = 0;
            for ( String e : union ) {
                int sElementCnt = this.getElementCount( e );
                int tElementCnt = ms.getElementCount( e );
                if ( sElementCnt > 1 && tElementCnt > 1 ) {
                    count += Math.max(sElementCnt, tElementCnt);
                } else {
                    count++;
                }
            }
            return count;
        }
    }

}
