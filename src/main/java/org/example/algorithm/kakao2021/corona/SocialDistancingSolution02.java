package org.example.algorithm.kakao2021.corona;

import org.example.algorithm.Solver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SocialDistancingSolution02 implements Solver<int[], String[][]> {

    /**
     * by. 탐색 알고리즘 구현
     *  -  사람들간의 맨하튼 거리 판단을 최단거리 이동으로 판단.
     *      즉, 대기실내 존재하는 사람들간의 최단거리를 구하여 맨하튼 거리가 2 이하인 case가 존재하는지 판단하면 됨.
     *      단, 예외 case를 예외처리해줘야 함. ( 이동하면서 벽(X)인 경우는 최대 1번만 허용 )
     *  - 시간복잡도 : 방의 크기는 5X5로 고정되어 있기 때문에 탐색을 수행하는 최대 case는 25건.
     *      ( 방에 사람이 빈틈없이 존재하는 case에 해당. 하지만 위 case는 탐색시작하자마자 사람을 식별하기 때문에 탐색이 조기 종료됨. 종료 조건에 따라 조기 종료처리를 해야 함. )
     *
     **/
    @Override
    public int[] solve( String[][] places ) {
        int[] answer = new int[ 5 ];
        int idx = 0;
        for ( String[] r : places ) {
            Room room = new Room( r );
            if ( room.complianceDistance() ) {
                answer[ idx ] = 1;
            } else {
                answer[ idx ] = 0;
            }
            idx++;
        }
        return answer;
    }

    class Room {
        private String PERSON = "P";
        private String WALL = "X";
        private String EMPTY = "O";
        private String[][] map;

        private Person[] people;

        Room( String[] array ) {
            this.map = new String[ 5 ][];
            List<Person> people = new ArrayList<>();
            int x = 0;
            int y = 0;
            for ( String row : array ) {
                String[] items = row.split( "" );
                this.map[ y ] = items;
                for ( String item : items ) {
                    if ( PERSON.equals( item ) ) {
                        people.add( new Person( x, y ) );
                    }
                    x++;
                }
                y++;
                x=0;
            }
            this.people = people.toArray( Person[]::new );
        }
        public boolean complianceDistance() {
            // 탐색 수행 : 맨하튼 거리 측정
            for ( Person p : this.people ) {
                if ( bfs( p.getLoc() ) ) {
                    return false;
                }
            }
            return true;
        }

        // 맨하튼 거리 2이내에 사람이 존재하는지 확인 ( 중간에 벽으로 가로막히거나 최단거리를 2초과할 경우 false 리턴 )
        private int[][] dirs = new int[][] {
            new int[] { 1, 0 },
            new int[] { -1 , 0 },
            new int[] { 0, 1 },
            new int[] { 0, -1 }
        };
        private boolean bfs( int[] p1 ) {
            /**
             * BFS 너비 우선 탐색 알고리즘 구현시 고려사항
             * - 방문여부 확인 방법 : 탐색대상 범위와 동일한 범위의 배열 혹은 자료구조를 만들어서 true/false로 관리
             * - 탐색 목적지( 탐색 목적지 도달 전 조기 종료 조건 존재유무 확인 )
             * - 방문한 노드를 보관하는 Queue
             * - 탐색 방향 및 탐색 조건 관리
             *
             * 0. 탐색 방향 선언 ( 상화좌우 )
             * 1. 방문여부를 관리하는 배열 생성
             * 2. 현재 위치에 대해 방문 표시
             * 3. 방문한 노드를 보관하는 Queue 선언
             * 4. 현재위치 Queue에 보관
             * 5. Queue가 빌때까지 탐색수행
             *      - Queue에서 탐색을 시작할 기준 노드 꺼내오기
             *      - 기준 노트의 위치에서 탐색방향에 따라 탐색 수행
             *        ( 주어진 탐색 범위에 해당하는지 확인 필요 )
             *      - 탐색 대상 노드의 방문여부 확인
             *      - 방문한 노드의 상태를 확인하고, 조건에 부합하면 방문여부 최신화 및 Queue에 해당 노드 할당
             */
            boolean[][] visited = new boolean[ 5 ][ 5 ];
            int curX = p1[ 0 ];
            int curY = p1[ 1 ];
            visited[ curY ][ curX ] = true;

            Queue<int[]> queue = new LinkedList<>();
            queue.add( p1 );

            // 탐색 수행
            while( !queue.isEmpty() ) {
                int[] current = queue.poll();
                for ( int[] d : this.dirs ) {
                    int newX = current[0] + d[0];
                    int newY = current[1] + d[1];
                    // 거리 체크 : 거리가 2 초과할 경우 바로 return false
                    int manhatenDistance = Math.abs( p1[0] - newX ) + Math.abs( p1[1] - newY );
                    if ( manhatenDistance > 2 ) return false;

                    if ( !this.checkRoomBound( newX, newY ) ) {
                        continue;
                    }

                    if ( visited[ newY ][ newX ] ) {
                        continue;
                    }
                    String item = this.getItem( newX, newY );
                    if ( PERSON.equals( item ) ) {
                        return true;
                    }
                    if ( EMPTY.equals( item ) ) {
                        queue.add( new int[] { newX, newY } );
                    }
                    visited[ newY ][ newX ] = true;
                }
            }
            return false;
        }

        private boolean checkRoomBound( int x, int y ) {
            return ( x > -1 && x < 5 ) && ( y > -1 && y < 5 );
        }
        private String getItem( int x, int y ) {
            return this.map[ y ][ x ];
        }
    }

    class Person {
        private int[] loc; // 위치 (x,y)

        Person( int x, int y ) {
            this.loc = new int[2];
            this.loc[ 0 ] = x;
            this.loc[ 1 ] = y;
        }
        public int[] getLoc() {
            return this.loc;
        }
    }

}
