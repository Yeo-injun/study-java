package org.example.algorithm.kakao2019;

import org.example.algorithm.Solver;

import java.util.Stack;
import java.util.stream.IntStream;

public class DollClawMachineSolution01 implements Solver<Integer, Object[]> {

    @Override
    public Integer solve( Object[] inputs ) {

        int[][] board   = ( int[][] ) inputs[0];
        int[] moves     = ( int[] ) inputs[1];

        // board의 맨뒤에서부터 스택에 할당
        int lines = board.length;
        DollLine[] dollLines = IntStream.range( 0, lines )
                .mapToObj( i -> new DollLine() )
                .toArray( DollLine[]::new );
        for ( int i=lines-1; i >=0; i-- ) {
            int[] row = board[i];
            for ( int l=0; l < row.length; l++ ) {
                int doll = row[ l ];
                DollLine dollLine = dollLines[l];
                dollLine.setDoll( doll );
            }
        }
        // move위치를 참조하여 인형픽
        Basket basket = new Basket();
        for ( int m : moves ) {
            DollLine dollLine = dollLines[ m - 1 ];
            Integer doll = dollLine.getDoll();
            if ( null == doll ) {
                continue;
            }
            basket.put( doll );
        }
        return basket.getCrashCount();
    }

    class DollLine {
        private Stack<Integer> stack;

        public DollLine () {
            this.stack = new Stack<>();
        }

        public Integer getDoll() {
            if ( this.stack.empty() ) {
                return null;
            }
            return this.stack.pop();
        }

        public void setDoll( Integer doll ) {
            if ( doll > 0 ) {
                this.stack.push( doll );
            }
        }
    }

    class Basket {
        private Stack<Integer> stack;
        private int crashCount;

        public Basket() {
            this.stack = new Stack<>();
            this.crashCount = 0;
        }

        // 바구니에 담기 전 맨위의 인형 비교 - 일치하면 제거 및 충돌횟수 +2
        public void put( Integer doll ) {
            if ( this.stack.empty() ) {
                this.stack.push( doll );
            } else {
                Integer topDoll = this.stack.peek();
                if ( topDoll == doll ) {
                    crashCount += 2;
                    this.stack.pop();
                } else {
                    this.stack.push( doll );
                }
            }
        }

        public int getCrashCount() {
            return this.crashCount;
        }
    }
}
