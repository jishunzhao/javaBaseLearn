package cn.zhaojishun.javaBase.并发.无锁;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**  
 * @Title: 比较AtomicLong 与LongAdder 的性能
 * @Description: TODO
 * @author 
 * @date  
 */
public class LongAdderTest {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            demo(() -> new LongAdder(), adder -> adder.increment());
        }
        for (int i = 0; i < 5; i++) {
            demo(() -> new AtomicLong(), adder -> adder.getAndIncrement());
        }

    }

    public static <T> void demo(Supplier<T> adSupplier , Consumer<T> action){
        T adder = adSupplier.get();

        long start = System.nanoTime();

        List<Thread> ts = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            ts.add(new Thread(()->{
                for (int i1 = 0; i1 < 500000; i1++) {
                    action.accept(adder);
                }
            }));
        }

        ts.forEach(t -> t.start());
        ts.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.nanoTime();
        System.out.println(adder + "cost:" + (end-start) / 1000_000);

    }
}
