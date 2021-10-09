package cn.zhaojishun.javaBase.并发.共享模型;

import lombok.extern.slf4j.Slf4j;

/**
 * @author
 * @Description: 演示两个线程对初始值为0 的静态变量一个做自增，一个做自减，各做5000 次
 * @date
 */
@Slf4j
public class Test01 {

    static int counter = 0;
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (lock) {
                    counter++;
                }
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (lock) {
                    counter--;
                }
            }
        }, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.info("{}", counter);
    }
}
