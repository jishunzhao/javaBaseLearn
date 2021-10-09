package cn.zhaojishun.javaBase.并发.线程;

public class CreateThreadByRunnable {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyThread());
        thread.start();

    }
}

class MyThread implements Runnable{

    @Override
    public void run() {
        System.out.println("MyThread start");
    }
}
