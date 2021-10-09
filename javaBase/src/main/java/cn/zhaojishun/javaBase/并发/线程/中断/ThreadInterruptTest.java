package cn.zhaojishun.javaBase.并发.线程.中断;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadInterruptTest {

    //打断正常的线程
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true){
                boolean interrupted = Thread.currentThread().isInterrupted();
                if (interrupted){
                    log.info("被打断了。");
                    break;
                }
            }
        },"t1");
        thread.start();

        Thread.sleep(1000);
        thread.interrupt();
        log.info("interrupt..");
    }
}
