package org.example.study240714;

import org.example.algorithm.Solver;

import java.util.*;


public class MulipleSetSolution01 implements Solver< Integer, String[] > {

    @Override
    public Integer solve( String[] strings ) {

        MultipleSet ms1 = new MultipleSet( strings[0] );
        MultipleSet ms2 = new MultipleSet( strings[1] );

        double interSectionCount = ms1.countInterSection( ms2 );
        double unionCount = ms1.countUnion( ms2 );
        if ( 0 == interSectionCount || 0 == unionCount ) {
            return 65536;
        }
        double similarity = interSectionCount / unionCount;

        return (int) ( similarity *  65536 );
    }

    public class MultipleSet {

        Set<String> elements;
        Map<String, Integer> dupEl;

        MultipleSet( String str ) {
            this.elements = new HashSet<>();
            this.dupEl = new HashMap<>();

            // 한글자씩 읽어들인다.
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
                        if ( this.elements.contains( element ) ) {
                            if ( this.dupEl.containsKey( element ) ) {
                                int dupCount = this.dupEl.get( element );
                                this.dupEl.put( element, ++dupCount );
                            } else {
                                this.dupEl.put( element, 2 );
                            }
                        } else {
                            this.elements.add( element );
                        }
                        firstStr = secStr;
                    }
                } else {
                    firstStr = null;
                }
            }
            // 두글자 연속으로 영어면 요소로 할당한다.

            // 요소로 할당하기 전 이미 존재하는 요소인지 체크하고 존재하면 중복 갯수를 올린다.
        }


        boolean hasElement( String e ) {
            return this.elements.contains( e );
        }

        public int getElementCount( String e ) {
            if ( this.dupEl.containsKey( e ) ) {
                return this.dupEl.get( e );
            }
            return this.hasElement( e ) ? 1 : 0;
        }

        public int countInterSection( MultipleSet ms ) {
            int count = 0;
            for ( String e : this.elements ) {
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

        public int countUnion( MultipleSet ms ) {
            Set<String> union = new HashSet<>();
            union.addAll( this.elements );
            union.addAll( ms.elements );

            int count = 0;
            for ( String e : union ) {
                int sElementCnt = this.getElementCount( e );
                int tElementCnt = ms.getElementCount( e );
                if ( sElementCnt > 1 && tElementCnt > 1 ) {
                    if ( sElementCnt > tElementCnt ) {
                        count += sElementCnt;
                    } else {
                        count += tElementCnt;
                    }
                } else {
                    count++;
                }
            }
            return count;
        }
    }
}
