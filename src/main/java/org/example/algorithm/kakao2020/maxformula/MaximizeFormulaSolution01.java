package org.example.algorithm.kakao2020.maxformula;

import org.example.algorithm.Solver;

import java.util.*;
public class MaximizeFormulaSolution01 implements Solver<Long, String> {

    private static String PLUS = "+";
    private static String MINUS = "-";
    private static String MULTIPLE = "*";
    @Override
    public Long solve( String s ) {
        Formula formula = new Formula( s );
        return formula.calculate();
    }

    public class Formula {

        private final List<String> operators;
        private final String operand;

        private long maxLong;
        Formula ( String s ) {
            List<String> operators = new ArrayList<>( List.of( new String[]{ "*", "+", "-" } ) );
            operators.removeIf( o -> !s.contains(o) );
            this.operators = operators;
            this.operand = s;
            this.maxLong = -1;
         }

        public long calculate() {
            int operatorCount = this.operators.size();
            switch( operatorCount ) {
                case 1:
                    return calculate( this.operators );
                case 2:
                    List<String>[] operaters2 = new List[] {
                            List.of( new String[]{ this.operators.get( 0 ), this.operators.get( 1 ) } ),
                            List.of( new String[]{ this.operators.get( 1 ), this.operators.get( 0 ) } ),
                    };
                    return calculateMax( operaters2 );
                case 3:
                    List<String>[] operaters6 = new List[] {
                        List.of( new String[]{ MULTIPLE, PLUS, MINUS } ),
                        List.of( new String[]{ MULTIPLE, MINUS, PLUS } ),
                        List.of( new String[]{ PLUS, MULTIPLE, MINUS } ),
                        List.of( new String[]{ PLUS, MINUS, MULTIPLE } ),
                        List.of( new String[]{ MINUS, PLUS, MULTIPLE } ),
                        List.of( new String[]{ MINUS, MULTIPLE, PLUS } )
                    };
                    return calculateMax( operaters6 );
                default:
                    return -1;
            }
        }

        private long calculateMax( List<String>[] operators ) {
            for ( List<String> ops : operators ) {
                this.maxLong = Math.max( this.maxLong, calculate( ops ) );
            }
            return this.maxLong;
        }
        private long calculate( List<String> operators ) {
            long result = 0L;
            String operand = this.operand;
            for ( String op : operators ) {
                operand = this.calculate( op, operand );
                if ( operand.matches("-?[0-9]+") ) {
                    result = Math.abs( Long.parseLong( operand ) );
                }
            }
            return result;
        }
        private String calculate( String operator, String operand ) {
            String[] chars = operand.split( "" );
            Stack<String> operandStack = new Stack<>();
            StringBuilder sb = new StringBuilder();
            String num = "";
            for ( String c : chars ) {
                // 숫자 만들기
                if ( Character.isDigit( c.charAt(0) ) ) {
                    num += c;
                // 연산자 확인
                } else {
                    // 계산과정에서 음수가 발생할 경우 예외 처리
                    if ( "".equals( num ) && MINUS.equals( c ) ) {
                        num += c;
                        continue;
                    }
                    // 대상 연산자면 연산대상에 보관
                    if ( operator.equals( c ) ) {
                        if ( !operandStack.isEmpty() && operator.equals( operandStack.peek() ) ) {
                            operandStack.push( Long.toString( calculateStack( operandStack, num ) ) );
                            operandStack.push( c );
                        } else {
                            operandStack.push( num );
                            operandStack.push( c );
                        }
                    // 대상 연산자가 아닌 경우
                    } else {
                        if ( !operandStack.isEmpty() && operator.equals( operandStack.peek() ) ) {
                            sb.append( calculateStack( operandStack, num ) );
                            sb.append( c );
                        } else {
                            sb.append( num );
                            sb.append( c );
                        }
                    }
                    // 숫자 보관 초기화
                    num = "";
                }
            }
            if ( !operandStack.isEmpty() && operator.equals( operandStack.peek() ) ) {
                sb.append( calculateStack( operandStack, num ) );
            } else {
                sb.append( num );
            }
            return sb.toString();
        }

        private long calculateStack( Stack<String> operandStack, String num ) {
            String op   = operandStack.pop();
            String num1 = operandStack.pop();
            return operate( num1, num, op );
        }
        private long operate( String operand1, String operand2, String operator ) {
            return operate( Long.parseLong( operand1 ), Long.parseLong( operand2 ), operator );
        }
        private long operate( long operand1, long operand2, String operator ) {
            switch ( operator ) {
                case "*" : return operand1 * operand2;
                case "+" : return operand1 + operand2;
                case "-" : return operand1 - operand2;
                default:
                    throw new Error( "operate Error ");
            }
        }
    }



}
