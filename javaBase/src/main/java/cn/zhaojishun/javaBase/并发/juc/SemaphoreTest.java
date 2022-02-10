package cn.zhaojishun.javaBase.并发.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;

@Slf4j
public class SemaphoreTest {

    public static void main(String[] args) {
// 1. 创建semaphore 对象
        Semaphore semaphore = new Semaphore(3);
// 2. 10个线程同时运行
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
// 3. 获取许可
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    log.info("running...");
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("end...");
                } finally {
// 4. 释放许可
                    semaphore.release();
                }
            }).start();
        }
    }
}
