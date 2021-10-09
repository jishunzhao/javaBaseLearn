package cn.zhaojishun.javaBase.并发.线程.中断;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class TwoInterruptTest {

    private Thread monitor;

    public void start(){
        monitor = new Thread(()->{
           while (true){
               Thread currentThread = Thread.currentThread();
               if (currentThread.isInterrupted()){
                   log.info("料理后事。。");
                   break;
               }
               try {
                   log.info("执行监控..");
                   Thread.sleep(2000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
                   //重新设置打断标记
                   currentThread.interrupt();
               }
           }
        });
        monitor.start();
    }

    public void stop(){
        monitor.interrupt();
    }

    public static void main(String[] args) throws InterruptedException {
        TwoInterruptTest t = new TwoInterruptTest();
        t.start();
        Thread.sleep(5000);
        t.stop();
    }
}
