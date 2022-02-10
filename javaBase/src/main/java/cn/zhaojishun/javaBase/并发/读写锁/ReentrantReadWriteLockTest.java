package cn.zhaojishun.javaBase.并发.读写锁;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class ReentrantReadWriteLockTest {
    public static void main(String[] args) {
        DataContainer dataContainer = new DataContainer();
        new Thread(() -> {
            dataContainer.read();
        }, "t1").start();
        new Thread(() -> {
            dataContainer.read();
        }, "t2").start();

    }

}

@Slf4j
class DataContainer{

    private int data;
    private ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock r = rw.readLock();
    private ReentrantReadWriteLock.WriteLock w = rw.writeLock();

    public int read() {
        log.info("获取读锁");
        r.lock();
        try {
            log.info("读取");
            Thread.sleep(1000);
            return data;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            log.info("释放读锁");
            r.unlock();
        }
        return data;
    }

    private void write(int data) {
        Thread thread = new Thread(() -> {
        });
        thread.start();

        log.debug("获取写锁...");
        w.lock();
        try {
            log.debug("写入");
            Thread.sleep(1000);;
            this.data = data;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            log.debug("释放写锁...");
            w.unlock();
        }
    }
}

