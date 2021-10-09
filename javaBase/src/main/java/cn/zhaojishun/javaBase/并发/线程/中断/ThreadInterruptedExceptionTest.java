package cn.zhaojishun.javaBase.并发.线程.中断;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ThreadInterruptedExceptionTest {

    //打断阻塞的线程
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Thread.sleep(5000);
            }
        });
        t1.start();

        Thread.sleep(1000);
        t1.interrupt();
        //Thread.sleep(500);
        System.out.println(t1.isInterrupted());

    }
}
