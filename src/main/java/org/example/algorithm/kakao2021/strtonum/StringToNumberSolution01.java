package org.example.algorithm.kakao2021.strtonum;

import org.example.algorithm.Solver;

import java.util.HashMap;
import java.util.Map;

// 배열의 인덱스와 replace와 replaceAll을 이용한 방법도 구현해보기
public class StringToNumberSolution01 implements Solver<Integer, String> {

    @Override
    public Integer solve( String inputs ) {
        Converter converter = new Converter();
        StringBuilder sb = new StringBuilder();
        char[] charArr = inputs.toCharArray();
        for ( char c : charArr ) {
            if ( Character.isDigit( c ) ) {
                sb.append( c );
            } else {
                String number = converter.convertToNumber( c );
                if ( null != number ) {
                    sb.append( number );
                }
            }
        }
        return Integer.parseInt( sb.toString() );
    }

    class Converter {
        private StringBuilder builder;

        private Map<String, String> convertMap;

        public Converter() {
            this.builder    = new StringBuilder();
            this.convertMap = new HashMap<>();
            convertMap.put( "zero", "0" );
            convertMap.put( "one", "1" );
            convertMap.put( "two", "2" );
            convertMap.put( "three", "3" );
            convertMap.put( "four", "4" );
            convertMap.put( "five", "5" );
            convertMap.put( "six", "6" );
            convertMap.put( "seven", "7" );
            convertMap.put( "eight", "8" );
            convertMap.put( "nine", "9" );
        }

        public String convertToNumber( char c ) {
            this.builder.append( c );
            // 쌓은 문자가 일정 길이 이상이 되면 컨버팅 시도
            // 컨버팅 성공하면 컨버터 초기화 및 결과값 리턴
            // 실패하면 이후 문자 추가해서 컨버팅 재시도
            if ( this.builder.length() < 3 ) {
                return null;
            }
            String str      = this.builder.toString();
            String result   = convertMap.get( str );
            if ( null != result ) {
                this.builder.setLength( 0 );
            }
            return result;
        }
    }
}
