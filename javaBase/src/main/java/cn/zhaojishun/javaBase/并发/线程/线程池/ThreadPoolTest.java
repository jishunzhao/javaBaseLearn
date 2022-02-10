package cn.zhaojishun.javaBase.并发.线程.线程池;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Title: 自定义线程池
 * @Description: TODO
 * @author 
 * @date  
 */
@Slf4j
public class ThreadPoolTest{
    public static void main(String[] args) {
        //拒绝策略
        RejectPolicy<Runnable> r = (queue, task)->{
            // 1) 死等
            //queue.put(task);
            // 2) 带超时等待
            //queue.put(task, 3000, TimeUnit.SECONDS);
            // 3) 让调用者放弃任务执行
            // 4) 让调用者抛出异常
            /*try{
                throw new RuntimeException("任务执行失败" + task);
            }catch(RuntimeException e){
                e.printStackTrace();
            }*/
            // 5) 让调用者自己执行任务
            task.run();
        };
        //创建线程池
        ThreadPool threadPool = new ThreadPool(2, 0, TimeUnit.MILLISECONDS, 5, r);

        //模拟30个任务
        for (int i = 0; i < 30; i++) {
            int j = i;
            threadPool.execute(()->{
                try {
                    //模拟任务耗时
                    Thread.sleep(900);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("我是任务" + j);
            });
        }
    }
}

@Slf4j
class ThreadPool {
    //任务队列
    public BlockingQueue<Runnable> taskQueue;
    //线程集合
    private HashSet<Worker> works = new HashSet<>();
    //核心线程数
    private int coreSize;
    //任务的超时时间
    private long timeout;
    private TimeUnit unit;
    //拒绝策略
    private RejectPolicy<Runnable> rejectPolicy;

    //初始化线程池
    public ThreadPool(int coreSize, long timeout, TimeUnit unit, int queueCapacit, RejectPolicy rejectPolicy) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.unit = unit;
        this.taskQueue = new BlockingQueue<>(queueCapacit);
        this.rejectPolicy = rejectPolicy;
        log.info("线程池初始化-- 核心线程数：{} | 超时时间: {} , " , coreSize , timeout);
    }

    //执行任务
    public void execute(Runnable task) {
        //当任务数没超过coreSize时，直接交给work执行 ，work继承Thread类
        //超过时，加入任务队列
        synchronized(works){
            if (works.size() < coreSize){
                log.info("没超过coreSize 直接执行 work");
                Worker worker = new Worker(task);
                //加入线程集合
                works.add(worker);
                //开始线程
                worker.start();
            }else{
                //超出coreSize处理
                //taskQueue.put(task);
                // 1) 死等
                // 2) 带超时等待
                // 3) 让调用者放弃任务执行
                // 4) 让调用者抛出异常
                // 5) 让调用者自己执行任务
                taskQueue.tryPut(rejectPolicy, task);
            }
        }
    }

    class Worker extends Thread{
        private Runnable task;
        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            //执行任务
            //当task不为空，执行任务
            //当task 执行完毕，接着从任务中获取任务执行
            while(task != null || (task = taskQueue.take(timeout, unit)) != null){
                try{
                    log.info("开始运行任务........{}", task);
                    task.run();
                    log.info("任务运行结束.......{}", task);
                }catch(Exception e){
                }finally{
                    task = null;
                }
            }
            synchronized(works){
                log.info("没有任务了,work移除。。。。");
                works.remove(this);
            }
        }
    }
}

//自定义任务队列
@Slf4j
class BlockingQueue<T> {
    // 任务队列
    private Deque<T> queue = new ArrayDeque<>();
    //锁
    private ReentrantLock lock = new ReentrantLock();
    //生产者条件变量
    private Condition fullCondition = lock.newCondition();
    //消费者条件变量
    private Condition emptyCondition = lock.newCondition();
    //容量
    private int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
        log.info("初始化任务队列 容量： {}" , capacity);
    }

    //阻塞获取
    public T take() {
        lock.lock();
        try{
            while(queue.isEmpty()) {
                try {
                    //空队列等待
                    log.info("获取任务等待中。。。");
                    emptyCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 返回并唤醒生产者
            T t = queue.removeFirst();
            fullCondition.signal();
            log.info("获取到了任务 ：{}"  , t);
            return t;
        }finally{
            lock.unlock();
        }
    }

    //带超时阻塞获取
    public T take(long timeout, TimeUnit unit) {
        lock.lock();
        try{
            long nanos = unit.toNanos(timeout);
            while(queue.isEmpty()) {
                try {
                    //返回值是剩余时间
                    if (nanos <= 0){
                        return null;
                    }
                    //空队列等待
                    log.info("获取可超时任务等待中。。。");
                     nanos = emptyCondition.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 返回并唤醒生产者
            T t = queue.removeFirst();
            fullCondition.signal();
            log.info("获取到了任务 ：{}"  , t);
            return t;
        }finally{
            lock.unlock();
        }
    }

    //阻塞添加
    public void put(T task) {
        lock.lock();
        try {
            //判断队列是否满
            while(queue.size() == capacity){
                try {
                    log.info("等待加入任务队列。。。" , task);
                    fullCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addLast(task);
            log.info("成功加入任务队列。。。" , task);
            emptyCondition.signal();
        }finally{
            lock.unlock();
        }
    }

    //带超时的阻塞添加
    public boolean put(T task , long timeout , TimeUnit unit) {
        lock.lock();
        try {
            long nanos = unit.toNanos(timeout);
            //判断队列是否满
            while(queue.size() == capacity){
                try {
                    if (nanos <= 0){
                        return false;
                    }
                    log.info("等待加入超时任务队列。。。" , task);
                     nanos = fullCondition.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.info("加入超时任务队列。。。" , task);
            queue.addLast(task);
            emptyCondition.signal();
            return true;
        }finally{
            lock.unlock();
        }
    }

    //尝试put 失败执行拒绝策略
    public void tryPut(RejectPolicy<T> rejectPolicy, T task) {
        lock.lock();
        try {
            if (queue.size() == capacity){
                rejectPolicy.reject(this , task);
            }else{
                log.info("加入任务队列 {}", task);
                queue.addLast(task);
                //唤醒阻塞的消费线程
                emptyCondition.signal();
            }
        }finally{
            lock.unlock();
        }
    }

    public int size() {
        lock.lock();
        try {
            return queue.size();
        }finally{
            lock.unlock();
        }
    }
}

//拒绝策略
@FunctionalInterface
interface RejectPolicy<T> {
    void reject(BlockingQueue<T> queue, T task);
}
