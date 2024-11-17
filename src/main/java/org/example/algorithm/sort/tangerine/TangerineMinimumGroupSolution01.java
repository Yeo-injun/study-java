package org.example.algorithm.sort.tangerine;

import org.example.algorithm.core.Solvable;

import java.util.Arrays;

public class TangerineMinimumGroupSolution01 implements Solvable<Object[], Integer> {
    /**
     * 정렬의 성능
     * - java에서 Array 정렬 방법
     * - 정렬시 성능 비교
     */
    @Override
    public Integer solve( Object[] input ) {
        int k = (int) input[ 0 ];
        int[] tangerine = (int[]) input[ 1 ];
        // 조건을 충족하는 최소 크기 갯수를 리턴해야 함
        // 크기별로 몇개씩 존재하는지를 파악해야 함
        // 많은 갯수를 가진 크기를 우선적으로 k갯수와 비교해야 함 ( k갯수를 넘는지 확인 )
        // 귤의 최대 갯수는 1000만개로 크기종류도 최대 1000만개임

        // 1000만개를 순회하여 크기별 갯수 파악
        // 크기별 갯수기준 큰 순으로 정렬

        // 크기별 갯수 정보를 가지고 있을 배열 생성
        int MAX_CLASS_COUNT = 10000001;
        int[] sizeArray = new int[ MAX_CLASS_COUNT ];
        for ( int tSize : tangerine ) {
            sizeArray[ tSize ]++;
        }
        // 성능이슈 존재하는 것으로 추정
        // 정렬하지 않고 연산하는 방법은?
        Arrays.sort( sizeArray );
        int answer = 0;
        for ( int i=1;  i < MAX_CLASS_COUNT; i++ ) {
            answer++;
            k -= sizeArray[ MAX_CLASS_COUNT - i ];
            if ( k <= 0 ) {
                break;
            }
        }

        return answer;
    }

}
