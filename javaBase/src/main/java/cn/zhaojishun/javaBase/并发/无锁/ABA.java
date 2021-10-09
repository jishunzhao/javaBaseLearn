package cn.zhaojishun.javaBase.并发.无锁;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

@Slf4j
public class ABA {
    static AtomicStampedReference<String> ref = new AtomicStampedReference<>("A", 0);
    public static void main(String[] args) throws InterruptedException {
        log.info("main start...");
// 获取值A
        String prev = ref.getReference();
// 获取版本号
        int stamp = ref.getStamp();
        log.info("版本{}", stamp);
// 如果中间有其它线程干扰，发生了ABA 现象
        other();
        Thread.sleep(1000);
// 尝试改为C
        log.info("change A->C {}", ref.compareAndSet(prev, "C", stamp, stamp + 1));
    }
    private static void other() throws InterruptedException {
        new Thread(() -> {
            log.info("change A->B {}", ref.compareAndSet(ref.getReference(), "B",
                    ref.getStamp(), ref.getStamp() + 1));
            log.info("更新版本为{}", ref.getStamp());
        }, "t1").start();
        Thread.sleep(500);
        new Thread(() -> {
            log.info("change B->A {}", ref.compareAndSet(ref.getReference(), "A",
                    ref.getStamp(), ref.getStamp() + 1));
            log.info("更新版本为{}", ref.getStamp());
        }, "t2").start();
    }
}
class GarbageBag {
    String desc;
    public GarbageBag(String desc) {
        this.desc = desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    @Override
    public String toString() {
        return super.toString() + " " + desc;
    }
}

@Slf4j
class TestABAAtomicMarkableReference {
    public static void main(String[] args) throws InterruptedException {
        GarbageBag bag = new GarbageBag("装满了垃圾");
// 参数2 mark 可以看作一个标记，表示垃圾袋满了
        AtomicMarkableReference<GarbageBag> ref = new AtomicMarkableReference<>(bag, true);
        log.info("主线程start...");
        GarbageBag prev = ref.getReference();
        log.info(prev.toString());
        new Thread(() -> {
            log.info("打扫卫生的线程start...");
            bag.setDesc("空垃圾袋");
            while (!ref.compareAndSet(bag, bag, true, false)) {}
            log.info(bag.toString());
        }).start();
        Thread.sleep(1000);
        log.info("主线程想换一只新垃圾袋？");
        boolean success = ref.compareAndSet(prev, new GarbageBag("空垃圾袋"), true, false);
        log.info("换了么？" + success);
        log.info(ref.getReference().toString());
    }

}

