package cn.zhaojishun.javaBase.并发.forkjoin;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class MainDemo{
    public static void main(String[] args) {
//        ForkJoinPool pool = new ForkJoinPool(4);
//        System.out.println(pool.invoke(new AddTask1(5)));
        ForkJoinPool pool = new ForkJoinPool(4);
        System.out.println(pool.invoke(new AddTask3(1, 10)));

    }
}

@Slf4j(topic = "c.AddTask")
class AddTask1 extends RecursiveTask<Integer> {
    int n;

    public AddTask1(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return "{" + n + '}';
    }

    @Override
    protected Integer compute() {
// 如果n 已经为1，可以求得结果了
        if (n == 1) {
            log.info("join() {}", n);
            return n;
        }
// 将任务进行拆分(fork)
        AddTask1 t1 = new AddTask1(n - 1);
        t1.fork();
        log.info("fork() {} + {}", n, t1);
// 合并(join)结果
        int result = n + t1.join();
        log.info("join() {} + {} = {}", n, t1, result);
        return result;
    }
}

@Slf4j
class AddTask3 extends RecursiveTask<Integer> {
    int begin;
    int end;
    public AddTask3(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }
    @Override
    public String toString() {
        return "{" + begin + "," + end + '}';
    }
    @Override
    protected Integer compute() {
// 5, 5
        if (begin == end) {
            log.info("join() {}", begin);
            return begin;
        }
// 4, 5
        if (end - begin == 1) {
            log.info("join() {} + {} = {}", begin, end, end + begin);
            return end + begin;
        }
// 1 5
        int mid = (end + begin) / 2; // 3
        AddTask3 t1 = new AddTask3(begin, mid); // 1,3
        t1.fork();
        AddTask3 t2 = new AddTask3(mid + 1, end); // 4,5
        t2.fork();
        log.info("fork() {} + {} = ?", t1, t2);
        int result = t1.join() + t2.join();
        log.info("join() {} + {} = {}", t1, t2, result);
        return result;
    }
}

