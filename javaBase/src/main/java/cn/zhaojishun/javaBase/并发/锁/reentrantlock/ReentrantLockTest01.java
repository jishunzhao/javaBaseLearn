package cn.zhaojishun.javaBase.并发.锁.reentrantlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ReentrantLockTest01 {
    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        //可重入
        //method1();

        //可中断
        //method4();

        //不可中断模式尝试中断
        //method5();

        //获得锁的时候设置超时时间
        //立刻返回
        //method6();
        //超时失败
        method7();

    }

    public static void method1() {
        lock.lock();
        try {
            log.info("execute method1");
            method2();
        } finally {
            lock.unlock();
        }
    }

    public static void method2() {
        lock.lock();
        try {
            log.info("execute method2");
            method3();
        } finally {
            lock.unlock();
        }
    }

    public static void method3() {
        lock.lock();
        try {
            log.info("execute method3");
        } finally {
            lock.unlock();
        }
    }

    public static void method4() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            log.info("启动...");
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.info("等锁的过程中被打断");
                return;
            }
            try {
                log.info("获得了锁");
            } finally {
                lock.unlock();
            }
        }, "t1");
        
        lock.lock();
        log.info("获得了锁");
        t1.start();
        try {
            Thread.sleep(1);
            t1.interrupt();
            log.info("执行打断");
        } finally {
            lock.unlock();
        }
    }

    public static void method5() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            log.info("启动...");
            lock.lock();
            try {
                log.info("获得了锁");
            } finally {
                lock.unlock();
            }
        }, "t1");

        lock.lock();
        log.info("获得了锁");
        t1.start();
        try {
            Thread.sleep(1);
            t1.interrupt();
            log.info("执行打断");
        } finally {
            log.info("释放了锁");
            lock.unlock();
        }
    }

    public static void method6() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            log.info("启动...");
            if (!lock.tryLock()) {
                log.info("获取立刻失败，返回");
                return;
            }
            try {
                log.info("获得了锁");
            } finally {
                lock.unlock();
            }
        }, "t1");

        lock.lock();
        log.info("获得了锁");
        t1.start();
        try {
            Thread.sleep(2000);
        } finally {
            lock.unlock();
        }
    }

    public static void method7() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            log.info("启动...");
            try {
                if (!lock.tryLock(1, TimeUnit.SECONDS)) {
                    log.info("获取等待1s 后失败，返回");
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                log.info("获得了锁");
            } finally {
                lock.unlock();
            }
        }, "t1");

        lock.lock();
        log.info("获得了锁");
        t1.start();
        try {
            Thread.sleep(2000);
        } finally {
            lock.unlock();
        }

    }
}
