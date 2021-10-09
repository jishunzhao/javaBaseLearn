package cn.zhaojishun.javaBase.并发.线程.sync;

public class SyncTest01 {

    static final Object lock = new Object();
    static int counter = 0;

    public static void main(String[] args) {
        synchronized (lock) {
            counter++;
        }
    }
}
