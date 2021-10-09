package cn.zhaojishun.javaBase.并发.线程.中断;

import lombok.SneakyThrows;

public class TestThread {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Run());
        thread.start();

        //中断标志位
        System.out.println(thread.isInterrupted());
        Thread.sleep(8000);
        //中断
        thread.interrupt();
        System.out.println(thread.isInterrupted());
    }

}

class Run implements Runnable{

    @SneakyThrows
    @Override
    public void run() {
        /*for (int i = 0; i < 100; i++) {
            System.out.println(i + "----");
            Thread.sleep(1000);
        }*/

            Long i = 0L;
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(i);
                i++;
                //Thread.sleep(1000);
            }

    }
}

