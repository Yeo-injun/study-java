package org.example.tread.timeout;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

public class TimeoutApp {
    public static void main(String[] args) {
        TimeoutSyncExecutor executor = new TimeoutSyncExecutor();
        Callable< String > task = () -> {
            System.out.println("==>> Execute Tread <<==");
            Thread.sleep(2000 ); // 5초 작업
            return "Done";
//            throw new Exception( "Exception Throw!!" );
        };

        try {
            executor.executeWithTimeout( task,  3000 ); // 3초 제한
        } catch ( TimeoutException timeoutEx ) {
            System.out.println( "==>> Occur Timeout Exception <<==" );
        } catch ( Exception e ) {
            System.out.println( "==>> Occur Simple Exception <<==" );
        }
    }
}
