package cn.zhaojishun.javaBase.并发.线程;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadStateTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> { }, "t1");  //NEW

        Thread t2 = new Thread(() -> {
            while (true){
            }
        }, "t2"); //RUNNABLE
        t2.start();

        Thread t3 = new Thread(() -> {
        }, "t3"); //TERMINATED
        t3.start();

        Thread t4 = new Thread(() -> {
            try {
                Thread.sleep(10000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t4"); //TIMED_WAITING
        t4.start();

        Thread t5 = new Thread(() -> {
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t5");//WAITING
        t5.start();

        Thread t = new Thread(() -> {
            synchronized (ThreadStateTest.class){
                while (true){

                }
            }
        }, "t");
        t.start();

        Thread t6 = new Thread(() -> {
            synchronized (ThreadStateTest.class){

            }
        }, "t6");
        t6.start();

        Thread.sleep(2000);
        log.info("t1 state : {}" , t1.getState());
        log.info("t2 state : {}" , t2.getState());
        log.info("t3 state : {}" , t3.getState());
        log.info("t4 state : {}" , t4.getState());
        log.info("t5 state : {}" , t5.getState());
        log.info("t6 state : {}" , t6.getState());
    }
}
