package cn.zhaojishun.javaBase.并发.线程;

public class CreateThreadByThread extends Thread{

    @Override
    public void run() {
        super.run();
        System.out.println("Thread start");
    }

    public static void main(String[] args) {
        new CreateThreadByThread().start();
    }
}
