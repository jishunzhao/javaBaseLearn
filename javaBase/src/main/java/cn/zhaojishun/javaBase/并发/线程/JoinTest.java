package cn.zhaojishun.javaBase.并发.线程;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JoinTest {

    static int i = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(10);
                i = 10;
                log.info("thread..");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1");

        t1.start();
        t1.join();
        log.info("i = {}" , i );
        log.info("end");

    }

}
