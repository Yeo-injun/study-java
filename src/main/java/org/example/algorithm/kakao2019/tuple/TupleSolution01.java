package org.example.algorithm.kakao2019.tuple;

import org.example.algorithm.Solver;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TupleSolution01 implements Solver<Integer[], String> {

    @Override
    public Integer[] solve( String s ) {
        Stack<Character> stack = new Stack<>();
        BraceElement brace = new BraceElement();

        // 문자열을 파싱하여 브레이스 집합 객체 목록 생성
        List<BraceElement> list = new ArrayList<>();
        int strLen = s.length();
        for ( int i=0; i<strLen; i++ ) {
            char c = s.charAt( i );
            if ( stack.empty() ) {
                stack.push( c );
                continue;
            }
            switch ( c ) {
                case '{' :
                    stack.push( c );
                    break;
                case '}' :
                    stack.pop();
                    if ( stack.empty() ) {
                        break;
                    }
                    brace.done();
                    list.add( brace );
                    brace = new BraceElement();
                    break;
                case ',' :
                default:
                    brace.add( c );
            }
        }

        // BraceElement[]을 원소의 갯수로 배열 정렬
        BraceElement[] elements = new BraceElement[ list.size() ];
        for ( BraceElement b : list ) {
            elements[ b.getSize() - 1 ] = b;
        }

        // 정렬된 BraceElemet[]를 순회하면서 구성 원소를 집합에 할당.
        // 마지막에 남는 원소를 본인 위치( 집합의 크기가 곧 위치 )의 배열 idx에 할당
        Set<Integer> set = new HashSet<>();
        Integer[] result = new Integer[ list.size() ];
        for ( BraceElement e : elements ) {
            int remains = e.remainItem( set );
            result[ e.getSize() - 1 ] = remains;
        }
        return result;
    }

    public class BraceElement{
        private int size;
        private StringBuilder buffer;
        private Integer[] items;

        public BraceElement() {
            this.size = 0;
            this.buffer = new StringBuilder();
        }

        public void add( char item ) {
            if ( item == ',' && this.buffer.length() == 0 ) {
                return;
            }
            this.buffer.append( item );
        }

        public void done() {
            String string = buffer.toString();
            String[] str = string.split( "," );
            this.items = Arrays.stream(str).map( Integer::parseInt ).collect( Collectors.toList() ).toArray( Integer[]::new );
            this.size = this.items.length;
        }

        public int getSize() {
            return this.size;
        }

        public int remainItem( Set<Integer> set ) {
            for ( int i : this.items ) {
                if ( set.add( i ) ) {
                    return i;
                }
                continue;
            }
            return -1;
        }
    }

}
