package org.example.study240710;

import org.example.study.StudyLauncher;

import java.util.ArrayList;
import java.util.List;

public class StudyAlgorithmProgrammers01 implements StudyLauncher {
    // 점찍기
    @Override
    public void launch() {
        // 1. 최대 좌표값 구하기
        // 2. 순서쌍 만들기
        // 3. d와 길이 비교하여 d가 더 크면 count +
        int k = 1;
        int d = 5;
        long answer = 0;

        int maxPointCount = ( d / k );
        List<Point> points = makePoints( maxPointCount, k );

        for ( Point p : points ) {
            if ( d >= p.distance ) {
                answer++;
            }
        }
        System.out.println( "[ Answer ] : " + answer );
    }

    private List<Point> makePoints( int maxPointCount, int k ) {
        List<Point> points = new ArrayList<>();

        for ( int y=0; y <= maxPointCount; y++ ) {
            int xCount = 0;
            while( maxPointCount >= xCount ) {
                int x = xCount * k;
                points.add( new Point( x, y * k ) );
                xCount++;
            }
        }

        return points;
    }

    class Point {
        int x;
        int y;
        double distance;
        Point( int x, int y ) {
            this.x = x;
            this.y = y;
            this.distance = Math.sqrt( x * x + y * y );
        }
    }
}
