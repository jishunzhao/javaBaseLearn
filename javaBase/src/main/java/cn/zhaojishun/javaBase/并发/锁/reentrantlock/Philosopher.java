package cn.zhaojishun.javaBase.并发.锁.reentrantlock;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class Philosopher extends Thread{

    Chopstick left;
    Chopstick right;

    public Philosopher(String name, Chopstick left, Chopstick right) {
        super(name);
        this.left = left;
        this.right = right;
    }
    private void eat() throws InterruptedException {
        log.info("eating...");
        Thread.sleep(1);
    }
    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            // 获得左手筷子
            if (left.tryLock()){
                try {
                    // 获得右手筷子
                    if (right.tryLock()){
                        try {
                            // 吃饭
                            eat();
                        }finally {
                            // 放下右手筷子
                            right.unlock();
                        }
                    }
                }finally {
                    // 放下左手筷子
                    left.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        Chopstick c1 = new Chopstick("1");
        Chopstick c2 = new Chopstick("2");
        Chopstick c3 = new Chopstick("3");
        Chopstick c4 = new Chopstick("4");
        Chopstick c5 = new Chopstick("5");
        new Philosopher("苏格拉底", c1, c2).start();
        new Philosopher("柏拉图", c2, c3).start();
        new Philosopher("亚里士多德", c3, c4).start();
        new Philosopher("赫拉克利特", c4, c5).start();
        new Philosopher("阿基米德", c5, c1).start();
    }
}

@Slf4j
class Chopstick extends ReentrantLock {
    String name;
    public Chopstick(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "筷子{" + name + '}';
    }
}




