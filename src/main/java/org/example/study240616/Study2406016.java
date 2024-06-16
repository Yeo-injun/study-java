package org.example.study240616;

import org.example.study.StudyLauncher;
import org.example.study240616.thread.RunnalbleTask;

/**
 * 쓰레드 동작방식에 대한 탐구
 * Q1. Main 쓰레드가 종료되더라도 자식 쓰레드가 계속해서 동작하는지
 *  - Java에서는 기본적으로 Main 쓰레드가 종료되더라도, 자식쓰레드가 실행중이면 강제종료되지 않는다.
 *  - 다만, Daemon 쓰레드는 Main 쓰레드가 종료되면 같이 강제종료된다.
 */
public class Study2406016 implements StudyLauncher {

    @Override
    public void launch() {
        System.out.println( "Main Thread Start" );

        Runnable[] tasks = new Runnable[] {
                new RunnalbleTask(),
                new RunnalbleTask(),
                new RunnalbleTask()
        };
        runTaskByThread( tasks );
        System.out.println( "Main Thread End" );
    }

    /**
     * Task를 별도의 Thread로 분리하여 실행
     * - Main Thread가 종료되더라도 Thread는 강제 종료되지 않는다.
     * - Thread가 종료되어야 Main Thread가 종료된다.
     * @param tasks
     */
    private void runTaskByThread(Runnable[] tasks ) {
        printCurrentThreadId();
        for ( Runnable task : tasks ) {
            Thread workerThread = new Thread( task );
            printCurrentThreadId();
            workerThread.start();
            printCurrentThreadId();
        }
    }

    /**
     * Task를 Deamon Thread로 실행
     * - Daemon Thread는 Main Thread 종료시 강제 종료됨.
     * @param tasks
     */
    private void runTaskAsDeamon(Runnable[] tasks ) {
        for ( Runnable task : tasks ) {
            Thread workerThread = new Thread( task );
            workerThread.setDaemon( true );
            workerThread.start();
        }
    }

    private void printCurrentThreadId() {
        Thread currentThread = Thread.currentThread();
        System.out.println( "Current Thread ID : " + currentThread.getId() );
    }
}
