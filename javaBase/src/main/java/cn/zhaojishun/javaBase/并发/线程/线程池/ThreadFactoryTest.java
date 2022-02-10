package cn.zhaojishun.javaBase.并发.线程.线程池;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class ThreadFactoryTest {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2, new ThreadFactory() {
            private AtomicInteger counter = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r, "MyThread-" + counter.getAndIncrement());
                return thread;
            }
        });
        pool.execute(()-> log.info("1"));
        pool.execute(()-> log.info("2"));

        try {
            pool.execute(()-> log.info("3"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
