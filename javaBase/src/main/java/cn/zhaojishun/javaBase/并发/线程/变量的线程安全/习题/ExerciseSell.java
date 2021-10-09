package cn.zhaojishun.javaBase.并发.线程.变量的线程安全.习题;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**  
 * @Title: 买票例子
 * @Description: TODO
 * @author 
 * @date  
 */
@Slf4j
public class ExerciseSell {
    public static void main(String[] args) {
        TicketWindow ticketWindow = new TicketWindow(2000);
        List<Thread> list = new ArrayList<>();
// 用来存储买出去多少张票
        List<Integer> sellCount = new Vector<>();
        for (int i = 0; i < 2000; i++) {
            Thread t = new Thread(() -> {
// 分析这里的竞态条件
                int count = ticketWindow.sell(randomAmount());
                sellCount.add(count);
            });
            list.add(t);
            t.start();
        }
        list.forEach((t) -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
// 买出去的票求和
        log.info("maic count:{}", sellCount.stream().mapToInt(c -> c).sum());
// 剩余票数
        log.info("remainder count:{}", ticketWindow.getCount());
    }

    // Random 为线程安全
    static Random random = new Random();

    // 随机1~5
    public static int randomAmount() {
        return random.nextInt(5) + 1;
    }


}

class TicketWindow {
    private int count;

    public TicketWindow(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public synchronized int sell(int amount) {
        if (this.count >= amount) {
            this.count -= amount;
            return amount;
        } else {
            return 0;
        }
    }
}


