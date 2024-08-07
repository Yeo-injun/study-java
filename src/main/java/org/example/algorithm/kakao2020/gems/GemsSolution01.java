package org.example.algorithm.kakao2020.gems;

import org.example.algorithm.Solver;

import java.util.*;
import java.util.stream.Collectors;

public class GemsSolution01 implements Solver<int[], String[]> {

    @Override
    public int[] solve( String[] gems ) {
        // 보석종류 추출하기
        Set<String> gemTypes = Arrays.stream( gems ).collect( Collectors.toSet() );
        Map<String, Integer> gemsIdxMap = initGemsIdxMap( gemTypes );
        int startIdx    = 0;
        int lastIdx     = gems.length;
        int lastStartIdx = lastIdx - gemTypes.size();
        int minPart = Integer.MAX_VALUE;
        int[] answer = new int[] { 0, 0 };
        while ( startIdx <= lastStartIdx ) {

            // 시작지점보다 뒤쳐진 인덱스의 보석은 인덱스 업데이트

            Set<String> targetForUpdateIdx = new HashSet<>();
            for ( String gem : gemTypes ) {
                int gemIdx = gemsIdxMap.get( gem );
                if ( gemIdx < startIdx ) {
                    targetForUpdateIdx.add( gem );
                }
            }

            // 인덱스를 업데이트해야 하는 보석들의 인덱스 업데이트
            for ( int i = startIdx; i <= lastIdx-1; i++ ) {
                String newGem = gems[ i ];
                if ( targetForUpdateIdx.remove( newGem ) ) {
                    gemsIdxMap.put( newGem, i );
                }
            }

            if ( !targetForUpdateIdx.isEmpty() ) {
                startIdx++;
                continue;
            }

            Collection<Integer> gemsIdxValue = gemsIdxMap.values();
            int gemLastIdx = startIdx;
            for ( int idx : gemsIdxValue ) {
                gemLastIdx = Math.max( gemLastIdx, idx );
            }

            if ( minPart > ( gemLastIdx - startIdx ) ) {
                minPart = gemLastIdx - startIdx;
                answer[0] = startIdx;
                answer[1] = gemLastIdx;
            }
            // 마지막 초기화
            startIdx++;
        }
        answer[0] += 1;
        answer[1] += 1;
        return answer;
    }

    private Map<String, Integer> initGemsIdxMap( Set<String> gemTypes ) {
        Map<String, Integer> gemsIdx = new HashMap<>();
        for ( String gemType : gemTypes ) {
            gemsIdx.put( gemType, -1 );
        }
        return gemsIdx;
    }
}
