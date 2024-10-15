package org.example.algorithm.kakao2020.gems;

import org.example.algorithm.Solver;

import java.util.*;

public class GemsSolution03 implements Solver<int[], String[]> {

    @Override
    public int[] solve( String[] gems ) {
        Map<String, Gem> gemsIdxMap = initGemsIdxMap( gems );
        int startIdx    = 0;
        int lastStartIdx = getLastStartIdx( gemsIdxMap );
        int minPart = Integer.MAX_VALUE;
        int[] answer = new int[] { 0, 0 };
        Collection<Gem> gemValues = gemsIdxMap.values();
        if ( gemValues.size() == 1 ) {
            return new int[] { 1, 1 };
        }
        // 맨 마지막 위치에 한개만 존재하는 보석이 위치할 경우
        int lastEndIdx = getLastEndIdx( gemsIdxMap );
        if ( gems.length == lastEndIdx + 1 ) {
            return new int[] { lastStartIdx + 1, lastEndIdx + 1 };
        }
        while ( startIdx <= lastStartIdx ) {
            int minIdx = Integer.MAX_VALUE;
            int maxIdx = Integer.MIN_VALUE;
            // gem의 갯수가 최대 10만개까지 가능하니 gem에 따라 순회하는 로직을 최소화시켜야 효율성 테스트를 통과할 수 있음.
            for ( Gem gem : gemValues ) {
                // TODO 보석이 한개만 존재하게 될 경우 조기 종료 처리
                // 보석중 유일하게 1개만 존재하는지 체크...
                if ( gem.peekIdx() < startIdx ) {
                    gem.removeIdx();
                }

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

    private int getLastStartIdx( Map<String, Gem> gemIndexMap ) {
        int lastIdx = Integer.MAX_VALUE;
        Set<String> gemNames = gemIndexMap.keySet();
        for ( String name : gemNames ) {
            lastIdx = Math.min( lastIdx, gemIndexMap.get( name ).getLastIdx() );
        }
        return lastIdx;
    }

    private int getLastEndIdx( Map<String, Gem> gemIndexMap ) {
        int lastIdx = Integer.MIN_VALUE;
        Set<String> gemNames = gemIndexMap.keySet();
        for ( String name : gemNames ) {
             Gem gem = gemIndexMap.get( name );
            if ( gem.size() == 1 ) {
                lastIdx = Math.max( lastIdx, gem.getLastIdx() );
            }
        }
        return lastIdx;
    }

    class Gem {
        private String name;
        private Queue<Integer> queue;
        private int lastAddedIdx;
        Gem( String name ) {
            this.name = name;
            this.queue = new LinkedList<>();
        }
        public String getName() {
            return this.name;
        }

        public void addIdx( int index ) {
            this.queue.add( index );
            this.lastAddedIdx = index;
        }

        public int getLastIdx() {
            return this.lastAddedIdx;
        }

        public int peekIdx() {
            return this.queue.peek();
        }

        public int removeIdx() {
            return this.queue.size() < 2
                    ? this.peekIdx()
                    : this.queue.remove();
        }

        public int size() {
            return this.queue.size();
        }
    }

}
