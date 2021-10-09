package cn.zhaojishun.javaBase.并发.线程.sync.wait;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WaitTest {
    static final Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            synchronized (obj){
                log.info("T1 执行。。。");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("T1 其他代码。。。");
            }
        },"t1").start();
        new Thread(()->{
            synchronized (obj){
                log.info("T2 执行。。。");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("T2 其他代码。。。");
            }
        },"t2").start();

        Thread.sleep(2000);
        log.info("唤醒waiting线程");
        synchronized (obj){
            //obj.notify(); //随机唤醒一个
            obj.notifyAll(); //全部唤醒
        }
    }

}
