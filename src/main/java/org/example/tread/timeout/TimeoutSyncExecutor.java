package org.example.tread.timeout;

import java.util.concurrent.*;

public class TimeoutSyncExecutor {


    public < T > void executeWithTimeout(Callable< T > task, long timeoutInMillis ) throws Exception {
        /**
         * ExecutorService는 Thread Pool의 Thread를 생성하고, 관리하는 역할을 수행한다.
         *
         * Thread에서 처리할 작업은 `Callable` 또는 `Runnable`객체로 정의할 수 있다.
         * cf.`Callabel` 과 `Runnable`의 차이점 : Callable은 결과를 반환하거나 예외를 던질 수 있다. ( Runnable은 그렇지 못하다. )
         *
         * Thread에서 처리할 작업을 `Callable`객체로 정의한 후 `ExecutorService`의 submit메소드에 전달하여 작업을 실행시킨다.
         * 1. 정의된 작업이 `ExecutorService`에서 관리하는 Thread Pool에 등록된다.
         * 2. Thread Pool에서는 가용 Thread를 판단하여 해당 작업을 즉시 실행하거나, 대기열에서 관리한다.
         *      ( 즉, 작업실행은 submit() 메소드 호출시부터 가능하다. 허나 Thread Pool의 상태에 따라 실행시점이 달라진다. )
         * 3. 작업 결과를 관리할 Future 객체를 리턴한다.
         *
         * `Future` 객체는 정의한 작업의 상태를 추적하고, 결과를 얻기 위한 인터페이스를 제공한다.
         * 정의한 작업의 결과를 얻기 위해서는 `Future`객체의 get( timeout )메소드를 호출하면 된다.
         * - get() 메소드 호출시 작업이 완료된 상태면 즉시 결과를 리턴한다.
         * - 아직 작업이 진행 중이거나, 대기중이면 지정된 timeout 시간 동안 블로킹된다.
         * - get() 호출후 timeout 시간 동안 작업이 완료되지 않으면 TimeoutException이 throw된다.
         *
         * 이러한 구조는 Thread Pool과 작업을 효율적으로 관리하고, 비동기 작업의 결과를 처리하는 데 유용하다.
         */
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future< T > future = executor.submit(task);
        System.out.print( ">> Future Objecet Return!!" );

        try {
            // 제한 시간 내에 작업이 완료되길 대기
            T result = future.get( timeoutInMillis, TimeUnit.MILLISECONDS );
            if ( result instanceof  String ) {
                System.out.printf(">> executeWithTimeout :: %s%n", result );
            }
        } finally {
            executor.shutdownNow(); // 실행 중인 작업 강제 종료
        }
    }

}
