package org.example.algorithm.kakao2019.abusiveuser;

import org.example.algorithm.Solver;

import java.util.ArrayList;
import java.util.List;

public class AbusiveUserSolution01 implements Solver<Integer, String[][]> {

    @Override
    public Integer solve( String[][] users ) {
        String[] user_id    = users[0];
        String[] banned_id  = users[1];
        // 마스킹 템플릿 구현
        // 마스킹 템플릿은 마스킹이 적용되는 userId목록을 기억한다.
        // 마스킹 템플릿이 적용되는 userId목록으로 조합을 만든다.
        // - 조합생성 요령
        //  > 마스킹템플릿들을 배열에 담는다.
        //  > 마스킹 템필릿들이 가지고 있는 userId갯수를 동일한 idx로 배열에 담는다.
        //  > 마스킹 템플릿의 userId를 가져올 순서쌍을 만든다.
        //  > 순서쌍 배열을 순회하면서 제재목록을 구성한다. ( 제재목록에 중복요소가 존재할경우 해당 생성작업은 종료한다. )
        // 조합한 제재 목록을 정렬한다.
        // 정렬한 훈 문자열로 만들어 Set에 넣고 중복제거를 한다.

        return 0;
    }


}
