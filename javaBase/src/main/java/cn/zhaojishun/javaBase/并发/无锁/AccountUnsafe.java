package cn.zhaojishun.javaBase.并发.无锁;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Title: 保证account.withdraw 取款方法的线程安全
 * @Description: TODO
 * @author 
 * @date  
 */
public class AccountUnsafe implements Account {

    private Integer balance;

    public AccountUnsafe(Integer balance) {
        this.balance = balance;
    }

    @Override
    public synchronized Integer getBalance() {
        return balance;
    }

    @Override
    public synchronized void withdraw(Integer amount) {
        balance -= amount;
    }

    public static void main(String[] args) {
        //Account.demo(new AccountUnsafe(10000));
        Account.demo(new AccountCas(10000));
    }
}

class AccountCas implements Account{

    private AtomicInteger balance;
    public AccountCas(Integer balance) {
        this.balance = new AtomicInteger(balance);
    }

        @Override
    public Integer getBalance() {
            return balance.get();
    }

    @Override
    public void withdraw(Integer amount) {
        while (true) {
            int prev = balance.get();
            int next = prev - amount;
            if (balance.compareAndSet(prev, next)) {
                break;
            }
        }
        // 可以简化为下面的方法
        // balance.addAndGet(-1 * amount);
    }
}
interface Account {
    // 获取余额
    Integer getBalance();
    // 取款
    void withdraw(Integer amount);
    /**
     * 方法内会启动1000 个线程，每个线程做-10 元的操作
     * 如果初始余额为10000 那么正确的结果应当是0
     */
    static void demo(Account account) {
        List<Thread> ts = new ArrayList<>();
        long start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            ts.add(new Thread(() -> {
                account.withdraw(10);
            }));
        }
        ts.forEach(Thread::start);
        ts.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.nanoTime();
        System.out.println(account.getBalance()
                + " cost: " + (end-start)/1000_000 + " ms");
    }
}



