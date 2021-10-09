package cn.zhaojishun.javaBase.并发.线程;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class TeaTest {

    public static void main(String[] args) {
        TeaTest teaTest = new TeaTest();
        teaTest.join();
    }


    void join(){
        Thread t1 = new Thread(() -> {
            log.info("洗水壶");
            sleep(1);
            log.info("烧开水");
            sleep(5);
        }, "老王");

        Thread t2 = new Thread(() -> {
            log.info("洗茶壶");
            sleep(1);
            log.info("烧茶杯");
            sleep(1);
            log.info("茶叶");
            sleep(1);

            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("泡茶");
        }, "老李");
        t1.start();
        t2.start();

    }

    static void sleep(int i){
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
