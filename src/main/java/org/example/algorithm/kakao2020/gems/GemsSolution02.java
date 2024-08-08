package org.example.algorithm.kakao2020.gems;

import org.example.algorithm.Solver;

import java.util.*;
import java.util.stream.Collectors;

public class GemsSolution02 implements Solver<int[], String[]> {

    @Override
    public int[] solve( String[] gems ) {
        // 보석종류 추출하기
        Map<String, Integer> gemsIdxMap = initGemsIdxMap( gems );
        int startIdx    = 0;
        int lastIdx     = gems.length;
        int lastStartIdx = lastIdx - gemsIdxMap.size();
        int minPart = Integer.MAX_VALUE;
        int[] answer = new int[] { 0, 0 };
        while ( startIdx <= lastStartIdx ) {

            // 시작지점보다 뒤쳐진 인덱스의 보석은 인덱스 업데이트
            Set<String> updateTarget = new HashSet<>();
            final int baseIdx = startIdx;
            gemsIdxMap.forEach(( g, idx ) -> {
                int gemIdx = gemsIdxMap.get( g );
                if ( gemIdx < baseIdx ) {
                    updateTarget.add( g );
                }
            });

            // 인덱스를 업데이트해야 하는 보석들의 인덱스 업데이트

            for ( int i = startIdx; i <= lastIdx-1; i++ ) {
                String newGem = gems[ i ];
                if ( updateTarget.remove( newGem ) ) {
                    gemsIdxMap.put( newGem, i );
                }
                if ( updateTarget.isEmpty() ) {
                    break;
                }
            }

            if ( !updateTarget.isEmpty() ) {
                startIdx++;
                continue;
            }


            // 자연정렬로 정렬 >> 숫자는 오름찬순 / 문자열은 사전순
            // 역순으로 정렬하여 맨처음 값 가져오기
            List<Integer> idxList = gemsIdxMap.values()
                                        .stream()
                                        .sorted( Comparator.reverseOrder() )
                                        .collect( Collectors.toList() );
            int gemLastIdx = idxList.get( 0 );
            if ( minPart > ( gemLastIdx - startIdx ) ) {
                minPart = gemLastIdx - startIdx;
                answer[0] = startIdx;
                answer[1] = gemLastIdx;
                if ( minPart == gems.length ) {
                    break;
                }
            }
            // 마지막 초기화
            startIdx++;
        }
        answer[0] += 1;
        answer[1] += 1;
        return answer;
    }

    private Map<String, Integer> initGemsIdxMap( String[] gems ) {
        Map<String, Integer> gemsIdx = new HashMap<>();
        for ( String gemType : gems ) {
            gemsIdx.put( gemType, -1 );
        }
        return gemsIdx;
    }
}
