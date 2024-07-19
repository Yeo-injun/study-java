package org.example.algorithm.kakao2021.strtonum;

import org.example.algorithm.Solver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StringToNumberSolution02 implements Solver<Integer, String> {
    @Override
    public Integer solve( String inputs ) {
        if ( inputs.chars().allMatch( Character::isDigit ) ) {
            return Integer.parseInt( inputs );
        }
        Converter converter = new Converter();
        String number = converter.convertToNumber( inputs );
        return Integer.parseInt( number );
    }

    class Converter {
        private String[] targets = new String[] {
            "zero", "one", "two", "three", "four",
            "five", "six", "seven", "eight", "nine"
        };
        public String convertToNumber( String input ) {
            int num = 0;
            for ( String t : this.targets ) {
                // String 클래스는 불변객체.
                // String타입에서 replaceAll로 값이 변경되면 새로운 메모리 주소에 할당된 새로운 String객체가 return
                // replaceAll에서 값이 변경되지 않으면 본래 메모리 주소 반환
                input = input.replaceAll( t, Integer.toString( num ) );
                num++;
            }
            return input;
        }
    }
}
