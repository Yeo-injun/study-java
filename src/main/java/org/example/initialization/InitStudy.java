package org.example.initialization;

import org.example.study.StudyLauncher;

import java.io.IOException;

public class InitStudy implements StudyLauncher {

    @Override
    public void launch() throws IOException {

        System.out.println( "Lancher :: Start!!" );
        System.out.println( "-----------------------------------" );
        /**
         * static 필드는 클래스가 로딩되면 값이 초기화됨.
         * 따라서 java어플리케이션이 모든 클래스를 메모리 공간에 적재할때 할당됨.
         *
         * 클래스의 static 메소드 혹은 생성자가 "최초"로 호출되는 시점에 1회성으로 static block이 실행됨.
         * static 메소드 실행 후 생성자가 실행되거나, 생성자가 실행된 후 static메소드가 실행되는 경우 모두 static block은 1번만 호출됨.
         *
         */
        OuterClass.printField();
        OuterClass outerClass = new OuterClass();
        OuterClass.printField();
        /**
         * enum이 초기화 되는 시점
         * - enumType의 멤버에 최초 접근할때 초기화됨
         * - enum의 경우 생성자가 먼저 호출
         */
        System.out.println( "-----------------------------------" );
        System.out.println( "Lancher :: End!!" );
        OuterClass.InnerEnum innerEnum = OuterClass.InnerEnum.PROP;

    }
}
