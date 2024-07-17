package org.example.study240714;

import org.example.algorithm.Solver;

import java.util.*;


/**
 * 정답 풀이
 * - 다중집합의 교집합과 합집합을 구하는 방식이 문제해결의 포인트였음.
 * - 다중집합의 교집합의 크기는 중복되는 원소의 갯수 중 작은 원소의 갯수를 반영
 * - 다중집합의 합집합의 크기는 존재하는 원소의 갯수 중 큰 원소의 갯수를 반영
 * - 이를 위해 두 집합의 일반 합집합을 구하고, 일반 합집합의 원소들을 순회.
 * - 각각 다중집합의 원소들의 갯수를 가져와 최대/최소 비교를 통해 다중집합의 교집합/합집합을 구함.
 */
public class MulipleSetSolution03 implements Solver< Integer, String[] > {

    @Override
    public Integer solve( String[] strings ) {

        MultiSet ms1 = new MultiSet( strings[0] );
        MultiSet ms2 = new MultiSet( strings[1] );

        // 일반합집합
        Set<String> elementSet1 = ms1.getElements();
        Set<String> elementSet2 = ms2.getElements();

        Set<String> union = new HashSet<>();
        union.addAll( elementSet1 );
        union.addAll( elementSet2 );

        double multiIntersectionCount = 0;
        double multiUnionCount = 0;
        for ( String e : union ) {
            int count1 = ms1.getElementCount( e );
            int count2 = ms2.getElementCount( e );
            // 중복집합의 합집합의 수
            multiUnionCount         += Math.max( count1, count2 );
            // 중복집합의 교집합의 수
            multiIntersectionCount  += Math.min( count1, count2 );
        }

        if ( multiUnionCount < 1 ) {
            return 65536;
        }
        double similarity = multiIntersectionCount / multiUnionCount;
        return (int) ( similarity *  65536 );
    }

    public class MultiSet {
        Map<String, Integer> elementEntry;

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
            this.elementEntry = elMaps;
        }

        private String[] toElements( String str ) {
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

        public Set<String> getElements() {
            return this.elementEntry.keySet();
        }

        public int getElementCount( String element ) {
            Integer count = this.elementEntry.get( element );
            return  ( null == count ) ? 0 : this.elementEntry.get( element );
        }
    }

}
