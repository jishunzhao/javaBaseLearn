package cn.zhaojishun.javaBase.并发.活跃性;

import lombok.extern.slf4j.Slf4j;

/**
 * @author
 * @Title: 死锁演示
 * @Description: TODO
 * @date
 */
@Slf4j
public class DeadLockTest {

   static final Object a = new Object();
   static final Object b = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (a){
                log.info("a操作");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b){
                    log.info("a中 b操作");
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (b){
                log.info("b操作");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (a){
                    log.info("b中 a操作");
                }
            }
        }).start();
    }
}
