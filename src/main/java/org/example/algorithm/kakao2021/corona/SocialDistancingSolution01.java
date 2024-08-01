package org.example.algorithm.kakao2021.corona;

import org.example.algorithm.Solver;

import java.util.ArrayList;
import java.util.List;

public class SocialDistancingSolution01 implements Solver<int[], String[][]> {

    private String WALL = "X";
    private String EMPTY = "O";
    private String PERSON = "P";

    @Override
    public int[] solve( String[][] places ) {
        // 대기실별 사람의 위치 파악
        // 대기실마다 2차원 배열로 생성
        // 사람 위치기준으로 거리두기 기준 적용
        //  1. 맨하튼 거리에 사람이 존재하는지 확인
        //  2. 맨하튼 거리내 존재하는 사람의 유형 분류
        // - 대각선에 있는 사람 : 인접한 벽으로 막히지 않으면 false
        // - 바로 사방에 인접해있는 사람 : 무조건 false
        // - 1칸 차이두고 인접해있는 사람 : 둘 사이에 벽이 아니면 false
        List<Integer> answer = new ArrayList<>();
        for ( String[] p : places ) {
            Room room = new Room( p );
            Person[] people = room.people();
            boolean isOk = true;
            for ( Person person : people ) {
                if ( !person.isDistanceOk( room ) ) {
                    isOk = false;
                    break;
                }
            }
            answer.add( isOk ? 1 : 0 );
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    // 대기실
    class Room {
        // 룸의 좌표로 뭐가 위치해 있는지
        private String[][] map;
        private int roomX;
        private int roomY;

        public Room( String[] array ) {
            this.roomY = array.length;
            this.roomX = array[0].length();
            this.map = new String[ this.roomY ][];
            int rowIdx = 0;
            for ( String row : array ) {
                this.map[ rowIdx++ ] = row.split( "" );
            }
        }

        public Person[] people() {
            List<Person> people = new ArrayList<>();
            int x = 0;
            int y = 0;
            for ( String[] row : this.map ) {
                for ( String c : row ) {
                    if ( PERSON.equals( c ) ) {
                        people.add( new Person( new int[] { x, y } ) );
                    }
                    x++;
                }
                y++;
                x=0;
            }
            return people.toArray( Person[]::new );
        }
        
        public String getItem( int x, int y ) {
            if ( ( x > -1 && x < roomX ) && ( y > -1 && y < roomY ) ) {
                return this.map[ y ][ x ];
            }
            return null;
        }
    }

    class Person {
        private int[] loc; // x, y

        public Person( int[] xy ) {
            this.loc = xy;
        }
        
        public boolean isDistanceOk( Room room ) {
            int[][] adjType1 = new int[][] { 
                new int[] { -1, 1 },
                new int[] { -1, -1 },
                new int[] { 1, -1 },
                new int[] { 1, 1 },
            };
            int[][] adjType1Exception = new int[][] {
                    new int[] {-1,0,0,1},
                    new int[] {-1,0,0,-1},
                    new int[] {0,-1,1,0},
                    new int[] {0,1,1,0},
            };
            int idx1 = 0;
            for ( int[] xy : adjType1 ) {
                if ( existPerson( room, xy ) ) {
                    // 예외 확인
                    int[] exception = adjType1Exception[idx1];
                    int[] e1 = new int[] { exception[0], exception[1] };
                    int[] e2 = new int[] { exception[2], exception[3] };
                    if ( existWall( room, e1 ) && existWall( room, e2 ) ) {
                        idx1++;
                        continue;
                    }
                    return false;
                }
                idx1++;
            }
            // 유형2
            int[][] adjType2 = new int[][] {
                new int[] {1,0},
                new int[] {-1,0},
                new int[] {0,1},
                new int[] {0,-1},
            };
            for ( int[] xy : adjType2 ) {
                if ( existPerson( room, xy ) ) {
                    return false;
                }
            }
            // 유형3
            int[][] adjType3 = new int[][] {
                    new int[] {2,0},
                    new int[] {-2,0},
                    new int[] {0,2},
                    new int[] {0,-2},
            };
            int[][] type3Exceptions = new int[][] {
                    new int[] {1,0},
                    new int[] {-1,0},
                    new int[] {0,1},
                    new int[] {0,-1},
            };
            int idx3 = 0;
            for ( int[] xy : adjType3 ) {
                if ( existPerson( room, xy ) ) {
                    // 예외 확인
                    int[] exception = type3Exceptions[ idx3 ];
                    if ( !existWall( room, exception ) ) {
                        return false;
                    }
                }
                idx3++;
            }
            
            return true;
        }
        
        private boolean existPerson( Room room, int[] xy ) {
            return PERSON.equals( room.getItem( this.loc[0] + xy[0], this.loc[1] + xy[1] ) );
        }

        private boolean existWall( Room room, int[] xy ) {
            return WALL.equals( room.getItem( this.loc[0] + xy[0], this.loc[1] + xy[1] ) );
        }
    }
}
