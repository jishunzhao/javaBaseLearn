package cn.zhaojishun.javaBase.并发.锁.reentrantlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Title: ReentrantLock 的条件Condition
 * @Description: 小南小女抽烟外卖例子
 * @author 
 * @date  
 */
@Slf4j
public class ConditionTest {
    static ReentrantLock lock = new ReentrantLock();
    //烟到位否
    private static boolean somkOk = false;
    private static boolean waimOk = false;

    public static void main(String[] args) throws InterruptedException {
        Condition somk = lock.newCondition();
        Condition waim = lock.newCondition();

        Thread t1 = new Thread(()->{
            try {
                lock.lock();
                while (!somkOk){
                    log.info("没烟干不了，等着");
                    try {
                        somk.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.info("有烟了，开干");
            }finally {
                lock.unlock();
            }

        } , "小南");

        Thread t2 = new Thread(()->{
            try {
                lock.lock();
                while (!waimOk){
                    log.info("没外卖干不了，等着");
                    try {
                        waim.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.info("外卖来了，开干");
            }finally {
                lock.unlock();
            }

        } , "小女");

        t1.start();
        t2.start();
        Thread.sleep(1000);

        new Thread(()->{
            try {
                lock.lock();
                log.info("送烟");
                somkOk = true;
                somk.signal();
            }finally {
                lock.unlock();
            }
        },"送烟").start();

        new Thread(()->{
            try {
                lock.lock();
                log.info("送外卖");
                waimOk = true;
                waim.signal();
            }finally {
                lock.unlock();
            }
        },"送外卖").start();
    }
}
