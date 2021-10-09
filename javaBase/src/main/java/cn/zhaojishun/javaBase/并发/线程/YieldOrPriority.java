package cn.zhaojishun.javaBase.并发.线程;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class YieldOrPriority {
    public static void main(String[] args) {
        Runnable r1 = ()->{
          int count = 0;
          for (;;){
              log.info("----->1 " + count);
              count++;
          }
        };
        Runnable r2 = ()->{
            int count = 0;
            for (;;){
                //Thread.yield();
                log.info("                ----->2 " + count);
                count++;
            }
        };
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        t2.start();


    }
}
