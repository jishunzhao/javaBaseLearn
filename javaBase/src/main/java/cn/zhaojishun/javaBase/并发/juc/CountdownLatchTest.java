package cn.zhaojishun.javaBase.并发.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.*;

import static java.lang.Thread.sleep;

@Slf4j
public class CountdownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        //test1();
        //test2();
//        test4();
        test6();
    }

    private static void test2() {
        CountDownLatch latch = new CountDownLatch(3);
        ExecutorService service = Executors.newFixedThreadPool(4);
        service.submit(() -> {
            log.info("begin...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
            log.info("end...{}", latch.getCount());
        });
        service.submit(() -> {
            log.info("begin...");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
            log.info("end...{}", latch.getCount());
        });
        service.submit(() -> {
            log.info("begin...");
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
            log.info("end...{}", latch.getCount());
        });
        service.submit(()->{
            try {
                log.info("waiting...");
                latch.await();
                log.info("wait end...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private static void test1() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        new Thread(() -> {
            log.info("begin...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
            log.info("end...{}", latch.getCount());
        }).start();
        new Thread(() -> {
            log.info("begin...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
            log.info("end...{}", latch.getCount());
        }).start();
        new Thread(() -> {
            log.info("begin...");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
            log.info("end...{}", latch.getCount());
        }).start();
        log.info("waiting...");
        latch.await();
        log.info("wait end...");
    }

    //模拟十个线程加载游戏，都加载完毕后开始游戏
    public static void test4() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(10);
        Random random = new Random();

        String[] str = new String[10];
        for (int i = 0; i < 10; i++) {
            int k = i;
            pool.submit(() -> {
                for (int i1 = 0; i1 <= 100; i1++) {
                    try {
                        Thread.sleep(random.nextInt(100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    str[k] = i1 + "%";
                    System.out.print("\r" + Arrays.toString(str));
                }
                countDownLatch.countDown();
            });

        }
        countDownLatch.await();
        System.out.println("游戏开始");
        pool.shutdown();
    }

    public static void test5() {
        /*RestTemplate restTemplate = new RestTemplate();
        log.debug("begin");
        ExecutorService service = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(4);
        Future<Map<String,Object>> f1 = service.submit(() -> {
            Map<String, Object> r =
                    restTemplate.getForObject("http://localhost:8080/order/{1}", Map.class, 1);
            return r;
        });
        Future<Map<String, Object>> f2 = service.submit(() -> {
            Map<String, Object> r =
                    restTemplate.getForObject("http://localhost:8080/product/{1}", Map.class, 1);
            return r;
        });
        Future<Map<String, Object>> f3 = service.submit(() -> {
            Map<String, Object> r =
                    restTemplate.getForObject("http://localhost:8080/product/{1}", Map.class, 2);
            return r;
        });

        Future<Map<String, Object>> f4 = service.submit(() -> {
            Map<String, Object> r =
                    restTemplate.getForObject("http://localhost:8080/logistics/{1}", Map.class, 1);
            return r;
        });
        System.out.println(f1.get());
        System.out.println(f2.get());
        System.out.println(f3.get());
        System.out.println(f4.get());
        log.debug("执行完毕");
        service.shutdown();*/

    }

    public static void test6() {
        ExecutorService pool = Executors.newFixedThreadPool(3);

        CyclicBarrier cb = new CyclicBarrier(3, () -> {
            log.info("task 1 、2、3 finish");
        }); // 个数为2时才会继续执行

        pool.submit(() -> {
            log.info("task1 start");
            try {
                Thread.sleep(2000);
                cb.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        pool.submit(() -> {
            log.info("task2 start");
            try {
                Thread.sleep(1000);
                cb.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        pool.submit(() -> {
            log.info("task3 start");
            try {
                Thread.sleep(3000);
                cb.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
    }
}
