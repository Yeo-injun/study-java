package org.example.algorithm.kakao2020.gems;

import org.example.algorithm.Solver;

import java.util.*;
import java.util.stream.Collectors;

public class GemsSolution03 implements Solver<int[], String[]> {

    @Override
    public int[] solve( String[] gems ) {

        // 주어진 데이터에서 종류를 추려내야 한다.
        // 가장 짧은 구간을 찾아야 한다.
        // 연속되어야 한다.
        Map<String, Gem> gemsIdxMap = initGemsIdxMap( gems );
        int startIdx    = 0;
        int lastIdx     = gems.length;
        int lastStartIdx = lastIdx - gemsIdxMap.size();
        int minPart = Integer.MAX_VALUE;
        int[] answer = new int[] { 0, 0 };
        while ( startIdx <= lastStartIdx ) {
            int baseIdx = startIdx;
            gemsIdxMap.forEach( ( name, gem ) -> {
                if ( gem.peekIdx() < baseIdx ) {
                    gem.removeIdx();
                }
            });

            int minIdx = Integer.MAX_VALUE;
            int maxIdx = Integer.MIN_VALUE;
            Collection<Gem> gemValues = gemsIdxMap.values();
            for ( Gem gem : gemValues ) {
                int idx = gem.peekIdx();
                minIdx = Math.min( minIdx, idx );
                maxIdx = Math.max( maxIdx, idx );
            }
            if ( minPart > ( maxIdx - minIdx ) ) {
                minPart = maxIdx - minIdx;
                answer[0] = minIdx;
                answer[1] = maxIdx;
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

    private Map<String, Gem> initGemsIdxMap( String[] gems ) {
        Map<String, Gem> idxMap = new HashMap<>();
        int idx = 0;
        for ( String gemName : gems ) {
            if ( idxMap.containsKey( gemName ) ) {
                Gem gem = idxMap.get( gemName );
                gem.addIdx( idx );
            } else {
                Gem newGem = new Gem( gemName );
                newGem.addIdx( idx );
                idxMap.put( gemName, newGem );
            }
            idx++;
        }
        return idxMap;
    }

    class Gem {
        private String name;
        private Queue<Integer> queue;

        Gem( String name ) {
            this.name = name;
            this.queue = new LinkedList<>();
        }

        public void addIdx( int index ) {
            this.queue.add( index );
        }

        public int peekIdx() {
            return this.queue.peek();
        }

        public int removeIdx() {
            return this.queue.size() < 2
                    ? this.peekIdx()
                    : this.queue.remove();
        }
    }

}
