package org.example.study240616.thread;

import java.sql.Time;
import java.util.Random;

public class RunnalbleTask implements Runnable {
    private String taskId;
    public RunnalbleTask() {
        Random random = new Random();
        this.taskId = "TASK_ID_" + random.nextInt( 1000 );
        System.out.println( this.taskId );
    }

    @Override
    public void run() {
        printIntArray();
    }

    private void printIntArray() {
        int[] intArray = { 0,1,2,3,4,5,6,7,8,9,10 };
        for ( int i : intArray ) {
            System.out.println( "Worker Thread : " + this.taskId + "_" + i );
        }
    }
}
