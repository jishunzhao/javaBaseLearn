package cn.zhaojishun.javaBase.并发.线程状态转换;

import lombok.extern.slf4j.Slf4j;

/**  
 * @Title: 线程状态转换之 RUNNABLE <--> WAITING
 * @Description: TODO
 * @author 
 * @date  
 */
@Slf4j
public class RunableToWaiting {
    final static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (obj) {
                log.info("执行....");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("其它代码...."); // 断点
            }
        },"t1").start();
        new Thread(() -> {
            synchronized (obj) {
                log.info("执行....");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("其它代码...."); // 断点
            }
        },"t2").start();
        Thread.sleep(500);
        log.info("唤醒obj 上其它线程");
        synchronized (obj) {
            obj.notifyAll(); // 唤醒obj上所有等待线程断点
        }
    }
}

