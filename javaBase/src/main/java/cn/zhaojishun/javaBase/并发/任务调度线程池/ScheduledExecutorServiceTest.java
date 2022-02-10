package cn.zhaojishun.javaBase.并发.任务调度线程池;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.*;

@Slf4j
public class ScheduledExecutorServiceTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //daly();
        extracted();
    }

    //异常处理
    private static void extracted() throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        Future<Boolean> f = pool.submit(() -> {
            log.debug("task1");
            int i = 1 / 0;
            return true;
        });
        log.debug("result:{}", f.get());
    }

    private static void daly() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        log.info("start");
        // 添加两个任务，希望它们都在1s 后执行
        executor.schedule(() -> {
            log.info("任务1");
            int i = 1 / 0;
            try { Thread.sleep(2000); } catch (InterruptedException e) { }
        }, 1, TimeUnit.SECONDS);

        executor.schedule(() -> {
            log.info("任务2");
        }, 1, TimeUnit.SECONDS);
    }
}
