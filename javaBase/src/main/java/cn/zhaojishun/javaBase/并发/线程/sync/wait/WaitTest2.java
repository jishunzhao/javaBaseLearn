package cn.zhaojishun.javaBase.并发.线程.sync.wait;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WaitTest2 {

    static final Object room = new Object();
    static boolean hasCigarette = false;
    static boolean hasTakeout = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (room) {
                log.info("有烟没？[{}]", hasCigarette);
                while (!hasCigarette) {
                    log.info("没烟，先歇会！");
                    try {
                        room.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.info("可以开始干活了");
            }
        }, "小南").start();

        new Thread(() -> {
            synchronized (room) {
                Thread thread = Thread.currentThread();
                log.info("外卖送到没？[{}]", hasTakeout);
                while (!hasTakeout) {
                    log.info("没外卖，先歇会！");
                    try {
                        room.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.info("可以开始干活了");
            }
        }, "小女").start();

        /*for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                synchronized (room) {
                    log.debug("可以开始干活了");
                }
            }, "其它人").start();
        }
        Thread.sleep(1000);*/
        new Thread(() -> {
            synchronized (room) {
                hasTakeout = true;
                log.info("外卖到了噢！");
                room.notifyAll();
            }
        }, "送外卖的").start();
    }

}
