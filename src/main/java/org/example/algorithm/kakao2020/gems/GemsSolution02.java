package org.example.algorithm.kakao2020.gems;

import org.example.algorithm.Solver;

import java.util.*;

public class GemsSolution02 implements Solver<int[], String[]> {

    @Override
    public int[] solve( String[] gems ) {
        List<String> gemList = Arrays.asList( gems );
        Map<String, Integer> gemsIdxMap = initGemsIdxMap( gems );
        int startIdx    = 0;
        int lastIdx     = gems.length;
        int lastStartIdx = lastIdx - gemsIdxMap.size();
        int minPart = Integer.MAX_VALUE;
        int[] answer = new int[] { 0, 0 };
        while ( startIdx <= lastStartIdx ) {
            int baseIdx = startIdx;
            Set<String> updateTarget = new HashSet<>();
            gemsIdxMap.forEach( ( g, i ) -> {
                if ( i < baseIdx ) {
                    updateTarget.add( g );
                }
            });

            if ( updateTarget.isEmpty() ) {
                break;
            }


            while( !updateTarget.isEmpty() ) {
                String gem = updateTarget.stream().findFirst().get();
                int gemIx = gemList.indexOf( gem );
                if ( gemIx < 0 ) {
                    break;
                }
                gemsIdxMap.put( gem, gemIx );
                updateTarget.remove( gem );
                gemList.set( gemIx, "NONE" );
            }
//            for ( int i = startIdx; i <= lastIdx-1; i++ ) {
//                String newGem = gems[ i ];
//                if ( updateTarget.remove( newGem ) ) {
//                    gemsIdxMap.put( newGem, i );
//                }
//                if ( updateTarget.isEmpty() ) {
//                    break;
//                }
//            }

            if ( !updateTarget.isEmpty() ) {
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
            // 중요한 예외 케이스
            if ( minPart == gems.length ) {
                break;
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
